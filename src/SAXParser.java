
import java.io.File;

import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class SAXParser {
	public static void main(String[] args) {

		try {
			File inputFile = new File("src/input.xml");
			SAXParserFactory factory = SAXParserFactory.newInstance();
			javax.xml.parsers.SAXParser saxParser = factory.newSAXParser();
			UserHandler userhandler = new UserHandler();
			saxParser.parse(inputFile, userhandler);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

class UserHandler extends DefaultHandler {

	boolean bFirstName = false;
	boolean bLastName = false;
	boolean bNickName = false;
	boolean bPoints = false;

	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		if (qName.equalsIgnoreCase("employee")) {
			String salary = attributes.getValue("salary");
			System.out.println("Salary : " + salary);
		} else if (qName.equalsIgnoreCase("firstname")) {
			bFirstName = true;
		} else if (qName.equalsIgnoreCase("lastname")) {
			bLastName = true;
		} else if (qName.equalsIgnoreCase("nickname")) {
			bNickName = true;
		} else if (qName.equalsIgnoreCase("points")) {
			bPoints = true;
		}
	}

	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		if (qName.equalsIgnoreCase("employee")) {
			System.out.println("End Element :" + qName);
		}
	}

	@Override
	public void characters(char ch[], int start, int length) throws SAXException {
		if (bFirstName) {
			System.out.println("First Name: " + new String(ch, start, length));
			bFirstName = false;
		} else if (bLastName) {
			System.out.println("Last Name: " + new String(ch, start, length));
			bLastName = false;
		} else if (bNickName) {
			System.out.println("Nick Name: " + new String(ch, start, length));
			bNickName = false;
		} else if (bPoints) {
			System.out.println("Points: " + new String(ch, start, length));
			bPoints = false;
		}
	}
}
