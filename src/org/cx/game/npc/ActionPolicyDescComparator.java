package org.cx.game.npc;

import java.util.Comparator;

import org.cx.game.action.IAction;

public class ActionPolicyDescComparator implements Comparator<IAction> {

	@Override
	public int compare(IAction o1, IAction o2) {
		// TODO Auto-generated method stub
		return o2.getPri().compareTo(o1.getPri());
	}

}
