package org.firstinspires.ftc.teamcode.Idea;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;

public class PrelimSwerveTester extends OpMode {

    PrelimSwerveModule swerveModuleFL;
    PrelimSwerveModule swerveModuleFR;
    PrelimSwerveModule swerveModuleBL;
    PrelimSwerveModule swerveModuleBR;

    @Override
    public void init() {
        swerveModuleFL = new PrelimSwerveModule(hardwareMap, "FLServo", "FLMotor");
        swerveModuleFR = new PrelimSwerveModule(hardwareMap, "FRServo", "FRMotor");
        swerveModuleBL = new PrelimSwerveModule(hardwareMap, "BLServo", "BLMotor");
        swerveModuleBR = new PrelimSwerveModule(hardwareMap, "BRServo", "BRMotor");
    }

    @Override
    public void loop() {
        double x = -gamepad1.left_stick_y;
        double y = gamepad1.right_stick_x;

        swerveModuleFL.setSwerveMotorPower(x);
        swerveModuleFL.setSwerveServoPower(y);

        swerveModuleFR.setSwerveMotorPower(x);
        swerveModuleFR.setSwerveServoPower(y);

        swerveModuleBL.setSwerveMotorPower(x);
        swerveModuleBL.setSwerveServoPower(y);

        swerveModuleBR.setSwerveMotorPower(x);
        swerveModuleBR.setSwerveServoPower(y);
    }
}
