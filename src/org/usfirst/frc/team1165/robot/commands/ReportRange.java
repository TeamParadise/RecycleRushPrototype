package org.usfirst.frc.team1165.robot.commands;

import org.usfirst.frc.team1165.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class ReportRange extends Command
{

	public ReportRange()
	{
		requires(Robot.rangeFinder);
	}

	protected void initialize()
	{
	}

	protected void execute()
	{
		SmartDashboard.putNumber("Range Inches", Robot.rangeFinder.getRange());
	}

	protected boolean isFinished()
	{
		return false;
	}

	protected void end()
	{
	}

	protected void interrupted()
	{
	}
}
