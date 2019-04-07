
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

   
    Robot.pneumatics.compressor.setClosedLoopControl(true);
    
    
  }

  
  @Override
  public void robotPeriodic() 
  {

    Robot.lift.leftReelRotations = Robot.lift.leftReelEncoder.getRaw() / 2048;
    Robot.lift.rightReelRotations = Robot.lift.rightReelEncoder.getRaw() / 2048;
 

    ///////////////////setting variables on dashboard
    if(Robot.lift.rightReel.get() == 0)
    {
      SmartDashboard.putString("Lift State", "Lift Immobile");
    } 
    else  if(Robot.lift.rightReel.get() < 0)
    {
      SmartDashboard.putString("Lift State", "Lift Rising!");
    }
    else if(Robot.lift.rightReel.get() > 0)
    SmartDashboard.putString("Lift State", "Lift Lowering");
    


    if(Robot.intake.leftIntake.get() == 0)
    {
      SmartDashboard.putString("Claw State", "Claw Immobile");
    }else if(Robot.intake.leftIntake.get() > 0)
    {
      SmartDashboard.putString("Claw State", "Claw Shooting Out!");
    }else if(Robot.intake.leftIntake.get() < 0)
    {
      SmartDashboard.putString("Claw State", "Claw taking In!");
    }

    SmartDashboard.putBoolean("Top LimitSwitch", Robot.lift.Toplimit.get());
    SmartDashboard.putBoolean("Bottom LimitSwitch", Robot.lift.Bottomlimit.get());

    SmartDashboard.putNumber("LeftReelGetRaw", Robot.lift.leftReelEncoder.getRaw());
    SmartDashboard.putNumber("RightReelGetRaw", Robot.lift.rightReelEncoder.getRaw());
  
    SmartDashboard.putNumber("LeftReelCount", Robot.lift.leftReelRotations);
    SmartDashboard.putNumber("RightReelCount", Robot.lift.rightReelRotations);
    

    ////////////////////


    if(Robot.oi.StopAllButton.get() == true)
    {
      Robot.intake.leftIntake.stopMotor();
      Robot.intake.rightIntake.stopMotor();

      Robot.lift.leftReel.stopMotor();
      Robot.lift.rightReel.stopMotor();

      Robot.driveBase.rightDrive.stopMotor();
      Robot.driveBase.leftDrive.stopMotor();
      Robot.intake.wristMotor.stopMotor();

    }


      if(Robot.oi.wristMotorLock.get())
      {
      if(Robot.oi.OperatorController.getAButton())
      {
        Robot.intake.wristMotor.set(-0.5);
        
      }
      else if(Robot.oi.OperatorController.getYButton())
      {
        Robot.intake.wristMotor.set(0.8);
        
      }else
      {
        Robot.intake.wristMotor.set(0.2);
      }
     }else
     {
       Robot.intake.wristMotor.set(0);
     }
    
      
  
   


  if(Robot.oi.OperatorController.getTriggerAxis(Hand.kLeft) > 0.1)
  {
    Robot.pneumatics.Mover.set(true);
  }else if (Robot.oi.OperatorController.getTriggerAxis(Hand.kLeft) == 0 )
  {
    Robot.pneumatics.Mover.set(false);
  }


  if(Robot.lift.Bottomlimit.get())
  {
    Robot.lift.rightReelEncoder.reset();
    Robot.lift.leftReelEncoder.reset();

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
   // InitiateIntake();
    //if (m_autonomousCommand != null) {
    //  m_autonomousCommand.cancel();
  }
  

  @Override
  public void teleopPeriodic()
  {
    Scheduler.getInstance().run();
    
  }

  @Override
  public void testPeriodic() 
  {

  }


//user made functions


private static void InitiateIntake()
{
  double currentActionTime = timer.get();
 
  
  
  currentActionTime = timer.get();
  while(timer.get() - currentActionTime < 1)
  {
  Robot.intake.wristMotor.set(0.8);
    
  }
 
  Robot.intake.wristMotor.set(0.0);

}


}