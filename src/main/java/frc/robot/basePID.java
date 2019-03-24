/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;


public class basePID implements PIDOutput {
private PIDController pid;

    public basePID(double Kp, double Ki, double Kd, PIDSource source)
    {
        pid = new PIDController(Kp, Ki, Kd, source, this);
    }

    public void start(){
        pid.enable();
    }

    public void stop() {
        pid.disable();
    }

    public void reset() {
        pid.reset();
    }

    public double getOutput() {
        return pid.get();
    }

    public void setTolerance(double tolerance){
        pid.setAbsoluteTolerance(tolerance);
    }

    public void setPoint(double point) {
        pid.setSetpoint(point);
    }

    public void setOutputRange(double min, double max){
        pid.setOutputRange(min, max);
    }

    public void setInputRange(double min, double max){
        pid.setInputRange(min, max);
    }
    @Override
    public void pidWrite(double output) {
    
    }
}
