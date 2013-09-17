package com.leantaas.components;

import java.util.ArrayList;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import com.leantaas.beans.Testsuite;

public class TestsuiteComponent {
	private Button button;
	private TextField name;
	private TextField node_order;
	private TextField details;
	private ArrayList<TestcaseComponent> testcase;

	public TestsuiteComponent() {
		button = new Button("+");
		name = new TextField();
		node_order = new TextField();
		details = new TextField();
		testcase = new ArrayList<TestcaseComponent>();
	}

	public TestsuiteComponent(Testsuite testsuite) {
		button = new Button("+");
		name = new TextField(testsuite.getName());
		node_order = new TextField(testsuite.getNodeOrder());
		details = new TextField(testsuite.getDetails());
		testcase = new ArrayList<TestcaseComponent>();
		for (int i = 0; i < testsuite.getTestcase().size(); i++) {
			testcase.add(new TestcaseComponent(testsuite.getTestcase().get(i)));
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

	public ArrayList<TestcaseComponent> getTestcase() {
		return testcase;
	}

	public void setTestcase(ArrayList<TestcaseComponent> testcase) {
		this.testcase = testcase;
	}

	public void addTestcase(TestcaseComponent testcaseComponent) {
		testcase.add(testcaseComponent);
	}

	public Button getButton() {
		return button;
	}

	public void setButton(Button button) {
		this.button = button;
	}
}
