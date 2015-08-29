package org.cx.game.command.expression;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Iterator;

import org.cx.game.command.InteriorCommand;
import org.cx.game.core.IPlayer;
import org.cx.game.exception.SyntaxValidatorException;
import org.cx.game.validator.IValidator;
import org.dom4j.Element;

public class InteriorCommandExpression extends CommandExpression {

	private IPlayer player = null;
	private Element element = null;
	
	public InteriorCommandExpression(IPlayer player, String cmd, Element root) {
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
			Element commands = getRoot().element("commands");
			for(Iterator it = commands.elementIterator("command");it.hasNext();){
				Element command = (Element) it.next();
				if(action.equals(command.attribute("name").getText())){
					this.element = command;
					Class clazz = Class.forName(this.element.attribute("class").getText());
					Constructor<InteriorCommand> c = clazz.getDeclaredConstructor(new Class[]{IPlayer.class});
					ret = c.newInstance(player);
					
					if(null!=this.element.attribute("parameter") && "request".equals(this.element.attribute("parameter").getText())){
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
