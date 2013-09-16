package com.leantaas.beans;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class AdapterCDATA extends XmlAdapter<String, String> {

	@Override
	public String marshal(String string) throws Exception {
		return "<![CDATA[" + string + "]]>";
	}

	@Override
	public String unmarshal(String string) throws Exception {
		return string;
	}
}
