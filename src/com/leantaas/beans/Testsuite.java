package com.leantaas.beans;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Testsuite {
	@XmlAttribute
	private String name;

	@XmlJavaTypeAdapter(AdapterCDATA.class)
	private String node_order;

	@XmlJavaTypeAdapter(AdapterCDATA.class)
	private String details;

	private Testcase[] testcase;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNodeOrder() {
		return node_order;
	}

	public void setNodeOrder(String node_order) {
		this.node_order = node_order;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public Testcase[] getTestcase() {
		return testcase;
	}

	public void setTestcase(Testcase[] testcase) {
		this.testcase = testcase;
	}
}
