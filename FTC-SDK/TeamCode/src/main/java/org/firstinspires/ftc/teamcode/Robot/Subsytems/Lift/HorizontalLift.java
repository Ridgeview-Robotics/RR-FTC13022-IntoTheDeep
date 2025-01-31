package org.firstinspires.ftc.teamcode.Robot.Subsytems.Lift;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.Robot.Core.GlobalVars;
import org.firstinspires.ftc.teamcode.Utilities.Core.MotorRRX;

public class HorizontalLift {

    public MotorRRX motor;

    public horizPositions mHoriz;


    public enum horizPositions{
        RETRACTED(GlobalVars.hl_retracted),
        TRANSFERRING(GlobalVars.hl_retracted),
        LIMIT(GlobalVars.hl_limit);

        private final int horizLiftPos;

        horizPositions(final int newPos){
            horizLiftPos = newPos;
        }

        public int getPos(){
            return horizLiftPos;
        }
    }


    public HorizontalLift(HardwareMap hardwareMap){
        motor = new MotorRRX(hardwareMap, "hliftm", 1.0);

        //initializing to 0
        //reverse?
        mHoriz = horizPositions.RETRACTED;
        setTarget(getState());

        motor.resetEncoder();
        setPower(GlobalVars.hl_power);
    }

    public horizPositions getState(){
        return mHoriz;
    }

    public int getPos(){
        return motor.getPos();
    }


    public void setTarget(horizPositions target){
        motor.setTargetPos(target.getPos());
    }

    public void setPower(double power){
        motor.setPower(power);
    }



}
