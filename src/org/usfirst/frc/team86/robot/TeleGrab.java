/*
 This is the version of the cube grabber that will be used in teleop stage for the robot
 Driver controls grabber by:
 
 Push joystick forward: spits cube out
 Push joystick back: sucks cube in
 push right: adjusts right
 push left: adjusts left
 */
package org.usfirst.frc.team86.robot;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Victor;

public class TeleGrab {
	private Victor mot1;
	private Victor mot2;
	private DigitalInput banSens;
	private int state = 0;
	private Solenoid grab1;
	private Solenoid grab2;

	public TeleGrab(Victor mot1, Victor mot2, Solenoid grab1, Solenoid grab2) {
		this.mot1 = mot1;
		this.mot2 = mot2;
		this.grab1 = grab1;
		this.grab2 = grab2;
			}

	public void grabberInit() {
		mot1.set(0);
		mot2.set(0);
		grab1.set(true);
		grab2.set(true);
	}

	public void grabberUpdate() {
		
		if(JoystickIO.cubeSole.isDown()){
		grab1.set(false);
		grab2.set(false);
	}else{
		grab1.set(true);
		grab1.set(true);
		}
		// TODO: change to correct ports and invert motors
			if (JoystickIO.rightJoystick.getX() > .5) {  //intake
				mot1.set(-JoystickIO.rightJoystick.getX());
				mot2.set(JoystickIO.rightJoystick.getX());
			} else if (JoystickIO.rightJoystick.getX() < -.5) { //spit out
				 mot1.set(JoystickIO.rightJoystick.getX());
					mot2.set(-JoystickIO.rightJoystick.getX());
			}// box is not aligned
			if(JoystickIO.rightJoystick.getY() > .5) { // joystick direction and motors need to be aligned
				 mot1.set(JoystickIO.rightJoystick.getY());
					mot2.set(0);
			} else if (JoystickIO.rightJoystick.getY() < -.5) { 
				mot1.set(0);
				mot2.set(JoystickIO.rightJoystick.getY());
				
			}
			mot1.set(0);
			mot2.set(0);
	
		}
	}
