package org.cx.game.npc;

import java.util.Comparator;

import org.cx.game.card.ICard;

public class UseCardPolicyDescComparator implements Comparator<ICard> {

	@Override
	public int compare(ICard o1, ICard o2) {
		// TODO Auto-generated method stub
		return o2.getUseCardPolicy().getPri().compareTo(o1.getUseCardPolicy().getPri());
	}

}
