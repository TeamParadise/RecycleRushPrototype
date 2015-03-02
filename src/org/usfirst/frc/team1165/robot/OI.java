package org.usfirst.frc.team1165.robot;
 
import org.usfirst.frc.team1165.robot.commands.DriveStraight;
import org.usfirst.frc.team1165.robot.commands.DriveStraightDistance;
import org.usfirst.frc.team1165.robot.commands.DriveToObject;
import org.usfirst.frc.team1165.robot.commands.ReportEncoder;
import org.usfirst.frc.team1165.robot.commands.RotateToHeading;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI
{
	private final Joystick mainJoystick = new Joystick(RobotMap.joystickPort1);
	private final Joystick secondaryJoystick = new Joystick(RobotMap.joystickPort2);

	public OI()
	{
		SmartDashboard.putNumber("Dampening", .9);
	
		SmartDashboard.putNumber("Forward Speed", -1);
		SmartDashboard.putData(new DriveStraight("Forward Speed", 0.5));

		SmartDashboard.putNumber("Drive Inches", 53);
		SmartDashboard.putData(new DriveStraightDistance("Forward Speed", "Drive Inches"));

		SmartDashboard.putNumber("Target Range", 19);
		SmartDashboard.putNumber("Brake Range", 50);
		SmartDashboard.putNumber("Creep Speed", -.2);
		SmartDashboard.putData(new DriveToObject("Forward Speed", "Brake Range", "Target Range", "Creep Speed"));

		SmartDashboard.putNumber("Target Heading", 45);
		SmartDashboard.putNumber("Brake Heading", 10);
		SmartDashboard.putNumber("Rotate Magnitude", .4);
		SmartDashboard.putNumber("Creep Magnitude", .2);
		SmartDashboard.putData(new RotateToHeading("Rotate Magnitude", "Brake Heading", "Target Heading",
				"Creep Magnitude"));
	}

	public double getDampening()
	{
		return SmartDashboard.getNumber("Dampening");
	}

	public double getDriveX()
	{
		return -mainJoystick.getY();
	}

	public double getDriveY()
	{
		return mainJoystick.getX();
	}

	public double getDriveTwist()
	{
		return mainJoystick.getTwist();
	}
	
	public double canPickupSpeedX()
	{
		return secondaryJoystick.getX();
	}

	public double canPickupSpeedY()
	{
		// Pushing forward on the joystick returns negative y values.
		// We want the reverse of that.
		return -secondaryJoystick.getY();
	}
}
