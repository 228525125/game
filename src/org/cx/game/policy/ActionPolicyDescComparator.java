package org.cx.game.policy;

import java.util.Comparator;

import org.cx.game.action.IAction;

public class ActionPolicyDescComparator implements Comparator<IActionPolicy> {

	@Override
	public int compare(IActionPolicy o1, IActionPolicy o2) {
		// TODO Auto-generated method stub
		o1.refreshPri();
		o2.refreshPri();
		return o2.getPri().compareTo(o1.getPri());
	}

}
