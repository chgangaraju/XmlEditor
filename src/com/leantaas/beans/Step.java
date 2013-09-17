package com.leantaas.beans;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import com.leantaas.xmlhelpers.AdapterCDATA;

@XmlAccessorType(XmlAccessType.FIELD)
public class Step {

	@XmlJavaTypeAdapter(AdapterCDATA.class)
	private String step_number;

	@XmlJavaTypeAdapter(AdapterCDATA.class)
	private String actions;

	@XmlJavaTypeAdapter(AdapterCDATA.class)
	private String expectedresults;

	@XmlJavaTypeAdapter(AdapterCDATA.class)
	private String execution_type;
	
	public Step() {
		step_number=new String();
		actions=new String();
		execution_type=new String();
		expectedresults=new String();
	}

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
