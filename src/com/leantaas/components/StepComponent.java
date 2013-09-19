package com.leantaas.components;

import javafx.scene.control.TextField;
import javafx.scene.web.HTMLEditor;

import com.leantaas.beans.Step;

public class StepComponent {
	private TextField step_number;
	private HTMLEditor actions;
	private HTMLEditor expectedresults;
	private TextField execution_type;

	public StepComponent() {
		step_number = new TextField("");
		actions = new HTMLEditor();
		actions.setMaxSize(650, 200);
		expectedresults = new HTMLEditor();
		expectedresults.setMaxSize(650, 200);
		execution_type = new TextField("");
	}

	public StepComponent(Step step) {
		step_number = new TextField(step.getStepNumber());
		actions = new HTMLEditor();
		actions.setHtmlText(step.getActions());
		actions.setMaxSize(650, 200);
		expectedresults = new HTMLEditor();
		expectedresults.setHtmlText(step.getExpectedresults());
		expectedresults.setMaxSize(650, 200);
		execution_type = new TextField(step.getExecutionType());
	}

	public TextField getStepNumber() {
		return step_number;
	}

	public void setStepNumber(TextField step_number) {
		this.step_number = step_number;
	}

	public HTMLEditor getActions() {
		return actions;
	}

	public void setActions(HTMLEditor actions) {
		this.actions = actions;
	}

	public HTMLEditor getExpectedresults() {
		return expectedresults;
	}

	public void setExpectedresults(HTMLEditor expectedresults) {
		this.expectedresults = expectedresults;
	}

	public TextField getExecutionType() {
		return execution_type;
	}

	public void setExecutionType(TextField execution_type) {
		this.execution_type = execution_type;
	}
}