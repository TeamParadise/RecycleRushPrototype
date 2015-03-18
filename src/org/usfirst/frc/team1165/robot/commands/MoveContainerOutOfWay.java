package org.usfirst.frc.team1165.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

import org.usfirst.frc.team1165.robot.Robot;
import org.usfirst.frc.team1165.robot.RobotMap;

/**
 *
 */
public class MoveContainerOutOfWay extends CommandGroup
{

	public MoveContainerOutOfWay()
	{
		//addSequential(new MovePickupArmsUp());
		//addSequential(new DriveStraightDistance(RobotMap.DRIVE_SPEED,70));
		addSequential(new DriveToObject(RobotMap.DRIVE_SPEED , 40 , 6 , RobotMap.DRIVE_CREEP_SPEED , 84 ));
		//addSequential(new MovePickupArmsDown());
		addSequential(new StrafeWithTimeout(RobotMap.DRIVE_SPEED ,1,true));
		//addSequential(new ReturnToZero());
		addSequential(new DriveStraightDistance(RobotMap.DRIVE_SPEED , 24, true));
		addSequential(new WaitCommand(0.3));
		//addSequential(new MovePickupArmsDown(0.3));
		//addSequential(new ReturnToZero());
		addSequential(new StrafeWithTimeout(-RobotMap.DRIVE_SPEED , 1.2, true));
		addSequential(new DriveToObject(RobotMap.DRIVE_SPEED , 40 , 6 , RobotMap.DRIVE_CREEP_SPEED , 40 ));
		addSequential(new RotateToHeading(RobotMap.ROTATE_SPEED , 15 , -90 , RobotMap.ROTATE_CREEP_SPEED));
		addSequential(new DriveStraightDistance(RobotMap.DRIVE_SPEED,  RobotMap.DISTANCE_TO_AUTO_ZONE ));
	}
}