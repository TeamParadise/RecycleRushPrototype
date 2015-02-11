package org.usfirst.frc.team1165.robot.commands;

import org.usfirst.frc.team1165.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class ReportGyroscope extends Command
{

	public ReportGyroscope()
	{
		requires(Robot.gyroscope);
	}

	protected void initialize()
	{
	}

	protected void execute()
	{
		SmartDashboard.putNumber("Gyro Heading", Robot.gyroscope.getHeading());
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
