package org.cx.game.command.expression;

import org.cx.game.command.CommandBuffer;
import org.cx.game.core.IPlayer;
import org.cx.game.exception.SyntaxValidatorException;
import org.dom4j.Element;

public class WithCacheCommandParameterExpression extends ParameterExpression {
	
	private CommandBuffer buffer = null;
	
	public WithCacheCommandParameterExpression(String cmd, Element cmdEl, CommandBuffer buffer) {
		super(cmd, cmdEl);
		// TODO Auto-generated constructor stub
		this.buffer = buffer;
	}
	
	public CommandBuffer getBuffer() {
		return buffer;
	}
}
