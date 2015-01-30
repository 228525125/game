package org.cx.game.policy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.cx.game.card.LifeCard;

public class LifeCardPolicy implements ILifeCardPolicy {

	private LifeCard owner = null;
	private IActionPolicy actionPolicy = null;

	@Override
	public LifeCard getOwner() {
		// TODO Auto-generated method stub
		return owner;
	}
	
	@Override
	public void setOwner(Object owner) {
		// TODO Auto-generated method stub
		this.owner = (LifeCard) owner;
	}

	@Override
	public IActionPolicy getActionPolicy() {
		// TODO Auto-generated method stub
		return this.actionPolicy;
	}

	@Override
	public Boolean hasNext() {
		// TODO Auto-generated method stub
		List<IActionPolicy> list = new ArrayList<IActionPolicy>();
		
		list.add(owner.getAttack().getPolicy());
		list.add(owner.getMove().getPolicy());
		list.add(owner.getConjure().getPolicy());

		Collections.sort(list, new ActionPolicyDescComparator());
		if(list.isEmpty()){
			actionPolicy = null;
			return false;
		}else{
			actionPolicy = list.get(0);
			return true;
		}
	}
	
	@Override
	public void execute() {
		// TODO Auto-generated method stub
		while(this.hasNext())
			this.getActionPolicy().execute();

	}
}
