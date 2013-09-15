package com.leantaas.components;

import javax.xml.bind.annotation.XmlElement;

import com.leantaas.xmlbeans.Steps;


public class StepsComponent {
	StepComponent[] step;
	public StepsComponent() {
		
	}
	
	public StepsComponent(Steps steps) {
		step=new StepComponent[steps.getStep().length];
		for(int i=0;i<steps.getStep().length;i++) {
			step[i]=new StepComponent(steps.getStep()[i]);
		}
	}

	public StepComponent[] getStep() {
		return step;
	}

	@XmlElement
	public void setStep(StepComponent[] step) {
		this.step = step;
	}
}
