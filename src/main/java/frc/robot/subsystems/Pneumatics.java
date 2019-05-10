
package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.Robot;
import frc.robot.commands.PneumaticsHatch;

public class Pneumatics extends Subsystem {
 
public static Solenoid Grippers = new Solenoid(0);
public static boolean gripperState = false;
public static Solenoid Mover = new Solenoid(1);
public static boolean moverState = false;  

public static Compressor compressor = new Compressor(0);


public static void moveGrippers()
{
    Grippers.set(Robot.oi.OperatorController.getBButton());
}


  @Override
  public void initDefaultCommand() {
    
    setDefaultCommand(new PneumaticsHatch());
  }


}
