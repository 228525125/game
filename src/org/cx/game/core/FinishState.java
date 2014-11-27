package org.cx.game.core;

import java.util.HashMap;
import java.util.Map;

import org.cx.game.observer.NotifyInfo;
import org.cx.game.out.JsonOut;

public class FinishState extends PlayState {

	public FinishState() {
		// TODO Auto-generated constructor stub
		addObserver(new JsonOut());
	}
	
	@Override
	public void deploy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void done() {
		// TODO Auto-generated method stub

	}

	@Override
	public void finish() {
		// TODO Auto-generated method stub
		context.getPlayer1().getCommandBuffer().clear();
		context.getPlayer2().getCommandBuffer().clear();
		Map<String,Object> map = new HashMap<String,Object>();
		NotifyInfo info = new NotifyInfo(NotifyInfo.Context_Finish,map);
		super.notifyObservers(info);
	}

	@Override
	public void start() {
		// TODO Auto-generated method stub

	}

}
