package org.firstinspires.ftc.teamcode.Robot.Subsytems.ServoSystems;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Robot.Core.GlobalVars;
import org.firstinspires.ftc.teamcode.Robot.Core.Robot;
import org.firstinspires.ftc.teamcode.Utilities.Core.BasicServoRRX;

public class Intake {

    BasicServoRRX intakeWheel;
    BasicServoRRX rotatingServo;
    ElapsedTime timer;

    private intakeWheelPositions desiredPosition = intakeWheelPositions.EXHUME;

    public enum intakePositions{
        EXTRACT(GlobalVars.i_extraction),
        TRANSFER(GlobalVars.i_transfer);

        private final double position;

        intakePositions(final double newPosition){
            position = newPosition;
        }

        private double getPosition(){
            return position;
        }
    }

    public enum intakeWheelPositions{
        CAPTURE(GlobalVars.i_captured),
        EXHUME(GlobalVars.i_exhume);

        private final double nPosition;

        intakeWheelPositions(final double newPosition){
            nPosition = newPosition;
        }

        private double getWheelPosition(){
            return nPosition;
        }
    }

    public Intake(HardwareMap hardwareMap){
        intakeWheel = new BasicServoRRX(hardwareMap, "wheel");
        rotatingServo = new BasicServoRRX(hardwareMap, "rotating");

        timer = new ElapsedTime();
    }

    public double getWheelPos(){
        return intakeWheel.getServoPosition();
    }

    public double getRotatingPos(){
        return rotatingServo.getServoPosition();
    }

    public void setWheelPos(double pos){
        intakeWheel.setPosition(pos);
    }

    public void setRotatingPos(double pos){
        rotatingServo.setPosition(pos);
    }

    public void SM_SetPosition(intakeWheelPositions position){
        timer.reset();
        desiredPosition = position;
        intakeWheel.setPosition(position.getWheelPosition());
    }

    public void toggleCapture(){
        if(timer.milliseconds() > 500){
            if(desiredPosition == intakeWheelPositions.EXHUME){
                Robot.wheelPosition = intakeWheelPositions.CAPTURE;
                SM_SetPosition(intakeWheelPositions.CAPTURE);
            }
            else if(desiredPosition == intakeWheelPositions.CAPTURE){
                Robot.wheelPosition = intakeWheelPositions.EXHUME;
                SM_SetPosition(intakeWheelPositions.EXHUME);
            }
        }
    }

    //TODO: Work with analog encoder on rotating servo to detect

}
