package org.firstinspires.ftc.teamcode.Robot.Core;


import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

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

    public ElapsedTime timer;

    //clearance booleans
    public boolean armClear = false;
    public boolean rotClear = false;

    public VerticalLift.vertPositions vertPos = VerticalLift.vertPositions.DOWN;
    public HorizontalLift.horizPositions horizPos = HorizontalLift.horizPositions.RETRACTED;

    public static RotatingArm.armPositions armington = RotatingArm.armPositions.DOWN;

    //external state machine variables
    public static Intake.intakePositions intakeRotPos = Intake.intakePositions.HOLDING;
    public static Intake.intakeWheelPositions wheelPos = Intake.intakeWheelPositions.EXHUME;
    public static Claw.ClawPositions clawPos = Claw.ClawPositions.CLOSED;

    public boolean shouldUpdate;


    public enum robotStates {
        Intaking("Intaking"),
        Transferring("Transferring"),
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
        intake = new Intake(hardwareMap);
        arm = new RotatingArm(hardwareMap);
        vert = new VerticalLift(hardwareMap);
        horiz = new HorizontalLift(hardwareMap);
        claw = new Claw(hardwareMap);
        autoDrivetrain = new SampleMecanumDrive(hardwareMap);
        timer = new ElapsedTime();

    }

    public void init(){
        //TODO: Establish initial states of subsystems
    }

    public void loop(){
        armClearanceCkr(); //TODO: any other clearance checkers or other loop based updating systems
        intakeRotClearanceCkr();
    }

    public void transfer(){
        claw.setRotatingHorizontal();
        horiz.setTarget(HorizontalLift.horizPositions.TRANSFERRING);
        intake.setRotatingState(Intake.intakePositions.TRANSFER);
        if (rotClear && horiz.getState() == HorizontalLift.horizPositions.TRANSFERRING) {
            timer.reset();
            claw.setClawClosed();
            if (timer.milliseconds() > 150) {
                intake.setRotatingState(Intake.intakePositions.HOLDING);
                arm.setTarget(RotatingArm.armPositions.HOLDING);
            }
        }
        switchState(robotStates.Scoring);
    }

    public void toIntake(){
        horiz.setTarget(HorizontalLift.horizPositions.RETRACTED);
        intake.setRotatingState(Intake.intakePositions.EXTRACT);
        arm.setTarget(RotatingArm.armPositions.DOWN);
        vert.setTarget(VerticalLift.vertPositions.DOWN);
    }

    public void switchState(robotStates state) {
        if (currentState != state) {
            lastState = currentState;
            currentState = state;
            shouldUpdate=true;
        }
        if(shouldUpdate) {
            if (state == robotStates.Intaking) {
                toIntake();
            } else if (state == robotStates.Transferring) {
                //From Intake only
                transfer();

            } else if (state == robotStates.Scoring) {
                //Only from Driving
                horiz.setPower(0);
            }
        }
        shouldUpdate = false;
    }

    public void armClearanceCkr() {
        armClear = false;
        //TODO: Un-hardcode variable
        armClear = vert.getLeftPos() < 100;
    }

    public void setArmDownWithClkr(){
        if(armClear){
            arm.setTarget(RotatingArm.armPositions.DOWN);
        }
    }

    public void intakeRotClearanceCkr(){
        rotClear = false;
        //TODO: Un-hardcode variable
        rotClear = intake.getRotatingPos() == intakeRotPos.getPosition();
    }

    public void armSetPosition(RotatingArm.armPositions pos) {
        if (pos == RotatingArm.armPositions.DOWN) {
            if (armClear) {
                arm.setTarget(pos);
            }
        }
        else{
            arm.setTarget(pos);
        }
    }

    public void storeDesiredVerticalPosition(VerticalLift.vertPositions pos){
        vertPos = pos;
    }


}
