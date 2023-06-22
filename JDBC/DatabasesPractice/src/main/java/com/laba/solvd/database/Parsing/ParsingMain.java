package com.laba.solvd.database.Parsing;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.xml.XMLConstants;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.xml.sax.SAXException;

public class ParsingMain {

	public static void main(String[] args) throws FileNotFoundException, XMLStreamException {
		String path2 = "src/main/java/com/laba/solvd/database/localDBS/Objects.xsd";
		String path1 = "src/main/java/com/laba/solvd/database/localDBS/Objects.xml";
		boolean validity = validateXmlAgainstXsd(path1, path2);
		System.out.println(validity);
		parseAndCreateObjects();
	}
	
	public static boolean validateXmlAgainstXsd(String xmlPath, String xsdPath) {
		SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
		Schema schema;
		try {
			schema = schemaFactory.newSchema(new StreamSource(xsdPath));
			Validator validator = schema.newValidator();
			validator.validate(new StreamSource(new File(xmlPath)));
			return true;
		} catch (SAXException e) {
			e.printStackTrace();
			return false;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return false;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public static void parseAndCreateObjects() throws FileNotFoundException, XMLStreamException {
		XMLInputFactory f = XMLInputFactory.newInstance();
		XMLEventReader r = f.createXMLEventReader(new FileInputStream("src/main/java/com/laba/solvd/database/localDBS/Objects.xml"));
		
		while (r.hasNext()) {
			XMLEvent event = r.nextEvent();
            if (event.isStartElement()) {
                StartElement startElement = event.asStartElement();
                String elementName = startElement.getName().getLocalPart();
                System.out.print(elementName + ": ");
                event = r.nextEvent();
                if (event.isCharacters()) {
                    String text = event.asCharacters().getData().trim();
                    System.out.println(text);
                }
            }
        }
	}
}