package org.firstinspires.ftc.teamcode.Util.nov25swerve;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import java.util.Arrays;

@TeleOp(name = "AdvSwerveTest")
public class AdvancedSwerveKitTester extends OpMode {

    AdvancedSwerveDrivetrain drivetrain;

    @Override
    public void init() {
        drivetrain = new AdvancedSwerveDrivetrain(hardwareMap);

        telemetry.addLine("Ready for Start");
        telemetry.update();
    }

    @Override
    public void loop() {
        double LX = gamepad1.left_stick_x;
        double LY = gamepad1.left_stick_y;
        double RX = gamepad1.right_stick_x;

        drivetrain.updateAll(LX, LY, RX);
        drivetrain.assignMotorPower();

        telemetry.addLine("Current Velocities: " + Arrays.toString(drivetrain.currentVelocities()));
        telemetry.addLine("Target Velocities: " + Arrays.toString(drivetrain.targetVelocities()));
        telemetry.addLine("Current Angles: " + Arrays.toString(drivetrain.currentAngles()));
        telemetry.addLine("Target Angles: " + Arrays.toString(drivetrain.targetAngles()));
        telemetry.update();
    }
}
