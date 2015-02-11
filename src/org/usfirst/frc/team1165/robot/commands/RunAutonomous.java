package org.usfirst.frc.team1165.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class RunAutonomous extends CommandGroup
{

	public RunAutonomous()
	{
		//addSequential(new PickUpTote());
		addSequential(new DriveToObject(-0.7, 40, 19, -0.2, 50)); 
		//addSequential(new PickUpContainer());
		addSequential(new WaitCommand(3.0));
		addSequential(new DriveToObject(-0.7, 45, 19, -0.2, 54)); 
		//addSequential(new PickUpTote());
		addSequential(new WaitCommand(3.0));
		addSequential(new RotateToHeading(.6, 15, 45, 0.2));
		addSequential(new DriveStraightDistance(-1, 53));
		addSequential(new RotateToHeading(.6, 30, -75, 0.2));
		addSequential(new DriveStraightDistance(-1, 65));
		//addSequential(new DriveToObject(-0.4, 30, 19, -0.2, 15));
		addSequential(new RotateToHeading(.6, 30, -75, 0.2));
		addSequential(new DriveStraightDistanceWithTcas(-0.4, 125, 60, 30));
	}
}
