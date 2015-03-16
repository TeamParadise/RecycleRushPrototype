package org.usfirst.frc.team1165.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import org.usfirst.frc.team1165.robot.RobotMap;

/**
 *
 */
public class MoveRobotAndContainerToAutoZone extends CommandGroup
{

	public MoveRobotAndContainerToAutoZone()
	{
		//Autonomous Code for picking up container and moving to the AutoZone
		addSequential(new MovePickupArmsUp());
		addSequential(new RotateToHeading(RobotMap.ROTATE_SPEED , 15 , -90 , RobotMap.ROTATE_CREEP_SPEED));
		addSequential(new DriveStraightDistance(RobotMap.DRIVE_SPEED, RobotMap.DISTANCE_TO_AUTO_ZONE));

	}
}
