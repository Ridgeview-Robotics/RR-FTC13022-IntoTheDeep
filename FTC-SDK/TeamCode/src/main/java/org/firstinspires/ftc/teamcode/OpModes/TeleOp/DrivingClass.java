package org.firstinspires.ftc.teamcode.OpModes.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.robot.RobotState;

import org.firstinspires.ftc.teamcode.Robot.Core.Robot;
import org.firstinspires.ftc.teamcode.Robot.Subsytems.Lift.HorizontalLift;
import org.firstinspires.ftc.teamcode.Robot.Subsytems.Lift.VerticalLift;
import org.firstinspires.ftc.teamcode.Robot.Subsytems.RotatingArm.RotatingArm;
import org.firstinspires.ftc.teamcode.Robot.Subsytems.ServoSystems.Intake;


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

        if(gamepad1.ps){
            robot.horiz.setTarget(HorizontalLift.horizPositions.LIMIT);
        }

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
                robot.setArmDownWithClkr(); //ckr barely works but thats a later type thing
                robot.switchState(Robot.robotStates.Transferring);
            }

            if(gamepad1.dpad_right){
                robot.intake.setRotatingState(Intake.intakePositions.TRANSFER);
            }

//            if(gamepad1.dpad_up){
//                robot.arm.setTarget(RotatingArm.armPositions.SUBMERSIBLE);
//                robot.vert.setTarget(VerticalLift.vertPositions.BAR_HIGH);
//                robot.intake.setRotatingState(Intake.intakePositions.HOLDING);
//                robot.claw.setRotatingVertical();
//            }

            if(gamepad1.y){
                robot.arm.setTarget(RotatingArm.armPositions.STRAIGHT_UP);
                robot.vert.setTarget(VerticalLift.vertPositions.BUCKET_HIGH);
                robot.intake.setRotatingState(Intake.intakePositions.HOLDING);
                robot.claw.setRotatingVertical();
            }

            if(gamepad1.b){
                robot.arm.setTarget(RotatingArm.armPositions.STRAIGHT_UP);
                robot.vert.setTarget(VerticalLift.vertPositions.BUCKET_LOW);
                robot.intake.setRotatingState(Intake.intakePositions.HOLDING);
                robot.claw.setRotatingVertical();
            }

//            if(gamepad1.dpad_left){
//                robot.claw.setRotatingVertical();
//                robot.arm.setTarget(RotatingArm.armPositions.WALL);
//            }

            if(gamepad1.x){
                robot.arm.setTarget(RotatingArm.armPositions.BUCKET);
            }

            if(gamepad1.a){
                robot.claw.toggleClaw();
            }

            telemetry.addLine("In Scoring/Driving Mode");

            //Horizontal on dynamic control, intake intake and exhume
            //transfer button
            //vertical lift and arm are locked.


            //lt bring in
            // rt extend
            //left bumper returns to home then into transition
            //l3 outtake
            //r3 intake
            //button for set intake down: rb
            if(gamepad1.left_trigger > 0.0){
                robot.horiz.motor.setMotorBehavior(DcMotor.RunMode.RUN_USING_ENCODER);
                robot.horiz.setPower(-gamepad1.left_trigger);
            }
            else if(gamepad1.right_trigger > 0.0){
                robot.horiz.motor.setMotorBehavior(DcMotor.RunMode.RUN_USING_ENCODER);
                robot.horiz.setPower(gamepad1.right_trigger);
            }
            else{
                robot.horiz.setPower(0);
            }




            if(gamepad1.left_bumper){
                robot.horiz.motor.setTargetPos(robot.horiz.getPos());
                robot.horiz.motor.setMotorBehavior(DcMotor.RunMode.RUN_TO_POSITION);
                robot.horiz.setPower(1.0);
                robot.horiz.setTarget(HorizontalLift.horizPositions.RETRACTED);
                robot.intake.setRotatingState(Intake.intakePositions.HOLDING);
                robot.claw.setRotatingHorizontal();
            }

            if(gamepad1.right_stick_button){
                robot.intake.setRotatingState(Intake.intakePositions.EXTRACT);
            }

            if(gamepad1.left_stick_button){
                robot.intake.setWheelPower(-1);
            }
            else if(gamepad1.right_bumper){
                robot.intake.setWheelPower(1);
            }
            else{
                robot.intake.setWheelPower(0);
            }


        telemetry.addData("State", state.getName());
        telemetry.addData("Rotation Ckr", robot.rotClear);
        telemetry.addData("Arm Ckr", robot.armClear);
        telemetry.addData("LT:", gamepad1.left_trigger);
        telemetry.update();
    }
}
