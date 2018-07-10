package org.cx.game.command.expression;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Iterator;

import org.cx.game.command.InteriorCommand;
import org.cx.game.core.AbstractPlayer;
import org.cx.game.exception.SyntaxValidatorException;
import org.cx.game.tools.XmlUtil;
import org.dom4j.Element;

public class InteriorCommandExpression extends CommandExpression {

	private AbstractPlayer player = null;
	private Element element = null;
	
	public InteriorCommandExpression(AbstractPlayer player, String cmd, Element root) {
		super(cmd, root);
		// TODO Auto-generated constructor stub
		this.player = player;
	}	

	@Override
	public InteriorCommand interpreter() throws SyntaxValidatorException {
		// TODO Auto-generated method stub
		super.interpreter();
		
		InteriorCommand ret = null;
		
		String action = getAction(getCmd());
		
		try {
			Element commands = getRoot().element(XmlUtil.Command_Commands);
			for(Iterator it = commands.elementIterator(XmlUtil.Command_Command);it.hasNext();){
				Element command = (Element) it.next();
				if(action.equals(command.attribute(XmlUtil.Command_Command_Name).getText())){
					this.element = command;
					Class clazz = Class.forName(this.element.attribute(XmlUtil.Command_Command_Class).getText());
					Constructor<InteriorCommand> c = clazz.getDeclaredConstructor(new Class[]{AbstractPlayer.class});
					ret = c.newInstance(player);
					
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
