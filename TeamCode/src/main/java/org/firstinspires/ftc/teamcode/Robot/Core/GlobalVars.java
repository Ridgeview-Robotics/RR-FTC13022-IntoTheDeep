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

    //LIFT

    public static final double vl_power = 0.1;
    public static final int vl_down = 0;
    public static final int vl_bucket_high = 1100;
    public static final int vl_bucket_low = 900;
    public static final int vl_wall = 600;
    public static final int vl_bar_low = 1300;
    public static final int vl_bar_high = 1000;


    public static final double hl_power = 0.1;
    public static final int hl_retracted = 0;
    public static final int hl_extended = 1000;


    public static final double ra_power = 0.1;
    public static final int ra_down = 300;
    public static final int ra_high = 900;
    public static final int ra_backwards = 1400;

    //SERVOS

    public static final double c_closed = 0.0;
    public static final double c_open = 1.0;


    public static final double i_extraction = 0.0;
    public static final double i_transfer = 1.0;

    public static final double i_captured = 1.0;
    public static final double i_exhume = 0.0;

}
