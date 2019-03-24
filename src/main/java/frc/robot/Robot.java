
package frc.robot;

import javax.lang.model.util.ElementScanner6;

import com.kauailabs.navx.frc.AHRS;


import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.subsystems.DriveBase;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Lift;
import frc.robot.subsystems.Pneumatics;



public class Robot extends TimedRobot {
  
  public static DriveBase driveBase = new DriveBase();
  public static Lift lift = new Lift();
  public static Intake intake = new Intake();
  public static Pneumatics pneumatics = new Pneumatics();
  public static OI oi = new OI();
  public static Encoder enc = new Encoder(1, 2, true, EncodingType.k4X);
  public static Servo servo1 = new Servo(9);
  public static AHRS navX = new AHRS(I2C.Port.kMXP);
  public static Timer timer = new Timer();
  


 // Command m_autonomousCommand;
 // SendableChooser<Command> m_chooser = new SendableChooser<>();
 
  @Override
  public void robotInit()
   {
    timer.start();


    CameraServer.getInstance().startAutomaticCapture(0);
    CameraServer.getInstance().startAutomaticCapture(1);

   
   
    
    
  }

  
  @Override
  public void robotPeriodic() 
  {
 

    ///////////////////setting variables on dashboard
    if(!Robot.oi.liftButton.get() && !Robot.oi.lowerButton.get())
    {
      SmartDashboard.putString("Lift State", "Lift Immobile");
    }
    
    if(!Robot.oi.shootOutButton.get() && !Robot.oi.takeInButton.get())
    {
      SmartDashboard.putString("Claw State", "Claw Immobile");
    }

    SmartDashboard.putBoolean("Top LimitSwitch", Robot.lift.limit.get());
    

    ////////////////////
    if(Robot.oi.StopAllButton.get() == true)
    {
      Robot.intake.leftIntake.stopMotor();
      Robot.intake.rightIntake.stopMotor();

      Robot.lift.leftReel.stopMotor();
      Robot.lift.rightReel.stopMotor();

      Robot.driveBase.blMotor.stopMotor();
      Robot.driveBase.brMotor.stopMotor();
      Robot.driveBase.flMotor.stopMotor();
      Robot.driveBase.frMotor.stopMotor();
      Robot.intake.wristMotor.stopMotor();

    }


    if(Robot.oi.wristMotorLock.get())
    {
      if(Robot.oi.OperatorController.getAButton())
      {
        Robot.intake.wristMotor.set(-0.5);
        SmartDashboard.putString("WristMotorState", "GoingDown");
      }
      if(Robot.oi.OperatorController.getYButton())
      {
        Robot.intake.wristMotor.set(0.8);
        SmartDashboard.putString("WristMotorState", "GoingUp");
      }
    }else
    {
      Robot.intake.wristMotor.set(0.0);
      SmartDashboard.putString("WristMotorState", "No Power");
    }
      
  
    double intakespeed = Robot.oi.OperatorController.getY(Hand.kLeft);

  if(intakespeed > 0.5)
  {
    Robot.intake.leftIntake.set(-Robot.oi.OperatorController.getY(Hand.kLeft));
    Robot.intake.rightIntake.set(Robot.oi.OperatorController.getY(Hand.kLeft));
  }
  if(intakespeed < 0.5)
  {
    Robot.intake.leftIntake.set(Robot.oi.OperatorController.getY(Hand.kLeft) /4 );
    Robot.intake.rightIntake.set(-(Robot.oi.OperatorController.getY(Hand.kLeft)/4));
  
  }

  if(intakespeed == 0 )
  {
    Robot.intake.leftIntake.set(0);
    Robot.intake.rightIntake.set(0);
  }

  if(Robot.oi.OperatorController.getTriggerAxis(Hand.kLeft) > 0.1)
  {
    Robot.pneumatics.Mover.set(true);
  }else if (Robot.oi.OperatorController.getTriggerAxis(Hand.kLeft) == 0 )
  {
    Robot.pneumatics.Mover.set(false);
  }
  
    }

  
   
 
  @Override
  public void disabledInit() 
  {
    Robot.pneumatics.compressor.setClosedLoopControl(false);
  }

  @Override
  public void disabledPeriodic() 
  {
    Scheduler.getInstance().run();
  }


  @Override
  public void autonomousInit() 
  {
  
    // schedule the autonomous command (example)
   // if (m_autonomousCommand != null) {
    // m_autonomousCommand.start();
    }
  

  
  @Override
  public void autonomousPeriodic() 
  {
    Scheduler.getInstance().run();
  }

  @Override
  public void teleopInit() 
  {
    Robot.pneumatics.compressor.setClosedLoopControl(true);
    InitiateIntake();
    
    //if (m_autonomousCommand != null) {
    //  m_autonomousCommand.cancel();
  }
  

  @Override
  public void teleopPeriodic()
  {
    Scheduler.getInstance().run();
    SmartDashboard.putNumber("Encoder", Robot.enc.getRaw());
  }

  @Override
  public void testPeriodic() 
  {

  }


//user made functions

//Servo Movement

private static void InitiateIntake()
{
  double currentActionTime = timer.get();
 
  
  
  currentActionTime = timer.get();
  while(timer.get() - currentActionTime < 1.3)
  {
  Robot.intake.wristMotor.set(0.8);
    if(timer.get() - currentActionTime > 0.8)
    {
      servo1.set(180);
    }
  }
 
  Robot.intake.wristMotor.set(0.0);

}
public static void setupPID(basePID pid, double target) {
  pid.reset();
  pid.setTolerance(1);
  pid.setOutputRange(0.0, 0.5);
  pid.setInputRange(0, 110);
  pid.setPoint(target);
}

}