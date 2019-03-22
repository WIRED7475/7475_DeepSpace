/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.RobotMap;


public class Intake extends Subsystem
 {

  public Spark leftIntake = new Spark(RobotMap.leftIntake_num);
  public Spark rightIntake = new Spark(RobotMap.rightIntake_num);

  public Spark wristMotor = new Spark(RobotMap.wristMotor_num);



  @Override
  public void initDefaultCommand() 
  {
   
  }

  public void TakeIn(boolean buttonState)
  {
    if(buttonState)
    {
    SmartDashboard.putString("Claw State", "Taking In!");
    leftIntake.set(-0.25);
    rightIntake.set(0.25);
    }
    else
    {
      leftIntake.set(0);
      rightIntake.set(0);
    }

  }

  public void ShootOut(boolean buttonState)
  {
    if(buttonState)
    {
    SmartDashboard.putString("Claw State", "Shooting!");
    leftIntake.set(1);
    rightIntake.set(-1);
    
    }
    else
    {
      leftIntake.set(0);
      rightIntake.set(0);
    }
  }
}
