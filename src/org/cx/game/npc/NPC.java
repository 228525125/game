package org.cx.game.npc;

import org.cx.game.core.IContext;
import org.cx.game.core.IPlayer;
import org.cx.game.domain.PlayInfo;

/**
 * NPC实际上是IPlayer的装饰类
 * @author chenxian
 *
 */
public abstract class NPC implements IPlayer {
	private PlayInfo info = null;                 //角色信息
	private IContext context = null;

	public PlayInfo getInfo() {
		return info;
	}

	public void setInfo(PlayInfo info) {
		this.info = info;
	}

	@Override
	public IContext getContext() {
		// TODO Auto-generated method stub
		return context;
	}

	@Override
	public void setContext(IContext context) {
		// TODO Auto-generated method stub
		this.context = context;
	}
}
