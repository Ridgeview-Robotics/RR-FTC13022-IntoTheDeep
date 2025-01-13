package org.firstinspires.ftc.teamcode.Robot.Subsytems.ServoSystems;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.Robot.Core.GlobalVars;
import org.firstinspires.ftc.teamcode.Utilities.Core.BasicServoRRX;

public class Claw {

    BasicServoRRX clawServo;

    public Claw(HardwareMap hardwareMap){
        clawServo = new BasicServoRRX(hardwareMap, "claw");
    }

    public double getPosition(){
        return clawServo.getServoPosition();
    }

    public void setPosition(double pos){
        clawServo.setPosition(pos);
    }

    public void setClawOpen(){
        clawServo.setPosition(GlobalVars.c_open);
    }

    public void setClawClosed(){
        clawServo.setPosition(GlobalVars.c_closed);
    }

}
