package org.firstinspires.ftc.teamcode.Utilities.Core;

import com.qualcomm.robotcore.hardware.AnalogInput;

public class AnalogEncoder {
    public static double standardRange = 3.3;
    public static boolean valueRejection = false;
    public final AnalogInput encoder;
    public double offset;
    public double analogRange;
    public boolean inverted;

    public AnalogEncoder(AnalogInput encInput, double range){
        encoder = encInput;
        analogRange = range;
        offset = 0;
        inverted = false;
    }
    public AnalogEncoder zero(double off){
        offset = off;
        return this;
    }
    public AnalogEncoder setInverted(boolean invert){
        inverted = invert;
        return this;
    }

    private double pastPosition = 1;
//    public double getCurrentPosition() {
//        double pos = Angle.norm((!inverted ? 1 - getVoltage() / analogRange : getVoltage() / analogRange) * Math.PI*2 - offset);
//        //checks for crazy values when the encoder is close to zero
//        if(!valueRejection || Math.abs(Angle.normDelta(pastPosition)) > 0.1 || Math.abs(Angle.normDelta(pos)) < 1) pastPosition = pos;
//        return pastPosition;
//    }

    public AnalogInput getEncoder() {
        return encoder;
    }


    public double getVoltage(){
        return encoder.getVoltage();
    }

}
