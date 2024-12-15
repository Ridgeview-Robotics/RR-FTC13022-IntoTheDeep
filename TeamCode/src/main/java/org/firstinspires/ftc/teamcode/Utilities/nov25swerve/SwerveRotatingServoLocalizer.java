package org.firstinspires.ftc.teamcode.Utilities.nov25swerve;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.Telemetry;

import java.util.Arrays;

@TeleOp(name = "Swerve Rotating Servo Localizer")
public class SwerveRotatingServoLocalizer extends OpMode {

    AdvancedSwerveDrivetrain drivetrain;

    private boolean fullDrive = false;

    private FtcDashboard dashboard = FtcDashboard.getInstance();


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

            Telemetry telemetry = new MultipleTelemetry(this.telemetry, dashboard.getTelemetry());

            telemetry.addData("Current Angles: " , Arrays.toString(drivetrain.currentAngles()));
            telemetry.addData("Target Angles: " , Arrays.toString(drivetrain.targetAngles()));

            telemetry.addData("Voltage Values: " , Arrays.toString(drivetrain.currentVoltages()));
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
            drivetrain.setBRTarg(gamepad1.right_stick_y*360);

            telemetry.addLine("In Debug Mode");

            telemetry.addLine("Back Y, Front X");

            telemetry.addLine("Current Angles: " + Arrays.toString(drivetrain.currentAngles()));
            telemetry.addLine("Target Angles: " + Arrays.toString(drivetrain.targetAngles()));
            //DEBUGGING PID
            telemetry.addLine("FL PID: " + Arrays.toString(drivetrain.getFL_PID()));
            telemetry.addLine("FR PID: " + Arrays.toString(drivetrain.getFR_PID()));
            telemetry.addLine("BL PID: " + Arrays.toString(drivetrain.getBL_PID()));
            telemetry.addLine("BR PID: " + Arrays.toString(drivetrain.getBR_PID()));


            telemetry.addLine("Errors: " + drivetrain.FL.returnError() + "," + drivetrain.FR.returnError() + "," + drivetrain.BL.returnError() + "," + drivetrain.BR.returnError());

            telemetry.update();
        }
    }
}
