package org.firstinspires.ftc.teamcode.Util.nov25swerve;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name = "AdvSwerveTest")
public class AdvancedSwerveKitTester extends OpMode {

    AdvancedSwerveDrivetrain drivetrain;

    @Override
    public void init() {
        drivetrain = new AdvancedSwerveDrivetrain();
    }

    @Override
    public void loop() {
        double LX = gamepad1.left_stick_x;
        double LY = gamepad1.left_stick_y;
        double RX = gamepad1.right_stick_x;

        drivetrain.solve(LX, LY, RX);
        drivetrain.assignMotorPower();



    }
}
