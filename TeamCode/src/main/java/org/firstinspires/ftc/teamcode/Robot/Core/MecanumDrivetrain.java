package org.firstinspires.ftc.teamcode.Robot.Core;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.Utilities.Core.MotorRRX;

public class MecanumDrivetrain {

    private final MotorRRX leftFront;
    private final MotorRRX leftBack;
    private final MotorRRX rightFront;
    private final MotorRRX rightBack;

    private final double mTicksPerRev = 383.6;

    private final double mWheelRadius = 1.8898;

    public MecanumDrivetrain(HardwareMap hardwareMap){
        leftFront = new MotorRRX(hardwareMap, "flm", 1.0);
        leftBack = new MotorRRX(hardwareMap, "blm", 1.0);
        rightFront = new MotorRRX(hardwareMap, "frm", 1.0);
        rightBack = new MotorRRX(hardwareMap, "brm", 1.0);


        leftFront.setForward();
        leftBack.setForward();
        rightFront.setReverse();
        rightBack.setReverse();


        setMotorPower(0, 0, 0, 0);

    }

    public void setMotorPower(double leftFront, double rightFront, double leftBack, double rightBack){
        this.leftFront.setPower(leftFront);
        this.rightFront.setPower(rightFront);
        this.leftBack.setPower(leftBack);
        this.rightBack.setPower(rightBack);
    }

    public void setMode(DcMotor.RunMode runMode){
        leftFront.setMotorBehavior(runMode);
        rightFront.setMotorBehavior(runMode);
        leftBack.setMotorBehavior(runMode);
        rightBack.setMotorBehavior(runMode);
    }

    private double inchesToTicks(double inches){
        return ((inches*mTicksPerRev)/mWheelRadius * 2 * Math.PI);
    }

    public void stopAndReset(){
        leftFront.setMotorBehavior(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }


}
