package org.cx.game.core;

import java.util.HashMap;
import java.util.Map;

import org.cx.game.card.LifeCard;
import org.cx.game.exception.RuleValidatorException;
import org.cx.game.observer.NotifyInfo;
import org.cx.game.out.JsonOut;

public class DoneState extends PlayState {

	public DoneState() {
		// TODO Auto-generated constructor stub
		addObserver(new JsonOut());
	}
	
	@Override
	public void deploy() {
		// TODO Auto-generated method stub
		context.setPlayState(IContext.deployState);
		context.deploy();
	}

	@Override
	public void done() {
		// TODO Auto-generated method stub
		LifeCard life = context.getControlLife();
		if(null!=life && life.getActivate().getActivation()){           //结束回合，不管life是否已经完成操作
			try {
				life.activate(false);
			} catch (RuleValidatorException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		IPlayer curPlayer = context.getControlPlayer();
		Integer bout = context.getBout();
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("control", curPlayer);
		map.put("life", life);
		NotifyInfo info = new NotifyInfo(NotifyInfo.Context_Done,map);
		super.notifyObservers(info);

		context.switchControl();                   //转换控制权
		
		deploy();         //部署
	}

	@Override
	public void finish() {
		// TODO Auto-generated method stub

	}

	@Override
	public void start() {
		// TODO Auto-generated method stub

	}

}
