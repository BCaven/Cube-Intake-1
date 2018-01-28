package org.usfirst.frc.team86.robot;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Victor;

public class CubeGrabber {
	private Victor mot1;
	private Victor mot2;
	private DigitalInput banSens;
	private int state = 0;
	private Solenoid grab1;
	private Solenoid grab2;

	public CubeGrabber(Victor mot1, Victor mot2, Solenoid grab1, Solenoid grab2, DigitalInput banSens) {
		this.mot1 = mot1;
		this.mot2 = mot2;
		this.grab1 = grab1;
		this.grab2 = grab2;
		this.banSens = banSens;
	}

	public void grabberInit() {
		mot1.set(0);
		mot2.set(0);
		grab1.set(false);
		grab2.set(false);
	}

	public void grabberUpdate() {
		switch (state) {
		case 1:
			if (banSens.get()) {
				mot1.set(0);
				mot2.set(0);
				grab1.set(true);
				grab2.set(true); // changed states after testing solenoids
				state = 0;
			} else {
				mot1.set(.5);
				mot2.set(-.5); // to be changed
			}
			break;
		case 2:
			if (IO.rightStick.getRawButton(1)) {
				mot1.set(0);
				mot2.set(0);
				state = 0;
			} else {
				mot1.set(-.5);
				mot1.set(.5); // to be changed
			}
			break;
		default:
			mot1.set(0);
			mot2.set(0);
			if (banSens.get()) {
				if (IO.rightStick.getRawButton(1)) { // button # change
					grab1.set(false);
					grab2.set(false);
					state = 2;
					
				}
			} else {
				if (IO.rightStick.getRawButton(1)) { // button # change
					state = 1;
				}
			}
			break;
		}
	}

}
