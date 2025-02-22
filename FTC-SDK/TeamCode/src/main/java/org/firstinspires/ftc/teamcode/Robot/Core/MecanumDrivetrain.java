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


        leftFront.setReverse();
        leftBack.setReverse();
        rightFront.setForward();
        rightBack.setForward();

        setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        setBrakeMode(DcMotor.ZeroPowerBehavior.BRAKE);

        setMotorPower(0, 0, 0, 0);

    }

    public void setMotorPower(double leftFront, double leftBack, double rightBack, double rightFront){
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

    public void setBrakeMode(DcMotor.ZeroPowerBehavior behavior){
        leftFront.setBrakeBehavior(behavior);
        rightFront.setBrakeBehavior(behavior);
        leftBack.setBrakeBehavior(behavior);
        rightBack.setBrakeBehavior(behavior);
    }

    public void stopAndReset(){
        leftFront.setMotorBehavior(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }


}
