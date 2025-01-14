package org.firstinspires.ftc.teamcode.Robot.Core;


import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.Robot.Subsytems.ServoSystems.Intake;

public class Robot {

    public MecanumDrivetrain drivetrain;

    public static Intake.intakeWheelPositions wheelPosition = Intake.intakeWheelPositions.EXHUME;


    public Robot(HardwareMap hardwareMap){
        drivetrain = new MecanumDrivetrain(hardwareMap);

    }



}
