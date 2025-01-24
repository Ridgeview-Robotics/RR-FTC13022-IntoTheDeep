package org.firstinspires.ftc.teamcode.OpModes.Testing;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.Robot.Subsytems.Lift.HorizontalLift;
import org.firstinspires.ftc.teamcode.Robot.Subsytems.RotatingArm.RotatingArm;

@TeleOp(name = "Rotating Arm Tester", group = "Testing")
public class ArmTester extends OpMode {

    RotatingArm rotatingArm;

    @Override
    public void init() {
        rotatingArm = new RotatingArm(hardwareMap);

        rotatingArm.motor.setMotorBehavior(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    @Override
    public void loop() {
        double ra_power = gamepad1.left_stick_x;

        rotatingArm.setPower(ra_power);

        telemetry.addData("Arm Position: " , rotatingArm.getPos());
        telemetry.update();
    }
}
