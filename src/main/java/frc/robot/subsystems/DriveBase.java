
package frc.robot.subsystems;


import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.SpeedControllerGroup;  
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.RobotMap;
import frc.robot.commands.JoystickDrive;

public class DriveBase extends Subsystem 
{

  public Spark rightDrive = new Spark(RobotMap.rightDrive_num);
  public Spark leftDrive = new Spark(RobotMap.leftDrive_num);


  public DifferentialDrive robotDrive = new DifferentialDrive(rightDrive, leftDrive);

   
  @Override
  public void initDefaultCommand() 
  {
  setDefaultCommand(new JoystickDrive());


  }
  public void joystickDrive(Joystick joystick)
  {
    double throttle = (1 - joystick.getThrottle()) / 1;
    robotDrive.setSafetyEnabled(false);
    robotDrive.arcadeDrive(-joystick.getY() * throttle, joystick.getX() * throttle);
  }

 
  public void Rotate(Joystick joystick, boolean buttonState)
  {
    double throttle = (1 - joystick.getThrottle()) / 1;
    if(buttonState)
    {
      robotDrive.arcadeDrive(0, joystick.getX() * throttle);
    }

  }

  public void Brake()
  {
    rightDrive.stopMotor();
    leftDrive.stopMotor();
  }


}
