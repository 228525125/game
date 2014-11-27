package org.cx.game.command.expression;

import org.cx.game.core.IPlayer;
import org.dom4j.Element;

public class InteriorCommandParameterExpressionFactory {

	public static InteriorCommandParameterExpression getInstance(IPlayer player,String cmd, Element cmdEl){
		String action = Expression.getAction(cmd);
		if("query".equals(action)){
			return new InteriorCommandParameterActionExpression(cmd,cmdEl); 
		}else{
			return new InteriorCommandParameterBufferExpression(player, cmd, cmdEl);
		}
	}
}
