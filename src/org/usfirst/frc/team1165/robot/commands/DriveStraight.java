package org.usfirst.frc.team1165.robot.commands;

import org.usfirst.frc.team1165.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class DriveStraight extends Command
{
	private String straightMagnitudeKey;

	private double straightMagnitude;
	
	public DriveStraight(String straightMagnitudeKey, double timeout) 
	{
		super(timeout);
		requires(Robot.driveTrain);
		this.straightMagnitudeKey = straightMagnitudeKey;
	}

	protected void initialize()
	{
		if (null != straightMagnitudeKey)
		{
			straightMagnitude = SmartDashboard.getNumber(straightMagnitudeKey);
		}
		
		Robot.gyroscope.reset();
	}

	protected void execute()
	{
		double twistCorrection = Robot.gyroscope.getTwistCorrection();
		
		Robot.driveTrain.driveCartesian(straightMagnitude, 0, twistCorrection, 0);
	}

	protected boolean isFinished()
	{
		return isTimedOut();
	}

	protected void end()
	{
		Robot.driveTrain.driveCartesian(0, 0, 0, 0);
	}

	protected void interrupted()
	{
		end();
	}
}
