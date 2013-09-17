package com.leantaas.beans;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlElement;

public class Steps {
	private ArrayList<Step> step;

	public Steps() {
		step = new ArrayList<Step>();
	}

	public ArrayList<Step> getStep() {
		return step;
	}

	@XmlElement
	public void setStep(ArrayList<Step> step) {
		this.step = step;
	}

	public void addStep(Step step) {
		this.step.add(step);
	}
}
