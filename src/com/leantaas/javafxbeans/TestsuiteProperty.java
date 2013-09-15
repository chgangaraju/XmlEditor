package com.leantaas.javafxbeans;

import com.leantaas.xmlbeans.Testsuite;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class TestsuiteProperty {
	public TestsuiteProperty() {
		
	}
	public TestsuiteProperty(Testsuite testsuite) {
		name=new SimpleStringProperty(testsuite.getName());
		node_order=new SimpleStringProperty(testsuite.getNodeOrder());
		details=new SimpleStringProperty(testsuite.getDetails());
		//testcase=new TestcaseProperty();
	}

	StringProperty name;

	StringProperty node_order;

	StringProperty details;

	TestcaseProperty[] testcase;

	public String getName() {
		return name.get();
	}

	public void setName(String name) {
		this.name.set(name);
	}

	public String getNodeOrder() {
		return node_order.get();
	}

	public void setNodeOrder(String node_order) {
		this.node_order.set(node_order);
	}

	public String getDetails() {
		return details.get();
	}

	public void setDetails(String details) {
		this.details.set(details);
	}

	public TestcaseProperty[] getTestcase() {
		return testcase;
	}

	public void setTestcase(TestcaseProperty[] testcase) {
		this.testcase = testcase;
	}
	public StringProperty getNameProperty() {
		return name;
	}
	public StringProperty getNodeorderProperty() {
		return node_order;
	}
	public StringProperty getDetailsProperty() {
		return details;
	}
}
