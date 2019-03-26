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
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;
import frc.robot.RobotMap;
import frc.robot.basePID;


public class Lift extends Subsystem 
{

    public static Spark leftReel = new Spark(RobotMap.leftReel_num);
    public static Spark rightReel = new Spark(RobotMap.rightReel_num);

    public  DigitalInput limit = new DigitalInput(0);

    public static Encoder leftReelEncoder = new Encoder(1, 2, true, EncodingType.k4X);
    public static Encoder rightReelEncoder = new Encoder(3,4,true, EncodingType.k4X);

    public static double leftReelRotations;
    public static double rightReelRotations;

    public static double RotationsToFirstLvl = 5;
    public static double RotationsToSecondLvl = 15;
    public static double RotationsToThirdLvl = 25;



    public static basePID raiseLiftPID = new basePID(0.05, 0, 0, rightReelEncoder );
   
    public static double pidOutputVar;
  

    
  public void Lift()
  {
    leftReelEncoder.reset();
    rightReelEncoder.reset();

    raiseLiftPID.setInputRange(0, RotationsToThirdLvl * 2048 * 1.5);
    raiseLiftPID.setOutputRange(-0.5, 0.5);
  }


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
   
    if(buttonState && !limit.get() && !Robot.oi.wristMotorLock.get())
    {
    SmartDashboard.putString("Lift State", "Raising Lift");
    leftReel.set(0.5);
    rightReel.set(-0.46);
    }else
    {
    leftReel.set(0);
    rightReel.set(0);
    
    }

  }

  public void LowerLift(boolean buttonState)
  {
    if(buttonState && !Robot.oi.wristMotorLock.get())
    {
    SmartDashboard.putString("Lift State", "Lowering Lift");
    leftReel.set(-0.5);
    rightReel.set(0.46);
    }else
    {
    leftReel.set(0);
    rightReel.set(0);
    }

  }

  public static void GroundToFirst()
  {
    
    raiseLiftPID.setPoint(RotationsToFirstLvl * 2048);
    raiseLiftPID.start();
    raiseLiftPID.pidWrite(pidOutputVar);
    leftReel.set(-pidOutputVar);
    rightReel.set(pidOutputVar);

    //alternatively

   /*
    while(rightReelRotations != RotationsToFirstLvl)
    {
      rightReel.set(-0.5);
      leftReel.set(0.5);
        if(RotationsToFirstLvl - rightReelRotations < 10) //when getting close
        {
           rightReel.set(0.2);
           leftReel.set(0.2);
        }
    }

    */
    
  }
  public static void GroundToSecond()
  {
    
  }
  public static void GroundToThird()
  {
    
    
  }
  
}

