package org.firstinspires.ftc.teamcode.Util.nov25swerve;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import java.util.Arrays;

@TeleOp(name = "Swerve Rotating Servo Localizer")
public class SwerveRotatingServoLocalizer extends OpMode {

    AdvancedSwerveDrivetrain drivetrain;

    private boolean fullDrive = false;


    @Override
    public void init() {
        drivetrain = new AdvancedSwerveDrivetrain(hardwareMap);



        telemetry.addLine("Ready for Start");
        telemetry.update();
    }

    @Override
    public void loop() {
        if(!fullDrive) {
            double LX = gamepad1.left_stick_x;
            double LY = gamepad1.left_stick_y;
            double RX = gamepad1.right_stick_x;

            drivetrain.updateAll(LX, LY, RX);

            if(gamepad1.a){
                fullDrive = true;
            }

            telemetry.addLine("In Full Drive Mode");

            telemetry.addLine("Current Angles: " + Arrays.toString(drivetrain.currentAngles()));
            telemetry.addLine("Target Angles: " + Arrays.toString(drivetrain.targetAngles()));
            //DEBUGGING PID
            telemetry.addLine("FL PID: " + Arrays.toString(drivetrain.getFL_PID()));
            telemetry.addLine("FR PID: " + Arrays.toString(drivetrain.getFR_PID()));
            telemetry.addLine("BL PID: " + Arrays.toString(drivetrain.getBL_PID()));
            telemetry.addLine("BR PID: " + Arrays.toString(drivetrain.getBR_PID()));

            telemetry.update();
        }
        else{
            if(gamepad1.a){
                fullDrive = false;
            }

            drivetrain.setFLTarg(gamepad1.left_stick_x*360);
            drivetrain.setFRTarg(gamepad1.right_stick_x*360);
            drivetrain.setBLTarg(gamepad1.left_stick_y*360);
            drivetrain.setBLTarg(gamepad1.right_stick_y*360);

            telemetry.addLine("In Debug Mode");

            telemetry.addLine("Back Y, Front X");

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
}
