/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DigitalGlitchFilter;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.Robot;
import frc.robot.RobotMap;


public class Lift extends Subsystem 
{

    public Spark leftReel = new Spark(RobotMap.leftReel_num);
    public Spark rightReel = new Spark(RobotMap.rightReel_num);

    DigitalInput limit = new DigitalInput(0);

  

  @Override
  public void initDefaultCommand() 
  {
    
  }

  public void RaiseLift(boolean buttonState)
  {
    if(limit.get())
    {
      leftReel.set(0);
      rightReel.set(0);
      return;
    }
    if(buttonState)
    {
    leftReel.set(-0.5);
    rightReel.set(0.465);
    }else
    {
    leftReel.set(0);
    rightReel.set(0);
    }

  }

  public void LowerLift(boolean buttonState)
  {
    if(buttonState)
    {
    leftReel.set(0.5);
    rightReel.set(-0.465);
    }else
    {
    leftReel.set(0);
    rightReel.set(0);
    }

  }
}
