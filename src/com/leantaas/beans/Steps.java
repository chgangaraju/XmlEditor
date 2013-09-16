package com.leantaas.beans;

import javax.xml.bind.annotation.XmlElement;


public class Steps {
	private Step[] step;

	public Step[] getStep() {
		return step;
	}

	@XmlElement
	public void setStep(Step[] step) {
		this.step = step;
	}
}
