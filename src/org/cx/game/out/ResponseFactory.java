package org.cx.game.out;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.util.Iterator;

import org.cx.game.builder.ObjectTypeBuilder;
import org.cx.game.builder.ObjectTypeParse;
import org.cx.game.command.expression.InteriorCommandParameterExpression;
import org.cx.game.core.IPlayer;
import org.cx.game.exception.BuilderException;
import org.cx.game.exception.ParseException;
import org.cx.game.tools.PropertiesUtil;
import org.cx.game.tools.XmlUtil;
import org.cx.game.widget.building.IBuilding;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class ResponseFactory {
	
	private static AbstractResponse response = null;
	
	public static AbstractResponse getResponse(){
		if(null==response){
			response = getInstance(AbstractResponse.Response_Format_DEFAULT);
		}
		return response;
	}
	
	public static AbstractResponse getInstance(String responseName){
		Element configEl = getRoot();
		Element responseEl = configEl.element(XmlUtil.GameConfig_ResponseFormat);
		for(Iterator it = responseEl.elementIterator();it.hasNext();){
			Element el = (Element) it.next();
			if(responseName.equals(el.element(XmlUtil.GameConfig_FormatName).getText())){
				Class clazz;
				try {
					clazz = Class.forName(el.element(XmlUtil.GameConfig_FormatClass).getText());
					return (AbstractResponse) clazz.newInstance();
				} catch (ReflectiveOperationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		return null;
	}

	private static Element getRoot() {
		SAXReader saxReader = new SAXReader();
		try {
			InputStream is=new BufferedInputStream(new FileInputStream(PropertiesUtil.getConfigure("gameconfig.path"))); 
		
			Document document = saxReader.read(is);
			return document.getRootElement();
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
