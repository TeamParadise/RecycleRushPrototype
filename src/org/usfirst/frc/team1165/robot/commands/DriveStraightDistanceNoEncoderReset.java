package org.usfirst.frc.team1165.robot.commands;

import org.usfirst.frc.team1165.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class DriveStraightDistanceNoEncoderReset extends Command
{
	private String forwardSpeedKey;
	private String driveInchesKey;

	private double forwardSpeed;
	private double driveInches;
	private boolean finishedWhenGreater = true;
	
	public DriveStraightDistanceNoEncoderReset(String forwardSpeedKey, String driveInchesKey) 
	{
		requires(Robot.driveTrain);
		this.forwardSpeedKey = forwardSpeedKey;
		this.driveInchesKey = driveInchesKey;
	}

	public DriveStraightDistanceNoEncoderReset(double forwardSpeed, double driveInches) 
	{
		requires(Robot.driveTrain);
		this.forwardSpeed = forwardSpeed;
		this.driveInches = driveInches;
		forwardSpeedKey = null;
	}

	protected void initialize()
	{
		if (null != forwardSpeedKey)
		{
			forwardSpeed = SmartDashboard.getNumber(forwardSpeedKey);
			driveInches = SmartDashboard.getNumber(driveInchesKey);
		}
		ResetSensors();
		driveInches += Robot.quadEncoder.getInches();
		finishedWhenGreater = driveInches > Robot.quadEncoder.getInches();
	}
	
	private void ResetSensors()
	{
		Robot.gyroscope.reset();
		//Robot.quadEncoder.reset();
	}

	protected void execute()
	{
		//double currentRange = Robot.rangeFinder.getRange();
		double twistCorrection = Robot.gyroscope.getTwistCorrection();
		
		Robot.driveTrain.driveCartesian(forwardSpeed, 0, twistCorrection, 0);
	}
 
	protected boolean isFinished()
	{
		//SmartDashboard.putNumber("Encoder Inches",Robot.quadEncoder.getInches());
		//SmartDashboard.putNumber("End Inches",driveInches);
		//return Math.abs(Robot.quadEncoder.getInches()) > Math.abs(driveInches);
		if (finishedWhenGreater)
			return Robot.quadEncoder.getInches() > driveInches;
		else	
			return Robot.quadEncoder.getInches() < driveInches;
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
