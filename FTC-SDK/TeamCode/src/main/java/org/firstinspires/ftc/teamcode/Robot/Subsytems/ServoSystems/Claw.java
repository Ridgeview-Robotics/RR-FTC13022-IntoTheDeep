package org.firstinspires.ftc.teamcode.Robot.Subsytems.ServoSystems;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Robot.Core.GlobalVars;
import org.firstinspires.ftc.teamcode.Robot.Core.Robot;
import org.firstinspires.ftc.teamcode.Utilities.Core.BasicServoRRX;

public class Claw {

    BasicServoRRX clawServo;
    BasicServoRRX rotatingClawServo;
    ElapsedTime timer;

    public ClawPositions clawPos = ClawPositions.CLOSED;

    public enum ClawPositions{
        OPEN(GlobalVars.c_open),
        CLOSED(GlobalVars.c_closed);

        private final double position;

        ClawPositions(final double newPos){
            position = newPos;
        }
    }

    public Claw(HardwareMap hardwareMap){
        clawServo = new BasicServoRRX(hardwareMap, "cs");
        rotatingClawServo = new BasicServoRRX(hardwareMap, "rc");
        timer = new ElapsedTime();
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

    public void toggleClaw(){
        if(timer.milliseconds() > 300){
            if(clawPos == ClawPositions.CLOSED){
                Robot.clawPos = ClawPositions.OPEN;
                clawServo.setPosition(GlobalVars.c_open);
            }
            else{
                Robot.clawPos = ClawPositions.CLOSED;
                clawServo.setPosition(GlobalVars.c_closed);
            }
        }
    }

}
