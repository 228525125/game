package org.cx.game.core;

import java.util.HashMap;
import java.util.Map;

import org.cx.game.card.ICard;
import org.cx.game.command.CommandBuffer;
import org.cx.game.npc.NPC;
import org.cx.game.observer.NotifyInfo;
import org.cx.game.out.JsonOut;
import org.cx.game.widget.ICardGroup;
import org.cx.game.widget.IUseCard;
import org.cx.game.widget.UseCard;

public class DeployState extends PlayState {
	
	@Override
	public void deploy() {
		// TODO Auto-generated method stub
		IPlayer curPlayer = context.getControlPlayer();
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("control", curPlayer);
		map.put("bout", context.getBout());
		NotifyInfo info = new NotifyInfo(NotifyInfo.Context_Deploy,map);
		super.notifyObservers(info);
		
		//判断是否电脑玩家，如果是就启动AI
		if(curPlayer.getComputer())
			curPlayer.automation();
	}

	@Override
	public void done() {
		// TODO Auto-generated method stub
		//操作完毕
		context.setPlayState(IContext.doneState);
		context.done();
	}

	@Override
	public void finish() {
		// TODO Auto-generated method stub
		//操作中比赛随时可能结束
		context.setPlayState(IContext.finishState);
		context.finish();
	}

	@Override
	public void start() {
		// TODO Auto-generated method stub

	}

}
