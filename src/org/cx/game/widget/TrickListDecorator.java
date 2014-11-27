package org.cx.game.widget;

import java.util.List;
import java.util.Map;
import java.util.Observer;

import org.cx.game.card.TrickCard;
import org.cx.game.card.skill.ITrick;
import org.cx.game.intercepter.IIntercepter;

public class TrickListDecorator implements ITrickList {

	private ITrickList trickList = null;
	
	public TrickListDecorator(ITrickList trickList) {
		// TODO Auto-generated constructor stub
		this.trickList = trickList;
	}

	@Override
	public Map<String, List<IIntercepter>> getIntercepterList() {
		// TODO Auto-generated method stub
		return trickList.getIntercepterList();
	}

	@Override
	public void addIntercepter(IIntercepter intercepter) {
		// TODO Auto-generated method stub
		trickList.addIntercepter(intercepter);
	}

	@Override
	public void deleteIntercepter(IIntercepter intercepter) {
		// TODO Auto-generated method stub
		trickList.deleteIntercepter(intercepter);
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		trickList.clear();
	}

	@Override
	public void addObserver(Observer o) {
		// TODO Auto-generated method stub
		trickList.addObserver(o);
	}

	@Override
	public void deleteObserver(Observer o) {
		// TODO Auto-generated method stub
		trickList.deleteObserver(o);
	}

	@Override
	public int countObservers() {
		// TODO Auto-generated method stub
		return trickList.countObservers();
	}

	@Override
	public void deleteObservers() {
		// TODO Auto-generated method stub
		trickList.deleteObservers();
	}

	@Override
	public boolean hasChanged() {
		// TODO Auto-generated method stub
		return trickList.hasChanged();
	}

	@Override
	public void notifyObservers() {
		// TODO Auto-generated method stub
		trickList.notifyObservers();
	}

	@Override
	public void notifyObservers(Object arg) {
		// TODO Auto-generated method stub
		trickList.notifyObservers(arg);
	}

	@Override
	public void add(ITrick trick) {
		// TODO Auto-generated method stub
		trickList.add(trick);
	}

	@Override
	public void remove(ITrick trick) {
		// TODO Auto-generated method stub
		trickList.remove(trick);
	}

	@Override
	public Integer getSize() {
		// TODO Auto-generated method stub
		return trickList.getSize();
	}

	@Override
	public ITrick getTrick(Integer index) {
		// TODO Auto-generated method stub
		return trickList.getTrick(index);
	}

	@Override
	public IPlace getPlace() {
		// TODO Auto-generated method stub
		return trickList.getPlace();
	}

	@Override
	public Integer indexOf(ITrick trick) {
		// TODO Auto-generated method stub
		return trickList.indexOf(trick);
	}

	@Override
	public Boolean contains(ITrick trick) {
		// TODO Auto-generated method stub
		return trickList.contains(trick);
	}
	
	
}
