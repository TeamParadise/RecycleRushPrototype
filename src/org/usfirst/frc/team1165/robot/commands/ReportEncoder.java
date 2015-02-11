package org.usfirst.frc.team1165.robot.commands;

import org.usfirst.frc.team1165.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class ReportEncoder extends Command
{

	public ReportEncoder()
	{
		requires(Robot.quadEncoder);		
	}

	protected void initialize()
	{
		Robot.quadEncoder.reset();		
	}

	protected void execute()
	{
		Robot.quadEncoder.report();
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
