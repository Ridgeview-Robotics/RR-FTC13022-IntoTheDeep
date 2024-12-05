package org.firstinspires.ftc.teamcode.Util.nov25swerve;

import static java.lang.Math.abs;

import com.qualcomm.robotcore.hardware.AnalogInput;
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
        enc = new AnalogEncoder(hardwareMap.get(AnalogInput.class, EncName), 3.3);
    }

    public void setDirection(CRServo.Direction direction){
        rcsServo.setDirection(direction);
    }

    public void setPower(double power){
        rcsServo.setPower(power);
    }

    public double getPosition(){
        return volToDeg(enc.getVoltage());
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

    // PLEASE CHECK MY CODE!!! - Emerson

    // Function to calculate the actual difference between two angles
    private double angleDiff(double angle1, double angle2) {
        double altAngle1 = angle1 > 0.0 ? angle1 - 2.0 : 1.0 - angle1;
        double distance = abs(angle1 - angle2) < abs(altAngle1 - angle2) ? angle1 - angle2 : altAngle1 - angle2;
        return distance;
    } //I think the if-false portion of that if statement is correct, and I think that if that is where its range
    //is landing, then it is correct, but we'll need to check values with the abs enc, and then decide whether we route this
    //to be in RAD or DEG

    // Servo travel minimization function, to minimize the time the servo takes to travel to the new position.
    private double minimumTravelAngle(double angle) {
        double pos = getPosition();
        angle = normalizeAngle(angle);
        // Calculate the angle 180 degrees from the current angle
        double inverseAngle = (angle + 1.0) <= 1.0? angle + 1.0: angle - 1.0;
        // Calculate the amount by which we need to move the servo, and in what direction
        double angleDelta = abs(angleDiff(angle, pos)) < abs(angleDiff(inverseAngle, pos)) ? angleDiff(angle, pos) : angleDiff(inverseAngle, pos);
        double newAngle = pos + angleDelta;
        //this looks correct to me, all good
        return newAngle;
    }


}
