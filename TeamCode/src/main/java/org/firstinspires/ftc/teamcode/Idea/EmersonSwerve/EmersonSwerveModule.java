package org.firstinspires.ftc.teamcode.Idea.EmersonSwerve;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.Util.MotorRRX;

public class EmersonSwerveModule {

//    ContServoRRX swerveServo;
    MotorRRX swerveMotor;
    Servo swerveServo;
    double swerveServoMin;
    double swerveServoMax;

    public EmersonSwerveModule(HardwareMap hardwareMap){
        swerveServo = hardwareMap.get(Servo.class,"swerveServo");
        swerveMotor = new MotorRRX(hardwareMap, "swerveMotor", 1.0);
    }

//    public void setSwerveServoDirection(CRServo.Direction direction){
//        swerveServo.setDirection(direction);
//    }

    public void setSwerveMotorPower(double power){
        swerveMotor.setPower(power);
    }

//    public void setSwerveServoPower(double power){
//        swerveServo.setPower(power);
//    }

    public void setSwerveServoAngle(double theta) {
        double angle = (swerveServoMax - swerveServoMin) / (theta - swerveServoMin);
        swerveServo.setPosition(angle );
    }

}
