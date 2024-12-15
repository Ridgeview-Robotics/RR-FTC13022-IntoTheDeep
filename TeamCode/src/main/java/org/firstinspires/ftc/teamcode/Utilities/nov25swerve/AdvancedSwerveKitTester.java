package org.firstinspires.ftc.teamcode.Utilities.nov25swerve;

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

        telemetry.addLine("Current Velocities: " + Arrays.toString(drivetrain.currentVelocities()));
        telemetry.addLine("Target Velocities: " + Arrays.toString(drivetrain.targetVelocities()));
        telemetry.addLine("Current Angles: " + Arrays.toString(drivetrain.currentAngles()));
        telemetry.addLine("Target Angles: " + Arrays.toString(drivetrain.targetAngles()));
        //DEBUGGING PID
        telemetry.addLine("FL PID: " + Arrays.toString(drivetrain.getFL_PID()));
        telemetry.addLine("FR PID: " + Arrays.toString(drivetrain.getFR_PID()));
        telemetry.addLine("BL PID: " + Arrays.toString(drivetrain.getBL_PID()));
        telemetry.addLine("BR PID: " + Arrays.toString(drivetrain.getBR_PID()));

        telemetry.update();
    }
}
