package org.firstinspires.ftc.teamcode.Drive.SwerveKit;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.ftc.DownsampledWriter;
import com.acmerobotics.roadrunner.ftc.LynxFirmware;
import com.qualcomm.hardware.lynx.LynxModule;
import com.qualcomm.robotcore.hardware.HardwareMap;

@Config
public final class MecanumDrive {

    //Parameters Section
    // drive model parameters
    public double inPerTick = 1;
    public double lateralInPerTick = inPerTick;
    public double trackWidthTicks = 0;

    // feedforward parameters (in tick units)
    public double kS = 0;
    public double kV = 0;
    public double kA = 0;

    // path profile parameters (in inches)
    public double maxWheelVel = 50;
    public double minProfileAccel = -30;
    public double maxProfileAccel = 50;

    // turn profile parameters (in radians)
    public double maxAngVel = Math.PI; // shared with path
    public double maxAngAccel = Math.PI;

    // path controller gains
    public double axialGain = 0.0;
    public double lateralGain = 0.0;
    public double headingGain = 0.0; // shared with turn

    public double axialVelGain = 0.0;
    public double lateralVelGain = 0.0;
    public double headingVelGain = 0.0; // shared with turn
    //end Parameters

    //SwerveKinematics Class
    //Velocity and acceleration constraint classes

//    public final AdvancedSwerveDrivetrain drivetrain;
//
//    public final VoltageSensor voltageSensor;
//
//    public final Localizer localizer;

    public Pose2d pose;

    private final DownsampledWriter estimatedPoseWriter = new DownsampledWriter("ESTIMATED_POSE", 50_000_000);
    private final DownsampledWriter targetPoseWriter = new DownsampledWriter("TARGET_POSE", 50_000_000);
    private final DownsampledWriter driveCommandWriter = new DownsampledWriter("DRIVE_COMMAND", 50_000_000);
    private final DownsampledWriter mecanumCommandWriter = new DownsampledWriter("MECANUM_COMMAND", 50_000_000);

    public MecanumDrive(HardwareMap hardwareMap, Pose2d pose){
        this.pose = pose;

        LynxFirmware.throwIfModulesAreOutdated(hardwareMap);

        for(LynxModule module : hardwareMap.getAll(LynxModule.class)) {
            module.setBulkCachingMode(LynxModule.BulkCachingMode.AUTO);
        }



    }

}