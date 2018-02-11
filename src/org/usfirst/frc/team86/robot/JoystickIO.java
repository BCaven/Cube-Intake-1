package org.usfirst.frc.team86.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;

import java.util.ArrayList;

public class JoystickIO {
	// Joysticks
	public static Joystick leftJoystick = new Joystick(0);
	public static Joystick rightJoystick = new Joystick(1);
	public static Joystick coJoystick = new Joystick(2);

	// Buttons
	private static ArrayList<Button> buttons = new ArrayList<>();

	public static Button cubeSole = createButton(rightJoystick, 1);
	public static Button positionControl = createButton(rightJoystick, 2);
	
	public static void update() {
		for (Button b : buttons) {
			b.update();
		}
	}

	private static Button createButton(GenericHID stick, int button) {
		Button newButton = new Button(stick, button);
		buttons.add(newButton);
		return newButton;
	}
}