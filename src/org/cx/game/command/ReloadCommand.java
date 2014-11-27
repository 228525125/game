package org.cx.game.command;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.cx.game.core.IPlayer;
import org.cx.game.exception.CommandValidatorException;
import org.cx.game.exception.ValidatorException;
import org.cx.game.observer.NotifyInfo;
import org.cx.game.widget.IUseCard;

public class ReloadCommand extends InteriorCommand {

	public ReloadCommand(IPlayer player) {
		// TODO Auto-generated constructor stub
		super(player);
	}
	
	@Override
	public void execute() throws ValidatorException {
		// TODO Auto-generated method stub
		super.execute();
		
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		Map<String,Object> map = new HashMap<String,Object>();
		
		map.put("player", player);
		map.put("group", player.getCardGroup().toList());
		map.put("use", player.getUseCard().toList());
		map.put("god", player.getGround().toList());      //ground已被屏蔽
		list.add(map);
		
		IPlayer other = player.getContext().getOtherPlayer(player);
		map = new HashMap<String,Object>();
		map.put("other", other);
		map.put("othergroup", other.getCardGroup().toList());
		map.put("otheruse", other.getUseCard().toList());
		list.add(map);
		
		NotifyInfo info = new NotifyInfo(NotifyInfo.Command_Reload,list); 
		super.notifyObservers(info);    //通知观察者
	}
}
