package org.usfirst.frc.team1165.robot.commands;

import org.usfirst.frc.team1165.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DriveToObject extends Command
{
	private String forwardMagnitudeKey;
	private String brakeRangeKey;
	private String targetRangeKey;
	private String creepMagnitudeKey;

	private double forwardMagnitude;
	private double brakeRange;
	private double targetRange;
	private double creepMagnitude;
	private double neverMore;

	private double currentRange;
	private double previousRange;
	private double twistCorrection;
	
	private boolean isCreeping;
	
	public DriveToObject(String forwardMagnitudeKey, String brakeRangeKey, String targetRangeKey, String creepMagnitudeKey)
	{
		requires(Robot.driveTrain);
		this.forwardMagnitudeKey = forwardMagnitudeKey;
		this.brakeRangeKey = brakeRangeKey;
		this.targetRangeKey = targetRangeKey;
		this.creepMagnitudeKey = creepMagnitudeKey;
	}

	public DriveToObject(double forwardMagnitude, double brakeRange, double targetRange, double creepMagnitude, double neverMore)
	{
		requires(Robot.driveTrain);
		this.forwardMagnitude = forwardMagnitude;
		this.brakeRange = brakeRange;
		this.targetRange = targetRange;
		this.creepMagnitude = creepMagnitude;
		this.neverMore = neverMore;
		forwardMagnitudeKey = null;
	}

	protected void initialize()
	{
		if (null != forwardMagnitudeKey)
		{
			forwardMagnitude = SmartDashboard.getNumber(forwardMagnitudeKey);
			brakeRange = SmartDashboard.getNumber(brakeRangeKey);
			targetRange = SmartDashboard.getNumber(targetRangeKey);
			creepMagnitude = SmartDashboard.getNumber(creepMagnitudeKey);
		}
		Robot.quadEncoder.reset();
		Robot.gyroscope.reset();
		isCreeping = false;
		previousRange = 0;
	}

	protected void execute()
	{
		SmartDashboard.putBoolean("Is creeping", isCreeping);
		
		// We drive forward until we reach brakeRange.
		// We then reverse the motors until we come to a stop.
		// We then creep forwards until we reach range.
		currentRange = Robot.rangeFinder.getRange();
		twistCorrection = Robot.gyroscope.getTwistCorrection();
		
		if (currentRange > brakeRange)
		{
			Robot.driveTrain.driveCartesian(forwardMagnitude, 0, twistCorrection, 0);
		}
		else if (!isCreeping && previousRange > currentRange)
		{
			Robot.driveTrain.driveCartesian(-forwardMagnitude, 0, twistCorrection, 0);
		}
		else
		{ 
			isCreeping = true;
			Robot.driveTrain.driveCartesian(creepMagnitude, 0, twistCorrection, 0);
		}

		previousRange = currentRange;
	}

	protected boolean isFinished()
	{
		return Robot.rangeFinder.getRange() <= targetRange || (Math.abs(Robot.quadEncoder.getInches()) >= neverMore);
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
