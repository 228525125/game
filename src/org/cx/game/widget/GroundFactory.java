package org.cx.game.widget;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.Properties;
import java.util.Set;

import org.cx.game.builder.ObjectTypeBuilder;
import org.cx.game.builder.ObjectTypeParse;
import org.cx.game.card.CardFactory;
import org.cx.game.card.ICard;
import org.cx.game.exception.BuilderException;
import org.cx.game.exception.ParseException;
import org.cx.game.tools.PropertiesUtil;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class GroundFactory {
	
	private static Element getRoot() {
		SAXReader saxReader = new SAXReader();
		try {
			InputStream is = new BufferedInputStream(new FileInputStream(PropertiesUtil.getConfigure("map.path")));
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
	
	public static IGround getInstance(String mapId){
		Element mapEl = null;
		for(Iterator it = getRoot().elementIterator();it.hasNext();){
			Element el = (Element) it.next();
			if(mapId.equals(el.attribute("id").getText()))
				mapEl = el;
		}
		
		if(null!=mapEl){
			ObjectTypeBuilder otb = new ObjectTypeBuilder();
			try {
				new ObjectTypeParse(otb).parse(mapEl);
				IGround ground = new GroundDecorator((IGround) otb.builder());
				return ground;
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (BuilderException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}
}
