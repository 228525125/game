package org.cx.game.intercepter;

import java.util.Comparator;

public class IntercepterAscComparator implements Comparator<IIntercepter> {

	@Override
	public int compare(IIntercepter in1, IIntercepter in2) {
		// TODO Auto-generated method stub		
		return in1.getOrder().compareTo(in2.getOrder());
	}

}
