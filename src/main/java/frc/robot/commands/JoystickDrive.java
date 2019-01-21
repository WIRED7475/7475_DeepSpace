
package frc.robot.commands;
import edu.wpi.first.wpilibj.command.Command;
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
