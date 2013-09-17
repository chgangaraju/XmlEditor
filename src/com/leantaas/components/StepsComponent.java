package com.leantaas.components;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlElement;

import com.leantaas.beans.Steps;

public class StepsComponent {
	private ArrayList<StepComponent> step;

	public StepsComponent() {
		step = new ArrayList<StepComponent>();
	}

	public StepsComponent(Steps steps) {
		step = new ArrayList<StepComponent>();
		for (int i = 0; i < steps.getStep().size(); i++) {
			step.add(new StepComponent(steps.getStep().get(i)));
		}
	}

	public ArrayList<StepComponent> getStep() {
		return step;
	}

	@XmlElement
	public void setStep(ArrayList<StepComponent> step) {
		this.step = step;
	}

	public void addStep(StepComponent stepComponent) {
		this.step.add(stepComponent);
		
	}
}
