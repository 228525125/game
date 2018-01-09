package org.cx.game.builder;

import java.util.Iterator;

import org.cx.game.exception.BuilderException;
import org.cx.game.exception.ParseException;
import org.cx.game.tools.Util;
import org.cx.game.tools.XmlUtil;
import org.dom4j.Attribute;
import org.dom4j.Element;

public class ObjectTypeParse implements IParse {
	
	private ObjectTypeBuilder builder = null;
	
	public ObjectTypeParse(ObjectTypeBuilder builder) {
		// TODO Auto-generated constructor stub
		this.builder = builder;
	}
	
	public void parse (Element objEl) throws ParseException, BuilderException {
		/*
		 * 类型名
		 */
		String className = objEl.attribute(XmlUtil.Attribute_Type).getText();
		builder.setClassName(className);
		
		/*
		 * 使用工厂方法创建对象
		 */
		Attribute factory = objEl.attribute(XmlUtil.Attribute_Factory);
		if(null!=factory){
			Class factoryClass = getType(factory.getText());
			builder.setFactoryClass(factoryClass);
			
			Element param = objEl.element(XmlUtil.Element_FactoryMethodParameter);
			if(null!=param){
				for(Iterator it = param.elementIterator();it.hasNext();){
					Element el = (Element) it.next();
					
					Class cls = getType(el.attribute(XmlUtil.Attribute_Type).getText());
					
					if(Util.isWrapClass(cls)){  //工厂方法参数是基本类型
						BasicTypeBuilder btb = new BasicTypeBuilder();
						new BasicTypeParse(btb).parse(el);
						builder.setFactoryParameter(cls, btb.builder());
					}else{
						throw new ParseException("工厂方法参数只能是基本类型！");
					}
				}
			}
		}
		
		/*
		 * 使用类的构造函数创建对象
		 * 获取构造参数
		 */
		Element param = objEl.element(XmlUtil.Element_Parameter);
		if(null!=param){
			for(Iterator it = param.elementIterator();it.hasNext();){
				Element el = (Element) it.next();
				
				Class cls = getType(el.attribute(XmlUtil.Attribute_Type).getText());
				
				if(Util.isWrapClass(cls)){  //构造函数参数是基本类型
					BasicTypeBuilder btb = new BasicTypeBuilder();
					new BasicTypeParse(btb).parse(el);
					builder.setConstructParameter(cls, btb.builder());
				}else{
					throw new ParseException("构造参数只能是基本类型！"+el.getPath());
				}
			}
		}
		
		/*
		 * 获取属性值
		 */
		for(Iterator it = objEl.elementIterator();it.hasNext();){
			Element el = (Element) it.next();
			String proName = el.getName();           //corps属性名称
			
			Attribute attr = el.attribute(XmlUtil.Attribute_Type);
			if(null==attr)
				continue;
			
			Attribute inter = el.attribute(XmlUtil.Attribute_Interface);   //如果带interface属性，表示使用接口赋值
			if(null!=inter)
				attr = inter;
			
			
			
			Class cls = getType(attr.getText());   //属性类型
			String method = "set"+Util.toUpperCaseFirstOne(proName);  //属性对应的set方法;
			
			Object propertyValue = null;
			if(Util.isWrapClass(cls)){                    //基本类型
				BasicTypeBuilder btb = new BasicTypeBuilder();
				new BasicTypeParse(btb).parse(el);
				propertyValue = btb.builder();
			}else if(null!=el.attribute(XmlUtil.Attribute_Collection)){    //Collection
				CollectionTypeBuilder ctb = new CollectionTypeBuilder();
				new CollectionTypeParse(ctb).parse(el);
				propertyValue = ctb.builder();
			}else if(null!=el.attribute(XmlUtil.Attribute_Map)){    //Map
				MapTypeBuilder mtb = new MapTypeBuilder();
				new MapTypeParse(mtb).parse(el);
				propertyValue = mtb.builder();
			}else {                                        //对象
				ObjectTypeBuilder otb = new ObjectTypeBuilder();
				new ObjectTypeParse(otb).parse(el);
				propertyValue = otb.builder();
			}
			
			builder.set(method, cls, propertyValue);
		}
	}
	
	public static Class getType(String className){
		if(XmlUtil.Attribute_Value_Int.equals(className)){
			return Integer.class;
		}else if(XmlUtil.Attribute_Value_Str.equals(className)){
			return String.class;
		}else if(XmlUtil.Attribute_Value_Double.equals(className)){
			return Double.class;
		}else if(XmlUtil.Attribute_Value_Bool.equals(className)){
			return Boolean.class;
		}
		try {
			return Class.forName(className);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
