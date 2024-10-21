package org.firstinspires.ftc.teamcode.Idea;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.Util.ContServoRRX;
import org.firstinspires.ftc.teamcode.Util.MotorRRX;

public class PrelimSwerveModule {

   ContServoRRX swerveServo;
   MotorRRX swerveMotor;

   public PrelimSwerveModule(HardwareMap hardwareMap, String servoName, String motorName){
      swerveServo = new ContServoRRX(hardwareMap, servoName);
      swerveMotor = new MotorRRX(hardwareMap, motorName, 1.0);
   }

   public void setSwerveServoDirection(CRServo.Direction direction){
      swerveServo.setDirection(direction);
   }

   public void setSwerveMotorPower(double power){
      swerveMotor.setPower(power);
   }

   public void setSwerveServoPower(double power){
      swerveServo.setPower(power);
   }


}
