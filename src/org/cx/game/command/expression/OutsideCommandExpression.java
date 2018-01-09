package org.cx.game.command.expression;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Iterator;

import org.cx.game.command.OutsideCommand;
import org.cx.game.exception.SyntaxValidatorException;
import org.cx.game.tools.XmlUtil;
import org.dom4j.Element;

public class OutsideCommandExpression extends CommandExpression {
	
	private Element element = null;
	
	public OutsideCommandExpression(String cmd, Element root) {
		super(cmd, root);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public OutsideCommand interpreter() throws SyntaxValidatorException {
		// TODO Auto-generated method stub
		super.interpreter();
		
		OutsideCommand ret = null;
		
		String action = getCmd().split(Calculator.SPACE)[0];
		
		try {
			Element commands = getRoot().element(XmlUtil.Command_Commands);
			for(Iterator it = commands.elementIterator(XmlUtil.Command_Command);it.hasNext();){
				Element command = (Element) it.next();
				if(action.equals(command.attribute(XmlUtil.Command_Command_Name).getText())){
					this.element = command;
					Class clazz = Class.forName(this.element.attribute(XmlUtil.Command_Command_Class).getText());
					Constructor<OutsideCommand> c = clazz.getDeclaredConstructor();
					ret = c.newInstance();
					
					if(null!=this.element.attribute(XmlUtil.Command_Command_Parameter) && XmlUtil.Command_Command_Parameter_Request.equals(this.element.attribute(XmlUtil.Command_Command_Parameter).getText())){
						setParamRequest(true);
					}else{
						setParamRequest(false);
					}
					break;
				}
			}

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ret;
	}

	public Element getElement() {
		return element;
	}

}
