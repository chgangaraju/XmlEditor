package com.leantaas.xmlbeans;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlAccessorType(XmlAccessType.FIELD)
public class Testcase {
	@XmlAttribute
	String internalid;
	
	@XmlAttribute
	String name;
	
	@XmlJavaTypeAdapter(AdapterCDATA.class)
	String node_order;
	
	@XmlJavaTypeAdapter(AdapterCDATA.class)
	String externalid;
	
	@XmlJavaTypeAdapter(AdapterCDATA.class)
	String version;
	
	@XmlJavaTypeAdapter(AdapterCDATA.class)
	String summary;
	
	@XmlJavaTypeAdapter(AdapterCDATA.class)
	String preconditions;
	
	@XmlJavaTypeAdapter(AdapterCDATA.class)
	String execution_type;
	
	@XmlJavaTypeAdapter(AdapterCDATA.class)
	String importance;
	
	Steps steps;

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
