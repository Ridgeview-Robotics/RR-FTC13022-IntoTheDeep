package org.firstinspires.ftc.teamcode.Util.Dec11RevisedSwerveDrivetrain
//
//import com.acmerobotics.roadrunner.Pose2d
//import com.acmerobotics.roadrunner.Twist2d
//import com.acmerobotics.roadrunner.Vector2d
//import kotlin.math.cos
//import kotlin.math.sin
//
//object SwerveKinematics {
//    private fun robotToModuleVelocityVectors(robotVelocity: Pose2d, trackWidth: Double, wheelBase: Double = trackWidth): List<Vector2d> {
//        val x = wheelBase / 2
//        val y = trackWidth / 2
//
//        val vx = robotVelocity.position.x
//        val vy = robotVelocity.position.y
//        val omega = robotVelocity.heading
//
//        return listOf(
//            Vector2d((vx - omega) * y, (vy + omega) * x),
//            Vector2d((vx - omega) * y, (vy - omega) * x),
//            Vector2d((vx + omega) * y, (vy - omega) * x),
//            Vector2d((vx + omega) * y, (vy + omega) * x)
//        )
//    }
//
//    fun robotToWheelVelocities(robotPoseVelocity: Pose2d, trackWidth: Double, wheelBase: Double = trackWidth) =
//        robotToModuleVelocityVectors(robotPoseVelocity, trackWidth, wheelBase).map(Vector2d::norm)
//
//    fun robotToModuleOrientations(robotPoseVelocity: Pose2d, trackWidth: Double, wheelBase: Double = trackWidth) =
//        robotToModuleVelocityVectors(robotPoseVelocity, trackWidth, wheelBase)
//
//    private fun robotToModuleAccelerationVectors(robotPoseAcceleration: Pose2d, trackWidth: Double, wheelBase: Double = trackWidth): List<Vector2d> {
//        val x = wheelBase / 2
//        val y = trackWidth / 2
//
//        val ax = robotPoseAcceleration.position.x
//        val ay = robotPoseAcceleration.position.y
//        val alpha = robotPoseAcceleration.heading
//
//        return listOf(
//            Vector2d((ax - alpha) * y, (ay + alpha) * x),
//            Vector2d((ax - alpha) * y, (ay - alpha) * x),
//            Vector2d((ax + alpha) * y, (ay - alpha) * x),
//            Vector2d((ax + alpha) * y, (ay + alpha) * x)
//
//            //Vector2d has 2 parameters, two doubles, x and y.  the p/m operators require vectors to add.
//            //multiplication on the other hand, asks for a double to multiply in.
//            //so, somehow, ax and alpha MUST be vectors, then transform into doubles
//            //robot post accel/etc, cannot be Pose2d to be used for this, effectively?
//
//            //Pose2d calls for a twist2d, i.e. an angle.
//
//            //ax/ay - alpha has to be cast to double (*)
//            //alpha must be cast to vector2d (-/+)
//
//            //okay nevermind, i think heading is a Rotation2d?  I'm bored of this shit
//        )
//    }
//
//    fun robotToWheelAccelerations(robotPoseAcceleration: Pose2d, trackWidth: Double, wheelBase: Double = trackWidth) =
//        robotToModuleVelocityVectors(robotPoseAcceleration, trackWidth, wheelBase)
//            .zip(robotToModuleAccelerationVectors(robotPoseAcceleration, trackWidth, wheelBase))
//            .map { (it.first.x * it.second.x + it.first.y * it.second.y) / it.first.norm() }
//
//    fun robotToModuleAngularVelocities(robotPoseVelocity: Pose2d, robotAngularVelocity: Twist2d, robotPoseAcceleration: Pose2d, trackWidth: Double, wheelBase: Double = trackWidth) =
//        robotToModuleVelocityVectors(robotPoseVelocity, trackWidth, wheelBase)
//            .zip(robotToModuleAccelerationVectors(robotPoseAcceleration, trackWidth, wheelBase))
//            .map { ((it.first.x * it.second.y - it.first.y * it.second.x) / (it.first.x * it.first.x + it.first.y * it.first.y)) }
//
//    fun wheelToRobotVelocities(wheelVelocities: List<Double>, moduleOrientations: List<Double>, trackWidth: Double, wheelBase: Double = trackWidth): Pose2d {
//        val x = wheelBase / 2
//        val y = trackWidth / 2
//
//        val vectors = wheelVelocities
//            .zip(moduleOrientations)
//            .map { Vector2d(it.first * cos(it.second), it.first * sin(it.second)) }
//
//        val vx = vectors.sumOf { it.x } / 4
//        val vy = vectors.sumOf { it.y } / 4
//        val omega = (y * (vectors[2].x + vectors[3].x - vectors[0].x - vectors[1].x)
//                + x * (vectors[0].y + vectors[2].y - vectors[1].y - vectors[3].y)) / (4 * (x * x + y * y))
//
//        return Pose2d(vx, vy, omega)
//    }
//}