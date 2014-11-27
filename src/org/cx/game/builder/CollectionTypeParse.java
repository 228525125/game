package org.cx.game.builder;

import java.util.Iterator;

import org.cx.game.exception.BuilderException;
import org.cx.game.exception.ParseException;
import org.cx.game.tools.Util;
import org.dom4j.Attribute;
import org.dom4j.Element;

public class CollectionTypeParse implements IParse {

	private CollectionTypeBuilder builder;
	
	public CollectionTypeParse(CollectionTypeBuilder builder) {
		// TODO Auto-generated constructor stub
		this.builder = builder;
	}
	
	@Override
	public void parse(Element collEl) throws ParseException, BuilderException {
		// TODO Auto-generated method stub
		/*
		 * 类型名
		 */
		String className = collEl.attribute("type").getText();
		builder.setClassName(className);
		
		String collectionType = collEl.attribute("interface").getText();
		builder.setCollectionType(collectionType);
		
		/*
		 * 添加元素
		 */
		for(Iterator it = collEl.elementIterator();it.hasNext();){
			Element el = (Element) it.next();
			Attribute attr = el.attribute("type");
			if(null==attr)
				continue;
			
			Class cls = ObjectTypeParse.getType(attr.getText());   //属性类型
			
			if(Util.isWrapClass(cls)){
				BasicTypeBuilder btb = new BasicTypeBuilder();
				new BasicTypeParse(btb).parse(el);
				builder.add(btb.builder());
			}else{
				ObjectTypeBuilder otb = new ObjectTypeBuilder();
				new ObjectTypeParse(otb).parse(el);
				builder.add(otb.builder());
			}
		}
	}

}
