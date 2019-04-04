/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;
import frc.robot.RobotMap;
import frc.robot.commands.TakeInBall;


public class Intake extends Subsystem
 {

  public Spark leftIntake = new Spark(RobotMap.leftIntake_num);
  public Spark rightIntake = new Spark(RobotMap.rightIntake_num);

  public Spark wristMotor = new Spark(RobotMap.wristMotor_num);



  @Override
  public void initDefaultCommand() 
  {
   setDefaultCommand(new TakeInBall());
  }

  public void TakeIn()
  {
    double intakespeed = Robot.oi.OperatorController.getY(Hand.kLeft);
    Robot.intake.leftIntake.set(-intakespeed);
    Robot.intake.rightIntake.set(intakespeed);

    if(intakespeed < 0)
    {
      Robot.intake.leftIntake.set(-intakespeed/4);
      Robot.intake.rightIntake.set(intakespeed/4);
    }
    
  }
  

  public void ShootOut(boolean buttonState)
  {
   /* if(buttonState)
    {
    leftIntake.set(1);
    rightIntake.set(-1);
    
    }
    else
    {
      leftIntake.set(0);
      rightIntake.set(0);
    }
    */
  }

  public void NeutralWrist()
  {
    wristMotor.set(0.2);
    SmartDashboard.putString("WristMotorState", "Neutral");

  }
}
