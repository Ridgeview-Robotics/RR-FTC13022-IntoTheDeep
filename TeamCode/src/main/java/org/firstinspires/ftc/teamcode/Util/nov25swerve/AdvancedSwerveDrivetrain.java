package org.firstinspires.ftc.teamcode.Util.nov25swerve;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class AdvancedSwerveDrivetrain{
    public double wb = 14;
    public double tw = 14;

    public double FL_V;
    public double FL_A;

    public double FR_V;
    public double FR_A;

    public double BL_V;
    public double BL_A;

    public double BR_V;
    public double BR_A;

    private DcMotorEx FL;
    private DcMotorEx FR;
    private DcMotorEx BL;
    private DcMotorEx BR;

    public AdvancedSwerveDrivetrain(HardwareMap hardwareMap){
        FL = hardwareMap.get(DcMotorEx.class, "frontLeft");
        FR = hardwareMap.get(DcMotorEx.class, "frontRight");
        BL = hardwareMap.get(DcMotorEx.class, "backLeft");
        BR = hardwareMap.get(DcMotorEx.class, "backRight");


    }

    public void solve(double Lx, double Ly, double Rx){

        //translational vectors
        double vTranslate = Math.sqrt((Lx * Lx)+(Ly * Ly));
        double phiTranslate = Math.atan2(Ly, Lx);

        //Rot velo
        double omegaRot = Rx;

        // Calculate vectors for each wheel
        // Translation velocity contributions
        //abs statement
        double vTransX = vTranslate * Math.cos(phiTranslate);
        double vTransY = vTranslate * Math.sin(phiTranslate);

        //Offset Components
        double FL_x = -tw/2;
        double FL_y = wb/2;

        double FR_x = tw/2;
        double FR_y = wb/2;

        double BL_x = -tw/2;
        double BL_y = -wb/2;

        double BR_x = tw/2;
        double BR_y = -wb/2;

        // Rotational velocity contributions
        double FL_vRotX = -omegaRot * FL_y; // Perpendicular to radius
        double FL_vRotY = omegaRot * FL_x; // Tangential to radius

        double FR_vRotX = -omegaRot * FR_y; // Perpendicular to radius
        double FR_vRotY = omegaRot * FR_x; // Tangential to radius

        double BL_vRotX = -omegaRot * BL_y; // Perpendicular to radius
        double BL_vRotY = omegaRot * BL_x; // Tangential to radius

        double BR_vRotX = -omegaRot * BR_y; // Perpendicular to radius
        double BR_vRotY = omegaRot * BR_x; // Tangential to radius

        // Total velocity components
        double FL_vTotalX = vTransX + FL_vRotX;
        double FL_vTotalY = vTransY + FL_vRotY;

        double FR_vTotalX = vTransX + FR_vRotX;
        double FR_vTotalY = vTransY + FR_vRotY;

        double BL_vTotalX = vTransX + BL_vRotX;
        double BL_vTotalY = vTransY + BL_vRotY;

        double BR_vTotalX = vTransX + BR_vRotX;
        double BR_vTotalY = vTransY + BR_vRotY;

        // Calculate wheel angle and speed
        double FLAngle= Math.atan2(FL_vTotalY, FL_vTotalX);
        double FLSpeed= Math.sqrt((FL_vTotalX * FL_vTotalX) + (FL_vTotalY * FL_vTotalY));

        double FRAngle= Math.atan2(FR_vTotalY, FR_vTotalX);
        double FRSpeed= Math.sqrt((FR_vTotalX * FR_vTotalX) + (FR_vTotalY * FR_vTotalY));

        double BLAngle= Math.atan2(BL_vTotalY, BL_vTotalX);
        double BLSpeed= Math.sqrt((BL_vTotalX * BL_vTotalX) + (BL_vTotalY * BL_vTotalY));

        double BRAngle= Math.atan2(BR_vTotalY, BR_vTotalX);
        double BRSpeed= Math.sqrt((BR_vTotalX * BR_vTotalX) + (BR_vTotalY * BR_vTotalY));

        FL_A = FLAngle;
        FL_V = FLSpeed;

        FR_A = FRAngle;
        FR_V = FRSpeed;

        BL_A = BLAngle;
        BL_V = BLSpeed;

        BR_A = BRAngle;
        BR_V = BRSpeed;



    }
    
    public void assignMotorPower(){
        FL.setPower(FL_V);
        FR.setPower(FR_V);
        BL.setPower(BL_V);
        BR.setPower(BR_V);
    }


}
