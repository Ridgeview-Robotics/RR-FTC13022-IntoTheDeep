package org.firstinspires.ftc.teamcode.Utilities.Dec11RevisedSwerveDrivetrain

import com.acmerobotics.roadrunner.Pose2d
import com.acmerobotics.roadrunner.ProfileParams
import com.acmerobotics.roadrunner.Rotation2d
import com.acmerobotics.roadrunner.TimeTrajectory
import com.acmerobotics.roadrunner.TimeTurn
import com.acmerobotics.roadrunner.TrajectoryActionBuilder
import com.acmerobotics.roadrunner.TrajectoryActionFactory
import com.acmerobotics.roadrunner.TrajectoryBuilderParams
import com.acmerobotics.roadrunner.TurnActionFactory

/**
 * This class provides basic functionality of a mecanum drive using on [MecanumKinematics].
 *
 * @param trackWidth lateral distance between pairs of wheels on different sides of the robot
 * @param wheelBase distance between pairs of wheels on the same side of the robot
 */
abstract class SwerveDrive @JvmOverloads constructor(
    val trackWidth: Double,
    val wheelBase: Double
) : Drive() {
    override var poseEstimate: Pose2d = Pose2d()
        set(value) {
            lastWheelPositions = emptyList()
            field = value
        }

    abstract fun Pose2d(): Pose2d

    private var lastWheelPositions = emptyList<Double>()

    override fun setVelocity(poseVelocity: Pose2d) {
        val motorPowers = SwerveKinematics.robotToWheelVelocities(poseVelocity, trackWidth, wheelBase)
        val moduleOrientations = SwerveKinematics.robotToModuleOrientations(poseVelocity, trackWidth, wheelBase)
        setMotorPowers(motorPowers[0], motorPowers[1], motorPowers[2], motorPowers[3])
        setModuleOrientations(moduleOrientations[0], moduleOrientations[1], moduleOrientations[2], moduleOrientations[3])
    }

    // TODO: move to base class? note: could get tricky with the inherited properties
    override fun updatePoseEstimate() {
        // TODO!!!
        val wheelPositions = getWheelPositions()
        val moduleOrientations = getModuleOrientations()
        if (lastWheelPositions.isNotEmpty()) {
            val positionDeltas = wheelPositions
                .zip(lastWheelPositions)
                .map { it.first - it.second }
            val robotPoseDelta = SwerveKinematics.wheelToRobotVelocities(positionDeltas, moduleOrientations, wheelBase, trackWidth)
            val newHeading = poseEstimate.heading + robotPoseDelta.heading.toDouble()
            poseEstimate = (Pose2d(robotPoseDelta.position, robotPoseDelta.heading)) // there are reasonable odds that this will not work
        }
        lastWheelPositions = wheelPositions
    }

    /**
     * Sets the following motor powers (normalized voltages). All arguments are on the interval `[-1.0, 1.0]`.
     */
    abstract fun setMotorPowers(frontLeft: Double, rearLeft: Double, rearRight: Double, frontRight: Double)

    abstract fun setModuleOrientations(frontLeft: Rotation2d, rearLeft: Rotation2d, rearRight: Rotation2d, frontRight: Rotation2d)

    /**
     * Returns the positions of the wheels in linear distance units.
     */
    abstract fun getWheelPositions(): List<Double>

    abstract fun getModuleOrientations(): List<Double>

    fun actionBuilder(beginPose: Pose2d?): TrajectoryActionBuilder {
        return TrajectoryActionBuilder(
            TurnActionFactory { turn: TimeTurn -> TurnAction(turn) },
            TrajectoryActionFactory { t: TimeTrajectory -> FollowTrajectoryAction(t) },
            TrajectoryBuilderParams(
                1e-6,
                ProfileParams(
                    0.25, 0.1, 1e-2
                )
            ),
            beginPose!!, 0.0,
            defaultTurnConstraints,
            defaultVelConstraint, defaultAccelConstraint
        )
    }

}