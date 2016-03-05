package org.cx.game.widget;

import java.util.List;
import java.util.Map;
import java.util.Observer;

import org.cx.game.card.LifeCard;
import org.cx.game.card.trick.ITrick;
import org.cx.game.intercepter.IIntercepter;

public class CemeteryDecorator implements ICemetery{
	
	private ICemetery cemetery = null;
	
	public CemeteryDecorator(ICemetery cemetery) {
		// TODO Auto-generated constructor stub
		this.cemetery = cemetery;
	}
	
	@Override
	public Map<String, List<IIntercepter>> getIntercepterList() {
		// TODO Auto-generated method stub
		return cemetery.getIntercepterList();
	}

	@Override
	public void addIntercepter(IIntercepter intercepter) {
		// TODO Auto-generated method stub
		cemetery.addIntercepter(intercepter);
	}

	@Override
	public void deleteIntercepter(IIntercepter intercepter) {
		// TODO Auto-generated method stub
		cemetery.deleteIntercepter(intercepter);
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		cemetery.clear();
	}

	@Override
	public void addObserver(Observer o) {
		// TODO Auto-generated method stub
		cemetery.addObserver(o);
	}

	@Override
	public void deleteObserver(Observer o) {
		// TODO Auto-generated method stub
		cemetery.deleteObserver(o);
	}

	@Override
	public int countObservers() {
		// TODO Auto-generated method stub
		return cemetery.countObservers();
	}

	@Override
	public void deleteObservers() {
		// TODO Auto-generated method stub
		cemetery.deleteObservers();
	}

	@Override
	public boolean hasChanged() {
		// TODO Auto-generated method stub
		return cemetery.hasChanged();
	}

	@Override
	public void notifyObservers() {
		// TODO Auto-generated method stub
		cemetery.notifyObservers();
	}

	@Override
	public void notifyObservers(Object arg) {
		// TODO Auto-generated method stub
		cemetery.notifyObservers(arg);
	}

	@Override
	public void add(LifeCard life) {
		// TODO Auto-generated method stub
		cemetery.add(life);
	}

	@Override
	public void remove(LifeCard life) {
		// TODO Auto-generated method stub
		cemetery.remove(life);
	}

	@Override
	public Integer getSize() {
		// TODO Auto-generated method stub
		return cemetery.getSize();
	}

	@Override
	public LifeCard getLife(Integer index) {
		// TODO Auto-generated method stub
		return cemetery.getLife(index);
	}

	@Override
	public IPlace getOwner() {
		// TODO Auto-generated method stub
		return cemetery.getOwner();
	}

	@Override
	public Integer indexOf(LifeCard life) {
		// TODO Auto-generated method stub
		return cemetery.indexOf(life);
	}

	@Override
	public Boolean contains(LifeCard life) {
		// TODO Auto-generated method stub
		return cemetery.contains(life);
	}

}
