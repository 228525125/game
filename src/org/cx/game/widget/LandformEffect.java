package org.cx.game.widget;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.cx.game.tools.PropertiesUtil;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

/**
 * 单位移动时的消耗，根据移动类型与地形的关系
 * @author chenxian
 *
 */
public class LandformEffect {

	private static Map<Integer, Map<Integer, Integer>> moveType_Landform = new HashMap<Integer, Map<Integer, Integer>>();  //移动类型与地形对应的消耗
	
	private static Element getRoot() {
		SAXReader saxReader = new SAXReader();
		try {
			InputStream is = new BufferedInputStream(new FileInputStream(PropertiesUtil.getConfigure("move.path")));
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
	
	public static Integer getConsume(Integer moveType, Integer landform){
		return moveType_Landform.get(moveType).get(landform);
	}
	
	static {
		Element gameEl = getRoot();
		Element moveEl = gameEl.element("move");
		Element consumeEl = moveEl.element("consume"); 
		for(Iterator it = consumeEl.elementIterator();it.hasNext();){
			Element typeEl = (Element) it.next();
			Integer moveType = Integer.valueOf(typeEl.attribute("type").getText());
			
			Map<Integer, Integer> landform_consume = new HashMap<Integer, Integer>();
			
			for(Iterator itr = typeEl.elementIterator();itr.hasNext();){
				Element landformEl = (Element) itr.next();
				Integer landform = Integer.valueOf(landformEl.attribute("type").getText());
				Integer consume = Integer.valueOf(landformEl.getText());
				
				landform_consume.put(landform, consume);
				
			}
			
			moveType_Landform.put(moveType, landform_consume);
		}
	}
}
