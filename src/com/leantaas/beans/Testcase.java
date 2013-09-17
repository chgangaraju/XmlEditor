package com.leantaas.beans;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import com.leantaas.xmlhelpers.AdapterCDATA;

@XmlAccessorType(XmlAccessType.FIELD)
public class Testcase {
	@XmlAttribute
	private String internalid;

	@XmlAttribute
	private String name;

	@XmlJavaTypeAdapter(AdapterCDATA.class)
	private String node_order;

	@XmlJavaTypeAdapter(AdapterCDATA.class)
	private String externalid;

	@XmlJavaTypeAdapter(AdapterCDATA.class)
	private String version;

	@XmlJavaTypeAdapter(AdapterCDATA.class)
	private String summary;

	@XmlJavaTypeAdapter(AdapterCDATA.class)
	private String preconditions;

	@XmlJavaTypeAdapter(AdapterCDATA.class)
	private String execution_type;

	@XmlJavaTypeAdapter(AdapterCDATA.class)
	private String importance;

	private Steps steps;

	public Testcase() {
		internalid = new String();
		name = new String();
		node_order = new String();
		externalid = new String();
		version = new String();
		summary = new String();
		preconditions = new String();
		execution_type = new String();
		importance = new String();
		steps=new Steps();
	}

	public String getInternalid() {
		return internalid;
	}

	public void setInternalid(String internalid) {
		this.internalid = internalid;
	}

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

	public String getExternalid() {
		return externalid;
	}

	public void setExternalid(String externalid) {
		this.externalid = externalid;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getPreconditions() {
		return preconditions;
	}

	public void setPreconditions(String preconditions) {
		this.preconditions = preconditions;
	}

	public String getExecutionType() {
		return execution_type;
	}

	public void setExecutionType(String execution_type) {
		this.execution_type = execution_type;
	}

	public String getImportance() {
		return importance;
	}

	public void setImportance(String importance) {
		this.importance = importance;
	}

	public Steps getSteps() {
		return steps;
	}

	public void setSteps(Steps steps) {
		this.steps = steps;
	}

}
