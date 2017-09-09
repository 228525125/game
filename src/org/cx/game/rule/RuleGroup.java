package org.cx.game.rule;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class RuleGroup extends Observable implements Observer {
	
	private Observable messageSource = null;
	
	public void setRuleList(List<IRule> ruleList) {
		for(IRule rule : ruleList)
			this.addObserver(rule);
	}

	/**
	 * 发起消息的对象
	 * @return
	 */
	public Observable getMessageSource() {
		return messageSource;
	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		this.messageSource = o;
		notifyObservers(arg);
		this.messageSource = null;
	}
	
	@Override
	public void notifyObservers(Object arg) {
		// TODO Auto-generated method stub
		super.setChanged();
		super.notifyObservers(arg);
	}
	
}
