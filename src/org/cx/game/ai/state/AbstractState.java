package org.cx.game.ai.state;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.cx.game.ai.Telegram;
import org.cx.game.ai.policy.AbstractPolicy;
import org.cx.game.ai.policy.PolicyDescComparator;

/**
 * 状态，就像一项任务，进入一种状态等于接受一项任务，状态有自己的策略，策略就是任务的逻辑，
 * 逻辑将任务拆分成若干行为，这些行为会指导智能体完成任务，当任务结束，状态也就结束；
 * @author chenxian
 *
 * @param <T> Agent 智能体
 */
public abstract class AbstractState<T> {
	
	private PolicyDescComparator<T> policyDescComparator = new PolicyDescComparator<T>();
	
	private List<AbstractPolicy<T>> policyList = new ArrayList<AbstractPolicy<T>>();
	
	public void addPolicy(AbstractPolicy<T> policy) {
		// TODO Auto-generated method stub
		this.policyList.add(policy);
	}

	public AbstractPolicy<T> getPolicy() {
		// TODO Auto-generated method stub
		if(getPolicyList().isEmpty())
			return null;
		
		Collections.sort(getPolicyList(), this.policyDescComparator);
		AbstractPolicy<T> policy = this.policyList.get(0);
		if(0<policy.getPri())
			return policy;
		else
			return null;
	}
	
	public List<AbstractPolicy<T>> getPolicyList() {
		return policyList;
	}
	
	public void enter(T t) {
		// TODO Auto-generated method stub
		for(AbstractPolicy<T> policy : getPolicyList())
			policy.setAgent(t);
	}
	
	public void execute(T t) {
		// TODO Auto-generated method stub
		while (true) {
			AbstractPolicy<T> policy = getPolicy();
			if(null!=policy){
				policy.execute();
			}else
				break;
		}
	}
	
	public void exit(T t) {
		// TODO Auto-generated method stub
		
	}

	public Boolean onMessage(T t, Telegram msg) {
		// TODO Auto-generated method stub
		return null;
	}
}
