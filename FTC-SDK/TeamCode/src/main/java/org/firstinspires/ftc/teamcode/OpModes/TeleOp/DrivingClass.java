package org.firstinspires.ftc.teamcode.OpModes.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.robot.RobotState;

import org.firstinspires.ftc.teamcode.Robot.Core.Robot;


@TeleOp(name = "DrivingT")
public class DrivingClass extends OpMode {

    Robot robot;

    Robot.robotStates state = Robot.robotStates.Scoring;

    @Override
    public void init() {
        robot = new Robot(hardwareMap);
        robot.init();

        telemetry.addLine("Here goes nothing");
        telemetry.update();
    }

    @Override
    public void loop() {
        robot.loop();

        double max;

        double x = -gamepad1.left_stick_y;
        double y = gamepad1.left_stick_x;
        double r = gamepad1.right_stick_x;

        double leftFrontPower = (x + y + r);
        double rightFrontPower = (x - y - r);
        double leftBackPower = (x - y + r);
        double rightBackPower = (x + y - r);

        max = Math.max(Math.abs(leftFrontPower), Math.abs(rightFrontPower));
        max = Math.max(max, Math.abs(leftBackPower));
        max = Math.max(max, Math.abs(rightBackPower));

        if(max > 1.0){
            leftFrontPower /= max;
            rightFrontPower /= max;
            leftBackPower /= max;
            rightBackPower /= max;
        }

        robot.drivetrain.setMotorPower(leftFrontPower, leftBackPower, rightBackPower, rightFrontPower);

        if(state == Robot.robotStates.Scoring){
            //Vertical Lift, Claw Toggle.
            //Horizontal and Intake are locked.
        }
        else if(state == Robot.robotStates.Transferring){
            //All controlls other than driving are locked.
        }
        else if(state == Robot.robotStates.Intaking){
            //Horizontal on dynamic control, intake intake and exhume
            //transfer button
            //vertical lift and arm are locked.
        }
        else{
            telemetry.addLine("Error!  Null State.");
        }

        telemetry.addData("State", state.getName());
        telemetry.addData("Rotation Ckr", robot.rotClear);
        telemetry.addData("Arm Ckr", robot.armClear);
        telemetry.update();
    }
}
