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


public class Intake extends Subsystem
 {

  public Spark leftIntake = new Spark(RobotMap.leftIntake_num);
  public Spark rightIntake = new Spark(RobotMap.rightIntake_num);


  @Override
  public void initDefaultCommand() 
  {
   
  }

  public void TakeIn()
  {
    leftIntake.set(-1);
    rightIntake.set(1);

  }

  public void ShootOut()
  {
    leftIntake.set(1);
    rightIntake.set(-1);
  }
}
