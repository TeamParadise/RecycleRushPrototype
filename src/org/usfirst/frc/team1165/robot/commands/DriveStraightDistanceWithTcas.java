package org.usfirst.frc.team1165.robot.commands;

import org.usfirst.frc.team1165.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class DriveStraightDistanceWithTcas extends Command
{
	private String straightMagnitudeKey;
	private String straightInchesKey;

	private double straightMagnitude;
	private double straightInches;
	private double tcas;
	private boolean didWeAvoidOneThing = false;
	private boolean didWeAvoidThings = false;
	private boolean weSeeSomething = false;
	
	/**
	 * Uses values from the smart dashboard to drive the robot
	 * 
	 * @param forwardMagnitudeKey
	 * @param directionKey
	 * @param timeoutKey
	 * @param rotationKey
	 */
	public DriveStraightDistanceWithTcas(String straightMagnitudeKey, String straightInchesKey) 
	{
		requires(Robot.driveTrain);
		this.straightMagnitudeKey = straightMagnitudeKey;
		this.straightInchesKey = straightInchesKey;
	}

	public DriveStraightDistanceWithTcas(double straightMagnitude, double straightInches, double tcas) 
	{
		requires(Robot.driveTrain);
		this.straightMagnitude = straightMagnitude;
		this.straightInches = straightInches;
		this.tcas = tcas;
		straightMagnitudeKey = null;
	}

	// Called just before this Command runs the first time
	protected void initialize()
	{
		// See if we are using values from the smart dashboard:
		if (null != straightMagnitudeKey)
		{
			straightMagnitude = SmartDashboard.getNumber(straightMagnitudeKey);
			straightInches = SmartDashboard.getNumber(straightInchesKey);
		}
		
		Robot.gyroscope.reset();
		Robot.quadEncoder.reset();
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute()
	{
		double twistCorrection = Robot.gyroscope.getTwistCorrection();
		if (Robot.rangeFinder.getRange() < tcas) 
		{ 
			SmartDashboard.putNumber("We see something at", Robot.rangeFinder.getRange());
			if (!weSeeSomething) 
			{
				weSeeSomething = true;
				didWeAvoidThings = !didWeAvoidThings;
			}
			if (!didWeAvoidThings)
			{
				twistCorrection = 0.3;
			}
			else
			{
				twistCorrection = -0.3;
			}
			if (!didWeAvoidOneThing)
			{
				didWeAvoidOneThing = true;
				straightInches *= 1.3;
			}
			Robot.gyroscope.reset();
		}
		else
		{
			weSeeSomething = false;
		}
		
		Robot.driveTrain.driveCartesian(straightMagnitude, 0, twistCorrection, 0);
	}
 
	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished()
	{
		return Math.abs(Robot.quadEncoder.getInches()) > straightInches;
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
