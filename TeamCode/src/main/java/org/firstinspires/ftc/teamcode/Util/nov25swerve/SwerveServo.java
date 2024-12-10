package org.firstinspires.ftc.teamcode.Util.nov25swerve;

import static java.lang.Math.abs;

import com.qualcomm.robotcore.hardware.AnalogInput;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.Util.AnalogEncoder;

public class SwerveServo {

    public double electricConstant = 109.09090909; //voltage value * this = val in deg
    public double gearRatio = 0.4482758620689; //assuming drive pulley is 26t and driven pulley is 58t
    //so theoretically... every full rotation of the servo is a 44% rotation of the drive pulley, hence,
    //if the servo starts at FC, 0deg, and turns to 90deg right, the wheel will only have turned 40.3deg.
    //180 deg, or turned around, is only 80deg of wheel turn.

    public double l_pTerm;
    public double l_iTerm;
    public double l_dTerm;
    public double l_fTerm;

    CRServo rcsServo;
    AnalogEncoder enc;

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
        return voltageInput * electricConstant * gearRatio;
    }

    public double setTargetAngle(double targetAngle) { //suggest power multiplier
        double currentAngle = getPosition();
        double error = normalizeAngle(targetAngle - currentAngle);

        // Proportional term
        l_pTerm = GlobalVars.a_kP * error;

        // Integral term

        integral += error;
        l_iTerm = GlobalVars.a_kI * integral; //Integral system incorrect

        // Derivative term
        l_dTerm = GlobalVars.a_kD * (error - lastError);
        lastError = error;

        // Feedforward term (optional; scale to desired output range if necessary)
        l_fTerm = GlobalVars.a_kF * targetAngle;

        // Compute final output
        double output = l_pTerm + l_iTerm + l_dTerm + l_fTerm;

        // Apply output to motor
        return output;
    }

    private double normalizeAngle(double angle) {
        if(angle > 90){
            angle += 180;
        }
        else if(angle < 270){
            angle -= 180;
        }

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

    public double []getPIDS(){
        return new double[]{l_pTerm, l_iTerm, l_dTerm, l_fTerm};
    }
}

