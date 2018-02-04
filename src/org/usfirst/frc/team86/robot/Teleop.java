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

public class Teleop {
	private Victor mot1;
	private Victor mot2;
	private DigitalInput banSensL;
	private DigitalInput banSensR;
	private int state = 0;
	private Solenoid grab1;
	private Solenoid grab2;

	public Teleop(Victor mot1, Victor mot2, Solenoid grab1, Solenoid grab2, DigitalInput banSensLEFT, DigitalInput banSensRIGHT) {
		this.mot1 = mot1;
		this.mot2 = mot2;
		this.grab1 = grab1;
		this.grab2 = grab2;
		this.banSensL = banSensLEFT;
		this.banSensR = banSensRIGHT;
	}

	public void grabberInit() {
		mot1.set(0);
		mot2.set(0);
		grab1.set(true);
		grab2.set(true);
	}

	public void grabberUpdate() {
		switch (state) {
		case 0: // default state
			mot1.set(0);
			mot2.set(0);
			grab1.set(true);
			grab2.set(true);
			if (IO.rightStick.getX() > 0) { // TODO: change to correct ports
					state = 1;
			} else if (IO.rightStick.getX() < 0) { // button # change
					state = 2;
				
			}
			break;
		case 1: // spit out
			mot1.set(-IO.rightStick.getX());
			mot2.set(IO.rightStick.getX());
			grab1.set(false);
			grab2.set(false);
			if (!banSensL.get() && !banSensR.get()) { // box successfully spit out
				state = 0;
			}
			break;
		case 2: // suck box in
			mot1.set(IO.rightStick.getX());
			mot2.set(-IO.rightStick.getX());
			grab1.set(true);
			grab2.set(true);
			if (banSensL.get() && banSensR.get()) { // box is in, go back to checking state
				state = 0;
			} else if(IO.rightStick.getY() > 0) { // box is not aligned
				state = 3;
			} else if (IO.rightStick.getY() < 0) { // box is not aligned
				state = 4;
			}
			break;
		case 3: // adjust right
			mot1.set(.4);
			mot2.set(-.6);
			state = 2;
			break;
		case 4: // adjust left
			mot1.set(.6);
			mot2.set(-.4);
			state = 2;
			break;
		
		}
	}
}
