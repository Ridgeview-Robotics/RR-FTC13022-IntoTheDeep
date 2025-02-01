package org.firstinspires.ftc.teamcode.OpModes.Testing;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.Robot.Core.Robot;
import org.firstinspires.ftc.teamcode.Robot.Subsytems.Lift.HorizontalLift;
import org.firstinspires.ftc.teamcode.Robot.Subsytems.RotatingArm.RotatingArm;
import org.firstinspires.ftc.teamcode.Robot.Subsytems.ServoSystems.Claw;
import org.firstinspires.ftc.teamcode.Robot.Subsytems.ServoSystems.Intake;

@TeleOp(name = "Handoff")
public class HandoffTester extends OpMode {

    Intake intake;
    Claw claw;
    RotatingArm arm;
    HorizontalLift horiz;


    @Override
    public void init() {
        intake = new Intake(hardwareMap);
        claw = new Claw(hardwareMap);
        arm = new RotatingArm(hardwareMap);
        horiz = new HorizontalLift(hardwareMap);

        intake.setRotatingState(Intake.intakePositions.HOLDING);
        claw.setRotatingHorizontal();
        claw.clawPos = Claw.ClawPositions.OPEN;
        claw.setClawOpen();

        telemetry.addLine("Here we go");
        telemetry.update();
    }

    @Override
    public void loop() {

        if(gamepad1.a){
            intake.setRotatingState(Intake.intakePositions.TRANSFER);
        }

        if(gamepad1.dpad_left){
            horiz.setTarget(HorizontalLift.horizPositions.LIMIT);
        }

        if(gamepad1.b){
            claw.toggleClaw();
        }

        if(gamepad1.x){
            intake.setRotatingState(Intake.intakePositions.HOLDING);
            arm.setTarget(RotatingArm.armPositions.STRAIGHT_UP);
        }

        if(gamepad1.y){
            arm.setTarget(RotatingArm.armPositions.BUCKET);
        }

        if(gamepad1.dpad_down){
            arm.setTarget(RotatingArm.armPositions.HOLDING);
        }


    }
}
