package cn.edu.lingnan.util;

import java.util.HashMap;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class XmlHandler extends DefaultHandler {
	private StringBuffer currentValue = new StringBuffer();
	private HashMap<String, String> hm = new HashMap<String, String>();

	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		currentValue.delete(0, currentValue.length());
	}

	public void characters(char[] ch, int start, int length) throws SAXException {
		currentValue.append(ch, start, length);
	}

	public void endElement(String uri, String localName, String qName) throws SAXException {
		hm.put(qName.toLowerCase(), currentValue.toString().trim());
	}

	public HashMap<String, String> getHashMap() {
		return hm;
	}
}
