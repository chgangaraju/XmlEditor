package com.leantaas.components;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import com.leantaas.beans.Testcase;

public class TestcaseComponent {
	private Button button;
	private TextField internalid;
	private TextField name;
	private TextField node_order;
	private TextField externalid;
	private TextField version;
	private TextField summary;
	private TextField preconditions;
	private TextField execution_type;
	private TextField importance;
	private StepsComponent steps;

	public TestcaseComponent() {
		button = new Button("+");
		internalid = new TextField();
		name = new TextField();
		node_order = new TextField();
		externalid = new TextField();
		version = new TextField();
		summary = new TextField();
		preconditions = new TextField();
		execution_type = new TextField();
		importance = new TextField();
		steps = new StepsComponent();
	}

	public TestcaseComponent(Testcase testcase) {
		button = new Button("+");
		internalid = new TextField(testcase.getInternalid());
		name = new TextField(testcase.getName());
		node_order = new TextField(testcase.getNodeOrder());
		externalid = new TextField(testcase.getExternalid());
		version = new TextField(testcase.getVersion());
		summary = new TextField(testcase.getSummary());
		preconditions = new TextField(testcase.getPreconditions());
		execution_type = new TextField(testcase.getExecutionType());
		importance = new TextField(testcase.getImportance());
		steps = new StepsComponent(testcase.getSteps());
	}

	public TextField getInternalid() {
		return internalid;
	}

	public void setInternalid(TextField internalid) {
		this.internalid = internalid;
	}

	public TextField getName() {
		return name;
	}

	public void setName(TextField name) {
		this.name = name;
	}

	public TextField getNodeOrder() {
		return node_order;
	}

	public void setNodeOrder(TextField node_order) {
		this.node_order = node_order;
	}

	public TextField getExternalid() {
		return externalid;
	}

	public void setExternalid(TextField externalid) {
		this.externalid = externalid;
	}

	public TextField getVersion() {
		return version;
	}

	public void setVersion(TextField version) {
		this.version = version;
	}

	public TextField getSummary() {
		return summary;
	}

	public void setSummary(TextField summary) {
		this.summary = summary;
	}

	public TextField getPreconditions() {
		return preconditions;
	}

	public void setPreconditions(TextField preconditions) {
		this.preconditions = preconditions;
	}

	public TextField getExecutionType() {
		return execution_type;
	}

	public void setExecutionType(TextField execution_type) {
		this.execution_type = execution_type;
	}

	public TextField getImportance() {
		return importance;
	}

	public void setImportance(TextField importance) {
		this.importance = importance;
	}

	public StepsComponent getSteps() {
		return steps;
	}

	public void setSteps(StepsComponent steps) {
		this.steps = steps;
	}

	public Button getButton() {
		return button;
	}

	public void setButton(Button button) {
		this.button = button;
	}
}
