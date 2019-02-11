
package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import frc.robot.commands.Brake;
import frc.robot.commands.LowerLift;
import frc.robot.commands.RaiseLift;
import frc.robot.commands.ShootBall;
import frc.robot.commands.TakeInBall;

public class OI 
{
  public Joystick DriveStick = new Joystick(0);
  public XboxController OperatorController = new XboxController(1);

  public JoystickButton stopButton = new JoystickButton(DriveStick, 1);

  public JoystickButton liftButton = new JoystickButton(OperatorController, 4);
  public JoystickButton lowerButton = new JoystickButton(OperatorController, 4);

  public JoystickButton takeInButton = new JoystickButton(OperatorController, 3);
  public JoystickButton shootOutButton = new JoystickButton(OperatorController, 2);

public OI()
{
    stopButton.whileHeld(new Brake());

    liftButton.whileHeld(new RaiseLift());
    lowerButton.whileHeld(new LowerLift());

    takeInButton.whileHeld(new TakeInBall());
    shootOutButton.whileHeld(new ShootBall());

  }
}
