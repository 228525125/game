package org.cx.game.validator;

import java.util.Iterator;

import org.cx.game.command.expression.Calculator;
import org.cx.game.tools.I18n;
import org.cx.game.tools.Util;
import org.dom4j.Element;

/**
 * 验证SetCommand的参数，格式为：set 属性名+整数
 * @author chenxian
 *
 */
public class InteriorCommandParameterExpressionPropertyValidator extends
		Validator {

	private String parameter = null;
	private Element cmdEl = null;
	
	public InteriorCommandParameterExpressionPropertyValidator(String parameter, Element cmdEl) {
		// TODO Auto-generated constructor stub
		this.parameter = parameter;
		this.cmdEl = cmdEl;
	}
	
	@Override
	public Boolean validate() {
		// TODO Auto-generated method stub
		Boolean ret = false;
		
		if(-1==parameter.indexOf("[") || -1==parameter.indexOf("]")){
			addMessage(I18n.getMessage(InteriorCommandParameterExpressionPropertyValidator.class.getName()));
			return false;
		}
		
		String value = parameter.substring(parameter.indexOf("[")+1, parameter.indexOf("]"));
		if("".equals(value)){
			addMessage(I18n.getMessage(InteriorCommandParameterExpressionPropertyValidator.class.getName()));
			return false;
		}
		
		String property = parameter.substring(0, parameter.indexOf("["));
		
		String type = Calculator.itemToType(property);
		
		if(null==type){
			addMessage(I18n.getMessage(InteriorCommandParameterExpressionPropertyValidator.class.getName()));
			return false;
		}
		
		Element paramEl = cmdEl.element("parameter");
		
		for(Iterator it = paramEl.elementIterator("option");it.hasNext();){
			Element option = (Element) it.next();
			if(option.getText().equals(type)){
				ret = true;
				break;
			}
		}
		
		if(!ret)
			addMessage(I18n.getMessage(InteriorCommandParameterExpressionPropertyValidator.class.getName()));
		
		return ret;
	}
}
