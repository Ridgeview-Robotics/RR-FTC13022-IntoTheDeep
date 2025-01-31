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

    //external state machine variables
    public static Intake.intakePositions intakeRotPos = Intake.intakePositions.HOLDING;
    public static Intake.intakeWheelPositions wheelPos = Intake.intakeWheelPositions.EXHUME;

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
        timer = new ElapsedTime();

    }

    public void init(){
        //TODO: Establish initial states of subsystems
    }

    public void loop(){
        armClearanceCkr(); //TODO: any other clearance checkers or other loop based updating systems
        intakeRotClearanceCkr();
    }

    public void switchState(robotStates state) {
        if (currentState != state) {
            lastState = currentState;
            currentState = state;
        }
        if (state == robotStates.Intaking) {
            horiz.setTarget(HorizontalLift.horizPositions.RETRACTED);
            intake.setRotatingState(Intake.intakePositions.EXTRACT);
            arm.setTarget(RotatingArm.armPositions.DOWN);
            vert.setTarget(VerticalLift.vertPositions.DOWN);
            //Score to Intake
            //Drive to Intake
            }
        else if (state == robotStates.Transferring) {
            //From Intake only
            horiz.setTarget(HorizontalLift.horizPositions.TRANSFERRING);
            intake.setRotatingState(Intake.intakePositions.TRANSFER);
            if(rotClear){
                timer.reset();
                claw.setClawClosed();
                if(timer.milliseconds()>200){
                    intake.setRotatingState(Intake.intakePositions.HOLDING);
                    arm.setTarget(RotatingArm.armPositions.HOLDING);
                }
            }
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

    public void armClearanceCkr(){
        armClear = false;
        //TODO: Un-hardcode variable
        armClear = vert.getState().getVertPos() < 100;
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
