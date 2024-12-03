package org.firstinspires.ftc.teamcode.Util.nov25swerve;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.Util.MotorRRX;

public class AdvSwerveModule {

    MotorRRX sMotor;
    SwerveServo mServo;

    double mNwb;  //negative wheelbase
    double mNtw; //negative trackwidth

    double x;
    double y;

    public static double tA;
    public static double tV;

    public AdvSwerveModule(HardwareMap hardwareMap, String motor, String servo, String enc, double negWB, double negTW){
        sMotor = new MotorRRX(hardwareMap, motor, 1.0);
        mServo = new SwerveServo(hardwareMap, servo, enc);

        mNwb = negWB; //these variables will come out as either 1 or -1.  This is just a refrencer.
        mNtw = negTW;

        x = mNtw*GlobalVars.tw/2;
        y = mNwb*GlobalVars.wb/2;
    }

    public void solve(double Lx, double Ly, double Rx){
        double vRotX = -Rx * y; // Perpendicular to radius
        double vRotY = Rx * x; // Tangential to radius

        double vTotalX = Lx + vRotX;
        double vTotalY = Ly + vRotY;

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
