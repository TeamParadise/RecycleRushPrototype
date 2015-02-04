package org.usfirst.frc.team1165.robot.subsystems;

import org.usfirst.frc.team1165.robot.RobotMap;

import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class EncoderInit extends Subsystem {
	
	public Encoder encoder;
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	public EncoderInit()
	{
	encoder = new Encoder(RobotMap.encoderChannelA, RobotMap.encoderChannelB, true, EncodingType.k4X ); 
	}

    public void initDefaultCommand() 
    {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

