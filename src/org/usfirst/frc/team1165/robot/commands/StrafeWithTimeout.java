package org.usfirst.frc.team1165.robot.commands;

import org.usfirst.frc.team1165.robot.Robot;
import org.usfirst.frc.team1165.robot.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class StrafeWithTimeout extends Command
{

	private double timeout;
	private double speed;
	private boolean resetWhenFinished = false;

	
	public StrafeWithTimeout(double speed,double timeout)
	{
		super(timeout);
		requires(Robot.driveTrain);
		this.timeout = timeout;
		this.speed = speed;
	}

	public StrafeWithTimeout(double speed,double timeout, boolean resetWhenFinished)
	{
		super(timeout);
		requires(Robot.driveTrain);
		this.timeout = timeout;
		this.speed = speed;
		this.resetWhenFinished = resetWhenFinished;
	}

	// Called just before this Command runs the first time
	protected void initialize()
	{
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute()
	{
		Robot.driveTrain.driveCartesian(0,-speed,0,0);
	}

	private void ResetSensors()
	{
		Robot.gyroscope.reset();
		Robot.quadEncoder.reset();
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished()
	{
		boolean finished = isTimedOut();
		if (finished && resetWhenFinished) ResetSensors();
		return finished;
	}

	// Called once after isFinished returns true
	protected void end()
	{
		Robot.driveTrain.driveCartesian(0, 0, 0, 0);
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted()
	{
		end();
	}
}
