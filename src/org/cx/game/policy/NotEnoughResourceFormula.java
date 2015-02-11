package org.cx.game.policy;

import org.cx.game.card.ICard;

/**
 * 判断player是否有足够资源使用card
 * @author chenxian
 *
 */
public class NotEnoughResourceFormula implements IFormula {

	private IPriority priority = null;
	
	@Override
	public IPriority getPriority() {
		// TODO Auto-generated method stub
		return this.priority;
	}

	@Override
	public void setPriority(IPriority priority) {
		// TODO Auto-generated method stub
		this.priority = priority;
	}

	@Override
	public Integer execute() {
		// TODO Auto-generated method stub
		if (this.priority.getOwner() instanceof ICard) {
			ICard card = (ICard) this.priority.getOwner();
			if(card.getConsume()>card.getPlayer().getResource()){
				this.priority.setPri(0);
			}
		}
		return 0;
	}
}
