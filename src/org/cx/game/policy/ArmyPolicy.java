package org.cx.game.policy;

import java.util.List;

import org.cx.game.core.IPlayer;
import org.cx.game.corps.Corps;

/**
 * 行军策略-player
 * @author chenxian
 *
 */
public class ArmyPolicy extends Policy {
	
	private Corps corps = null;
	
	@Override
	public void calculate() {
		// TODO Auto-generated method stub
		super.calculate();
		
		IPlayer owner = (IPlayer) getOwner().getOwner();
		setPri(IPolicy.PRI_Min);
		
		List<Corps> list = owner.getAttendantList(true);
		
		if(!list.isEmpty()){
			this.corps = list.get(0);
			setPri(IPolicy.PRI_High);
		}
	}
	
	@Override
	public void execute() {
		// TODO Auto-generated method stub
		this.corps.automation();
	}

}
