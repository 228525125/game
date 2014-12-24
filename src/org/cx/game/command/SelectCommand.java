package org.cx.game.command;

import java.util.HashMap;
import java.util.Map;

import org.cx.game.core.IPlayer;
import org.cx.game.exception.ValidatorException;
import org.cx.game.observer.NotifyInfo;

public class SelectCommand extends InteriorCommand {
	
	public SelectCommand(IPlayer player) {
		// TODO Auto-generated constructor stub
		super(player);
	}
	
	@Override
	public void execute() throws ValidatorException {
		// TODO Auto-generated method stub
		super.execute();
		
		buffer.set(parameter);
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("player", buffer.getPlayer());
		
		if(null!=buffer.getContainer())
			map.put("container", buffer.getContainer());
		
		if(null!=buffer.getPlace()){
			map.put("place", buffer.getPlace());
			map.put("position", buffer.getPlace().getPosition());
		}
		
		if(null!=buffer.getCemetery()){
			map.put("cemetery", buffer.getCemetery());
			map.put("position", buffer.getCemetery().getOwner().getPosition());
		}
		
		if(null!=buffer.getTrickList()){
			map.put("tricklist", buffer.getTrickList());
			map.put("position", buffer.getTrickList().getOwner().getPosition());
		}
		
		if(null!=buffer.getCard()){
			map.put("card", buffer.getCard());
			map.put("position", buffer.getCard().getContainerPosition());
		}
		
		if(null!=buffer.getSkill())
			map.put("skill", buffer.getSkill());
		
		if(null!=buffer.getTrick())
			map.put("trick", buffer.getTrick());
		
		NotifyInfo info = new NotifyInfo(NotifyInfo.Command_Select,map); 
		super.notifyObservers(info);    
	}
}
