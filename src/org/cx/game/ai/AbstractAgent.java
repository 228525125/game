package org.cx.game.ai;

import org.cx.game.ai.state.AbstractState;

/**
 * 智能体的基类，该类包含了Policy中需要用到的所有参数
 * @author chenxian
 *
 * @param <T> 智能体
 */
public abstract class AbstractAgent<T> {

	private Integer id = null;
	
	public AbstractAgent(Integer id) {
		// TODO Auto-generated constructor stub
		this.id = id;
	}
	
	public Integer getId(){
		return this.id;
	}
	
	/**
	 * 改变状态
	 * @param newState
	 */
	public void changeState(AbstractState<T> newState){
		getStateMachine().changeState(newState);
	}

	/**
	 * 状态更新，执行状态逻辑
	 */
	public void update(){
		getStateMachine().update();
	}
	
	/**
	 * 是否更新完毕，即当前状态是否还有操作未完成
	 * @return
	 */
	public Boolean isUpdateComplete(){
		return null==getStateMachine().getCurrentState().getPolicy();
	}

	public void handleMessage(Telegram msg) {
		// TODO Auto-generated method stub
		getStateMachine().onMessage(msg);
	}
	
	public abstract StateMachine<T> getStateMachine();
}
