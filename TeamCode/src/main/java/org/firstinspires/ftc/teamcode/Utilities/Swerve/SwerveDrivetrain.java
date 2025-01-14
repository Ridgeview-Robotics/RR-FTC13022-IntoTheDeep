//package org.firstinspires.ftc.teamcode.Utilities.Swerve;
//
//import static org.firstinspires.ftc.teamcode.Robot.Core.GlobalVars.USE_WHEEL_FEEDFORWARD;
//import static java.lang.Math.atan2;
//import static java.lang.Math.hypot;
//
//import com.qualcomm.robotcore.hardware.DcMotor;
//
//
//import org.firstinspires.ftc.teamcode.Robot.Core.Robot;
//import org.firstinspires.ftc.teamcode.Utilities.Core.AnalogEncoder;
//import org.firstinspires.ftc.teamcode.Utilities.Geometry.MathUtils;
//import org.firstinspires.ftc.teamcode.Utilities.Geometry.Pose;
//
//public class SwerveDrivetrain {
//    public SwerveModule frontLeftModule, backLeftModule, backRightModule, frontRightModule;
//    public SwerveModule[] modules;
//
//    public static double TRACK_WIDTH = 9, WHEEL_BASE = 9;
//    private final double R;
//    public static double frontLeftOffset = 2, frontRightOffset = 0, backLeftOffset = 0, backRightOffset = -0.055;
//
//    public static boolean maintainHeading = false;
//
//    double[] ws = new double[4];
//    double[] wa = new double[4];
//    double max = 0.0;
//
//    public final double minPow = 0.1;
//    public static double imuOffset = 0.0;
//
//    private boolean locked = false;
//
//    public SwerveDrivetrain(Robot robot) {
//        frontLeftModule = new SwerveModule(robot.flm, robot.fls, new AnalogEncoder(robot.fle.encoder, 3.3).zero(frontLeftOffset).setInverted(true));
//        backLeftModule = new SwerveModule(robot.blm, robot.bls, new AnalogEncoder(robot.ble.encoder, 3.3).zero(backLeftOffset).setInverted(true));
//        backRightModule = new SwerveModule(robot.brm, robot.brs, new AnalogEncoder(robot.bre.encoder, 3.3).zero(backRightOffset).setInverted(true));
//        frontRightModule = new SwerveModule(robot.frm, robot.frs, new AnalogEncoder(robot.fre.encoder, 3.3).zero(frontRightOffset).setInverted(true));
//
//        modules = new SwerveModule[]{frontLeftModule, frontRightModule, backRightModule, backLeftModule};
//        for (SwerveModule m : modules) m.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
//        R = hypot(TRACK_WIDTH, WHEEL_BASE);
//    }
//
//    public void read() {
//        for (SwerveModule module : modules) module.read();
//    }
//
//
//    public void set(Pose pose) {
//        double x = pose.x, y = pose.y, head = pose.heading;
//
//        double a = x - head * (WHEEL_BASE / R),
//                b = x + head * (WHEEL_BASE / R),
//                c = y - head * (TRACK_WIDTH / R),
//                d = y + head * (TRACK_WIDTH / R);
//
//        if (locked) {
//            ws = new double[]{0, 0, 0, 0};
//            wa = new double[]{Math.PI / 4, -Math.PI / 4, Math.PI / 4, -Math.PI / 4};
//        } else {
//            ws = new double[]{hypot(b, c), hypot(b, d), hypot(a, d), hypot(a, c)};
//            if (!maintainHeading) wa = new double[]{atan2(b, c), atan2(b, d), atan2(a, d), atan2(a, c)};
//        }
//
//        max = MathUtils.max(ws);
//    }
//
//    public void write() {
//        for (int i = 0; i < 4; i++) {
//            SwerveModule m = modules[i];
//            if (Math.abs(max) > 1) ws[i] /= max;
//            m.setMotorPower(Math.abs(ws[i]) + ((USE_WHEEL_FEEDFORWARD) ? minPow * Math.signum(ws[i]) : 0));
//            m.setTargetRotation(MathUtils.norm(wa[i]));
//        }
//    }
//
//    public void updateModules() {
//        for (SwerveModule m : modules) m.update();
//    }
//
//    public void setLocked(boolean locked){
//        this.locked = locked;
//    }
//
//    public boolean isLocked(){
//        return locked;
//    }
//
//    public String getTelemetry() {
//        return frontLeftModule.getTelemetry("leftFrontModule") + "\n" +
//                backLeftModule.getTelemetry("leftRearModule") + "\n" +
//                frontRightModule.getTelemetry("rightFrontModule") + "\n" +
//                backRightModule.getTelemetry("rightRearModule") + "\n";
//    }
//}
