package org.usfirst.frc.team1165.robot;
 
import org.usfirst.frc.team1165.robot.commands.DriveSidewaysDistance;
import org.usfirst.frc.team1165.robot.commands.DriveStraight;
import org.usfirst.frc.team1165.robot.commands.DriveStraightDistance;
import org.usfirst.frc.team1165.robot.commands.DriveStraightTicks;
import org.usfirst.frc.team1165.robot.commands.DriveToObject;
import org.usfirst.frc.team1165.robot.commands.ReportEncoder;
import org.usfirst.frc.team1165.robot.commands.Rotate;
import org.usfirst.frc.team1165.robot.commands.RotateToHeading;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI
{
	// // CREATING BUTTONS
	// One type of button is a joystick button which is any button on a
	// joystick.
	// You create one by telling it which joystick it's on and which button
	// number it is.
	// Joystick stick = new Joystick(port);
	// Button button = new JoystickButton(stick, buttonNumber);

	// There are a few additional built in buttons you can use. Additionally,
	// by subclassing Button you can create custom triggers and bind those to
	// commands the same as any other Button.

	// // TRIGGERING COMMANDS WITH BUTTONS
	// Once you have a button, it's trivial to bind it to a button in one of
	// three ways:

	// Start the command when the button is pressed and let it run the command
	// until it is finished as determined by it's isFinished method.
	// button.whenPressed(new ExampleCommand());

	// Run the command while the button is being held down and interrupt it once
	// the button is released.
	// button.whileHeld(new ExampleCommand());

	// Start the command when the button is released and let it run the command
	// until it is finished as determined by it's isFinished method.
	// button.whenReleased(new ExampleCommand());

	private final Joystick mainJoystick = new Joystick(RobotMap.joystickPort1);

	public OI()
	{
		SmartDashboard.putNumber("Dampening", .9);
	
 
		//SmartDashboard.putNumber("Target Angle", 45);

		SmartDashboard.putNumber("Target Heading", 45);
		SmartDashboard.putNumber("Brake Heading", 10);
		SmartDashboard.putNumber("Rotate Magnitude", .4);
		SmartDashboard.putNumber("Creep Magnitude", .2);
		SmartDashboard.putData(new RotateToHeading("Rotate Magnitude", "Brake Heading", "Target Heading",
				"Creep Magnitude"));

		//SmartDashboard.putData(new Rotate("Target Angle"));

		SmartDashboard.putNumber("Straight Magnitude", .4); 
		SmartDashboard.putData(new DriveStraight("Straight Magnitude", .5));

		SmartDashboard.putNumber("Straight Ticks", 250);
		SmartDashboard.putData(new DriveStraightTicks("Straight Magnitude", "Straight Ticks"));

		SmartDashboard.putNumber("Straight Inches", 53);
		SmartDashboard.putData(new DriveStraightDistance("Straight Magnitude", "Straight Inches"));
		SmartDashboard.putData(new DriveSidewaysDistance("Straight Magnitude", "Straight Inches"));

		SmartDashboard.putNumber("Target Range", 19);
		SmartDashboard.putNumber("Brake Range", 50);
		SmartDashboard.putNumber("Forward Speed", -1);
		SmartDashboard.putNumber("Creep Speed", -.2);
		SmartDashboard.putData(new DriveToObject("Forward Speed", "Brake Range", "Target Range", "Creep Speed"));

	}

	public double getDampening()
	{
		return SmartDashboard.getNumber("Dampening");
	}

	public double getDriveX()
	{
		return mainJoystick.getY();
	}

	public double getDriveY()
	{
		return mainJoystick.getX();
	}

	public double getDriveTwist()
	{
		return mainJoystick.getTwist();
	}
}
