package com.leantaas.components;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import com.leantaas.beans.Step;

public class StepComponent {
	private Button button;
	private TextField step_number;
	private TextField actions;
	private TextField expectedresults;
	private TextField execution_type;

	public StepComponent() {
		step_number = new TextField("");
		actions = new TextField("");
		expectedresults = new TextField("");
		execution_type = new TextField("");
		button=new Button("+");
	/*	button.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				System.out.println("success");
			}
		});*/
	}

	public StepComponent(Step step) {
		setButton(new Button("+"));
		step_number = new TextField(step.getStepNumber());
		actions = new TextField(step.getActions());
		expectedresults = new TextField(step.getExpectedresults());
		execution_type = new TextField(step.getExecutionType());
		button.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				System.out.println("success");
			}
		});
	}

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

	public Button getButton() {
		return button;
	}

	public void setButton(Button button) {
		this.button = button;
	}
}
