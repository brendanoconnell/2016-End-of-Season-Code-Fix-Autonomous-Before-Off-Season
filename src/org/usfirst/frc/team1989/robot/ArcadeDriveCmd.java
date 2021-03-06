package org.usfirst.frc.team1989.robot;

import java.util.ArrayList;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class ArcadeDriveCmd implements cmd{

	public String type = "arcadeDriveCmd";
	public ArrayList<cmd> list;
	JsScaled driveStick;
	public double offset = 0;
	RobotDrive rd;

	/*
	 * Main controller for use.  Based on 4 motors and a speed controller.
	 */
	public ArcadeDriveCmd(int frontLeftMotor, int rearLeftMotor, int frontRightMotor, int rearRightMotor, JsScaled driveStick) {
		rd = new RobotDrive(frontLeftMotor, rearLeftMotor, frontRightMotor, rearRightMotor);
		this.driveStick = driveStick;

	}
		
	// Included for completeness, but unused
	public ArcadeDriveCmd(SpeedController leftMotor, SpeedController rightMotor, JsScaled driveStick) {
		rd = new RobotDrive(leftMotor, rightMotor);
		this.driveStick = driveStick;
	}
	public ArcadeDriveCmd(int leftMotorChannel, int rightMotorChannel, JsScaled driveStick) {
		rd = new RobotDrive(leftMotorChannel, rightMotorChannel);
		this.driveStick = driveStick;
	}
	
	
	public ArcadeDriveCmd(SpeedController frontLeftMotor, SpeedController rearLeftMotor, SpeedController frontRightMotor, SpeedController rearRightMotor, JsScaled driveStick) {
		rd = new RobotDrive(frontLeftMotor, rearLeftMotor, frontRightMotor, rearRightMotor);
		this.driveStick = driveStick;
	}
	
	// Autonomous Function - Arcade Drive dependency
	public void arcadeDrive(double magnitude, double stwist){
		
		rd.arcadeDrive(magnitude, stwist);
	}

	@Override
	public void autonomousInit() {
		// TODO Auto-generated method stub

	}

	@Override
	public void autonomousPeriodic() {
		
		arcadeDrive(driveStick.pY, driveStick.pTwist + offsetCalc());
		// TODO Auto-generated method stub

	}

		
	@Override
	public void testInit() {
		// TODO Auto-generated method stub

	}

	@Override
	public void teleopInit() {
		// TODO Auto-generated method stub

	}

	public double offsetCalc(){
		
		//SharedStuff.msg[1] = "Y Value: " + driveStick.sgetY();
		if(driveStick.sgetTwist() <= 0.25 || driveStick.sgetTwist() >= -0.25){
			return 0.0;
			
		}else {
			if(driveStick.sgetY() >= .15 || driveStick.sgetY() <= -.15){
				return .8;
			}
			
		}
		return 0.0;
	}
	
	@Override
	public void teleopPeriodic() {
		// TODO Auto-generated method stub
		if(driveStick.getRawButton(7) == true) {
			arcadeDrive(0 - driveStick.sgetY(), 0 - driveStick.sgetTwist()*.45 + offsetCalc());
		} else {
			arcadeDrive(0 - driveStick.sgetY(), 0-driveStick.sgetTwist()*.75 + offsetCalc());
		}
	}

	@Override
	public void testPeriodic() {
		// TODO Auto-generated method stub
		arcadeDrive(0 - driveStick.sgetY(), 0-driveStick.sgetTwist()*.75 + (driveStick.sgetTwist() !=0 ? offset : 0));
		
	}
}