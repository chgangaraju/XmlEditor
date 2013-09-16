package com.leantaas.javafx;

import java.io.File;
import java.io.IOException;
import java.io.Writer;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import com.leantaas.beans.Testsuite;
import com.sun.xml.internal.bind.marshaller.CharacterEscapeHandler;

public class JAXBExample {
	public static Testsuite getObjectFromXml() throws Exception {
		File file = new File("src/sample.xml");
		JAXBContext jaxbContext = JAXBContext.newInstance(Testsuite.class);
		Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
		Testsuite testsuite =  (Testsuite) jaxbUnmarshaller.unmarshal(file);
		return testsuite;
	}

	public static void setObjectToXml(Testsuite testsuite) throws Exception {
		File file = new File("src/sample1.xml");
		JAXBContext jaxbContext = JAXBContext.newInstance(Testsuite.class);
		Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
		jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		jaxbMarshaller.setProperty(CharacterEscapeHandler.class.getName(),
                new CharacterEscapeHandler() {
                    @Override
                    public void escape(char[] ac, int i, int j, boolean flag,
                            Writer writer) throws IOException {
                        writer.write(ac, i, j);
                    }
                });
		jaxbMarshaller.marshal(testsuite, file);
	}
}
