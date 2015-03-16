package org.usfirst.frc.team1165.robot.commands;

import org.usfirst.frc.team1165.robot.RobotMap;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class RotateToPushContainerOutOfWay extends CommandGroup
{

	public RotateToPushContainerOutOfWay()
	{
		addSequential(new MovePickupArmsUp());
		addSequential(new DriveToObject(RobotMap.DRIVE_SPEED , 40 , 6 , RobotMap.DRIVE_CREEP_SPEED , 84 ));
		addSequential(new RotateToHeading(RobotMap.ROTATE_SPEED , 15 , -45 , RobotMap.ROTATE_CREEP_SPEED));
		addSequential(new DriveStraightDistance(RobotMap.DRIVE_SPEED,15));
		addSequential(new RotateToHeading(RobotMap.ROTATE_SPEED , 15 , 45 , RobotMap.ROTATE_CREEP_SPEED));
		addSequential(new StrafeWithTimeout(-RobotMap.DRIVE_SPEED , 0.8));
		addSequential(new DriveToObject(RobotMap.DRIVE_SPEED , 40 , 6 , RobotMap.DRIVE_CREEP_SPEED , 55 ));
		addSequential(new RotateToHeading(RobotMap.ROTATE_SPEED , 15 , -90 , RobotMap.ROTATE_CREEP_SPEED));
		addSequential(new DriveStraightDistance(RobotMap.DRIVE_SPEED,  RobotMap.DISTANCE_TO_AUTO_ZONE ));
	
	}
}
