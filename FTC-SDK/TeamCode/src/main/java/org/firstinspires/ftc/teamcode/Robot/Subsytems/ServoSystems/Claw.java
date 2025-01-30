package org.firstinspires.ftc.teamcode.Robot.Subsytems.ServoSystems;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.Robot.Core.GlobalVars;
import org.firstinspires.ftc.teamcode.Utilities.Core.BasicServoRRX;

public class Claw {

    BasicServoRRX clawServo;
    BasicServoRRX rotatingClawServo;

    public Claw(HardwareMap hardwareMap){
        clawServo = new BasicServoRRX(hardwareMap, "cs");
        rotatingClawServo = new BasicServoRRX(hardwareMap, "rc");
    }

    public double getClawPosition(){
        return clawServo.getServoPosition();
    }

    public void setClawPosition(double pos){
        clawServo.setPosition(pos);
    }

    public void setClawOpen(){
        clawServo.setPosition(GlobalVars.c_open);
    }

    public void setClawClosed(){
        clawServo.setPosition(GlobalVars.c_closed);
    }


    public void setRotatingPosition(double pos){
        rotatingClawServo.setPosition(pos);
    }

    public double getRotatingPosition(){
        return clawServo.getServoPosition();
    }

    public void setRotatingVertical(){
        rotatingClawServo.setPosition(GlobalVars.c_vertical);
    }

    public void setRotatingHorizontal(){
        rotatingClawServo.setPosition(GlobalVars.c_horizontal);
    }

}
