package org.firstinspires.ftc.teamcode.OpModes.Testing;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.Robot.Core.GlobalVars;
import org.firstinspires.ftc.teamcode.Robot.Subsytems.RotatingArm.RotatingArm;

public class ArmPIDTester extends OpMode {

    RotatingArm arm;

    @Override
    public void init() {
        arm = new RotatingArm(hardwareMap);
        arm.setPower(GlobalVars.arm_power);

        telemetry.update();
    }

    @Override
    public void loop() {
        if(gamepad1.a){
            arm.setTarget(RotatingArm.armPositions.DOWN);
        }
        if(gamepad1.dpad_right){
            arm.setTarget(RotatingArm.armPositions.SUB_LOW);
        }
        if(gamepad1.dpad_up){
            arm.setTarget(RotatingArm.armPositions.SUB_HIGH);
        }
        if(gamepad1.dpad_left){
            arm.setTarget(RotatingArm.armPositions.WALL);
        }
        if(gamepad1.b) {
            arm.setTarget(RotatingArm.armPositions.BUCKET);
        }

        telemetry.addData("Arm Position: " , arm.getPos());
        telemetry.addData("Arm PIDS: " , arm.motor.getPIDFCoEff(DcMotor.RunMode.RUN_TO_POSITION));
        telemetry.update();
    }
}
