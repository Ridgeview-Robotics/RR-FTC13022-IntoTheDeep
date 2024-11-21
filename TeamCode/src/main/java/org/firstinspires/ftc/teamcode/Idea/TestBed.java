package org.firstinspires.ftc.teamcode.Idea;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;


@TeleOp(name="Test Bed OPMode", group="Linear OpMode")
public class TestBed extends LinearOpMode {

    // Declare OpMode members for each of the 4 motors.
    private ElapsedTime runtime = new ElapsedTime();

    private DcMotor OuttakeArm;
    private DcMotor IntakeExtender = null;

    private Servo IntakeWrist = null;
    private CRServo IntakeDrive = null;
    private Servo OuttakeWrist = null;
    private Servo OuttakeClaw = null;

    @Override
    public void runOpMode() {
        
        double OuttakeWristPosition = 0.0;

        // Initialize the hardware variables. Note that the strings used here must correspond
        // to the names assigned during the robot configuration step on the DS or RC devices.
        IntakeExtender = hardwareMap.get(DcMotor.class, "IntakeExtender");
        IntakeDrive  = hardwareMap.get(CRServo.class, "IntakeDrive");
        IntakeWrist  = hardwareMap.get(Servo.class, "IntakeWrist");

        OuttakeArm = hardwareMap.get(DcMotor.class, "OuttakeArm");
        OuttakeWrist = hardwareMap.get(Servo.class, "OuttakeWrist");
        OuttakeClaw = hardwareMap.get(Servo.class, "OuttakeClaw");

        IntakeExtender.setDirection(DcMotor.Direction.REVERSE);
        OuttakeArm.setDirection(DcMotorSimple.Direction.FORWARD);

        // Wait for the game to start (driver presses START)
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        waitForStart();
        runtime.reset();

        while (opModeIsActive()) {
            double max;

            // Intake extender motor control
            double iExtenderPower = -gamepad1.left_stick_y;  // Note: pushing stick forward gives negative value
            IntakeExtender.setPower(iExtenderPower * iExtenderPower);

            // Intake wrist position
            if (gamepad1.dpad_up) {
                IntakeWrist.setPosition(1);
            }
            if (gamepad1.dpad_down) {
                IntakeWrist.setPosition(0);
            }

            // Intake wheels power
            if (gamepad1.left_bumper) {
                IntakeDrive.setPower(-1);
            }
            if (gamepad1.right_bumper) {
                IntakeDrive.setPower(1);
            }
            if (!(gamepad1.left_bumper || gamepad1.right_bumper)) {
                IntakeDrive.setPower(0);
            }

            // Outtake arm control
            double oArmPower = -gamepad1.right_stick_y;
            OuttakeArm.setPower(oArmPower * oArmPower);

            // Outtake wrist control
            OuttakeWristPosition += gamepad1.right_stick_x * 0.1;

            if (OuttakeWristPosition > 1) {
                OuttakeWristPosition = 1;
            } else if (OuttakeWristPosition <0) {
                OuttakeWristPosition = 0;
            }

            OuttakeWrist.setPosition(OuttakeWristPosition);

            // Outtake claw control
            if (gamepad1.a) {
                OuttakeClaw.setPosition(0);
            } else if (gamepad1.b) {
                OuttakeClaw.setPosition(1);
            }

            // Show the elapsed game time and wheel power.
            telemetry.addData("Status", "Run Time: " + runtime.toString());
            telemetry.update();
        }
    }}
