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

    public static double vl_power = 0.1;
    public static int vl_down = 0;
    public static int vl_bucket_high = 1100;
    public static int vl_bucket_low = 900;
    public static int vl_wall = 600;
    public static int vl_bar_low = 1300;
    public static int vl_bar_high = 1000;


    public static double hl_power = 0.1;
    public static int hl_retracted = 0;
    public static int hl_limit = 1000;


    //SERVOS

    public static double c_closed = 0.0;
    public static double c_open = 1.0;


    public static double i_extraction = 0.0;
    public static double i_transfer = 1.0;

    public static double i_captured = 1.0;
    public static double i_exhume = 0.0;


    //ARM
    public static double arm_p = 0.0;
    public static double arm_i = 0.0;
    public static double arm_d = 0.0;
    public static  double arm_f = 0.0;

    public static double arm_power = 0.1;

    public static int arm_down = 0;
    public static int arm_submersible_low = 100;
    public static int arm_submersible_high = 400;
    public static int arm_wall = 200;
    public static int arm_bucket = 900;

    //LIFT PIDS
    public static double l_KP = 0.01; // Proportional gain for PID
    public static double l_KI = 0.001; // Integral gain for PID
    public static double l_KD = 0.005; // Derivative gain for PID

    //rotating claw servo positions
    public static double c_vertical = 0.0;
    public static double c_horizontal = 1.0;

}
