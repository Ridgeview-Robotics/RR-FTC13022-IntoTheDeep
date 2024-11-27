package org.firstinspires.ftc.teamcode.Util.nov25swerve;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class AdvancedSwerveDrivetrain{

    private AdvSwerveModule FL;
    private AdvSwerveModule FR;
    private AdvSwerveModule BL;
    private AdvSwerveModule BR;

    public AdvancedSwerveDrivetrain(HardwareMap hardwareMap){
        FL = new AdvSwerveModule(hardwareMap, "FLM", "FLS", "FLE", 1, -1);
        FR = new AdvSwerveModule(hardwareMap, "FRM", "FRS", "FRE", 1, 1);
        BL = new AdvSwerveModule(hardwareMap, "BLM", "BLS", "BLE", -1, -1);
        BR = new AdvSwerveModule(hardwareMap, "BRM", "BRS", "BRE", -1, 1);

    }

    public void updateAll(double iLX, double iLY, double iRX){
        FL.solve(iLX, iLY, iRX);
        FR.solve(iLX, iLY, iRX);
        BL.solve(iLX, iLY, iRX);
        BR.solve(iLX, iLY, iRX);
    }

    public void assignMotorPower(){
        FL.set();
        FR.set();
        BL.set();
        BR.set();
    }

    public double[] currentVelocities(){
        return new double[]{FL.getMotorPower(), FL.getMotorPower(), FL.getMotorPower(), FL.getMotorPower()};
    }

    public double[] targetVelocities(){
        return new double[]{FL.targetVelo(), FL.targetVelo(), FL.targetVelo(), FL.targetVelo()};
    }


    public double[] targetAngles(){
        return new double[]{FL.getTargetAngle(), FL.getTargetAngle(), FL.getTargetAngle(), FL.getTargetAngle()};
    }


    public double[] currentAngles(){
        return new double[]{FL.getCurrentAngle(), FL.getCurrentAngle(), FL.getCurrentAngle(), FL.getCurrentAngle()};
    }


}
