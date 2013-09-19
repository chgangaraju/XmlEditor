package com.leantaas.xmlhelpers;

import java.io.File;
import java.io.IOException;
import java.io.Writer;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import com.leantaas.beans.Testsuite;
import com.sun.xml.internal.bind.marshaller.CharacterEscapeHandler;

public class XMLReader {
	public static Testsuite getObjectFromXml(File file) throws Exception {
		/*Properties properties=new Properties();
		properties.load(new FileInputStream("src/files.properties"));
		File file = new File(properties.getProperty("readFile"));*/
		JAXBContext jaxbContext = JAXBContext.newInstance(Testsuite.class);
		Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
		Testsuite testsuite =  (Testsuite) unmarshaller.unmarshal(file);
		return testsuite;
	}

	public static void setObjectToXml(Testsuite testsuite,File file) throws Exception {
		/*Properties properties=new Properties();
		properties.load(new FileInputStream("src/files.properties"));
		File file = new File(properties.getProperty("writeFile"));*/
		JAXBContext jaxbContext = JAXBContext.newInstance(Testsuite.class);
		Marshaller marshaller = jaxbContext.createMarshaller();
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		marshaller.setProperty(CharacterEscapeHandler.class.getName(),
                new CharacterEscapeHandler() {
                    @Override
                    public void escape(char[] c, int i, int j, boolean flag,
                            Writer writer) throws IOException {
                        writer.write(c, i, j);
                    }
                });
		marshaller.marshal(testsuite, file);
	}
}
