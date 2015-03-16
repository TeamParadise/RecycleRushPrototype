package org.usfirst.frc.team1165.robot.commands;

import org.usfirst.frc.team1165.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class MovePickupArmsDown extends Command
{

	public MovePickupArmsDown()
	{
		super(1.2);
		requires( Robot.canPickupArms);
	}

	public MovePickupArmsDown(double timelimit)
	{
		super(timelimit);
		requires( Robot.canPickupArms);
	}

	// Called just before this Command runs the first time
	protected void initialize()
	{
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute()
	{
		Robot.canPickupArms.moveDown(0.5);
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished()
	{
		return isTimedOut();
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
