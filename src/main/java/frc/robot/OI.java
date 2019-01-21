
package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import frc.robot.commands.Brake;

public class OI 
{
  public Joystick DriveStick = new Joystick(0);
  public JoystickButton stopButton = new JoystickButton(DriveStick, 1);


public OI()
{
    stopButton.whileHeld(new Brake());

  }
}
