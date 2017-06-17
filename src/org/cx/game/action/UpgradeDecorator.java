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
	public Integer getConsume() {
		// TODO Auto-generated method stub
		return this.upgrade.getConsume();
	}
	
	@Override
	public void setConsume(Integer consume) {
		// TODO Auto-generated method stub
		this.upgrade.setConsume(consume);
	}

	@Override
	public Integer getWaitBout() {
		// TODO Auto-generated method stub
		return this.upgrade.getWaitBout();
	}
	
	@Override
	public void setWaitBout(Integer bout) {
		// TODO Auto-generated method stub
		this.upgrade.setWaitBout(bout);
	}

	@Override
	public Integer getProcess() {
		// TODO Auto-generated method stub
		return this.upgrade.getProcess();
	}

}
