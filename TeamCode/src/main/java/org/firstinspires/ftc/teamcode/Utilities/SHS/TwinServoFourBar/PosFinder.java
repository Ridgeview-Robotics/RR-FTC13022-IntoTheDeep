package org.firstinspires.ftc.teamcode.Utilities.SHS.TwinServoFourBar;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@Disabled
@TeleOp(name = "Pos Finder", group = "Testing")
public class PosFinder extends OpMode {

    //I highly recommend disconnecting anything attached to the servo gearbox itself, and then running this
    //It will do weird things to servo's here and there, so be careful.
    //I'd disconnect it, get both of them to 0.5 if your part is in the center, or maybe 0.0 if its at the start, regardless, you
    //will have the servos in opposition of one another on a four bar.  get your positions using this, i think you can figure it out,
    //just be logical, and if it comes to it, i Might be able to drop by/meet you somewhere in the middle to work on it
    //lmk if u have any questions

    FourBar fourBar;

    @Override
    public void init() {
        fourBar = new FourBar(hardwareMap);

        telemetry.addLine("Ready for Start!");
        telemetry.update();
    }

    @Override
    public void loop() {
        if(gamepad1.square){
            fourBar.setLeftServo(fourBar.getLeftServo() + 0.01);
        }

        if(gamepad1.cross){
            fourBar.setLeftServo(fourBar.getLeftServo() - 0.01);
        }

        if(gamepad1.triangle){
            fourBar.setRightServo(fourBar.getRightServo() + 0.01);
        }

        if(gamepad1.circle){
            fourBar.setRightServo(fourBar.getRightServo() - 0.01);
        }


        telemetry.addLine("Your Left Servo is Up = Square, Down = Cross");
        telemetry.addLine("Your Right Servo is Up = Triangle, Down = Circle");
        telemetry.addData("Left Position:", fourBar.getLeftServo());
        telemetry.addData("Right Position:", fourBar.getRightServo());
        telemetry.update();
    }
}
