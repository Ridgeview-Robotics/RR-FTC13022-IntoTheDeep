package org.firstinspires.ftc.teamcode.Robot.Core;


import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.Robot.Subsytems.Lift.HorizontalLift;
import org.firstinspires.ftc.teamcode.Robot.Subsytems.Lift.VerticalLift;
import org.firstinspires.ftc.teamcode.Robot.Subsytems.RotatingArm.RotatingArm;
import org.firstinspires.ftc.teamcode.Robot.Subsytems.ServoSystems.Claw;
import org.firstinspires.ftc.teamcode.Robot.Subsytems.ServoSystems.Intake;
import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;

public class Robot {
    public SampleMecanumDrive autoDrivetrain;
    public MecanumDrivetrain drivetrain;

    public Intake intake;
    public Claw claw;
    public VerticalLift vert;
    public HorizontalLift horiz;
    public RotatingArm arm;

    //external state machine variables
    public Intake.intakePositions intakeRotPos = Intake.intakePositions.HOLDING;
    public Intake.intakeWheelPositions wheelPos = Intake.intakeWheelPositions.EXHUME;

    public enum robotStates {
        Intaking("Intaking"),
        Transferring("Transferring"),
        Driving("Driving"),
        Scoring("Scoring");

        robotStates(final String name) {
            this.name = name;
        }

        private final String name;

        public String getName() {
            return name;
        }
    }

    public robotStates currentState;
    public robotStates lastState;

    public Robot(HardwareMap hardwareMap) {
        drivetrain = new MecanumDrivetrain(hardwareMap);
        autoDrivetrain = new SampleMecanumDrive(hardwareMap);

    }

    public void switchState(robotStates state) {
        if (currentState != state) {
            lastState = currentState;
            currentState = state;
        }
        if (state == robotStates.Intaking) {
            horiz.setTarget(HorizontalLift.horizPositions.RETRACTED);
            intake.setRotatingState(Intake.intakePositions.EXTRACT);
            //Score to Intake
            //Drive to Intake
            }
        else if (state == robotStates.Transferring) {
            //From Intake only
            horiz.setTarget(HorizontalLift.horizPositions.RETRACTED);
            intake.setRotatingState(Intake.intakePositions.TRANSFER);
            if(intakeRotPos == Intake.intakePositions.TRANSFER && intake.get)

            }
        else if (state == robotStates.Driving) {
            //All States Possible

            }
        else if (state == robotStates.Scoring) {
            //Only from Driving

            }
        else {

            }
        }


    }
