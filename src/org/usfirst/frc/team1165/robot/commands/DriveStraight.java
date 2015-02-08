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
	//private String driveTimeKey;

	private double straightMagnitude;
	//private double driveTime;
	
	/**
	 * Uses values from the smart dashboard to drive the robot
	 * 
	 * @param forwardMagnitudeKey
	 * @param directionKey
	 * @param timeoutKey
	 * @param rotationKey
	 */
	public DriveStraight(String straightMagnitudeKey, double timeout) 
	{
		super(timeout);
		requires(Robot.driveTrain);
		this.straightMagnitudeKey = straightMagnitudeKey;
		//this.driveTimeKey = driveTimeKey;
	}

	// Called just before this Command runs the first time
	protected void initialize()
	{
		// See if we are using values from the smart dashboard:
		if (null != straightMagnitudeKey)
		{
			straightMagnitude = SmartDashboard.getNumber(straightMagnitudeKey);
		//	driveTime = SmartDashboard.getNumber(driveTimeKey);
		}
		
		Robot.gyroscope.reset();
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute()
	{
		double twistCorrection = Robot.gyroscope.getTwistCorrection();
		
		Robot.driveTrain.driveCartesian(straightMagnitude, 0, twistCorrection, 0);
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished()
	{
		return isTimedOut();
		//Robot.rangeFinder.getRange() <= targetRange;
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
