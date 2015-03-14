package org.usfirst.frc.team1165.robot.subsystems;

import org.usfirst.frc.team1165.robot.RobotMap;
import org.usfirst.frc.team1165.robot.commands.RunCanPickupArmsFromJoystick;

import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;

public class CanPickupArms extends Subsystem
{
	Victor leftPickupArm = new Victor(RobotMap.leftCanPickupArm);
	Victor rightPickupArm = new Victor(RobotMap.rightCanPickupArm);

	/**
	 * Accepts a value in the form of a double or float from -1 to 1 and takes
	 * the absolute value of it and inverts it for the left pickup motor so that
	 * the arms will be moved up.
	 * 
	 * @param speed
	 */
	public void moveUp(double speed)
	{
		speed = Math.min(Math.abs(speed), 1);
		leftPickupArm.set(-speed);
		rightPickupArm.set(speed*0.75);
	}

	/**
	 * Accepts a value in the form of a double or float from -1 to 1 and takes
	 * the absolute value of it and inverts it for the right pickup motor so
	 * that the arms will be moved down.
	 * 
	 * @param speed
	 */
	public void moveDown(double speed)
	{
		speed = Math.min(Math.abs(speed), 1);
		leftPickupArm.set(speed);
		rightPickupArm.set(-speed*0.75);
	}

	/**
	 * Stops both of the pickup motors for idling when there is no user input
	 */
	public void idle()
	{
		leftPickupArm.set(0);
		rightPickupArm.set(0);
	}

	public void initDefaultCommand()
	{
		setDefaultCommand(new RunCanPickupArmsFromJoystick());
	}
}
