package cn.edu.lingnan.test;

import java.util.HashMap;

import cn.edu.lingnan.util.XmlParser;
import cn.edu.lingnan.util.XmlValidator;

public class XMLtest {

	public static void main(String[] args) throws Exception {
		String xmlPath = "database.conf.xml";
		String xsdPath = "database.conf.xsd";
		System.out.println(XmlValidator.validate(xmlPath, xsdPath));
		if (XmlValidator.validate(xmlPath, xsdPath)) {
			HashMap<String, String> hm = XmlParser.parser(xmlPath);
			System.out.println(hm.get("driver"));
			System.out.println(hm.get("password"));
		} else {

		}

	}
}
