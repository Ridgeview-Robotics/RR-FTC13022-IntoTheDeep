package org.firstinspires.ftc.teamcode.OpModes.Autonomous.Blue;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Robot.Core.Robot;
import org.firstinspires.ftc.teamcode.trajectorysequence.TrajectorySequenceBuilder;

public class b_double_t extends LinearOpMode {

    public Robot mRobot;


    private void setupSequence(){
        TrajectorySequenceBuilder builder = mRobot.autoDrivetrain.trajectorySequenceBuilder(new Pose2d(0, 0, Math.toRadians(0)));


    }



    @Override
    public void runOpMode() throws InterruptedException {

    }
}
