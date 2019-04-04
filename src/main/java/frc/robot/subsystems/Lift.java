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

    public  static DigitalInput Toplimit = new DigitalInput(RobotMap.topLimit_num);
    public  static DigitalInput Bottomlimit = new DigitalInput(RobotMap.bottomLimt_num);


    public static Encoder leftReelEncoder = new Encoder(1, 2, true, EncodingType.k4X);
    public static Encoder rightReelEncoder = new Encoder(3,4,true, EncodingType.k4X);

    public static double leftReelRotations;
    public static double rightReelRotations;

    public static double RotationsToFirstLvl = 5;
    public static double RotationsToSecondLvl = 15;
    public static double RotationsToThirdLvl = 25;

    public static double rightReelSpeed = 0.46;
    public static double leftReelSpeed = 0.5;


   
  


  @Override
  public void initDefaultCommand() 
  {
    
  }

  public void RaiseLift(boolean buttonState)
  {
   if(Toplimit.get())
   {
    leftReel.set(0);
     rightReel.set(0);
     return;
    }
   
    if(buttonState && !Toplimit.get() && !Robot.oi.wristMotorLock.get())
    {
    leftReel.set(leftReelSpeed);
    rightReel.set(-rightReelSpeed);
    }else
    {
    leftReel.set(0);
    rightReel.set(0);
    
    }

  }

  public void LowerLift(boolean buttonState)
  { 
    if(Bottomlimit.get())
   {
    leftReel.set(0);
     rightReel.set(0);
     return;
    }

    if(buttonState && !Robot.oi.wristMotorLock.get() && !Bottomlimit.get())
    {
    leftReel.set(-leftReelSpeed);
    rightReel.set(rightReelSpeed);
    }else
    {
    leftReel.set(0);
    rightReel.set(0);
    }

  }

  public static void GroundToFirst()
  {
    
    rightReelRotations = rightReelEncoder.getRaw()/2048;
    while(rightReelRotations <= RotationsToFirstLvl && !Toplimit.get() )
    {
      rightReelRotations = rightReelEncoder.getRaw()/2048;
        if(RotationsToFirstLvl - rightReelRotations < 10) //when getting close
        {
           rightReel.set(-0.2);
           leftReel.set(0.2);
        }else{
          rightReel.set(-rightReelSpeed );
          leftReel.set(0.5);
        }
    }
    leftReel.set(0);
    rightReel.set(0); 
    
  }
  public static void GroundToSecond()
  {
    rightReelRotations = rightReelEncoder.getRaw()/2048;
    while(rightReelRotations <= RotationsToSecondLvl && !Toplimit.get() )
    {
      rightReelRotations = rightReelEncoder.getRaw()/2048;
        if(RotationsToSecondLvl - rightReelRotations < 10) //when getting close
        {
           rightReel.set(-0.2);
           leftReel.set(0.2);
        }else{
          rightReel.set(-rightReelSpeed);
          leftReel.set(0.5);
        }
    } 
    leftReel.set(0);
    rightReel.set(0);

    
    
  }
  public static void GroundToThird()
  {
    rightReelRotations = rightReelEncoder.getRaw()/2048;
    while(!Toplimit.get())
    {
      rightReelRotations = rightReelEncoder.getRaw()/2048;
        if(RotationsToThirdLvl - rightReelRotations < 10) //when getting close
        {
           rightReel.set(-0.2);
              leftReel.set(0.2);
        }else{
          rightReel.set(-rightReelSpeed);
          leftReel.set(0.5);
        }
    } 
    leftReel.set(0);
    rightReel.set(0);
    
    
  }


  public static void GroundLift()
  {
    if(Bottomlimit.get())
    {
     leftReel.set(0);
      rightReel.set(0);
      return;
     }

     while(!Bottomlimit.get())
     {
       leftReel.set(-0.5);
       leftReel.set(0.5);
     }
  }
  
}

