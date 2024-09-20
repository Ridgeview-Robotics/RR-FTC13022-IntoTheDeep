package org.firstinspires.ftc.teamcode.Util;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class ContServoRRX {

    CRServo rcsServo;

    public ContServoRRX(HardwareMap hardwareMap, String servoName){
        rcsServo = hardwareMap.get(CRServo.class, servoName);
    }

    public void setDirection(CRServo.Direction direction){
        rcsServo.setDirection(direction);
    }

    public void setPower(double power){
        rcsServo.setPower(power);
    }

}
