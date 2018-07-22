package org.cx.game.widget.building;

import org.cx.game.core.AbstractPlayer;
import org.cx.game.widget.AbstractOption;

public class OptionSpacingProcess extends AbstractProcess {
	
	public OptionSpacingProcess(Integer waitBout, AbstractPlayer player, AbstractOption option) {
		// TODO Auto-generated constructor stub
		super(waitBout, player, option);
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
