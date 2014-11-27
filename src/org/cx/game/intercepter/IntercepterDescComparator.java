package org.cx.game.intercepter;

import java.util.Comparator;

public class IntercepterDescComparator implements Comparator<IIntercepter> {

	@Override
	public int compare(IIntercepter in1, IIntercepter in2) {
		// TODO Auto-generated method stub
		return in2.getOrder().compareTo(in1.getOrder());
	}

}
