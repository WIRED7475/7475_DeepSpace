
package frc.robot;

import javax.lang.model.util.ElementScanner6;

import com.kauailabs.navx.frc.AHRS;


import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.subsystems.DriveBase;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Lift;



public class Robot extends TimedRobot {
  
  public static DriveBase driveBase = new DriveBase();
  public static Lift lift = new Lift();
  public static Intake intake = new Intake();
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


    CameraServer.getInstance().startAutomaticCapture();
    
   InitiateIntake();

    
    
  }

  
  @Override
  public void robotPeriodic() 
  {
 

    ///////////////////setting variables
    if(!Robot.oi.liftButton.get() && !Robot.oi.lowerButton.get())
    {
      SmartDashboard.putString("Lift State", "Lift Immobile");
    }
    
    if(!Robot.oi.shootOutButton.get() && !Robot.oi.takeInButton.get())
    {
      SmartDashboard.putString("Claw State", "Claw Immobile");
    }



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

    }


    if(Robot.oi.wristMotorRaise.get())
    {
      Robot.intake.wristMotor.set(0.5);
    }else if(Robot.oi.wristMotorLower.get())
    {
      Robot.intake.wristMotor.set(0.0);
    }else
    {
      Robot.intake.wristMotor.set(0.2);
    }
      
  
      
    }

  

 
  @Override
  public void disabledInit() 
  {
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

//Servo Movement

private static void InitiateIntake()
{
  double currentActionTime = timer.get();
 
  
  
  currentActionTime = timer.get();
  while(timer.get() - currentActionTime < 4)
  {
  Robot.intake.wristMotor.set(0.5);
    if(timer.get() - currentActionTime > 1.5)
    {
      servo1.set(180);
    }
  }
 
  Robot.intake.wristMotor.set(0.2);

}

}