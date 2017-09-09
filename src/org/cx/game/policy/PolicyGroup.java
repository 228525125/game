package org.cx.game.policy;

import java.util.Collections;
import java.util.List;

public class PolicyGroup implements IPolicyGroup {

	protected List<IPolicy> policyList = null;
	private Integer id = null;

	public PolicyGroup(Integer id) {
		// TODO Auto-generated constructor stub
		this.id = id;
	}
	
	@Override
	public void setPolicyList(List<IPolicy> policyList) {
		// TODO Auto-generated method stub
		this.policyList = policyList;
		for(IPolicy policy : policyList)
			policy.setOwner(this);
	}
	
	@Override
	public Integer getId() {
		// TODO Auto-generated method stub
		return id;
	}

	@Override
	public IPolicy getPolicy() {
		// TODO Auto-generated method stub
		Collections.sort(this.policyList, new PolicyDescComparator());
		IPolicy policy = this.policyList.get(0);
		if(0<policy.getPri())
			return policy;
		else
			return null;
	}
	
	private Object owner = null;
	
	@Override
	public Object getOwner() {
		// TODO Auto-generated method stub
		return owner;
	}
	
	@Override
	public void setOwner(Object owner) {
		// TODO Auto-generated method stub
		this.owner = owner;
	}

}
