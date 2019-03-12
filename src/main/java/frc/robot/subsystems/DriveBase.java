
package frc.robot.subsystems;


import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import frc.robot.RobotMap;
import frc.robot.commands.JoystickDrive;

public class DriveBase extends Subsystem 
{

  public Spark blMotor = new Spark(RobotMap.blMotor_num);
  public Spark flMotor = new Spark(RobotMap.flMotor_num);
  public SpeedControllerGroup leftMotorGroup= new SpeedControllerGroup(flMotor, blMotor);

  public Spark brMotor = new Spark(RobotMap.brMotor_num);
  public Spark frMotor = new Spark(RobotMap.frMotor_num); 
  public SpeedControllerGroup rightMotorGroup= new SpeedControllerGroup(frMotor, brMotor);

  public DifferentialDrive robotDrive = new DifferentialDrive(leftMotorGroup, rightMotorGroup);

   
  @Override
  public void initDefaultCommand() 
  {
  setDefaultCommand(new JoystickDrive());


  }
  public void joystickDrive(Joystick joystick)
  {
    robotDrive.setSafetyEnabled(false);
    robotDrive.arcadeDrive(-joystick.getY(), joystick.getX());
  }

  public void Rotate(Joystick joystick, boolean buttonState)
  {
    if(buttonState)
    {
      rightMotorGroup.set(joystick.getX());
      leftMotorGroup.set(joystick.getX());
    }

  }

  public void Brake()
  {
    blMotor.set(0);
    flMotor.set(0);
    brMotor.set(0);
    frMotor.set(0);
  }


}
