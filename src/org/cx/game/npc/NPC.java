package org.cx.game.npc;

import java.util.HashMap;
import java.util.Map;

import org.cx.game.core.Context;
import org.cx.game.core.IPlayer;
import org.cx.game.domain.PlayInfo;

/**
 * NPC实际上是IPlayer的装饰类
 * @author chenxian
 *
 */
public abstract class NPC implements IPlayer {
	private PlayInfo info = null;                 //角色信息
	private IPlayerPolicy playerPolicy = null;   //有的NPC可以比赛，所以有一个比赛策略属性
	private Map map = null;
	private Context context = null;

	public IPlayerPolicy getPlayerPolicy() {
		return playerPolicy;
	}

	public void setPlayerPolicy(IPlayerPolicy playerPolicy) {
		this.playerPolicy = playerPolicy;
	}

	public PlayInfo getInfo() {
		return info;
	}

	public void setInfo(PlayInfo info) {
		this.info = info;
	}

	@Override
	public Context getContext() {
		// TODO Auto-generated method stub
		return context;
	}

	@Override
	public void setContext(Context context) {
		// TODO Auto-generated method stub
		this.context = context;
	}
}
