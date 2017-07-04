package org.cx.game.widget.building;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.cx.game.action.IUpgrade;
import org.cx.game.core.ContextFactory;
import org.cx.game.core.IContext;
import org.cx.game.core.IPlayer;
import org.cx.game.exception.RuleValidatorException;
import org.cx.game.intercepter.IInterceptable;
import org.cx.game.intercepter.IIntercepter;
import org.cx.game.intercepter.IRecover;

public class UpgradeProcess implements IIntercepter, IRecover {

	private Integer waitBout = 0;
	private Integer beginBout = 0;
	private Integer curBout = 0;
	private Boolean isDelete = false;
	private IUpgrade upgrade = null;
	
	private List<Map<IInterceptable, IIntercepter>> resetList = new ArrayList<Map<IInterceptable, IIntercepter>>();
	
	public UpgradeProcess(Integer waitBout, IUpgrade upgrade) {
		// TODO Auto-generated constructor stub
		this.waitBout = waitBout;
		this.upgrade = upgrade;
		
		IContext context = ContextFactory.getInstance();
		this.beginBout = context.getBout();
		
		recordIntercepter(context, this);
	}
	
	public Integer getProcess(){
		Integer bout = curBout-beginBout;
		return bout*100/waitBout;
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
	public void finish(Object[] args) {
		// TODO Auto-generated method stub
		IContext context = ContextFactory.getInstance();
		
		curBout = context.getBout();
		if((curBout-beginBout)==this.waitBout){
			try {
				this.upgrade.action();
			} catch (RuleValidatorException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@Override
	public void after(Object[] args) {
		// TODO Auto-generated method stub

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
