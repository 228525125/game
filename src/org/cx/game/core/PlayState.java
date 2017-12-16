package org.cx.game.core;

import java.util.Observable;

import org.cx.game.exception.RuleValidatorException;
import org.cx.game.out.JsonOut;
import org.cx.game.rule.RuleGroupFactory;

public abstract class PlayState extends Observable implements org.cx.game.observer.Observable{

	public PlayState() {
		// TODO Auto-generated constructor stub
		addObserver(JsonOut.getInstance());
	}
	
	protected IContext context;

	public void setContext(IContext context) {
		this.context = context;
	}
	
	/**
	 * 比赛开始
	 * @throws RuleValidatorException 
	 */
	public abstract void start() throws RuleValidatorException;
	
	/**
	 * 玩家部署
	 * @throws RuleValidatorException 
	 */
	public abstract void deploy() throws RuleValidatorException;;
	
	/**
	 * 回合结束
	 * @throws RuleValidatorException 
	 */
	public abstract void done() throws RuleValidatorException;
	
	/**
	 * 比赛结束
	 * @throws RuleValidatorException 
	 */
	public abstract void finish() throws RuleValidatorException;;
	
	@Override
	public void notifyObservers(Object arg0) {
		// TODO Auto-generated method stub
		super.setChanged();
		super.notifyObservers(arg0);
	}
}
