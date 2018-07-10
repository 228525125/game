package org.cx.game.widget.building;

public class OptionSpacingProcess extends AbstractProcess {
	
	public OptionSpacingProcess(Integer waitBout, AbstractOption option) {
		// TODO Auto-generated constructor stub
		super(waitBout, option.getOwner().getPlayer(), option);
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
