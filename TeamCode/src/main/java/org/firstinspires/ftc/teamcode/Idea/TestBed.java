package org.firstinspires.ftc.teamcode.Idea;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;


@TeleOp(name="Test Bed OPMode", group="Linear OpMode")
public class TestBed extends LinearOpMode {

    // Declare OpMode members for each of the 4 motors.
    private ElapsedTime runtime = new ElapsedTime();
    private DcMotor extender = null;

    private Servo IntakeWrist = null;
    private CRServo IntakeDrive = null;
    private Servo OuttakeWrist = null;
    private Servo OuttakeClaw = null;

    @Override
    public void runOpMode() {

        // Initialize the hardware variables. Note that the strings used here must correspond
        // to the names assigned during the robot configuration step on the DS or RC devices.
        extender  = hardwareMap.get(DcMotor.class, "Extender");
        IntakeDrive  = hardwareMap.get(CRServo.class, "IntakeDrive");
        IntakeWrist  = hardwareMap.get(Servo.class, "IntakeWrist");

        extender.setDirection(DcMotor.Direction.REVERSE);

        // Wait for the game to start (driver presses START)
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        waitForStart();
        runtime.reset();

        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {
            double max;

            // POV Mode uses left joystick to go forward & strafe, and right joystick to rotate.
            double axial   = -gamepad1.left_stick_y;  // Note: pushing stick forward gives negative value

            extender.setPower(axial * 0.5);

            if (gamepad1.dpad_up) {
                IntakeWrist.setPosition(1);
            }
            if (gamepad1.dpad_down) {
                IntakeWrist.setPosition(0);
            }

            if (gamepad1.left_bumper) {
                IntakeDrive.setPower(-1);
            }
            if (gamepad1.right_bumper) {
                IntakeDrive.setPower(1);
            }
            if (!(gamepad1.left_bumper || gamepad1.right_bumper)) {
                IntakeDrive.setPower(0);
            }

            // Show the elapsed game time and wheel power.
            telemetry.addData("Status", "Run Time: " + runtime.toString());
            telemetry.addData("Extender motor power", "%4.2f", axial);
            telemetry.update();
        }
    }}
