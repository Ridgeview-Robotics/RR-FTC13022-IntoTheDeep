package org.firstinspires.ftc.teamcode.OpModes.Testing;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Robot.Subsytems.ServoSystems.Claw;
import org.firstinspires.ftc.teamcode.Robot.Subsytems.ServoSystems.Intake;

@TeleOp(name = "Intake Servo Programmer", group = "Testing")
public class ServoProgrammer extends OpMode {

    Intake intake;

    Claw claw;

    @Override
    public void init() {
        intake = new Intake(hardwareMap);
        claw = new Claw(hardwareMap);

        telemetry.addLine("Ready for Start!");
        telemetry.update();
    }

    @Override
    public void loop() {
        if(gamepad1.a){
            intake.setWheelPos(intake.getWheelPos() - 0.01);
        }

        if(gamepad1.y){
            intake.setWheelPos(intake.getWheelPos() + 0.01);
        }

        if(gamepad1.dpad_down){
            intake.setRotatingPos(intake.getRotatingPos() - 0.01);
        }

        if(gamepad1.dpad_up){
            intake.setRotatingPos(intake.getRotatingPos() + 0.01);
        }

        if(gamepad1.left_bumper){
            claw.setPosition(claw.getPosition() - 0.01);
        }

        if(gamepad1.right_bumper){
            claw.setPosition(claw.getPosition() + 0.01);
        }

        telemetry.addLine("Wheel: A and Y");
        telemetry.addLine("Rotating: Up and Down");
        telemetry.addLine("Claw: Bumpers R=Up");
        telemetry.addLine("Wheel Pos: " + intake.getWheelPos());
        telemetry.addLine("Rotating Pos: " + intake.getRotatingPos());
        telemetry.addLine("Claw Pos: " + claw.getPosition());
        telemetry.update();
    }
}
