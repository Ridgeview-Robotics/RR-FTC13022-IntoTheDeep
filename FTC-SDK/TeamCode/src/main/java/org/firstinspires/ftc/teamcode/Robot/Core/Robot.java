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
            if (lastState == robotStates.Transferring) {

            }

            }
        else if (state == robotStates.Transferring) {

            }
        else if (state == robotStates.Driving) {

            }
        else if (state == robotStates.Scoring) {

            }
        else {

            }
        }


    }
