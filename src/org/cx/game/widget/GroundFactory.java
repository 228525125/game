package org.cx.game.widget;

import java.io.InputStream;

import org.cx.game.card.CardFactory;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class GroundFactory {

	private static String filePath = "/org/cx/game/widget/map.xml";
	
	private static Element getRoot() {
		SAXReader saxReader = new SAXReader();
		InputStream is=CardFactory.class.getResourceAsStream(filePath); 
		try {
			Document document = saxReader.read(is);
			return document.getRootElement();
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public static IGround getInstance(Element mapEl){
		
		return null;
	}
}
