package org.usfirst.frc.team1165.robot.commands;

import org.usfirst.frc.team1165.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ReturnToZero extends Command
{
	private double brakeOffset;
	private double targetHeading;
	private double rotateMagnitude;
	private double creepMagnitude;

	private double currentHeading;
	private double previousHeading; 
	private boolean isCreeping;
	private double sign;

	public ReturnToZero()
	{
		requires(Robot.driveTrain);
		brakeOffset = 15;
		targetHeading = 0;
		rotateMagnitude = 1;
		creepMagnitude = 0.2;
	}

	// Called just before this Command runs the first time
	protected void initialize()
	{
		isCreeping = Math.abs(targetHeading) < brakeOffset;
		sign = targetHeading < 0 ? -1 : 1;
		previousHeading = 0;
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute()
	{
		currentHeading = Robot.gyroscope.getHeading();
		
		if (Math.abs(currentHeading) < (Math.abs(targetHeading) - brakeOffset))
		{
			Robot.driveTrain.driveCartesian(0, 0, sign * rotateMagnitude, 0);
		}
		else if (!isCreeping && Math.abs(previousHeading) < Math.abs(currentHeading))
		{
			Robot.driveTrain.driveCartesian(0, 0, -sign * rotateMagnitude, 0);
		}
		else
		{ 
			isCreeping = true;
			Robot.driveTrain.driveCartesian(0, 0, sign * creepMagnitude, 0);
		}

		previousHeading = currentHeading;
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished()
	{
		return  Math.abs(Robot.gyroscope.getHeading()) >= Math.abs(targetHeading);
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
