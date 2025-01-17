package org.firstinspires.ftc.teamcode.Utilities.SHS.TwinServoFourBar;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class FourBar {

    private final Servo leftServo;
    private final Servo rightServo;

    private final double POSITION_1_LEFT = 0.0;
    private final double POSITION_1_RIGHT = 1.0;

    private final double POSITION_2_LEFT = 0.5;
    private final double POSITION_2_RIGHT = 0.5;

    //ETC ETC, you should fill in these values, use the servo position finder in this folder to get these values.

    public FourBar(HardwareMap hardwareMap){
        leftServo = hardwareMap.get(Servo.class, "leftServo");
        rightServo = hardwareMap.get(Servo.class, "rightServo");

        //here you could do something like:
        //setPosition1();
        //I have it commented out as it will 100% break your servo, but you get the jist
        //once you have the positions programmed, you'll get it
    }

    public void setPosition1(){
        leftServo.setPosition(POSITION_1_LEFT);
        rightServo.setPosition(POSITION_1_RIGHT);
    }

    public void setPosition2(){
        leftServo.setPosition(POSITION_2_LEFT);
        rightServo.setPosition(POSITION_2_RIGHT);
    }

    //this could require some kind of encoder to fiddle with the stuff, but i doubt it
    //this could also all required being written a little differently if you end up having any issues with the

    public void setLeftServo(double pos){
        leftServo.setPosition(pos);
    }

    public void setRightServo(double pos){
        rightServo.setPosition(pos);
    }

    public double getLeftServo(){
        return leftServo.getPosition();
    }

    public double getRightServo(){
        return rightServo.getPosition();
    }

}
