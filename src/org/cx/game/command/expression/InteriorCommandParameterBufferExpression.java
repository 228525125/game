package org.cx.game.command.expression;

import org.cx.game.command.CommandBuffer;
import org.cx.game.core.IPlayer;
import org.cx.game.exception.SyntaxValidatorException;
import org.cx.game.tools.Util;
import org.cx.game.validator.InteriorCommandParameterExpressionObjectTypeValidator;
import org.dom4j.Element;

public class InteriorCommandParameterBufferExpression extends
		InteriorCommandParameterExpression {

	private IPlayer player = null;
	
	private ParameterExpressionBuffer buffer = null;
	
	public InteriorCommandParameterBufferExpression(IPlayer own,
			String cmd, Element cmdEl) {
		super(cmd, cmdEl);
		this.player = own;
		this.buffer = new ParameterExpressionBuffer(own);
		Util.copyBuffer(buffer, own.getCommandBuffer());
		addValidator(new InteriorCommandParameterExpressionObjectTypeValidator(getParameter(), player, cmdEl, buffer));    //隐含完整性验证
	}
	
	@Override
	public Object interpreter() throws SyntaxValidatorException {
		// TODO Auto-generated method stub
		Object ret = super.interpreter();
		
		String [] ps = getParameter().split(Calculator.SPACE);
		for(String param : ps){             //所有的位置信息都这样表示：类型+位置 = 字母+数字
			String item = Util.filterAlphabet(param);
			
			if(CommandBuffer.OWN.equals(item) || CommandBuffer.OTHER.equals(item)){
				ret = buffer.getPlayer();
			}
			
			if(CommandBuffer.CARDGROUP.equals(item)){
				ret = buffer.getCardGroup();
			}
			
			if(CommandBuffer.USECARD.equals(item)){
				ret = buffer.getUseCard();
			}
			
			if(CommandBuffer.GROUND.equals(item)){
				ret = buffer.getGround();
			}
			
			if(CommandBuffer.PLACE.equals(item)){
				ret = buffer.getPlace();
			}
			
			if(CommandBuffer.CEMETERY.equals(item)){
				ret = buffer.getCemetery();
			}
			
			if(CommandBuffer.TRICKLIST.equals(item)){
				ret = buffer.getTrickList();
			}
			
			if(CommandBuffer.CARD.equals(item)){
				ret = buffer.getCard();
			}			
			
			if(CommandBuffer.SKILL.equals(item)){
				ret = buffer.getSkill();
			}
			
			if(CommandBuffer.TRICK.equals(item)){
				ret = buffer.getTrick();
			}
		}
		
		return ret;
	}

}
