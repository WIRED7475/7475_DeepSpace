/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;


import edu.wpi.first.wpilibj.DigitalGlitchFilter;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDBase;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;
import frc.robot.RobotMap;
import frc.robot.basePID;
import frc.robot.commands.RaiseLift;



public class Lift extends Subsystem 
{

    public static Spark leftReel = new Spark(RobotMap.leftReel_num);
    public static Spark rightReel = new Spark(RobotMap.rightReel_num);

    public  static DigitalInput Toplimit = new DigitalInput(RobotMap.topLimit_num);
    public  static DigitalInput Bottomlimit = new DigitalInput(RobotMap.bottomLimt_num);


    public static Encoder leftReelEncoder = new Encoder(1, 2, true, EncodingType.k4X);
    public static Encoder rightReelEncoder = new Encoder(3,4,true, EncodingType.k4X);

    public static double leftReelRotations;
    public static double rightReelRotations;

  

    public static double rightReelSpeed = 0.61;
    public static double leftReelSpeed = 0.7; 


   
  


  @Override
  public void initDefaultCommand() 
  {
   setDefaultCommand(new RaiseLift());
  }

  public static void RaiseLiftCmd()
  {
    if(!Robot.oi.OperatorController.getYButton())
    {
      leftReel.set(0);
      rightReel.set(0);
      return;
    }
  if(Robot.oi.OperatorController.getYButton() && !Toplimit.get() && !Robot.oi.wristMotorLock.get()) 
    {
      leftReel.set(0.7);
      rightReel.set(-0.61);
    }
    else{
      leftReel.set(0);
      leftReel.set(0);
    }
  }
  

  public static void LowerLiftCmd()
  {
    if(!Robot.oi.OperatorController.getAButton())
    {
      leftReel.set(0);
      rightReel.set(0);
      return;
    }
    if(Robot.oi.OperatorController.getAButton() && !Bottomlimit.get() && !Robot.oi.wristMotorLock.get()) 
    {
      leftReel.set(-0.7);
      rightReel.set(0.61);
    }
    else{
      leftReel.set(0);
      leftReel.set(0);
    }
  }
  
}

