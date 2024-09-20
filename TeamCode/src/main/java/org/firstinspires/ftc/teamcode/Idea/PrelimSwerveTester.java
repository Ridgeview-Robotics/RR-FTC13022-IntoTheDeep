package org.firstinspires.ftc.teamcode.Idea;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;

public class PrelimSwerveTester extends OpMode {

    PrelimSwerveModule swerveModule;

    @Override
    public void init() {
        swerveModule = new PrelimSwerveModule(hardwareMap);
    }

    @Override
    public void loop() {
        double x = -gamepad1.left_stick_y;
        double y = gamepad1.right_stick_x;

        swerveModule.setSwerveMotorPower(x);
        swerveModule.setSwerveServoPower(y);
    }
}
