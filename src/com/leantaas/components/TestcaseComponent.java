package com.leantaas.components;

import com.leantaas.xmlbeans.Testcase;

import javafx.scene.control.TextField;

public class TestcaseComponent {
	public TestcaseComponent() {

	}

	public TestcaseComponent(Testcase testcase) {
		internalid=new TextField(testcase.getInternalid());
		name=new TextField(testcase.getName());
		node_order=new TextField(testcase.getNodeOrder());
		externalid=new TextField(testcase.getExternalid());
		version=new TextField(testcase.getVersion());
		summary=new TextField(testcase.getSummary());
		preconditions=new TextField(testcase.getPreconditions());
		execution_type=new TextField(testcase.getExecutionType());
		importance=new TextField(testcase.getImportance());
		steps=new StepsComponent(testcase.getSteps());
	}

	TextField internalid;

	TextField name;

	TextField node_order;

	TextField externalid;

	TextField version;

	TextField summary;

	TextField preconditions;

	TextField execution_type;

	TextField importance;

	StepsComponent steps;

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

}
