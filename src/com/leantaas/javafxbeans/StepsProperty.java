package com.leantaas.javafxbeans;

import javax.xml.bind.annotation.XmlElement;


public class StepsProperty {
	StepProperty[] step;

	public StepProperty[] getStep() {
		return step;
	}

	@XmlElement
	public void setStep(StepProperty[] step) {
		this.step = step;
	}
}
