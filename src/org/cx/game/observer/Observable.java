package org.cx.game.observer;

import java.util.List;
import java.util.Observer;

/**
 * 被观察者接口，用于观察模式
 * @author chenxian
 *
 */
public interface Observable {

	public void addObserver(Observer o);
	
	public void deleteObserver(Observer o);
	
	public int countObservers();
	
	public void deleteObservers();
	
	public boolean hasChanged();
	
	public void notifyObservers();
	
	public void notifyObservers(Object arg);
}
