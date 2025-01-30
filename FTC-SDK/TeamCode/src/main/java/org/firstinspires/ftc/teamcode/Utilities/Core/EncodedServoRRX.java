package org.firstinspires.ftc.teamcode.Utilities.Core;

import com.qualcomm.robotcore.hardware.AnalogInput;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class EncodedServoRRX {

    BasicServoRRX servo;
    AnalogEncoder enc;

    public EncodedServoRRX(HardwareMap hardwareMap, String servoName, String encName){
        servo = new BasicServoRRX(hardwareMap, servoName);
        enc = new AnalogEncoder(hardwareMap.get(AnalogInput.class, encName), 3.3); //axon standard a/e vol range
    }

    public double getVoltage(){
        return enc.getVoltage();
    }

    public void setPosition(double pos){
        servo.setPosition(pos);
    }

    public void setDirection(Servo.Direction direction){
        servo.setDirection(direction);
    } //To change the direction of the servo if necessary

    public void setServoRange(double min, double max){
        servo.setServoRange(min, max);
    }//limits servo range from 0.0 to min, and down from 1.0 to max.
    //if you were to set the range to 0.4 to 0.6, the tick from the servo would have 0.0 = 0.4 and 1.0 = 0.6.

}
