package org.usfirst.frc.team1165.robot.subsystems;

import org.usfirst.frc.team1165.robot.RobotMap;
import org.usfirst.frc.team1165.robot.commands.ReportEncoder;

import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class QuadEncoder extends Subsystem {
	
	private Encoder encoder;
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	public QuadEncoder()
	{
		encoder = new Encoder(RobotMap.encoderChannelA, RobotMap.encoderChannelB, true, EncodingType.k4X );
		reset();
	}

    public void initDefaultCommand() 
    {
        // Set the default command for a subsystem here.
        setDefaultCommand(new ReportEncoder());
    }
    
    public void report()
    {
    	//encoder.getDistance();
    	double distancePerTick = 6*Math.PI/280;
    	SmartDashboard.putNumber("Encoder Tick Distance", getTicks());
    	SmartDashboard.putNumber("Encoder Distance", distancePerTick*getTicks());
    }
    
    public double getInches()
    {
    	double distancePerTick = 6*Math.PI/280;
    	return distancePerTick*getTicks();
    }
    
    public double getStrafeInches() 
    {
    	double strafePerTick = 6*Math.PI/280/3;
    	return strafePerTick*getTicks();
    }
    
    public double getTicks() 
    {
    	return encoder.getDistance();
    }
    
    public void reset()
    {
    	encoder.reset();
    }
}

