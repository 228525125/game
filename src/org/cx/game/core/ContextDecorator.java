package org.cx.game.core;

import java.util.List;
import java.util.Map;
import java.util.Observer;

import org.cx.game.action.IAction;
import org.cx.game.card.LifeCard;
import org.cx.game.intercepter.IIntercepter;
import org.cx.game.intercepter.ProxyFactory;
import org.cx.game.widget.IControlQueue;

public class ContextDecorator implements IContext {

	private IContext original=null;
	
	public ContextDecorator(IContext context) {
		// TODO Auto-generated constructor stub
		this.original = context;
		context.setDecorator(this);
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
	public IControlQueue getQueue() {
		// TODO Auto-generated method stub
		return original.getQueue();
	}

	@Override
	public void setQueue(IControlQueue queue) {
		// TODO Auto-generated method stub
		original.setQueue(queue);
	}

	@Override
	public void setPlayState(PlayState playState) {
		// TODO Auto-generated method stub
		original.setPlayState(playState);
	}

	@Override
	public void start() {
		// TODO Auto-generated method stub
		original.start();
	}

	@Override
	public void deploy() {
		// TODO Auto-generated method stub
		original.deploy();
	}

	@Override
	public void done() {
		// TODO Auto-generated method stub
		original.done();
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
	public LifeCard getControlLife() {
		// TODO Auto-generated method stub
		return original.getControlLife();
	}

	@Override
	public IPlayer getControlPlayer() {
		// TODO Auto-generated method stub
		return original.getControlPlayer();
	}

	@Override
	public IPlayer getOtherPlayer(IPlayer player) {
		// TODO Auto-generated method stub
		return original.getOtherPlayer(player);
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

}