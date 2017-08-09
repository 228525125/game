package org.cx.game.policy;

import java.util.List;

/**
 * 原地驻守
 * @author chenxian
 *
 */
public class GuardGroupPolicy extends GroupPolicy {

	private Integer guardPosition = null;
	
	public GuardGroupPolicy(Integer guardPosition) {
		// TODO Auto-generated constructor stub
		super(10400001);
		this.guardPosition = guardPosition;
	}
	
	@Override
	public void setPolicyList(List<IPolicy> policyList) {
		// TODO Auto-generated method stub
		for(IPolicy policy : policyList){
			if (policy instanceof RunbackPolicy) {
				RunbackPolicy rp = (RunbackPolicy) policy;
				rp.setGuardPoistion(this.guardPosition);
			}
			
			if (policy instanceof SallyPolicy) {
				SallyPolicy sp = (SallyPolicy) policy;
				sp.setGuardPoistion(this.guardPosition);
			}
		}
		super.setPolicyList(policyList);
	}
}
