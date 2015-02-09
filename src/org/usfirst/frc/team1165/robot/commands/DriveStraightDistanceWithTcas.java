package org.usfirst.frc.team1165.robot.commands;

import org.usfirst.frc.team1165.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DriveStraightDistanceWithTcas extends Command
{
	private String straightMagnitudeKey;
	private String straightInchesKey;

	private double straightMagnitude;
	private double straightInches;
	private double tcas;
	private boolean didWeAvoidOneThing = false;
	
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

	protected void initialize()
	{
		if (null != straightMagnitudeKey)
		{
			straightMagnitude = SmartDashboard.getNumber(straightMagnitudeKey);
			straightInches = SmartDashboard.getNumber(straightInchesKey);
		}
		
		Robot.gyroscope.reset();
		Robot.quadEncoder.reset();
	}

	protected void execute()
	{
		double twistCorrection = Robot.gyroscope.getTwistCorrection();
		if (Robot.rangeFinder.getRange() < tcas) 
		{ 
			twistCorrection = -0.3;
			if (!didWeAvoidOneThing)
			{
				didWeAvoidOneThing = true;
				straightInches *= 1.2;
			}
		} 
		
		Robot.driveTrain.driveCartesian(straightMagnitude, 0, twistCorrection, 0);
	}
 
	protected boolean isFinished()
	{
		return Math.abs(Robot.quadEncoder.getInches()) > straightInches;
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
