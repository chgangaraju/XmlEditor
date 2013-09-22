package com.leantaas.xmlhelpers;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class AdapterCDATA extends XmlAdapter<String, String> {

	@Override
	public String marshal(String string) throws Exception {
		string=string.replace("<html><head></head><body contenteditable=\"true\">", "");
		string=string.replace("style=\"text-align: left;\"", "");
		string=string.replace("</body></html>", "");
		return "<![CDATA[" + string + "]]>";
	}

	@Override
	public String unmarshal(String string) throws Exception {
		return string;
	}
}
