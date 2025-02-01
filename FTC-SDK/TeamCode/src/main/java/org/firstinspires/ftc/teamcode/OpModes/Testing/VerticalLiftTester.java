package org.firstinspires.ftc.teamcode.OpModes.Testing;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.Robot.Subsytems.Lift.VerticalLift;

@TeleOp(name = "Vertical Lift Tester", group = "Testing")
public class VerticalLiftTester extends OpMode {

    VerticalLift verticalLift;

    double l_power = 0;

    @Override
    public void init() {
        verticalLift = new VerticalLift(hardwareMap);

        verticalLift.setPower(l_power, l_power);

        verticalLift.leftLift.setMotorBehavior(DcMotor.RunMode.RUN_USING_ENCODER);
        verticalLift.rightLift.setMotorBehavior(DcMotor.RunMode.RUN_USING_ENCODER);

    }

    @Override
    public void loop() {
        l_power = gamepad1.left_stick_y;

        verticalLift.setPower(l_power, l_power);

        telemetry.addData("Left Position: " , verticalLift.getLeftPos());
        telemetry.addData("Right Position: " , verticalLift.getRightPos());
        telemetry.update();
    }
}
