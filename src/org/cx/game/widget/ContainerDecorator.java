package org.cx.game.widget;

import java.util.List;
import java.util.Map;
import java.util.Observer;

import org.cx.game.card.ICard;
import org.cx.game.core.IPlayer;
import org.cx.game.intercepter.IIntercepter;
import org.cx.game.intercepter.ProxyFactory;

public class ContainerDecorator implements IContainer {

	protected IContainer original;
	
	public ContainerDecorator(IContainer container) {
		// TODO Auto-generated constructor stub
		container.setDecorator(this);
		this.original = container;
	}

	@Override
	public void remove(ICard card) {
		// TODO Auto-generated method stub
		Object proxy = ProxyFactory.getProxy(original);
		((IContainer)proxy).remove(card);
	}

	@Override
	public Integer getSize() {
		// TODO Auto-generated method stub
		return original.getSize();
	}
	
	@Override
	public ICard getCard(Integer position) {
		// TODO Auto-generated method stub
		return original.getCard(position);
	}
	
	@Override
	public Integer getPosition(ICard card) {
		// TODO Auto-generated method stub
		return original.getPosition(card);
	}

	@Override
	public IPlayer getPlayer() {
		// TODO Auto-generated method stub
		return original.getPlayer();
	}

	@Override
	public List<ICard> list() {
		// TODO Auto-generated method stub
		return original.list();
	}

	@Override
	public void setPlayer(IPlayer player) {
		// TODO Auto-generated method stub
		original.setPlayer(player);
	}

	@Override
	public void add(Integer position, ICard card) {
		// TODO Auto-generated method stub
		Object proxy = ProxyFactory.getProxy(original);  
		((IContainer)proxy).add(position, card);
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
	public String getName() {
		// TODO Auto-generated method stub
		return original.getName();
	}
	
	@Override
	public Boolean contains(ICard card) {
		// TODO Auto-generated method stub
		return original.contains(card);
	}
	
	@Override
	public List<ICard> toList() {
		// TODO Auto-generated method stub
		return original.toList();
	}

	@Override
	public void setDecorator(ContainerDecorator decorator) {
		// TODO Auto-generated method stub
		original.setDecorator(decorator);
	}
	
}
