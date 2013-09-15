package com.leantaas.javafxbeans;

import javafx.beans.property.StringProperty;


public class StepProperty {

	StringProperty step_number;

	StringProperty actions;

	StringProperty expectedresults;

	StringProperty execution_type;

	public String getStepNumber() {
		return step_number.get();
	}

	public void setStepNumber(String step_number) {
		this.step_number.set(step_number);
	}

	public String getActions() {
		return actions.get();
	}

	public void setActions(String actions) {
		this.actions.set(actions);
	}

	public String getExpectedresults() {
		return expectedresults.get();
	}

	public void setExpectedresults(String expectedresults) {
		this.expectedresults.set(expectedresults);
	}

	public String getExecutionType() {
		return execution_type.get();
	}

	public void setExecutionType(String execution_type) {
		this.execution_type.set(execution_type);
	}
}
