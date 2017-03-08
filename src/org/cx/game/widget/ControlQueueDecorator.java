package org.cx.game.widget;

import java.util.List;
import java.util.Map;
import java.util.Observer;

import org.cx.game.intercepter.IIntercepter;
import org.cx.game.intercepter.ProxyFactory;
import org.cx.game.rule.IRule;
import org.cx.game.widget.ControlQueue.Place;
import org.cx.game.widget.ControlQueue.PlaceComparator;

public class ControlQueueDecorator implements IControlQueue {

	protected IControlQueue original;
	
	public ControlQueueDecorator(IControlQueue queue) {
		// TODO Auto-generated constructor stub
		this.original = queue;
	}
	
	@Override
	public void add(Object object) {
		// TODO Auto-generated method stub
		Object proxy = ProxyFactory.getProxy(original);
		((IControlQueue)proxy).add(object);
	}

	@Override
	public Object out() {
		// TODO Auto-generated method stub
		Object proxy = ProxyFactory.getProxy(original);
		return ((IControlQueue)proxy).out();
	}
	
	@Override
	public void addObserver(Observer o) {
		// TODO Auto-generated method stub
		original.addObserver(o);
	}

	@Override
	public int countObservers() {
		// TODO Auto-generated method stub
		return original.countObservers();
	}

	@Override
	public void deleteObserver(Observer o) {
		// TODO Auto-generated method stub
		original.deleteObserver(o);
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
	public void addIntercepter(IIntercepter intercepter) {
		// TODO Auto-generated method stub
		original.addIntercepter(intercepter);
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		original.clear();
	}

	@Override
	public void deleteIntercepter(IIntercepter intercepter) {
		// TODO Auto-generated method stub
		original.deleteIntercepter(intercepter);
	}

	@Override
	public Map<String,List<IIntercepter>> getIntercepterList() {
		// TODO Auto-generated method stub
		return original.getIntercepterList();
	}

	@Override
	public IRule getRule() {
		// TODO Auto-generated method stub
		return original.getRule();
	}

	@Override
	public void refurbish() {
		// TODO Auto-generated method stub
		original.refurbish();
	}

	@Override
	public void remove(Object object) {
		// TODO Auto-generated method stub
		original.remove(object);
	}
	
	@Override
	public org.cx.game.widget.ControlQueue.Place getPlace(Object object) {
		// TODO Auto-generated method stub
		return original.getPlace(object);
	}

	@Override
	public void moveToPrior(Place place) {
		// TODO Auto-generated method stub
		original.moveToPrior(place);
	}

	@Override
	public Integer getLength() {
		// TODO Auto-generated method stub
		return original.getLength();
	}
}
