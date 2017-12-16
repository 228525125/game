package org.cx.game.core;

import java.util.List;
import java.util.Map;
import java.util.Observer;

import org.cx.game.action.IAction;
import org.cx.game.card.LifeCard;
import org.cx.game.intercepter.IIntercepter;
import org.cx.game.intercepter.ProxyFactory;
import org.cx.game.rule.RuleGroupFactory;
import org.cx.game.widget.IControlQueue;
import org.cx.game.widget.IGround;

public class ContextDecorator implements IContext {

	private IContext original=null;
	
	public ContextDecorator(IContext context) {
		// TODO Auto-generated constructor stub
		this.original = context;
		context.setDecorator(this);
		
		RuleGroupFactory.bindingRule(this);
	}
	
	@Override
	public Map<String, List<IIntercepter>> getIntercepterList() {
		// TODO Auto-generated method stub
		return original.getIntercepterList();
	}

	@Override
	public void addIntercepter(IIntercepter intercepter) {
		// TODO Auto-generated method stub
		original.addIntercepter(intercepter);
	}

	@Override
	public void deleteIntercepter(IIntercepter intercepter) {
		// TODO Auto-generated method stub
		original.deleteIntercepter(intercepter);
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		original.clear();
	}

	@Override
	public void addObserver(Observer o) {
		// TODO Auto-generated method stub
		original.addObserver(o);
	}

	@Override
	public void deleteObserver(Observer o) {
		// TODO Auto-generated method stub
		original.deleteObserver(o);
	}

	@Override
	public int countObservers() {
		// TODO Auto-generated method stub
		return original.countObservers();
	}

	@Override
	public void deleteObservers() {
		// TODO Auto-generated method stub
		original.deleteObservers();
	}

	@Override
	public boolean hasChanged() {
		// TODO Auto-generated method stub
		return original.hasChanged();
	}

	@Override
	public void notifyObservers() {
		// TODO Auto-generated method stub
		original.notifyObservers();
	}

	@Override
	public void notifyObservers(Object arg) {
		// TODO Auto-generated method stub
		original.notifyObservers(arg);
	}

	@Override
	public IControlQueue getControlQueue() {
		// TODO Auto-generated method stub
		return original.getControlQueue();
	}

	@Override
	public void setControlQueue(IControlQueue queue) {
		// TODO Auto-generated method stub
		original.setControlQueue(queue);
	}

	@Override
	public void setPlayState(PlayState playState) {
		// TODO Auto-generated method stub
		original.setPlayState(playState);
	}

	@Override
	public void start() {
		// TODO Auto-generated method stub
		Object proxy = ProxyFactory.getProxy(this.original);     
		((IContext)proxy).start();
	}

	@Override
	public void deploy() {
		// TODO Auto-generated method stub
		Object proxy = ProxyFactory.getProxy(this.original);     
		((IContext)proxy).deploy();
	}

	@Override
	public void done() {
		// TODO Auto-generated method stub
		Object proxy = ProxyFactory.getProxy(this.original);     
		((IContext)proxy).done();
	}

	@Override
	public void finish() {
		// TODO Auto-generated method stub
		original.finish();
	}

	@Override
	public int getBout() {
		// TODO Auto-generated method stub
		return original.getBout();
	}

	@Override
	public void addBout() {
		// TODO Auto-generated method stub
		Object proxy = ProxyFactory.getProxy(this.original);     
		((IContext)proxy).addBout();
	}
	
	@Override
	public void addDay() {
		// TODO Auto-generated method stub
		Object proxy = ProxyFactory.getProxy(this.original);     
		((IContext)proxy).addDay();
	}
	
	@Override
	public void addWeek() {
		// TODO Auto-generated method stub
		Object proxy = ProxyFactory.getProxy(this.original);     
		((IContext)proxy).addWeek();
	}

	@Override
	public IPlayer getControlPlayer() {
		// TODO Auto-generated method stub
		return original.getControlPlayer();
	}

	@Override
	public void switchControl() {
		// TODO Auto-generated method stub
		original.switchControl();
	}

	@Override
	public String getPlayNo() {
		// TODO Auto-generated method stub
		return original.getPlayNo();
	}

	@Override
	public void setDecorator(ContextDecorator decorator) {
		// TODO Auto-generated method stub
		original.setDecorator(decorator);
	}

	@Override
	public Long newCardPlayId() {
		// TODO Auto-generated method stub
		return original.newCardPlayId();
	}

	@Override
	public List<IPlayer> getPlayerList() {
		// TODO Auto-generated method stub
		return original.getPlayerList();
	}

	@Override
	public void setControlPlayer(IPlayer player) {
		// TODO Auto-generated method stub
		original.setControlPlayer(player);
	}

	@Override
	public Integer getDay() {
		// TODO Auto-generated method stub
		return this.original.getDay();
	}

	@Override
	public Integer getWeek() {
		// TODO Auto-generated method stub
		return this.original.getWeek();
	}

	@Override
	public IGround getGround() {
		// TODO Auto-generated method stub
		return this.original.getGround();
	}

}
