package org.firstinspires.ftc.teamcode.Idea;

import android.util.Log;

import com.qualcomm.robotcore.util.ElapsedTime;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class AutoPrepLogger {

    public File logFile;
    public String mLogFileName;
    public ElapsedTime mTimer;

    public AutoPrepLogger(String logFileName) {
        logFile = new File(logFileName);
        mLogFileName = logFileName;
    }

    public void createNewLogFile() throws IOException {
        try {
            logFile.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public double timerSeconds(){
        return mTimer.seconds();
    }

    public void resetTimer(){
        mTimer.reset();
    }

    public void beginLogging(ElapsedTime timer){
        timer = new ElapsedTime();
        mTimer = timer;
    }

    public void log(String message) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(logFile, true))) {
            writer.write(message);
            writer.newLine();
            writer.write(timerSeconds() + " seconds elapsed");
        } catch (IOException e) {
            Log.e(mLogFileName, "Error writing to log file", e);
        }
    }



}
