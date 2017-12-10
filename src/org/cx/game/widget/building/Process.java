package org.cx.game.widget.building;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.cx.game.action.IAction;
import org.cx.game.action.IExecute;
import org.cx.game.core.ContextFactory;
import org.cx.game.core.IContext;
import org.cx.game.core.IPlayer;
import org.cx.game.exception.RuleValidatorException;
import org.cx.game.intercepter.IInterceptable;
import org.cx.game.intercepter.IIntercepter;
import org.cx.game.intercepter.IRecover;
import org.cx.game.observer.NotifyInfo;
import org.cx.game.observer.Observable;

public abstract class Process implements IIntercepter, IRecover {

	private Integer waitBout = 0;
	private Integer beginBout = 0;
	private Integer curBout = 0;
	private Boolean isDelete = false;
	
	private List<Map<IInterceptable, IIntercepter>> resetList = new ArrayList<Map<IInterceptable, IIntercepter>>();
	
	private IOption owner = null;
	
	public Process(Integer waitBout, IOption owner) {
		// TODO Auto-generated constructor stub
		this.waitBout = waitBout;
		this.owner = owner;
		
		IPlayer player = owner.getOwner().getOwner().getPlayer();
		this.beginBout = player.getBout();
		this.curBout = player.getBout();
		
		recordIntercepter(player, this);
	}
	
	public Integer getRemainBout(){
		return this.waitBout-(this.curBout-this.beginBout);
	}
	
	@Override
	public String getIntercepterMethod() {
		// TODO Auto-generated method stub
		return "addBout";
	}

	@Override
	public void before(Object[] args) {
		// TODO Auto-generated method stub

	}

	@Override
	public void after(Object[] args) {
		// TODO Auto-generated method stub
		this.curBout = getOwner().getOwner().getOwner().getPlayer().getBout();
	}
	
	public IOption getOwner(){
		return this.owner;
	}

	@Override
	public Boolean isInvoke() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public Integer getOrder() {
		// TODO Auto-generated method stub
		return IIntercepter.Order_Default;
	}

	@Override
	public Integer getLevel() {
		// TODO Auto-generated method stub
		return IIntercepter.Level_Current;
	}

	@Override
	public void delete() {
		// TODO Auto-generated method stub
		this.isDelete = true;
	}

	@Override
	public Boolean isDelete() {
		// TODO Auto-generated method stub
		return this.isDelete;
	}
	
	public void invalid(){
		resetIntercepter();
	}
	
	public void resetIntercepter() {
		// TODO Auto-generated method stub
		resetIntercepter(IIntercepter.Level_Current);
	}
	
	@Override
	public void resetIntercepter(Integer level) {
		// TODO Auto-generated method stub
		for(Map<IInterceptable, IIntercepter> map : resetList){
			for(Entry<IInterceptable, IIntercepter> entry : map.entrySet()){
				IInterceptable interceptable = entry.getKey();
				IIntercepter intercepter = entry.getValue();	
				if(level.equals(intercepter.getLevel()))
					interceptable.deleteIntercepter(intercepter);
			}
		}
	}
	
	public void recordIntercepter(IInterceptable interceptable, IIntercepter intercepter){
		interceptable.addIntercepter(intercepter);
		Map entry = new HashMap<IInterceptable, IIntercepter>();
		entry.put(interceptable, intercepter);
		resetList.add(entry);
	}

}
