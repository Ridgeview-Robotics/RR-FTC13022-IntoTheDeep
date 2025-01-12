package org.firstinspires.ftc.teamcode.Robot.Subsytems.Lift;



import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.Robot.Core.GlobalVars;
import org.firstinspires.ftc.teamcode.Utilities.Core.MotorRRX;

public class VerticalLift {

    public MotorRRX leftLift;
    public MotorRRX rightLift;

    public vertPositions mVert;


    public enum vertPositions{
        DOWN(GlobalVars.vl_down),
        WALL(GlobalVars.vl_wall),
        BUCKET_HIGH(GlobalVars.vl_bucket_high),
        BUCKET_LOW(GlobalVars.vl_bucket_low),
        BAR_HIGH(GlobalVars.vl_bar_high),
        BAR_LOW(GlobalVars.vl_bar_low);

        private final int verticalLiftPos;

        vertPositions(final int newPos){
            verticalLiftPos = newPos;
        }

        public int getVertPos(){
            return verticalLiftPos;
        }
    }


    public VerticalLift(HardwareMap hardwareMap){
        leftLift = new MotorRRX(hardwareMap, "lvliftm", 1.0);
        rightLift = new MotorRRX(hardwareMap, "rvliftm", 1.0);

        //initializing to 0
        //one motor needs to be set to reverse
        mVert = vertPositions.DOWN;
        setTarget(getState());

        rightLift.resetEncoder();
        leftLift.resetEncoder();
        setPower(GlobalVars.vl_power, GlobalVars.vl_power);
    }

    public vertPositions getState(){
        return mVert;
    }

    public int getLeftPos(){
        return leftLift.getPos();
    }

    public int getRightPos(){
        return rightLift.getPos();
    }

    public void setTarget(vertPositions target){
        leftLift.setTargetPos(target.getVertPos());
        rightLift.setTargetPos(target.getVertPos());
    }

    public void setPower(double powL, double powR){
        leftLift.setPower(powL);
        rightLift.setPower(powR);
    }


}
