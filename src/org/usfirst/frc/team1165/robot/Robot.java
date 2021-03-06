package org.usfirst.frc.team1165.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team1165.robot.commands.MoveContainerOutOfWay;
import org.usfirst.frc.team1165.robot.commands.MoveRobotAndToteToAutoZone;
import org.usfirst.frc.team1165.robot.commands.MoveRobotToAutoZone;
import org.usfirst.frc.team1165.robot.commands.MoveRobotToAutoZoneWithSetTote;
import org.usfirst.frc.team1165.robot.commands.MoveRobotAndContainerToAutoZone;
import org.usfirst.frc.team1165.robot.commands.RotateAndStrafeToPushContainerOutOfWay;
import org.usfirst.frc.team1165.robot.commands.RotateToPushContainerOutOfWay;
import org.usfirst.frc.team1165.robot.subsystems.CanPickupArms;
import org.usfirst.frc.team1165.robot.subsystems.DriveTrain;
import org.usfirst.frc.team1165.robot.subsystems.QuadEncoder;
import org.usfirst.frc.team1165.robot.subsystems.Gyroscope;
import org.usfirst.frc.team1165.robot.subsystems.RangeFinder;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot
{
	public static final CanPickupArms canPickupArms = new CanPickupArms();
	public static final DriveTrain driveTrain = new DriveTrain();
	public static final RangeFinder rangeFinder = new RangeFinder();
	public static final Gyroscope gyroscope = new Gyroscope();
	public static final QuadEncoder quadEncoder = new QuadEncoder();
	public static OI oi;
	//public static CameraServer camera = new CameraServer();
	SendableChooser autoChooser; 
	Command autonomousCommand;

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	public void robotInit()
	{
		oi = new OI();
		autoChooser = new SendableChooser();
		autoChooser.addObject("Move Robot To Auto Zone", new MoveRobotToAutoZone(0.5,RobotMap.DISTANCE_TO_AUTO_ZONE, RobotMap.DRIVE_CREEP_SPEED, 30)); //"Auto Speed", "Auto Inches"));
		autoChooser.addObject("Single Tote", new MoveRobotAndToteToAutoZone());
		autoChooser.addObject("MoveRobotAndContainerToAutoZone", new MoveRobotAndContainerToAutoZone());
		autoChooser.addObject("MoveRobotToAutoZoneWithSetTote", new MoveRobotToAutoZoneWithSetTote());
		autoChooser.addDefault("MoveContainerOutOfWay", new MoveContainerOutOfWay());
		autoChooser.addObject("RotateToPushContainerOutOfWay", new RotateToPushContainerOutOfWay());
		autoChooser.addObject("RotateAndStrafeToPushContainerOutOfWay", new RotateAndStrafeToPushContainerOutOfWay());
		SmartDashboard.putData("Auto:", autoChooser);
		//CameraServer camera = new CameraServer();
		//CameraServer.getInstance();
		//camera.setQuality(50);
		//camera.startAutomaticCapture("cam0");

		// instantiate the command used for the autonomous period
		//autonomousCommand = new MoveRobotToAutoZoneWithSetTote();
	}

	public void disabledPeriodic()
	{
		Scheduler.getInstance().run();
	}

	public void autonomousInit()
	{
		// schedule the autonomous command (example)
		autonomousCommand = (Command) autoChooser.getSelected();
		autonomousCommand.start();
	}

	/**
	 * This function is called periodically during autonomous
	 */
	public void autonomousPeriodic()
	{
		Scheduler.getInstance().run();
	}

	public void teleopInit()
	{
		// This makes sure that the autonomous stops running when
		// teleop starts running. If you want the autonomous to
		// continue until interrupted by another command, remove
		// this line or comment it out.
		if (autonomousCommand != null)
		{
			autonomousCommand.cancel();
		}
	}

	/**
	 * This function is called when the disabled button is hit. You can use it
	 * to reset subsystems before shutting down.
	 */
	public void disabledInit()
	{

	}

	/**
	 * This function is called periodically during operator control
	 */
	public void teleopPeriodic()
	{
		Scheduler.getInstance().run();
	}

	/**
	 * This function is called periodically during test mode
	 */
	public void testPeriodic()
	{
		LiveWindow.run();
	}
}
