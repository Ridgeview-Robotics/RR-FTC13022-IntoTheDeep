package org.firstinspires.ftc.teamcode.OpModes.Autonomous.Red;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Robot.Core.Robot;
import org.firstinspires.ftc.teamcode.trajectorysequence.TrajectorySequence;
import org.firstinspires.ftc.teamcode.trajectorysequence.TrajectorySequenceBuilder;

@Autonomous(name= "Red Tower")
public class r_double_h extends LinearOpMode {

    Robot robot;
    TrajectorySequence mTS;

    public void setUp(){
        TrajectorySequenceBuilder trajBuilder = robot.autoDrivetrain.trajectorySequenceBuilder(new Pose2d(-38.8, -62.8, Math.toRadians(90)));

        trajBuilder.lineTo(new Vector2d(-45, -62.8));


        mTS = trajBuilder.build();
    }


    @Override
    public void runOpMode() throws InterruptedException {

        telemetry.addLine("Ready");
        telemetry.update();


        waitForStart();
        if (isStopRequested()) return;

        //GAME START//

        setUp();
        robot.autoDrivetrain.setPoseEstimate(mTS.start());
        robot.autoDrivetrain.followTrajectorySequence(mTS);

    }
}
