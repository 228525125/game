package org.cx.game.policy;

import java.util.Comparator;

import org.cx.game.action.IAction;

public class PolicyDescComparator implements Comparator<IPolicy> {

	@Override
	public int compare(IPolicy o1, IPolicy o2) {
		// TODO Auto-generated method stub
		o1.calculate();
		o2.calculate();
		return o2.getPri().compareTo(o1.getPri());
	}

}
