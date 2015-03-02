package org.usfirst.frc.team1165.robot.subsystems;

import org.usfirst.frc.team1165.robot.RobotMap;
import org.usfirst.frc.team1165.robot.commands.RunCanPickupArmsFromJoystick;

import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;

public class CanPickupArms extends Subsystem
{
	Victor leftPickupWheel = new Victor(RobotMap.leftCanPickupArm);
	Victor rightPickupWheel = new Victor(RobotMap.rightCanPickupArm);

	/**
	 * Accepts a value in the form of a double or float from -1 to 1 and takes
	 * the absolute value of it and inverts it for the left pickup motor so that
	 * a box will be ejected
	 * 
	 * @param speed
	 */
	public void spinOut(double speed)
	{
		speed = Math.min(Math.abs(speed), 1);
		leftPickupWheel.set(-speed);
		rightPickupWheel.set(speed*0.75);
	}

	/**
	 * Accepts a value in the form of a double or float from -1 to 1 and takes
	 * the absolute value of it and inverts it for the right pickup motor so
	 * that a box will be grabbed
	 * 
	 * @param speed
	 */
	public void spinIn(double speed)
	{
		speed = Math.min(Math.abs(speed), 1);
		leftPickupWheel.set(speed);
		rightPickupWheel.set(-speed*0.75);
	}

	/**
	 * Accepts a value in the form of a double or float from -1 to 1 and takes
	 * the absolute value of it to spin both of the pickup motors to the right
	 * 
	 * @param speed
	 */
	public void spinRight(double speed)
	{
		speed = Math.min(Math.abs(speed), 1);
		leftPickupWheel.set(speed);
		rightPickupWheel.set(speed*0.75);
	}

	/**
	 * Accepts a value in the form of a double or float from -1 to 1 and takes
	 * the absolute value of it and inverts it to spin both of the pickup motors
	 * to the left
	 * 
	 * @param speed
	 */
	public void spinLeft(double speed)
	{
		speed = Math.min(Math.abs(speed), 1);
		leftPickupWheel.set(-speed);
		rightPickupWheel.set(-speed*0.75);
	}

	/**
	 * Stops both of the pickup motors for idling when there is no user input
	 */
	public void idle()
	{
		leftPickupWheel.set(0);
		rightPickupWheel.set(0);
	}

	public void initDefaultCommand()
	{
		setDefaultCommand(new RunCanPickupArmsFromJoystick());
	}
}