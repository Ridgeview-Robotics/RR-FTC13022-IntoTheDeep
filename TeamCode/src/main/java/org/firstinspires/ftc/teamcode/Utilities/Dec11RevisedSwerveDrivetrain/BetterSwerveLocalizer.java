package org.firstinspires.ftc.teamcode.Utilities.Dec11RevisedSwerveDrivetrain;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.acmerobotics.roadrunner.Pose2d;

import java.util.function.DoubleSupplier;

public class BetterSwerveLocalizer {
    //public SwerveModule.SwerveModuleState[] modules;
    public DoubleSupplier imu;
    public Pose2d poseEstimate;
    public Pose2d pastPoseEstimate;
    public BetterSwerveLocalizer(DoubleSupplier i){
        /*modules = Arrays.stream(mods).map(SwerveModule::asState).toArray(SwerveModule.SwerveModuleState[]::new);//
        TODO: see below, that method needs to be added to the 3 dead wheel localizer
        imu = i;
        poseEstimate = new Pose2d();
        pastPoseEstimate = new Pose2d();*/
    }
    @NonNull

    public Pose2d getPoseEstimate() {
        return poseEstimate;
    }


    public void setPoseEstimate(@NonNull Pose2d pose2d) {
        poseEstimate = pose2d;
    }

    @Nullable

    public Pose2d getPoseVelocity() {
        return Pose2d.exp(poseEstimate.minus(pastPoseEstimate));
    }

    /*@Override
    public void update() {
        pastPoseEstimate = poseEstimate;
        Vector2d accumulator = new Vector2d();
        double head = imu.getAsDouble();
        for(SwerveModule.SwerveModuleState s : modules){
            accumulator = accumulator.plus(s.calculateDelta());
        }
        accumulator = accumulator.div(modules.length).rotated(head);
        poseEstimate = new Pose2d(poseEstimate.vec().plus(accumulator), head); //Method here
    }*/

}
