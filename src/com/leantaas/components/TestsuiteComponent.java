package com.leantaas.components;

import javafx.scene.control.TextField;

import com.leantaas.beans.Testsuite;

public class TestsuiteComponent {
	private TextField name;
	private TextField node_order;
	private TextField details;
	private TestcaseComponent[] testcase;

	public TestsuiteComponent() {

	}

	public TestsuiteComponent(Testsuite testsuite) {
		name = new TextField(testsuite.getName());
		node_order = new TextField(testsuite.getNodeOrder());
		details = new TextField(testsuite.getDetails());
		testcase = new TestcaseComponent[testsuite.getTestcase().length];
		for (int i = 0; i < testsuite.getTestcase().length; i++) {
			testcase[i] = new TestcaseComponent(testsuite.getTestcase()[i]);
		}
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

	public TextField getDetails() {
		return details;
	}

	public void setDetails(TextField details) {
		this.details = details;
	}

	public TestcaseComponent[] getTestcase() {
		return testcase;
	}

	public void setTestcase(TestcaseComponent[] testcase) {
		this.testcase = testcase;
	}
}
