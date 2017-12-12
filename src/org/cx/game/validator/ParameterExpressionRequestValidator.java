package org.cx.game.validator;

import org.cx.game.command.expression.Calculator;
import org.cx.game.tools.I18n;
import org.dom4j.Element;

/**
 * 验证参数是必须的情况
 * @author chenxian
 *
 */
public class ParameterExpressionRequestValidator extends CommandExpressionActionNameValidator {

	private String cmd;
	
	public ParameterExpressionRequestValidator(String cmd, Element el) {
		// TODO Auto-generated constructor stub
		super(cmd,el);
		this.cmd = cmd;
	}
	
	@Override
	public Boolean validate() {
		// TODO Auto-generated method stub
		Boolean ret = super.validate();
		if(ret){
			Element el = getCommand();
			if(null!=el.attribute("parameter") && "request".equals(el.attribute("parameter").getText())){
				if(2>cmd.split(Calculator.SPACE).length){
					addMessage(I18n.getMessage(ParameterExpressionRequestValidator.class.getName()));
					ret = false;
				}
			}
		}
		
		return ret;
	}
}
