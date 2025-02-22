package org.firstinspires.ftc.teamcode.Robot.Subsytems.RotatingArm;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.Robot.Core.GlobalVars;
import org.firstinspires.ftc.teamcode.Utilities.Core.MotorRRX;

public class RotatingArm {

    public MotorRRX motor;
    armPositions mPos;

    private final double p = GlobalVars.arm_p;
    private final double i = GlobalVars.arm_i;
    private final double d = GlobalVars.arm_d;
    private final double f = GlobalVars.arm_f;

//    PIDFCoefficients coeff = new PIDFCoefficients(p, i, d, f);

    public enum armPositions{
        DOWN(GlobalVars.arm_down),
        HOLDING(GlobalVars.arm_holding),
        WALL(GlobalVars.WALL),
        SUBMERSIBLE(GlobalVars.arm_submersible),
        STRAIGHT_UP(GlobalVars.arm_straight_up),
        BUCKET(GlobalVars.arm_bucket);

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
        motor.setMotorBehavior(DcMotor.RunMode.RUN_TO_POSITION);

        motor.setReverse();

        motor.resetEncoder();
//        motor.setPIDFCoEff(DcMotorEx.RunMode.RUN_TO_POSITION, coeff);
        setPower(GlobalVars.arm_power);
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

    public void setBasicTarget(int pow){
        motor.setTargetPos(pow);
    }

}
