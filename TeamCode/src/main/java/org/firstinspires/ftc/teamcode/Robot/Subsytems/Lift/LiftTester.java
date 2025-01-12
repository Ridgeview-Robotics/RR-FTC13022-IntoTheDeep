package org.firstinspires.ftc.teamcode.Robot.Subsytems.Lift;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp(name = "LiftTester", group = "Testing")
public class LiftTester extends OpMode {

    VerticalLift verticalLift;
    HorizontalLift horizontalLift;

    @Override
    public void init() {
        verticalLift = new VerticalLift(hardwareMap);
        horizontalLift = new HorizontalLift(hardwareMap);

        verticalLift.leftLift.setMotorBehavior(DcMotor.RunMode.RUN_USING_ENCODER);
        verticalLift.rightLift.setMotorBehavior(DcMotor.RunMode.RUN_USING_ENCODER);
        horizontalLift.motor.setMotorBehavior(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    @Override
    public void loop() {
        double vl_power = gamepad1.left_stick_y;
        double vr_power = gamepad1.right_stick_y;
        double h_power = gamepad1.right_stick_x;
    }
}
