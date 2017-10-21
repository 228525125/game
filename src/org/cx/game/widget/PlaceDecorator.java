package org.cx.game.widget;

import java.util.List;
import java.util.Map;
import java.util.Observer;

import org.cx.game.card.LifeCard;
import org.cx.game.intercepter.IIntercepter;
import org.cx.game.intercepter.ProxyFactory;
import org.cx.game.rule.RuleGroupFactory;
import org.cx.game.widget.building.IBuilding;

public class PlaceDecorator implements IPlace {

	private IPlace original;
	
	public PlaceDecorator(IPlace place) {
		// TODO Auto-generated constructor stub
		this.original = place;
		
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
	public IContainer getContainer() {
		// TODO Auto-generated method stub
		return original.getContainer();
	}

	@Override
	public void in(LifeCard life) {
		// TODO Auto-generated method stub
		Object proxy = ProxyFactory.getProxy(original);
		((IPlace)proxy).in(life);
	}

	@Override
	public LifeCard out() {
		// TODO Auto-generated method stub
		Object proxy = ProxyFactory.getProxy(original);
		return ((IPlace)proxy).out();
	}

	@Override
	public LifeCard getLife() {
		// TODO Auto-generated method stub
		return original.getLife();
	}

	@Override
	public ICemetery getCemetery() {
		// TODO Auto-generated method stub
		return original.getCemetery();
	}

	@Override
	public ITrickList getTrickList() {
		// TODO Auto-generated method stub
		return original.getTrickList();
	}

	@Override
	public Integer getPosition() {
		// TODO Auto-generated method stub
		return original.getPosition();
	}

	@Override
	public Integer getStep(IPlace place, Integer moveType) {
		// TODO Auto-generated method stub
		return original.getStep(place, moveType);
	}

	@Override
	public Boolean getDisable() {
		// TODO Auto-generated method stub
		return original.getDisable();
	}

	@Override
	public void setDisable(Boolean disable) {
		// TODO Auto-generated method stub
		original.setDisable(disable);
	}

	@Override
	public IBuilding getBuilding() {
		// TODO Auto-generated method stub
		return original.getBuilding();
	}

	@Override
	public void setBuilding(IBuilding building) {
		// TODO Auto-generated method stub
		original.setBuilding(building);
	}

	@Override
	public Integer getLandform() {
		// TODO Auto-generated method stub
		return original.getLandform();
	}

	@Override
	public void setLandform(Integer langform) {
		// TODO Auto-generated method stub
		original.setLandform(langform);
	}

	@Override
	public Boolean getEmpty() {
		// TODO Auto-generated method stub
		return original.getEmpty();
	}

	@Override
	public void setEmpty(Boolean empty) {
		// TODO Auto-generated method stub
		original.setEmpty(empty);
	}

}
