package org.cx.game.policy;

import java.util.Comparator;

import org.cx.game.card.ICard;

public class UseCardPolicyDescComparator implements Comparator<IUseCardPolicy> {

	@Override
	public int compare(IUseCardPolicy o1, IUseCardPolicy o2) {
		// TODO Auto-generated method stub
		o1.refreshPri();
		o2.refreshPri();
		return o2.getPri().compareTo(o1.getPri());
	}

}
