package org.usfirst.frc.team1165.robot.commands;

import org.usfirst.frc.team1165.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class RunCanPickupArmsFromJoystick extends Command
{

	public RunCanPickupArmsFromJoystick()
	{
		requires(Robot.canPickupArms);
	}

	// Called just before this Command runs the first time
	protected void initialize()
	{
		Robot.canPickupArms.idle();
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute()
	{
		final double pickupSpeedX = Robot.oi.canPickupSpeedX();
		final double pickupSpeedY = Robot.oi.canPickupSpeedY();
		
		final double magnitudeX = Math.abs(pickupSpeedX);
		final double magnitudeY = Math.abs(pickupSpeedY);

		if (magnitudeY > 0.1 && magnitudeY > magnitudeX * 2)
		{
			if (pickupSpeedY > 0)
			{
				Robot.canPickupArms.moveUp(magnitudeY);
			}
			else
			{
				Robot.canPickupArms.moveDown(magnitudeY);
			}
		}
		else
		{
			Robot.canPickupArms.idle();
		}
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished()
	{
		return false;
	}

	// Called once after isFinished returns true
	protected void end()
	{
		Robot.canPickupArms.idle();
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted()
	{
		end();
	}
}