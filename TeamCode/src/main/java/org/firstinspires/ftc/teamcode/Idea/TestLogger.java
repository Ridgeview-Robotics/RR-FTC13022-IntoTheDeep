package org.firstinspires.ftc.teamcode.Idea;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;

public class TestLogger extends OpMode {

    AutoPrepLogger autoPrepLogger;

    @Override
    public void init() {
        autoPrepLogger = new AutoPrepLogger("latestLog.txt");

        autoPrepLogger.resetTimer();
        telemetry.addLine("Ready for Start!");
        telemetry.update();
    }

    @Override
    public void loop() {
        if(gamepad1.a){
            autoPrepLogger.log("Log Pose Beginning");
        }

        if(gamepad1.b){
            autoPrepLogger.log("Log Pose End");
        }

        if(gamepad1.x){
            autoPrepLogger.log("Log Non-Driving System Event");
        }


        telemetry.addLine("Running");
        telemetry.update();
    }
}
