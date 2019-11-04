package cn.edu.lingnan.util;

import java.io.File;
import java.io.IOException;

import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.xml.sax.SAXException;

public class XmlValidator {
	// 验证XML文件的有效性
	public static boolean validate(String xmlPath, String xsdPath) {
		boolean flag = false;
		String base = Thread.currentThread().getContextClassLoader().getResource("").getPath();
		xmlPath = base + xmlPath;
		xsdPath = base + xsdPath;
		try {
			SchemaFactory sf = SchemaFactory.newInstance("http://www.w3.org/2001/XMLSchema");
			File f = new File(xsdPath);
			Schema s;
			s = sf.newSchema(f);
			Validator v = s.newValidator();
			Source so = new StreamSource(xmlPath);
			v.validate(so);
			flag = true;
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("XML文件验证失败！");
			e.printStackTrace();
		}
		return flag;
	}
}
