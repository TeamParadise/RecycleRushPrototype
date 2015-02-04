package org.usfirst.frc.team1165.robot.commands;

import org.usfirst.frc.team1165.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class Rotate extends Command
{

	private double sign;
	private String targetAngleKey;
	private double targetAngle;
	private double rotateMagnitude;
	private int brakeCount;

	public Rotate(String targetAngleKey)
	{
		requires(Robot.driveTrain);
		this.targetAngleKey = targetAngleKey;

		SmartDashboard.putNumber("Rotate Magnitude", .2);
	}

	public double getTargetAdjustment()
	{
		if (Math.abs(rotateMagnitude) < 0.4)
			return 1 * sign;
		if (Math.abs(rotateMagnitude) < 0.7)
		{
			if (Math.abs(targetAngle) < 20)
				return 15 * sign;
			return 17 * sign;
		}
		if (Math.abs(targetAngle) <= 45)
			return 33 * sign;
		return 40 * sign;
	}

	// Called just before this Command runs the first time
	protected void initialize()
	{
		Robot.gyroscope.reset();
		targetAngle = SmartDashboard.getNumber(targetAngleKey);
		rotateMagnitude = SmartDashboard.getNumber("Rotate Magnitude");
		brakeCount = 0;
		sign = targetAngle < 0 ? -1 : 1;
		if (targetAngle < 0)
		{
			rotateMagnitude = -rotateMagnitude;
		}
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute()
	{
		if (Math.abs(Robot.gyroscope.getHeading()) < Math.abs(targetAngle
				- getTargetAdjustment()))
		{
			Robot.driveTrain.driveCartesian(0, 0, rotateMagnitude, 0);
		}
		else if (brakeCount < 3)
		{
			Robot.driveTrain.driveCartesian(0, 0, 0, 0);
			brakeCount++;
		}
		else
			Robot.driveTrain.driveCartesian(0, 0, 0.2 * sign, 0);
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished()
	{
		return (Math.floor(Math.abs(Robot.gyroscope.getHeading())) >= Math
				.abs(targetAngle));
	}

	// Called once after isFinished returns true
	protected void end()
	{
		Robot.driveTrain.driveCartesian(0, 0, -rotateMagnitude, 0);
		Robot.driveTrain.driveCartesian(0, 0, 0, 0);
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted()
	{
		end();
	}
}
