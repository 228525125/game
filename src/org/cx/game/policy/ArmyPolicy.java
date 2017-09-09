package org.cx.game.policy;

import java.util.List;

import org.cx.game.card.LifeCard;
import org.cx.game.core.IPlayer;

/**
 * 行军策略-player
 * @author chenxian
 *
 */
public class ArmyPolicy extends Policy {
	
	private LifeCard life = null;
	
	@Override
	public void calculate() {
		// TODO Auto-generated method stub
		IPlayer owner = (IPlayer) getOwner().getOwner();
		setPri(IPolicy.PRI_Min);
		
		List<LifeCard> list = owner.getAttendantList(true);
		
		if(!list.isEmpty()){
			this.life = list.get(0);
			setPri(IPolicy.PRI_High);
		}
	}
	
	@Override
	public void execute() {
		// TODO Auto-generated method stub
		this.life.automation();
	}

}
