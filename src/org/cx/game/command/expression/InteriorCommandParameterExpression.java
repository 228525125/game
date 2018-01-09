package org.cx.game.command.expression;

import org.cx.game.core.IPlayer;
import org.dom4j.Element;

/**
 * 内部命令的参数表达式
 * @author chenxian
 *
 */
public class InteriorCommandParameterExpression extends ParameterExpression {

	private IPlayer player = null;
	
	public InteriorCommandParameterExpression(IPlayer player, String cmd, Element cmdEl) {
		// TODO Auto-generated constructor stub
		super(cmd, cmdEl);
		this.player = player;
	}
	
	public IPlayer getPlayer() {
		return player;
	}
}
