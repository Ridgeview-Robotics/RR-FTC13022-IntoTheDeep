package org.firstinspires.ftc.teamcode.Robot.Subsytems.ServoSystems;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Robot.Core.GlobalVars;
import org.firstinspires.ftc.teamcode.Robot.Core.Robot;
import org.firstinspires.ftc.teamcode.Utilities.Core.AnalogEncoder;
import org.firstinspires.ftc.teamcode.Utilities.Core.BasicServoRRX;
import org.firstinspires.ftc.teamcode.Utilities.Core.ContServoRRX;
import org.firstinspires.ftc.teamcode.Utilities.Core.EncodedServoRRX;

public class Intake {

    ContServoRRX intakeWheel;
    EncodedServoRRX rotatingServo;
    ElapsedTime timer;

    private intakeWheelPositions desiredPosition = intakeWheelPositions.EXHUME;
    private intakePositions rotatePosition = intakePositions.EXTRACT;


    public double rot_low_bound = 0.05;

    public enum intakePositions{
        EXTRACT(GlobalVars.i_extraction),
        HOLDING(GlobalVars.i_holding),
        TRANSFER(GlobalVars.i_transfer);

        private final double position;

        intakePositions(final double newPosition){
            position = newPosition;
        }

        public double getPosition(){
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
        intakeWheel = new ContServoRRX(hardwareMap, "iws");
        rotatingServo = new EncodedServoRRX(hardwareMap, "ris", "rie");

        timer = new ElapsedTime();
    }

    public double getRotatingPos(){
        return rotatingServo.getVoltage();
    }

    public double getNotEncoderPosition(){
        return rotatingServo.getNEPower();
    }


    public void setWheelPower(double pos){
        intakeWheel.setPower(pos);
    }

    public void setRotatingPos(double pos){
        rotatingServo.setPosition(pos);
    }

    public void setRotatingState(intakePositions position){
        setRotatingPos(position.getPosition());
    }

    public void toggleCapture(){
        if(timer.milliseconds() > 500){
            if(desiredPosition == intakeWheelPositions.EXHUME){
                Robot.wheelPos = intakeWheelPositions.CAPTURE;
            }
            else if(desiredPosition == intakeWheelPositions.CAPTURE){
                Robot.wheelPos = intakeWheelPositions.EXHUME;
            }
        }
    }

    //TODO: Work with analog encoder on rotating servo to detect

}
