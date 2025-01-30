package org.firstinspires.ftc.teamcode.Robot.Subsytems.ServoSystems;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Robot.Core.GlobalVars;
import org.firstinspires.ftc.teamcode.Robot.Core.Robot;
import org.firstinspires.ftc.teamcode.Utilities.Core.AnalogEncoder;
import org.firstinspires.ftc.teamcode.Utilities.Core.BasicServoRRX;
import org.firstinspires.ftc.teamcode.Utilities.Core.EncodedServoRRX;

public class Intake {

    BasicServoRRX intakeWheel;
    EncodedServoRRX rotatingServo;
    ElapsedTime timer;

    private intakeWheelPositions desiredPosition = intakeWheelPositions.EXHUME;
    private intakePositions rotatePosition = intakePositions.EXTRACT;

    public enum intakePositions{
        EXTRACT(GlobalVars.i_extraction),
        HOLDING(GlobalVars.i_holding),
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
        intakeWheel = new BasicServoRRX(hardwareMap, "iws");
        rotatingServo = new EncodedServoRRX(hardwareMap, "ris", "rie");

        timer = new ElapsedTime();
    }

    public double getWheelPos(){
        return intakeWheel.getServoPosition();
    }

    public double getRotatingPos(){
        return rotatingServo.getVoltage();
    }

    public void setWheelPos(double pos){
        intakeWheel.setPosition(pos);
    }

    public void setRotatingPos(double pos){
        rotatingServo.setPosition(pos);
    }

    public void setRotatingState(intakePositions position){

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
