package org.firstinspires.ftc.teamcode.Util.nov25swerve;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.Util.AnalogEncoder;

public class SwerveServo {

    public double electricConstant = 109.09090909; //voltage value * this = val in deg

    CRServo rcsServo;
    AnalogEncoder enc;

    private double kP = 0.001;
    private double kI = 0.01;
    private double kD = 0.001;
    private double kF = 0.001;

    // PID State
    private double lastError = 0.0;
    private double integral = 0.0;


    public SwerveServo(HardwareMap hardwareMap, String servoName, String EncName){
        rcsServo = hardwareMap.get(CRServo.class, servoName);
        enc = new AnalogEncoder(hardwareMap.get(AnalogEncoder.class, EncName).getEncoder(), 3.3);
    }

    public void setDirection(CRServo.Direction direction){
        rcsServo.setDirection(direction);
    }

    public void setPower(double power){
        rcsServo.setPower(power);
    }

    public double getPosition(){
        return volToDeg(rcsServo.getPower());
    }

    public double volToDeg(double voltageInput){
        return voltageInput * electricConstant;
    }

    public void setTargetAngle(double targetAngle) {
        double currentAngle = volToDeg(enc.getVoltage());
        double error = normalizeAngle(targetAngle - currentAngle);

        // Proportional term
        double pTerm = kP * error;

        // Integral term

        integral += error;
        double iTerm = kI * integral;

        // Derivative term
        double dTerm = kD * (error - lastError);
        lastError = error;

        // Feedforward term (optional; scale to desired output range if necessary)
        double fTerm = kF * targetAngle;

        // Compute final output
        double output = pTerm + iTerm + dTerm + fTerm;

        // Apply output to motor
        rcsServo.setPower(output);
    }

    private double normalizeAngle(double angle) {
        while (angle > 0.5) angle -= 1.0;
        while (angle < -0.5) angle += 1.0;
        return angle;
    }


}
