package org.firstinspires.ftc.teamcode.Util.Dec11RevisedSwerveDrivetrain

import com.acmerobotics.roadrunner.Pose2d
import com.acmerobotics.roadrunner.Twist2d

/**
 * Abstraction for generic drivetrain motion and localization.
 */
abstract class Drive {
    /**
     * The robot's current pose estimate.
     */
    abstract var poseEstimate: Pose2d

    /**
     * Sets the [poseVelocity] of the robot.
     */
    abstract fun setVelocity(poseVelocity: Pose2d)

    /**
     * Updates [poseEstimate] with the most recent positional change.
     */
    abstract fun updatePoseEstimate()

}