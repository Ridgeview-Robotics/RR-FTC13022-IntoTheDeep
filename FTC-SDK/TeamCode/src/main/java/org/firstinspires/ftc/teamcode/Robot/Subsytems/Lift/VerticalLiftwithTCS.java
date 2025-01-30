package org.firstinspires.ftc.teamcode.Robot.Subsytems.Lift;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Robot.Core.GlobalVars;
import org.firstinspires.ftc.teamcode.Utilities.Core.MotorRRX;

public class VerticalLiftwithTCS {
    private static final double MAX_POWER = 1.0; // Maximum motor power
    private static final double MIN_POWER = -1.0; // Minimum motor power

    private final double KP = GlobalVars.l_KP;
    private final double KI = GlobalVars.l_KI;
    private final double KD = GlobalVars.l_KD;

    private double leftIntegral = 0;

    private int lastLeftPosition = 0;
    private int lastRightPosition = 0;

    ElapsedTime timer;

    MotorRRX leftLift;
    MotorRRX rightLift;

    private double lastTime = timer.milliseconds();

    public VerticalLiftwithTCS(HardwareMap hardwareMap){
        timer = new ElapsedTime();

        leftLift = new MotorRRX(hardwareMap, "leftLift", 1.0);
        rightLift = new MotorRRX(hardwareMap, "rightLift", 1.0);

        lastLeftPosition = leftLift.getPos();
        lastRightPosition = rightLift.getPos();
    }

    public void update() {
        int leftPosition = leftLift.getPos();
        int rightPosition = rightLift.getPos();

        int positionError = leftPosition - rightPosition;

        double currentTime = timer.milliseconds();
        double deltaTime = (currentTime - lastTime); // Convert to seconds
        lastTime = currentTime;

        // Proportional term
        double proportional = KP * positionError;

        // Integral term
        leftIntegral += positionError * deltaTime;
        double integral = KI * leftIntegral;

        // Derivative term
        double derivative = KD * ((positionError - (lastLeftPosition - lastRightPosition)) / deltaTime);

        // PID output
        double correction = proportional + integral + derivative;

        // scaling for power
        double leftPower = Math.max(MIN_POWER, Math.min(MAX_POWER, 1.0 - Math.log(1 + Math.abs(positionError))));
        double rightPower = Math.max(MIN_POWER, Math.min(MAX_POWER, 1.0 - Math.log(1 + Math.abs(-positionError))));

        // Adjust based on PID correction
        leftLift.setPower(leftPower - correction);
        rightLift.setPower(rightPower + correction);

        // Update last positions
        lastLeftPosition = leftPosition;
        lastRightPosition = rightPosition;
    }
}
