package org.cx.game.validator;

import java.util.Iterator;

import org.cx.game.command.expression.Calculator;
import org.cx.game.tools.I18n;
import org.dom4j.Element;

/**
 * 验证命令中action部分是否正确
 * @author chenxian
 *
 */
public class CommandExpressionActionNameValidator extends Validator {

	private String cmd;
	private Element el;
	private Element command;
	
	public CommandExpressionActionNameValidator(String cmd, Element el) {
		// TODO Auto-generated constructor stub
		this.cmd = cmd;
		this.el = el;
	}
	
	@Override
	public Boolean validate() {
		// TODO Auto-generated method stub
		Boolean ret = false;
		String name = cmd.split(Calculator.SPACE)[0];
		Element commands = el.element("commands");
		for(Iterator it = commands.elementIterator("command");it.hasNext();){
			this.command = (Element) it.next();
			if(name.equals(command.attribute("name").getText())){
				ret = true;
				break;
			}
		}
		
		if(!ret){
			addMessage(I18n.getMessage(this));
		}
		
		return ret;
	}

	protected Element getCommand() {
		return command;
	}
}
