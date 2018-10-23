package org.cx.game.widget.building;

import org.cx.game.widget.AbstractControlQueue;
import org.cx.game.widget.AbstractOption;

public class OptionSpacingProcess extends AbstractProcess {
	
	public OptionSpacingProcess(Integer waitBout, AbstractControlQueue queue, AbstractOption option) {
		// TODO Auto-generated constructor stub
		super(waitBout, queue, option);
	}
	
	@Override
	public AbstractOption getOwner() {
		// TODO Auto-generated method stub
		return (AbstractOption) super.getOwner();
	}

	@Override
	public void finish(Object[] args) {
		// TODO Auto-generated method stub
		if(Integer.valueOf(0).equals(getRemainBout())){
			invalid();
		}
	}

}
