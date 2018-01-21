package org.usfirst.frc.team86.robot;


import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Victor;


public class IO {
// all need to be changed to channels #
public static Victor mot1 = new Victor(1);
public static Victor mot2 = new Victor(2);

public static DigitalInput banSens = new DigitalInput(1);	

public static Joystick leftStick = new Joystick(0);	
public static Joystick rightStick = new Joystick(1);
	
}
