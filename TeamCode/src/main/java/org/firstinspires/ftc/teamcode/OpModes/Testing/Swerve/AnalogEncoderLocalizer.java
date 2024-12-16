package org.firstinspires.ftc.teamcode.OpModes.Testing.Swerve;


import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.AnalogInput;

import org.firstinspires.ftc.teamcode.Utilities.Core.AnalogEncoder;
import org.firstinspires.ftc.teamcode.Utilities.Core.ContServoRRX;

@TeleOp(name = "AELocalizer")
public class AnalogEncoderLocalizer extends OpMode {

    ContServoRRX FLS;
    ContServoRRX FRS;
    ContServoRRX BLS;
    ContServoRRX BRS;

    AnalogEncoder FLE;
    AnalogEncoder FRE;
    AnalogEncoder BLE;
    AnalogEncoder BRE;

    @Override
    public void init() {
        FLS = new ContServoRRX(hardwareMap, "FLS");
        FRS = new ContServoRRX(hardwareMap, "FRS");
        BLS = new ContServoRRX(hardwareMap, "BLS");
        BRS = new ContServoRRX(hardwareMap, "BRS");

        FLE = new AnalogEncoder(hardwareMap.get(AnalogInput.class, "FLE"), 3.3);
        FRE = new AnalogEncoder(hardwareMap.get(AnalogInput.class, "FRE"), 3.3);
        BLE = new AnalogEncoder(hardwareMap.get(AnalogInput.class, "BLE"), 3.3);
        BRE = new AnalogEncoder(hardwareMap.get(AnalogInput.class, "BRE"), 3.3);

        telemetry.addLine("Ready for Start");
        telemetry.update();
    }

    @Override
    public void loop() {
        FLS.setPower(gamepad1.left_stick_y);
        FRS.setPower(gamepad1.right_stick_y);
        BLS.setPower(gamepad1.left_stick_x);
        BRS.setPower(gamepad1.right_stick_x);

        telemetry.addLine("FL VOLTAGE:" + FLE.getVoltage());
        telemetry.addLine("FR VOLTAGE:" + FRE.getVoltage());
        telemetry.addLine("BL VOLTAGE:" + BLE.getVoltage());
        telemetry.addLine("BR VOLTAGE:" + BRE.getVoltage());

        telemetry.update();
    }
}
