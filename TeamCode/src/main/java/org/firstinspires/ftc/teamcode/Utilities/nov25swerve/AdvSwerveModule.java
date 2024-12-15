package org.firstinspires.ftc.teamcode.Utilities.nov25swerve;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.Utilities.MotorRRX;

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

        sMotor.setMotorBehavior(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        mNwb = negWB; //these variables will come out as either 1 or -1.  This is just a refrencer.
        mNtw = negTW;

        x = mNtw*GlobalVars.tw/2;
        y = mNwb*GlobalVars.wb/2;
    }

    public void solve(double Lx, double Ly, double Rx){
        sMotor.setForward();
        double vRotX = -Rx * y; // Perpendicular to radius
        double vRotY = Rx * x; // Tangential to radius

        double vTotalX = Lx + vRotX;
        double vTotalY = Ly + vRotY;

        double Angle= Math.toDegrees(Math.atan2(vTotalY, vTotalX) - Math.PI/2);
        double Speed= Math.sqrt((vTotalX * vTotalX) + (vTotalY * vTotalY));

        if(Ly > 0){
            sMotor.setReverse();
        }

        tA = Angle;
        tV = Speed;

        sMotor.setPower(tV);
        mServo.setPower(mServo.setTargetAngle(tA));
    }
    //Servo power multiplier?

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
        return mServo.getPosition();
    }

    public double[] getPIDS(){
        return mServo.getPIDS();
    }

    public double getVoltage(){
        return mServo.getVoltage();
    }

    public void setServoTarget(double target){
        mServo.setTargetAngle(target);
    }

    public double returnError(){
        return mServo.getError();
    }

}
