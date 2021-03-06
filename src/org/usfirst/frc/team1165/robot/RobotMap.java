package org.usfirst.frc.team1165.robot;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap
{
    // For example to map the left and right motors, you could define the
    // following variables to use with your drivetrain subsystem.
    // public static int leftMotor = 1;
    // public static int rightMotor = 2;

    // If you are using multiple modules, make sure to define both the port
    // number and the module. For example you with a rangefinder:
    // public static int rangefinderPort = 1;
    // public static int rangefinderModule = 1;
    
    // Wheel motors: Digital I/O PWM
    public static final int frontLeftMotorChannel	= 0;
    public static final int frontRightMotorChannel	= 1;
    public static final int rearLeftMotorChannel	= 2;
    public static final int rearRightMotorChannel	= 3;
    
    // Ultrasonic sensor
    public static final int ultrasonicPingChannel	= 0;
    public static final int ultrasonicEchoChannel	= 1;
    
    // Gyroscope
    public static final int gyroscopeChannel		= 0;
    
    //PickUpArms
    public static final int leftCanPickupArm        = 6;
	public static final int rightCanPickupArm       = 7;

    //Joystick Ports
    public static final int joystickPort1          = 1;
    public static final int joystickPort2          = 2;
    
    //Encoder 
    public static final int encoderChannelA			= 8;
    public static final int encoderChannelB			= 9;
    
    public static final double DRIVE_SPEED 			 =   0.8;		// Drive magnitude
    public static final double DRIVE_CREEP_SPEED 	 =   0.2;	// Rotate magnitude
    public static final double ROTATE_SPEED 		 =   0.4;	// Rotate magnitude
    public static final double ROTATE_CREEP_SPEED 	 =   0.2;	// Rotate magnitude
    public static final double DISTANCE_TO_AUTO_ZONE = 84;		// Inches

}
