package org.cx.game.ai.policy;

import java.util.Comparator;

public class PolicyDescComparator<T> implements Comparator<AbstractPolicy<T>> {
	
	@Override
	public int compare(AbstractPolicy<T> o1, AbstractPolicy<T> o2) {
		// TODO Auto-generated method stub
		o1.calculate();
		o2.calculate();
		return o2.getPri().compareTo(o1.getPri());
	}

}
