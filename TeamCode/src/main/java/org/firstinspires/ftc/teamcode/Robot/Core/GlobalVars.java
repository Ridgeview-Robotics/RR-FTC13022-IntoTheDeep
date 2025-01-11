package org.firstinspires.ftc.teamcode.Robot.Core;

import com.acmerobotics.dashboard.config.Config;

@Config
public class GlobalVars {

    public static double tw = 14;
    public static double wb = 14;

    public static double a_kP = 0.00;
    public static double a_kI = 0.00;
    public static double a_kD = 0.00;
    public static double a_kF = 0.00;

    public enum Side {
        LEFT,
        RIGHT
    }

    public static boolean AUTO = false;
    public static boolean USING_IMU = true;
    public static boolean MANUAL_ENABLED = true;
    public static boolean HAS_AUTO_ERROR = false;
    public static boolean IS_PARKING = false;
    public static boolean USE_WHEEL_FEEDFORWARD = false;

}
