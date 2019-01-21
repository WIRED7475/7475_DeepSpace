
package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;


public class Brake extends Command 
{
  public Brake() 
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
    Robot.driveBase.Brake();
  }

  
  @Override
  protected boolean isFinished() {
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
