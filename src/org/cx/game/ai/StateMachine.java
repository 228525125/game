package org.cx.game.ai;

import org.cx.game.ai.state.AbstractState;

/**
 * 状态机，智能体标配；
 * @author chenxian
 *
 * @param <T> Agent 智能体
 */
public class StateMachine<T> {
	
	private T owner = null;
	private AbstractState<T> currentState = null;        //当前状态
	private AbstractState<T> previousState = null;       //前一个状态
	private AbstractState<T> priorState = null;         //优先状态，用来处理那些在随时都可能切换的状态，例如逃跑；
	
	public StateMachine(T owner) {
		// TODO Auto-generated constructor stub
		this.owner = owner;
	}
	
	/**
	 * 状态更新
	 */
	public void update(){
		if(null!=this.priorState)
			this.priorState.execute(this.owner);
		
		if(null!=this.currentState)
			this.currentState.execute(this.owner);
	}
	
	/**
	 * 改变状态
	 * @param newState
	 */
	public void changeState(AbstractState<T> newState){
		this.previousState = this.currentState;
		
		if(null!=this.currentState)
			this.currentState.exit(this.owner);		
		
		this.currentState = newState;
		
		this.currentState.enter(this.owner);
	}
	
	/**
	 * 状态翻转
	 */
	public void revertToPreviousState(){
		if(null!=this.previousState)
			changeState(this.previousState);
	}
	
	/**
	 * 接收消息
	 * @param msg
	 * @return 消息是否被处理
	 */
	public Boolean onMessage(Telegram msg){
		
		if(null!=this.currentState && this.currentState.onMessage(this.owner, msg)){
			return true;
		}
		
		if(null!=this.priorState && this.priorState.onMessage(this.owner, msg)){
			return true;
		}
		
		return false;
	}

	public void setCurrentState(AbstractState<T> currentState) {
		this.currentState = currentState;
	}
	
	public AbstractState<T> getCurrentState() {
		return currentState;
	}

	public void setPreviousState(AbstractState<T> previousState) {
		this.previousState = previousState;
	}
	
	public AbstractState<T> getPreviousState() {
		return previousState;
	}

	public void setPriorState(AbstractState<T> priorState) {
		this.priorState = priorState;
	}
	
	public AbstractState<T> getPriorState() {
		return priorState;
	}
	
	public T getOwner() {
		return owner;
	}

}
