/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;


public class Lift extends Subsystem 
{

    public Spark leftReel = new Spark(RobotMap.leftReel_num);
    public Spark rightReel = new Spark(RobotMap.rightReel_num);

  

  @Override
  public void initDefaultCommand() 
  {
    
  }

  public void RaiseLift()
  {
    leftReel.set(-1);
    rightReel.set(0.465);
  }

  public void LowerLift()
  {
    leftReel.set(1);
    rightReel.set(-0.465);

  }
}
