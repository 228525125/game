package org.cx.game.policy;

import java.util.List;

/**
 * 组策略，它是一组具有某种特定目的的策略的组合
 * @author chenxian
 *
 */
public interface IGroupPolicy {
	
	public Integer getId();
	
	public Object getOwner();
	
	public void setOwner(Object owner);
	
	public void setPolicyList(List<IPolicy> policyList);
	
	public IPolicy getPolicy();
}
