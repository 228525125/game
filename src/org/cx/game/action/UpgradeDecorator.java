package org.cx.game.action;

public class UpgradeDecorator extends ActionDecorator implements IUpgrade {

	private IUpgrade upgrade = null;
	
	public UpgradeDecorator(IUpgrade upgrade) {
		super(upgrade);
		// TODO Auto-generated constructor stub
		this.upgrade = upgrade;
	}

	@Override
	public Integer getLevel() {
		// TODO Auto-generated method stub
		return this.upgrade.getLevel();
	}

	@Override
	public void setLevel(Integer level) {
		// TODO Auto-generated method stub
		this.upgrade.setLevel(level);
	}

	@Override
	public Integer getStandard() {
		// TODO Auto-generated method stub
		return this.upgrade.getStandard();
	}
	
	@Override
	public void setStandard(Integer consume) {
		// TODO Auto-generated method stub
		this.upgrade.setStandard(consume);
	}

	@Override
	public void updateStandard() {
		// TODO Auto-generated method stub
		this.upgrade.updateStandard();
	}

}
