package org.firstinspires.ftc.teamcode.Robot.Subsytems.RotatingArm;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.Robot.Core.GlobalVars;
import org.firstinspires.ftc.teamcode.Utilities.Core.MotorRRX;

public class RotatingArm {

    public MotorRRX motor;
    armPositions mPos;

    public enum armPositions{
        DOWN(GlobalVars.ra_down),
        HIGH(GlobalVars.ra_high),
        BACKWARDS(GlobalVars.ra_backwards);

        private final int rotatingArmPos;

        armPositions(final int newPos){
            rotatingArmPos = newPos;
        }

        public int getArmPos(){
            return rotatingArmPos;
        }

    }

    public RotatingArm(HardwareMap hardwareMap){
        motor = new MotorRRX(hardwareMap, "rarmm", 1.0);

        //initializing to 0
        mPos = armPositions.DOWN;
        setTarget(getState());

        motor.resetEncoder();
        motor.setPower(GlobalVars.ra_power);
    }

    public armPositions getState(){
        return mPos;
    }

    public int getPos(){
        return motor.getPos();
    }

    public void setTarget(armPositions target){
        motor.setTargetPos(target.getArmPos());
    }

    public void setPower(double power){
        motor.setPower(power);
    }

}
