package org.usfirst.frc.team1165.robot.commands;

import org.usfirst.frc.team1165.robot.RobotMap;
//import org.usfirst.frc.team1165.robot.commands.piston.MovePickupWheelsIn;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

public class MoveRobotAndToteToAutoZone extends CommandGroup
{

	public MoveRobotAndToteToAutoZone()
	{
		//addSequential(new PickupTote());
		addSequential(new WaitCommand(0.8));
		//addSequential(new MovePickupWheelsIn());
		addSequential(new RotateToHeading(RobotMap.ROTATE_SPEED, 15, -90, RobotMap.ROTATE_CREEP_SPEED));
		addSequential(new WaitCommand(0.3));
		addSequential(new DriveStraightDistance(0.4, RobotMap.DISTANCE_TO_AUTO_ZONE, RobotMap.DRIVE_CREEP_SPEED, 30  ));
	}
}