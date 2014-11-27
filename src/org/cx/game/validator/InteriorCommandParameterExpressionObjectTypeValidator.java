package org.cx.game.validator;

import java.util.Iterator;

import org.cx.game.command.CommandBuffer;
import org.cx.game.command.expression.Calculator;
import org.cx.game.command.expression.ParameterExpressionBuffer;
import org.cx.game.core.IPlayer;
import org.cx.game.tools.I18n;
import org.dom4j.Element;

/**
 * 验证参数类型是否正确，这里验证的对象是Object
 * @author chenxian
 *
 */
public class InteriorCommandParameterExpressionObjectTypeValidator extends
		InteriorCommandParameterExpressionIntegratedValidator {

	private Element cmdEl = null;
	
	public InteriorCommandParameterExpressionObjectTypeValidator(String parameter,
			IPlayer player, Element cmdEl, ParameterExpressionBuffer buffer) {
		super(parameter, player, buffer);
		// TODO Auto-generated constructor stub
		this.cmdEl = cmdEl;
	}
	
	@Override
	public Boolean validate() {
		// TODO Auto-generated method stub
		Boolean ret = super.validate();
		if(ret){
			ret = false;
			Element paramEl = cmdEl.element("parameter");
			String type = Calculator.itemToType(CommandBuffer.objectToItem(getParameterObject()));
						
			for(Iterator it = paramEl.elementIterator("option");it.hasNext();){
				Element option = (Element) it.next();
				if(option.getText().equals(type)){
					ret = true;
					break;
				}
			}
		}
		
		if(!ret){
			addMessage(I18n.getMessage(this));
		}
		
		return ret;
	}

}
