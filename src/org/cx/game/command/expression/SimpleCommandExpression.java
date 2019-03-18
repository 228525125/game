package org.cx.game.command.expression;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import org.cx.game.command.Command;
import org.cx.game.exception.SyntaxValidatorException;
import org.cx.game.tools.XmlUtil;
import org.dom4j.Element;

public class SimpleCommandExpression extends CommandExpression {

	public SimpleCommandExpression(String cmd, Element root) {
		super(cmd, root);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public Command interpreter() throws SyntaxValidatorException {
		// TODO Auto-generated method stub
		super.interpreter();
		
		Command ret = null;
		
		try{
			Class clazz = Class.forName(getCommandElement().attribute(XmlUtil.Command_Command_Class).getText());
			Constructor<Command> c = clazz.getDeclaredConstructor();
			ret = c.newInstance();
			
			if(null!=getCommandElement().attribute(XmlUtil.Command_Command_Parameter) && XmlUtil.Command_Command_Parameter_Request.equals(getCommandElement().attribute(XmlUtil.Command_Command_Parameter).getText())){
				setParamRequest(true);
			}else{
				setParamRequest(false);
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

}
