
package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.Robot;
import frc.robot.commands.PneumaticsHatch;


public class Pneumatics extends Subsystem {
 
public static Solenoid Grippers = new Solenoid(0);
public static boolean gripperState = false;
public static Solenoid Mover = new Solenoid(1);
public static boolean moverState = false;  

public static Compressor compressor = new Compressor(0);


public static void moveSolenoids()
{
  if(Robot.oi.GripperButton.get())
  {
    Grippers.set(Robot.oi.GripperButton.get());
  

  }
  if(Robot.oi.OperatorController.getTriggerAxis(Hand.kLeft) > 0.5)
  {
    Mover.set(Robot.oi.OperatorController.getTriggerAxis(Hand.kLeft) > 0.5);

  }
}


  @Override
  public void initDefaultCommand() {
    
    setDefaultCommand(new PneumaticsHatch());
  }


}
