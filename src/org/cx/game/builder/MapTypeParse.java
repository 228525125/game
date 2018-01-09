package org.cx.game.builder;

import java.util.Iterator;

import org.cx.game.exception.BuilderException;
import org.cx.game.exception.ParseException;
import org.cx.game.tools.Util;
import org.cx.game.tools.XmlUtil;
import org.dom4j.Attribute;
import org.dom4j.Element;

public class MapTypeParse implements IParse {

	private MapTypeBuilder builder;
	
	public MapTypeParse(MapTypeBuilder builder) {
		// TODO Auto-generated constructor stub
		this.builder = builder;
	}
	
	@Override
	public void parse(Element el) throws ParseException, BuilderException {
		// TODO Auto-generated method stub
		/*
		 * 类型名
		 */
		String className = el.attribute(XmlUtil.Attribute_Type).getText();
		builder.setClassName(className);
		
		String mapType = el.attribute(XmlUtil.Attribute_Interface).getText();
		builder.setMapType(mapType);
		
		/*
		 * 添加元素
		 */
		for(Iterator it = el.elementIterator();it.hasNext();){
			Element mapEl = (Element) it.next();

			Element keyEl = mapEl.element(XmlUtil.Element_Key);
			Element valueEl = mapEl.element(XmlUtil.Element_Value);
			Object key = parseObject(keyEl);
			Object value = parseObject(valueEl);
			
			if(null!=key && null!=value){
				builder.put(key, value);
			}
		}
		
	}
	
	private Object parseObject(Element el) throws ParseException, BuilderException{
		Object ret = null;
		
		Attribute attr = el.attribute("type");
		if(null==attr)
			return ret;
		
		Class cls = ObjectTypeParse.getType(attr.getText());   //属性类型
		
		if(Util.isWrapClass(cls)){
			BasicTypeBuilder btb = new BasicTypeBuilder();
			new BasicTypeParse(btb).parse(el);
			ret = btb.builder();
		}else{
			ObjectTypeBuilder otb = new ObjectTypeBuilder();
			new ObjectTypeParse(otb).parse(el);
			ret = otb.builder();
		}
		
		return ret;
	}

}
