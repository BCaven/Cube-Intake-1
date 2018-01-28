package org.usfirst.frc.team86.robot;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Victor;

public class CubeGrabberv2 {
	private Victor mot1;
	private Victor mot2;
	private int state = 0;
	private Solenoid grab1;
	private Solenoid grab2;

	public CubeGrabberv2(Victor mot1, Victor mot2, Solenoid grab1, Solenoid grab2) {
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
		 switch (state) {
	        case 0: 
	            
	            if(IO.leftStick.getRawButton(1)) {
	                state = 1;
	            }
	            
	                else{ 
	                    mot1.set(0);
	                    mot2.set(0);
	                    grab1.set(true);
	                    grab2.set(true);
	            
	            }
	            break;
	        case 1:
	            grab1.set(false);
	            grab2.set(false);
	            mot1.set(.5);
	            mot2.set(-.5);
	        
	            if(IO.leftStick.getRawButton(1)){
	                
	                state = 2;
	            }
	        
	            break;
	            
	        case 2:
	            grab1.set(true);
	            grab2.set(true);
	            mot1.set(0);
	            mot2.set(0);
	            
	            if(IO.leftStick.getRawButton(1)){
	                grab1.set(false);
	                grab2.set(false);
	                state = 3;
	             
	            }
	            
	            
	            break;
	        case 3:
	            if(IO.leftStick.getRawButton(1)){
	                
	                state = 0;
	             
	            }else{
	                grab1.set(false);
	                grab2.set(false);
	            }
	            break;
		}
	}

}
