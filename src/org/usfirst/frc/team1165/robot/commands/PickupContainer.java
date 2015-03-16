package org.usfirst.frc.team1165.robot.commands;

import org.usfirst.frc.team1165.robot.Robot;
import org.usfirst.frc.team1165.robot.subsystems.CanPickupArms;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class PickupContainer extends CommandGroup
{

	public PickupContainer()
	{
		addSequential(new MovePickupArmsDown());
		addSequential(new MovePickupArmsUp());
	}
}
