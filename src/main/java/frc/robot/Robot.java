
package frc.robot;

import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.subsystems.DriveBase;



public class Robot extends TimedRobot {
  
  public static DriveBase driveBase = new DriveBase();
  public static OI oi = new OI();
 
  public static Servo servo1 = new Servo(4);



 // Command m_autonomousCommand;
 // SendableChooser<Command> m_chooser = new SendableChooser<>();

 
  @Override
  public void robotInit()
   {
    CameraServer.getInstance().startAutomaticCapture();
   
    
  }

  
  @Override
  public void robotPeriodic() 
  {
    ServoSwerve();
    
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

private static void ServoSwerve()
{
    double sliderValue  = Robot.oi.DriveStick.getRawAxis(3);
    servo1.setAngle((sliderValue + 1 )* 90);
    SmartDashboard.putNumber("ServoAngle", servo1.getAngle());
}


}