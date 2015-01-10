package org.cx.game.builder;

import org.cx.game.exception.BuilderException;
import org.cx.game.exception.ParseException;
import org.dom4j.Element;

public class BasicTypeParse implements IParse {

	private BasicTypeBuilder builder;
	
	public BasicTypeParse(IBuilder builder) {
		// TODO Auto-generated constructor stub
		this.builder = (BasicTypeBuilder) builder;
	}

	@Override
	public void parse(Element el) throws ParseException, BuilderException {
		// TODO Auto-generated method stub
		Class cls = ObjectTypeParse.getType(el.attribute("type").getText());
		builder.setClazz(cls);
		String value = el.getText();
		builder.setValue(value);
	}
	
	public static void main(String[] args) {
		String [] str = new String[]{};
		System.out.println(str.getClass());
	}
}
