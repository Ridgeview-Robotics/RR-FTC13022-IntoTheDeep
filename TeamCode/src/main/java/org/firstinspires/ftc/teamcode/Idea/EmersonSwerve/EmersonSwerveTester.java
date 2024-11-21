package org.firstinspires.ftc.teamcode.Idea.EmersonSwerve;

import static java.lang.Math.atan2;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Idea.PrelimSwerveModule;

@TeleOp(name = "Emerson Swerve Test")
public class EmersonSwerveTester extends OpMode {

    PrelimSwerveModule swerveModule;

    @Override
    public void init() {
        swerveModule = new PrelimSwerveModule(hardwareMap);
    }

    @Override
    public void loop() {
        double dirX = gamepad1.left_stick_x;
        double dirY = -gamepad1.right_stick_y;
        double power = -gamepad1.right_stick_y;

        double theta = atan2(dirX, dirY);


        swerveModule.setSwerveMotorPower(power);

//        swerveModule.setSwerveMotorPower(x);
//        swerveModule.setSwerveServoPower(y);
    }
}
