package com.leantaas.components;

import javafx.scene.control.TextField;

import com.leantaas.xmlbeans.Step;

public class StepComponent {
	public StepComponent() {
		
	}
	public StepComponent(Step step) {
		step_number=new TextField(step.getStepNumber());
		actions=new TextField(step.getActions());
		expectedresults=new TextField(step.getExpectedresults());
		execution_type= new TextField(step.getExecutionType());
		
	}

	TextField step_number;

	TextField actions;

	TextField expectedresults;

	TextField execution_type;

	public TextField getStepNumber() {
		return step_number;
	}

	public void setStepNumber(TextField step_number) {
		this.step_number = step_number;
	}

	public TextField getActions() {
		return actions;
	}

	public void setActions(TextField actions) {
		this.actions = actions;
	}

	public TextField getExpectedresults() {
		return expectedresults;
	}

	public void setExpectedresults(TextField expectedresults) {
		this.expectedresults = expectedresults;
	}

	public TextField getExecutionType() {
		return execution_type;
	}

	public void setExecutionType(TextField execution_type) {
		this.execution_type = execution_type;
	}
}
