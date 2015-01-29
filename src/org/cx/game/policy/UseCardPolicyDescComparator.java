package org.cx.game.policy;

import java.util.Comparator;

import org.cx.game.card.ICard;

public class UseCardPolicyDescComparator implements Comparator<ICardPolicy> {

	@Override
	public int compare(ICardPolicy o1, ICardPolicy o2) {
		// TODO Auto-generated method stub
		return o2.getPri().compareTo(o1.getPri());
	}

}
