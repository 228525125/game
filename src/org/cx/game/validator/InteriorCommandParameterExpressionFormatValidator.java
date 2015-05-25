package org.cx.game.validator;

import org.cx.game.command.CommandBuffer;
import org.cx.game.command.expression.Calculator;
import org.cx.game.command.expression.ParameterExpressionBuffer;
import org.cx.game.tools.I18n;
import org.cx.game.tools.Util;

/**
 * 验证参数的格式
 * @author chenxian
 *
 */
public class InteriorCommandParameterExpressionFormatValidator extends Validator {

	private String parameter = null;
	private ParameterExpressionBuffer buffer = null;
	
	public InteriorCommandParameterExpressionFormatValidator(String parameter, ParameterExpressionBuffer buffer) {
		// TODO Auto-generated constructor stub
		this.parameter = parameter;
		this.buffer = buffer;
	}
	
	@Override
	public Boolean validate() {
		// TODO Auto-generated method stub
		Boolean ret = true;
		
		String [] ps = parameter.split(Calculator.SPACE);
		String previou = "";                               //保存上一个item，用于验证item顺序
		for(String param : ps){
			String item = Util.filterAlphabet(param);
			String position = Util.filterNumber(param);    //如果没有找到，返回""
			
			String type = Calculator.itemToType(item);
			if(null==type){
				addMessage(I18n.getMessage(this));
				ret = false;
				break;
			}			
			
			if(CommandBuffer.PLACE.equals(type) && !Util.isInteger(position)){
				addMessage(I18n.getMessage(this));
				ret = false;
				break;
			}
			
			if(CommandBuffer.CARD.equals(type) && previou.equals(CommandBuffer.USECARD) && !Util.isInteger(position)){
				addMessage(I18n.getMessage(this));
				ret = false;
				break;
			}
			
			if(CommandBuffer.CARD.equals(type) && previou.equals(CommandBuffer.CARDGROUP) && !Util.isInteger(position)){
				addMessage(I18n.getMessage(this));
				ret = false;
				break;
			}
			
			if(CommandBuffer.CARD.equals(type) && previou.equals(CommandBuffer.CEMETERY) && !Util.isInteger(position)){
				addMessage(I18n.getMessage(this));
				ret = false;
				break;
			}
			
			if(CommandBuffer.SKILL.equals(type) && !Util.isInteger(position)){
				addMessage(I18n.getMessage(this));
				ret = false;
				break;
			}
			
			if(CommandBuffer.TRICK.equals(type) && !Util.isInteger(position)){
				addMessage(I18n.getMessage(this));
				ret = false;
				break;
			}
			
			previou = item;
		}
		
		return ret;
	}

	protected String getParameter() {
		return parameter;
	}

	protected ParameterExpressionBuffer getBuffer() {
		return buffer;
	}
	
	
}
