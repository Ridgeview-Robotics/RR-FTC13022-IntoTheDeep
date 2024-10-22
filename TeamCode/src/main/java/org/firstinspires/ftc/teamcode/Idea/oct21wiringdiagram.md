The preliminary swerve module is set up.  wire as such:

your servo's and motors will all be FL, FR, BL, BR (Front Left... Back Right), and then motor/servo,
i.e.  FLServo

the left joystick once connected, when pushed up and down will drive the motors forward and backwards.  
the right joystick ", when pushed side to side, will drive the servos to the power that you have pushed
the joystick.  as in, they will not run to a degree-value, they will run with the power you throw at them.

make sure you input your hardwareMap on the robot.  
and i suggest you write down in this md file where you have put the cables.

__CONTROL HUB__  (no encoders yet)

M0: FLMotor
M1: FRMotor
M2: BLMotor
M3: BRMotor

    S0: FLServo  S1: FRServo  S2: BLServo  S3: BRServo

-----------------------------

should be all.   