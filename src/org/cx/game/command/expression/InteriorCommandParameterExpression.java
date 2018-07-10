package org.cx.game.command.expression;

import org.cx.game.core.AbstractPlayer;
import org.dom4j.Element;

/**
 * 内部命令的参数表达式
 * @author chenxian
 *
 */
public class InteriorCommandParameterExpression extends ParameterExpression {

	private AbstractPlayer player = null;
	
	public InteriorCommandParameterExpression(AbstractPlayer player, String cmd, Element cmdEl) {
		// TODO Auto-generated constructor stub
		super(cmd, cmdEl);
		this.player = player;
	}
	
	public AbstractPlayer getPlayer() {
		return player;
	}
}
