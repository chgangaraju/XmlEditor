package com.leantaas.components;

import java.util.ArrayList;

import javafx.scene.control.Button;

import com.leantaas.beans.Steps;

public class StepsComponent {
	private Button button;
	private ArrayList<StepComponent> step;

	public StepsComponent() {
		step = new ArrayList<StepComponent>();
		button = new Button("+");
	}

	public StepsComponent(Steps steps) {
		button = new Button("+");
		step = new ArrayList<StepComponent>();
		for (int i = 0; i < steps.getStep().size(); i++) {
			step.add(new StepComponent(steps.getStep().get(i)));
		}
	}

	public ArrayList<StepComponent> getStep() {
		return step;
	}

	public void setStep(ArrayList<StepComponent> step) {
		this.step = step;
	}

	public void addStep(StepComponent stepComponent) {
		this.step.add(stepComponent);
	}

	public Button getButton() {
		return button;
	}

	public void setButton(Button button) {
		this.button = button;
	}
}
