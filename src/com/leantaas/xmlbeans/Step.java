package com.leantaas.xmlbeans;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlAccessorType(XmlAccessType.FIELD)
public class Step {

	@XmlJavaTypeAdapter(AdapterCDATA.class)
	String step_number;

	@XmlJavaTypeAdapter(AdapterCDATA.class)
	String actions;

	@XmlJavaTypeAdapter(AdapterCDATA.class)
	String expectedresults;

	@XmlJavaTypeAdapter(AdapterCDATA.class)
	String execution_type;

	public String getStepNumber() {
		return step_number;
	}

	public void setStepNumber(String step_number) {
		this.step_number = step_number;
	}

	public String getActions() {
		return actions;
	}

	public void setActions(String actions) {
		this.actions = actions;
	}

	public String getExpectedresults() {
		return expectedresults;
	}

	public void setExpectedresults(String expectedresults) {
		this.expectedresults = expectedresults;
	}

	public String getExecutionType() {
		return execution_type;
	}

	public void setExecutionType(String execution_type) {
		this.execution_type = execution_type;
	}
}