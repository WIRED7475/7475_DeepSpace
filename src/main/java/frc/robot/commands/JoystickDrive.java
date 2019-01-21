
package frc.robot.commands;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;


public class JoystickDrive extends Command 
{
  public JoystickDrive() 
  {
 
    requires(Robot.driveBase);
  }

  @Override
  protected void initialize()
  {
  }


  @Override
  protected void execute() 
  {
    Robot.driveBase.joystickDrive(Robot.oi.DriveStick);
    SmartDashboard.putNumber("Joystick X Value", Robot.oi.DriveStick.getX());
    SmartDashboard.putNumber("Joystick Y Value", Robot.oi.DriveStick.getY());

  }

  
  @Override
  protected boolean isFinished() 
  {
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() 
  {
  }

 
  @Override
  protected void interrupted() 
  {
  }
}
