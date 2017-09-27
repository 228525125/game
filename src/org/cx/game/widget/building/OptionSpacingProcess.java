package org.cx.game.widget.building;

public class OptionSpacingProcess extends Process {

	private IOption option = null;
	
	public OptionSpacingProcess(Integer waitBout, IOption option) {
		// TODO Auto-generated constructor stub
		super(waitBout, option);
		this.option = option;
	}
	
	@Override
	public IOption getOwner() {
		// TODO Auto-generated method stub
		return (IOption) super.getOwner();
	}

	@Override
	public void finish(Object[] args) {
		// TODO Auto-generated method stub
		if(Integer.valueOf(0).equals(getRemainBout())){
			invalid();
			this.option.setAllow(true);
		}
	}

}
