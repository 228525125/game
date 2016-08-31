package org.cx.game.rule;

import java.util.Observer;

/**
 * 游戏规则
 * @author chenxian
 *
 */
public interface IRule extends Observer {

	public Object getOwner();
}
