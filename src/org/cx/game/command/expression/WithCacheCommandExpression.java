package org.cx.game.command.expression;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Iterator;

import org.cx.game.command.CommandBuffer;
import org.cx.game.command.WithCacheCommand;
import org.cx.game.core.IPlayer;
import org.cx.game.exception.SyntaxValidatorException;
import org.cx.game.tools.XmlUtil;
import org.dom4j.Element;

public class WithCacheCommandExpression extends CommandExpression {

	private CommandBuffer buffer = null;
	
	public WithCacheCommandExpression(String cmd, Element root, CommandBuffer buffer) {
		super(cmd, root);
		// TODO Auto-generated constructor stub
		this.buffer = buffer;
	}	

	@Override
	public WithCacheCommand interpreter() throws SyntaxValidatorException {
		// TODO Auto-generated method stub
		super.interpreter();
		
		WithCacheCommand ret = null;
		
		//String action = getAction(getCmd());
		
		try {
			/*Element commands = getRoot().element(XmlUtil.Command_Commands);
			for(Iterator it = commands.elementIterator(XmlUtil.Command_Command);it.hasNext();){
				Element command = (Element) it.next();
				if(action.equals(command.attribute(XmlUtil.Command_Command_Name).getText())){
					this.element = command;
					Class clazz = Class.forName(this.element.attribute(XmlUtil.Command_Command_Class).getText());
					Constructor<InteriorCommand> c = clazz.getDeclaredConstructor(new Class[]{CommandBuffer.class});
					ret = c.newInstance(buffer);
					
					if(null!=this.element.attribute(XmlUtil.Command_Command_Parameter) && XmlUtil.Command_Command_Parameter_Request.equals(this.element.attribute(XmlUtil.Command_Command_Parameter).getText())){
						setParamRequest(true);
					}else{
						setParamRequest(false);
					}
					break;
				}
			}*/
			
			Class clazz = Class.forName(getCommandElement().attribute(XmlUtil.Command_Command_Class).getText());
			Constructor<WithCacheCommand> c = clazz.getDeclaredConstructor(new Class[]{CommandBuffer.class});
			ret = c.newInstance(buffer);
			
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
