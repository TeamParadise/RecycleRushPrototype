package org.usfirst.frc.team1165.robot.commands;

import org.usfirst.frc.team1165.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DriveToObject extends Command
{
	private String forwardSpeedKey;
	private String brakeRangeKey;
	private String targetRangeKey;
	private String creepSpeedKey;

	private double forwardSpeed;
	private double brakeRange;
	private double targetRange;
	private double creepSpeed;
	private double neverMore;

	private double currentRange;
	private double previousRange;
	private double twistCorrection;
	
	private boolean isCreeping;
	
	public DriveToObject(String forwardSpeedKey, String brakeRangeKey, String targetRangeKey, String creepSpeedKey)
	{
		requires(Robot.driveTrain);
		requires(Robot.gyroscope);
		requires(Robot.quadEncoder);
		this.forwardSpeedKey = forwardSpeedKey;
		this.brakeRangeKey = brakeRangeKey;
		this.targetRangeKey = targetRangeKey;
		this.creepSpeedKey = creepSpeedKey;
	}

	public DriveToObject(double forwardSpeed, double brakeRange, double targetRange, double creepSpeed, double neverMore)
	{
		requires(Robot.driveTrain);
		requires(Robot.gyroscope);
		requires(Robot.quadEncoder);
		this.forwardSpeed = forwardSpeed;
		this.brakeRange = brakeRange;
		this.targetRange = targetRange;
		this.creepSpeed = creepSpeed;
		this.neverMore = neverMore;
		forwardSpeedKey = null;
	}

	protected void initialize()
	{
		if (null != forwardSpeedKey)
		{
			forwardSpeed = SmartDashboard.getNumber(forwardSpeedKey);
			brakeRange = SmartDashboard.getNumber(brakeRangeKey);
			targetRange = SmartDashboard.getNumber(targetRangeKey);
			creepSpeed = SmartDashboard.getNumber(creepSpeedKey);
		}
		ResetSensors();
		isCreeping = false;
		//in case the encode did not reset
		neverMore += Robot.quadEncoder.getInches();
		//let's check if the sonar is already readin something to close
		//and only use nevermore if that's the case
		if (Robot.rangeFinder.getRange() <= brakeRange/2) targetRange = 4;
		previousRange = 0;
	}

	private void ResetSensors()
	{
		//Robot.gyroscope.reset();
		//Robot.quadEncoder.reset();
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
			Robot.driveTrain.driveCartesian(forwardSpeed, 0, twistCorrection, 0);
		}
		else if (!isCreeping && previousRange > currentRange)
		{
			Robot.driveTrain.driveCartesian(-forwardSpeed, 0, twistCorrection, 0);
		}
		else
		{ 
			isCreeping = true;
			Robot.driveTrain.driveCartesian(creepSpeed, 0, twistCorrection, 0);
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
