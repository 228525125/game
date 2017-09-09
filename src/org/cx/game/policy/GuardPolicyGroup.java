package org.cx.game.policy;

import java.util.List;

/**
 * 原地驻守
 * @author chenxian
 *
 */
public class GuardPolicyGroup extends PolicyGroup {

	private Integer guardPosition = null;
	
	public static final Integer gpId = 10400001;
	
	public GuardPolicyGroup() {
		// TODO Auto-generated constructor stub
		super(gpId);
	}
	
	public void setGuardPosition(Integer gp) {
		// TODO Auto-generated method stub
		this.guardPosition = gp;
		for(IPolicy policy : policyList){
			if (policy instanceof RunbackPolicy) {
				RunbackPolicy rp = (RunbackPolicy) policy;
				rp.setGuardPoistion(this.guardPosition);
			}
			
			if (policy instanceof SallyPolicy) {
				SallyPolicy sp = (SallyPolicy) policy;
				sp.setGuardPosition(this.guardPosition);
			}
		}
	}
	
	@Override
	public void setPolicyList(List<IPolicy> policyList) {
		// TODO Auto-generated method stub 反射需要
		super.setPolicyList(policyList);
	}
}
