package org.firstinspires.ftc.teamcode.Util.nov25swerve;

import com.qualcomm.robotcore.hardware.HardwareMap;

public class AdvancedSwerveDrivetrain{

    public final AdvSwerveModule FL;
    public final AdvSwerveModule FR;
    public final AdvSwerveModule BL;
    public final AdvSwerveModule BR;

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

    public double[] currentVelocities(){
        return new double[]{FL.getMotorPower(), FR.getMotorPower(), BL.getMotorPower(), BR.getMotorPower()};
    }

    public double[] targetVelocities(){
        return new double[]{FL.targetVelo(), FR.targetVelo(), BL.targetVelo(), BR.targetVelo()};
    }

    public double[] targetAngles(){
        return new double[]{FL.getTargetAngle(), FR.getTargetAngle(), BL.getTargetAngle(), BR.getTargetAngle()};
    }

    public double[] currentAngles(){
        return new double[]{FL.getCurrentAngle(), FR.getCurrentAngle(), BL.getCurrentAngle(), BR.getCurrentAngle()};
    }

    public double[] currentVoltages(){
        return new double[]{FL.getVoltage(), FR.getVoltage(), BL.getVoltage(), BR.getVoltage()};
    }

    public double[] getFL_PID(){
        return FL.getPIDS();
    }

    public double[] getFR_PID(){
        return FR.getPIDS();
    }

    public double[] getBL_PID(){
        return BL.getPIDS();
    }

    public double[] getBR_PID(){
        return BR.getPIDS();
    }

    public void setFLTarg(double t){
        FL.setServoTarget(t);
    }
    public void setFRTarg(double t){
        FR.setServoTarget(t);
    }
    public void setBLTarg(double t){
        BL.setServoTarget(t);
    }
    public void setBRTarg(double t){
        BR.setServoTarget(t);
    }

}
