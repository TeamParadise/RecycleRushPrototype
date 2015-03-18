package org.usfirst.frc.team1165.robot.commands;

import org.usfirst.frc.team1165.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class DriveStraightDistance extends Command
{
	private String forwardSpeedKey;
	private String driveInchesKey;

	private double forwardSpeed;
	private double driveInches;
	private boolean resetWhenFinished = false;
	
	public DriveStraightDistance(String forwardSpeedKey, String driveInchesKey) 
	{
		requires(Robot.driveTrain);
		requires(Robot.gyroscope);
		requires(Robot.quadEncoder);
		this.forwardSpeedKey = forwardSpeedKey;
		this.driveInchesKey = driveInchesKey;
	}

	public DriveStraightDistance(double forwardSpeed, double driveInches) 
	{
		requires(Robot.driveTrain);
		requires(Robot.gyroscope);
		requires(Robot.quadEncoder);
		this.forwardSpeed = forwardSpeed;
		this.driveInches = driveInches;
		forwardSpeedKey = null;
	}

	public DriveStraightDistance(double forwardSpeed, double driveInches, boolean resetWhenFinished) 
	{
		requires(Robot.driveTrain);
		requires(Robot.gyroscope);
		requires(Robot.quadEncoder);
		this.forwardSpeed = forwardSpeed;
		this.driveInches = driveInches;
		this.resetWhenFinished = resetWhenFinished;
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
	}
	
	private void ResetSensors()
	{
		Robot.gyroscope.reset();
		Robot.quadEncoder.reset();
	}

	protected void execute()
	{
		double twistCorrection = Robot.gyroscope.getTwistCorrection();
		
		Robot.driveTrain.driveCartesian(forwardSpeed, 0, twistCorrection, 0);
	}
 
	protected boolean isFinished()
	{
		boolean finished = Math.abs(Robot.quadEncoder.getInches()) > Math.abs(driveInches);
		if (finished && resetWhenFinished) ResetSensors();
		return finished;
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
