package org.cx.game.policy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.cx.game.core.IPlayer;

public class PlayerPolicy implements IPlayerPolicy {

	private IPlayer owner = null;
	private IUseCardPolicy useCardPolicy = null;
	
	@Override
	public IPlayer getOwner() {
		// TODO Auto-generated method stub
		return owner;
	}
	
	@Override
	public void setOwner(Object owner) {
		// TODO Auto-generated method stub
		this.owner = (IPlayer) owner;
	}
	
	@Override
	public IUseCardPolicy getUseCardPolicy() {
		// TODO Auto-generated method stub
		return useCardPolicy;
	}
	
	@Override
	public Boolean hasNext() {
		// TODO Auto-generated method stub
		List<IUseCardPolicy> list = new ArrayList<IUseCardPolicy>();
		for(int i=0;i<this.owner.getUseCard().getSize();i++)
			list.add(this.owner.getUseCard().getCard(i).getUseCardPolicy());

		Collections.sort(list, new UseCardPolicyDescComparator());
		if(list.isEmpty()){
			this.useCardPolicy = null;
			return false;
		}else{
			this.useCardPolicy = list.get(0);
			return true;
		}
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		while(this.hasNext())
			this.getUseCardPolicy().execute();
	}
}
