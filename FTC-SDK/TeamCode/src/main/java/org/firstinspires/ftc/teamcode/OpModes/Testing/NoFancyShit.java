package org.firstinspires.ftc.teamcode.OpModes.Testing;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@TeleOp(name = "NoFancyShit")
public class NoFancyShit extends OpMode {


    DcMotorEx motor;

    final int pos1 = 50;
    final int pos2 = 200;


    @Override
    public void init() {
        motor = hardwareMap.get(DcMotorEx.class, "rarmm");

        motor.setDirection(DcMotorSimple.Direction.REVERSE);

        motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motor.setTargetPosition(motor.getCurrentPosition());
        motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

//        motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        motor.setPower(0.5);
    }

    @Override
    public void loop() {
        if(gamepad1.a){
            motor.setTargetPosition(pos1);
        }

        if(gamepad1.b){
            motor.setTargetPosition(pos2);
        }

        motor.setPower(gamepad1.left_stick_x);



    }
}
