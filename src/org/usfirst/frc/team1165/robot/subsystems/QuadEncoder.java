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
	
	private	double distancePerTick = 18.84  ;
	private Encoder encoder;
    
	public QuadEncoder()
	{
		encoder = new Encoder(RobotMap.encoderChannelA, RobotMap.encoderChannelB, true, EncodingType.k4X );
		reset();
	}

    public void initDefaultCommand() 
    {
        setDefaultCommand(new ReportEncoder());
    }
    
    public void report()
    {
    	SmartDashboard.putNumber("Encoder Tick Distance", getTicks());
    	SmartDashboard.putNumber("Encoder Distance", distancePerTick*getTicks());
    }
    
    public double getInches()
    {
    	return distancePerTick*getTicks();
    }
    
    public double getStrafeInches() 
    {
    	double strafePerTick = distancePerTick/3;
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
