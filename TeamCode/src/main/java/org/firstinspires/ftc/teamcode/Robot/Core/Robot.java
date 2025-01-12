package org.firstinspires.ftc.teamcode.Robot.Core;


import com.qualcomm.robotcore.hardware.HardwareMap;

public class Robot {

    MecanumDrivetrain drivetrain;


    public Robot(HardwareMap hardwareMap){
        drivetrain = new MecanumDrivetrain(hardwareMap);

    }



}
