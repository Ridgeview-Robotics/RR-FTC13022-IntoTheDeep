package org.firstinspires.ftc.teamcode.OpModes.Testing;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.Robot.Subsytems.Lift.HorizontalLift;
import org.firstinspires.ftc.teamcode.Robot.Subsytems.Lift.VerticalLift;

@TeleOp(name = "Horizontal Lift Tester", group = "Testing")
public class HorizontalLiftTester extends OpMode {

    HorizontalLift horizontalLift;

    double h_power = 0;

    @Override
    public void init() {
        horizontalLift = new HorizontalLift(hardwareMap);

        horizontalLift.setPower(h_power);

        horizontalLift.motor.setMotorBehavior(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    @Override
    public void loop() {
        h_power = gamepad1.left_stick_x;

        horizontalLift.setPower(h_power);

        telemetry.addData("Lift Position: " , horizontalLift.getPos());
        telemetry.update();
    }
}
