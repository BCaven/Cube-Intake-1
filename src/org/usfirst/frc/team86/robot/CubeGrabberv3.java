package org.usfirst.frc.team86.robot;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Victor;

public class CubeGrabberv3 {
	private Victor mot1;
	private Victor mot2;
	private DigitalInput banSensL;
	private DigitalInput banSensR;
	private int state = 0;
	private Solenoid grab1;
	private Solenoid grab2;

	public CubeGrabberv3(Victor mot1, Victor mot2, Solenoid grab1, Solenoid grab2, DigitalInput banSensLEFT, DigitalInput banSensRIGHT) {
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
		case 0:
			mot1.set(0);
			mot2.set(0);
			if (banSensR.get() && banSensL.get()) {
				if (IO.rightStick.getRawButton(1)) { // button # change
					state = 2;

				}
			} else {
				if (IO.rightStick.getRawButton(1)) { // button # change
					state = 1;
				}
			}
			break;
		case 1:
			if (banSensL.get() && banSensR.get()) { // both are tripped, box is in intake
				mot1.set(0);
				mot2.set(0);
				grab1.set(true);
				grab2.set(true); // changed states after testing solenoids
				state = 0;
			} else if(!banSensL.get() && !banSensR.get()) { // box is not in intake range, intake is open, motors running
				mot1.set(.5);
				mot2.set(-.5); // to be changed
				grab1.set(false);
				grab2.set(false);
				
				// all these need to be changed with new info
			} else if (banSensL.get() && !banSensR.get()) { // box is uneven towards left side
				mot1.set(.75);
				mot2.set(-.25);
				grab1.set(false);
				grab2.set(true);
			} else if (!banSensL.get() && banSensR.get()) { // box is uneven towards right side
				mot1.set(.25);
				mot2.set(-.75);
				grab1.set(true);
				grab2.set(false);
			} else { // no input from ban sensors, intake is open, motors not running
				mot1.set(0);
				mot2.set(0);
				grab1.set(false);
				grab2.set(false); 
			}
			break;
		case 2:
			mot1.set(0);
			mot2.set(0);
			grab1.set(false);
			grab2.set(false);
			if (IO.rightStick.getRawButton(1)) {
				state = 0;
			}
			break;
		}
	}
}
			
		
		
	

