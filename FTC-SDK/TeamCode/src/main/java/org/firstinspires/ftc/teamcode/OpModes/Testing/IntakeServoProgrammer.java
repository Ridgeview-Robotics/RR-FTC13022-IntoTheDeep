package org.firstinspires.ftc.teamcode.OpModes.Testing;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Robot.Core.GlobalVars;
import org.firstinspires.ftc.teamcode.Robot.Subsytems.ServoSystems.Claw;
import org.firstinspires.ftc.teamcode.Robot.Subsytems.ServoSystems.Intake;

@TeleOp(name = "Intake Servo Programmer", group = "Testing")
public class IntakeServoProgrammer extends OpMode {

    Intake intake;

    @Override
    public void init() {
        intake = new Intake(hardwareMap);

        intake.setRotatingPos(GlobalVars.i_holding);

        telemetry.addLine("Ready for Start!");
        telemetry.update();
    }

    @Override
    public void loop() {
        if(gamepad1.left_trigger > 0.0){
            intake.setWheelPower(-gamepad1.left_trigger);
        }
        else{
            intake.setWheelPower(0);
        }

        if(gamepad1.right_trigger > 0.0){
            intake.setWheelPower(gamepad1.right_trigger);
        }
        else{
            intake.setWheelPower(0);
        }

        if(gamepad1.dpad_down){
            intake.setRotatingPos(intake.getNotEncoderPosition() - 0.0001);
        }

        if(gamepad1.dpad_up){
            intake.setRotatingPos(intake.getNotEncoderPosition() + 0.0001);
        }

        telemetry.addLine("Wheel: A and Y");
        telemetry.addLine("Rotating: Up and Down");
        telemetry.addLine("Rotating Pos: " + intake.getRotatingPos());
        telemetry.addLine("NE: " + intake.getNotEncoderPosition());
        telemetry.update();
    }
}
