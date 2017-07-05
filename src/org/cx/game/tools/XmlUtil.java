package org.cx.game.tools;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class XmlUtil {
	
	private static Element getRoot(String path) {
		SAXReader saxReader = new SAXReader();
		try {
			InputStream is = new BufferedInputStream(new FileInputStream(PropertiesUtil.getConfigure(path)));
			Document document = saxReader.read(is);
			return document.getRootElement();
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	static {
		
	}
}
