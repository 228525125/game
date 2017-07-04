package org.cx.game.widget;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.cx.game.tools.PropertiesUtil;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

/**
 * 地形影响
 * 单位移动时的消耗，根据移动类型与地形的关系
 * 职业与地形的关系
 * @author chenxian
 *
 */
public class LandformEffect {

	private static Map<Integer, Map<Integer, Integer>> moveType_Landform = new HashMap<Integer, Map<Integer, Integer>>();  //移动类型与地形对应的消耗
	
	private static Map<Integer, Map<Integer, String>> professionType_Landform = new HashMap<Integer, Map<Integer, String>>();    //职业类型与地形的优劣关系
	
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
	
	public static Integer getAttackAdvantage(Integer profession, Integer landform){
		String value = professionType_Landform.get(profession).get(landform);
		return Integer.valueOf(value.split(",")[0]);
	}
	
	public static Integer getDefendAdvantage(Integer profession, Integer landform){
		String value = professionType_Landform.get(profession).get(landform);
		return Integer.valueOf(value.split(",")[1]);
	}
	
	public static Integer getConsume(Integer moveType, Integer landform){
		return moveType_Landform.get(moveType).get(landform);
	}
	
	public static List<Integer> getDisable(Integer moveType, Map<Integer, IPlace> ground){
		List<Integer> list = new ArrayList<Integer>();
		for(Integer pos : ground.keySet()){
			if(Integer.valueOf(-1).equals(moveType_Landform.get(moveType).get(ground.get(pos).getLandform())))
				list.add(pos);
		}
		
		return list;
	}
	
	static {
		Element gameEl = getRoot("move.path");
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
		
		gameEl = getRoot("advantage.path");
		Element advantageEl = gameEl.element("advantage");
		
		for(Iterator it = advantageEl.elementIterator();it.hasNext();){
			Element professionEl = (Element) it.next();
			Integer profession = Integer.valueOf(professionEl.attribute("type").getText());
			
			Map<Integer, String> landform_profession = new HashMap<Integer, String>();
			
			for(Iterator itr = professionEl.elementIterator();itr.hasNext();){
				Element landformEl = (Element) itr.next();
				Integer landform = Integer.valueOf(landformEl.attribute("type").getText());
				String value = landformEl.getText();
				
				landform_profession.put(landform, value);
				
			}
			
			professionType_Landform.put(profession, landform_profession);
		}
		
	}
}
