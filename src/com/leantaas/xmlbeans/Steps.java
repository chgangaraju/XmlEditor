package com.leantaas.xmlbeans;

import javax.xml.bind.annotation.XmlElement;


public class Steps {
	Step[] step;

	public Step[] getStep() {
		return step;
	}

	@XmlElement
	public void setStep(Step[] step) {
		this.step = step;
	}
}
