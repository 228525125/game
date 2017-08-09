package org.cx.game.policy;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;

import org.cx.game.builder.ObjectTypeBuilder;
import org.cx.game.builder.ObjectTypeParse;
import org.cx.game.exception.BuilderException;
import org.cx.game.exception.ParseException;
import org.cx.game.tools.PropertiesUtil;
import org.cx.game.widget.GroundDecorator;
import org.cx.game.widget.IGround;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class GroupPolicyFactory {

	private static Element getRoot() {
		SAXReader saxReader = new SAXReader();
		try {
			InputStream is=new BufferedInputStream(new FileInputStream(PropertiesUtil.getConfigure("policy.path"))); 
		
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
	
	public static IGroupPolicy createGroupPolicy(Integer groupId){
		Element groupEl = null;
		for(Iterator it = getRoot().elementIterator();it.hasNext();){
			Element el = (Element) it.next();
			if(groupId.equals(Integer.valueOf(el.attribute("id").getText())))
				groupEl = el;
		}
		
		if(null!=groupEl){
			ObjectTypeBuilder otb = new ObjectTypeBuilder();
			try {
				new ObjectTypeParse(otb).parse(groupEl);
				IGroupPolicy gp = (IGroupPolicy) otb.builder();
				return gp;
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
