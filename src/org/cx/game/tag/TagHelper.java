package org.cx.game.tag;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.cx.game.tools.PropertiesUtil;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class TagHelper {
	
	private final static Map<Integer,Integer> TagCategory_1 = new HashMap<Integer,Integer>();
	private final static Map<Integer,List<Integer>> TagCategory_2 = new HashMap<Integer,List<Integer>>();
	private final static Map<Integer,List<Integer>> Tag_1 = new HashMap<Integer,List<Integer>>();
	private final static Map<Integer,List<Integer>> Tag_2 = new HashMap<Integer,List<Integer>>();

	static {
		
		/*
		 * tag
		 */
		Element tags = getRoot("tag.path").element("tags");
		for(Iterator it = tags.elementIterator("category");it.hasNext();){
			Element category = (Element) it.next();
			Integer categoryCode = Integer.valueOf(category.attribute("code").getText());
			
			TagCategory_2.put(categoryCode, new ArrayList());
			for(Iterator ite = category.elementIterator("tag");ite.hasNext();){
				Element tag = (Element) ite.next();
				Integer tagCode = Integer.valueOf(tag.attribute("code").getText());
				
				TagCategory_1.put(tagCode, categoryCode);
				TagCategory_2.get(categoryCode).add(tagCode);
				
				Tag_2.put(tagCode, new ArrayList<Integer>());
				for(Iterator iter = tag.elementIterator("object");iter.hasNext();){
					Element object = (Element) iter.next();
					Integer objectCode = Integer.valueOf(object.attribute("code").getText());
					
					
					if(null==Tag_1.get(objectCode))
						Tag_1.put(objectCode, new ArrayList<Integer>());
					
					Tag_1.get(objectCode).add(tagCode);
					Tag_2.get(tagCode).add(objectCode);
				}
			}
		}
	}
	
	private static Element getRoot(String pathName) {
		SAXReader saxReader = new SAXReader();
		try {
			InputStream is=new BufferedInputStream(new FileInputStream(PropertiesUtil.getConfigure(pathName))); 
		
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
	
	/**
	 * 根据对象，查询tag
	 * @param object
	 * @return
	 */
	public static List<Integer> queryForObject(Integer object){
		return Tag_1.get(object);
	}
	
	/**
	 * 根据tag，查询对象	
	 * @param tag
	 * @return
	 */
	public static List<Integer> queryForTag(Integer tag){
		return Tag_2.get(tag);
	}
	
	/**
	 * 根据category，查询tag的集合
	 * @param category
	 * @return
	 */
	public static List<Integer> queryForCategory(Integer category){
		return TagCategory_2.get(category);
	}
	
	/**
	 * 根据tag，查询category
	 * @param category
	 * @return
	 */
	public static Integer getCategory(Integer tag){
		return TagCategory_1.get(tag);
	}
}
