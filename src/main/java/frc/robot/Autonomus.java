package frc.robot;

/**
 * Some basic autonomus code.
 */
import frc.robot.basePID;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import frc.robot.Robot;
public class Autonomus {
   public static basePID leftPID;
   public static basePID rightPID;

    public void straight(int distance) {
      leftPID = new basePID(1, 0, 1, Robot.leftReelEncoder);
      leftPID.setOutputRange(-0.4, 0.4);
      leftPID.setTolerance(1);

      rightPID = new basePID(1, 0, 1, Robot.rightReelEncoder);
      rightPID.setOutputRange(-0.4, 0.4);
      rightPID.setTolerance(1);

       leftPID.setPoint(distance);
       rightPID.setPoint(distance);

       leftPID.start();
       rightPID.start();
    }
    public double getLeftOutput() {
       return leftPID.getOutput();
    }
    public double getRightOutput() {
        return rightPID.getOutput();
    }
}