
package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import frc.robot.commands.Brake;
import frc.robot.commands.LowerLift;
import frc.robot.commands.NeutralWrist;
import frc.robot.commands.RaiseLift;
import frc.robot.commands.Rotate;


public class OI 
{
  public Joystick DriveStick = new Joystick(0);
  public XboxController OperatorController = new XboxController(1);

  public JoystickButton stopButton = new JoystickButton(DriveStick, 2);
  public JoystickButton RotateButton = new JoystickButton(DriveStick, 1);
  public JoystickButton driveStraightBurron = new JoystickButton(DriveStick, 5);

  public JoystickButton liftButton = new JoystickButton(OperatorController, 4);
  public JoystickButton lowerButton = new JoystickButton(OperatorController, 1);

  public JoystickButton takeInButton = new JoystickButton(OperatorController, 3);
  public JoystickButton shootOutButton = new JoystickButton(OperatorController, 2);

  public JoystickButton StopAllButton = new JoystickButton(OperatorController, 9);

  public JoystickButton wristMotorLock = new JoystickButton(OperatorController, 6);
  public JoystickButton wristMotorNeutral = new JoystickButton(OperatorController, 5);



  public JoystickButton RaiseToFirstLevelButton = new JoystickButton(DriveStick, 7);
  public JoystickButton RaiseToSecondLevelButton = new JoystickButton(DriveStick, 8);
  public JoystickButton RaiseToThirdLevelButton = new JoystickButton(DriveStick, 9);

  public JoystickButton GroundLift = new JoystickButton(DriveStick, 10);


  



public OI()
{
    stopButton.whileHeld(new Brake());
    RotateButton.whileHeld(new Rotate());
   

    liftButton.whileHeld(new RaiseLift()); 
    lowerButton.whileHeld(new LowerLift());
  
    wristMotorLock.whenPressed(new NeutralWrist());
  


    }


  }


