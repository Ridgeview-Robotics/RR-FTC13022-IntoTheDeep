package org.firstinspires.ftc.teamcode.OpModes.Testing;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Robot.Subsytems.ServoSystems.Claw;
import org.firstinspires.ftc.teamcode.Robot.Subsytems.ServoSystems.Intake;

@TeleOp(name = "Arm Servo Programmer", group = "Testing")
public class ArmServoProgrammer extends OpMode {

    Claw claw;

    @Override
    public void init() {
        claw = new Claw(hardwareMap);

        telemetry.addLine("Ready for Start!");
        telemetry.update();
    }

    @Override
    public void loop() {
        if(gamepad1.a){
            claw.setClawPosition(claw.getClawPosition() - 0.0001);
        }

        if(gamepad1.y){
            claw.setClawPosition(claw.getClawPosition() + 0.0001);
        }

        if(gamepad1.dpad_down){
            claw.setRotatingPosition(claw.getRotatingPosition() - 0.0001);
        }

        if(gamepad1.dpad_up){
            claw.setRotatingPosition(claw.getRotatingPosition() + 0.0001);
        }

        telemetry.addLine("Claw: A and Y");
        telemetry.addLine("Rotating: Up and Down");
        telemetry.addLine("Claw Pos: " + claw.getClawPosition());
        telemetry.addLine("Rotating Pos: " + claw.getRotatingPosition());
        telemetry.update();
    }

}
