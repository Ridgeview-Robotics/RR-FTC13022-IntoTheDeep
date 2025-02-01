package org.firstinspires.ftc.teamcode.OpModes.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.robot.RobotState;

import org.firstinspires.ftc.teamcode.Robot.Core.Robot;
import org.firstinspires.ftc.teamcode.Robot.Subsytems.Lift.VerticalLift;
import org.firstinspires.ftc.teamcode.Robot.Subsytems.RotatingArm.RotatingArm;


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

            //down dpad is base,    back to driving
            //low rung is right dpad      arm is around and below
            //updpad is high rung
            //high bucket is y      straight up
            //low bucket is b      straight up
            //x is slam dunk
            // claw toggle is a
            //left dpad is wall grab

            //right bumper to intake, drops intake

            if(gamepad1.dpad_down){
                robot.vert.setTarget(VerticalLift.vertPositions.DOWN);
                robot.setArmDownWithClkr();
                //TODO
                robot.switchState(Robot.robotStates.Transferring);
            }

            if(gamepad1.dpad_right){
                robot.arm.setTarget(RotatingArm.armPositions.SUBMERSIBLE);
                robot.vert.setTarget(VerticalLift.vertPositions.BAR_LOW);
            }

            if(gamepad1.dpad_up){
                robot.arm.setTarget(RotatingArm.armPositions.SUBMERSIBLE);
                robot.vert.setTarget(VerticalLift.vertPositions.BAR_HIGH);
            }

            if(gamepad1.y){
                robot.arm.setTarget(RotatingArm.armPositions.STRAIGHT_UP);
                robot.vert.setTarget(VerticalLift.vertPositions.BUCKET_HIGH);
            }

            if(gamepad1.b){
                robot.arm.setTarget(RotatingArm.armPositions.STRAIGHT_UP);
                robot.vert.setTarget(VerticalLift.vertPositions.BUCKET_LOW);
            }

            if(gamepad1.x){
                robot.arm.setTarget(RotatingArm.armPositions.BUCKET);
            }

            if(gamepad1.a){
                robot.claw.toggleClaw();
            }

            if(gamepad1.left_bumper){
                robot.arm.setTarget(RotatingArm.armPositions.WALL);
            }

            if(gamepad1.right_bumper){
                robot.switchState(Robot.robotStates.Intaking);
            }

            telemetry.addLine("In Scoring/Driving Mode");
        }
        else if(state == Robot.robotStates.Transferring){
            //All controlls other than driving are locked.

        }
        else if(state == Robot.robotStates.Intaking){
            //Horizontal on dynamic control, intake intake and exhume
            //transfer button
            //vertical lift and arm are locked.


            //lt bring in
            // rt extend
            //left bumper returns to home then into transition
            //l3 outtake
            //r3 intake

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
