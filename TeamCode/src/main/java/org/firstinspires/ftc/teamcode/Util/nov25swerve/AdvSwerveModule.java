package org.firstinspires.ftc.teamcode.Util.nov25swerve;

import com.qualcomm.robotcore.hardware.AnalogInput;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.Util.MotorRRX;

public class AdvSwerveModule {

    MotorRRX sMotor;
    SwerveServo mServo;

    double mNwb;  //negative wheelbase
    double mNtw; //negative trackwidth

    double x = mNtw*GlobalVars.tw/2;
    double y = mNwb*GlobalVars.wb/2;
    public static double tA;
    public static double tV;

    public AdvSwerveModule(HardwareMap hardwareMap, String motor, String servo, String enc, double negWB, double negTW){
        sMotor = new MotorRRX(hardwareMap, motor, 1.0);
        mServo = new SwerveServo(hardwareMap, servo, enc);

        mNwb = negWB;
        mNtw = negTW;
    }



    public void solve(double Lx, double Ly, double Rx){
        double vTranslate = Math.sqrt((Lx * Lx)+(Ly * Ly));
        double phiTranslate = Math.atan2(Ly, Lx);

        double vTransX = vTranslate * Math.cos(phiTranslate);
        double vTransY = vTranslate * Math.sin(phiTranslate);

        double vRotX = -Rx * y; // Perpendicular to radius
        double vRotY = Rx * x; // Tangential to radius

        double vTotalX = vTransX + vRotX;
        double vTotalY = vTransY + vRotY;

        double Angle= Math.toDegrees(Math.atan2(vTotalY, vTotalX));
        double Speed= Math.sqrt((vTotalX * vTotalX) + (vTotalY * vTotalY));

        tA = Angle;
        tV = Speed;
    }


    public void set(){
        sMotor.setPower(tV);
        mServo.setTargetAngle(tA);
    }

    public double getMotorPower(){
        return sMotor.getPower();
    }

    public double targetVelo(){
        return tV;
    }

    public double getTargetAngle(){
        return tA;
    }

    public double getCurrentAngle(){
        return mServo.volToDeg(mServo.getPosition());
    }

}
