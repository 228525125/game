package org.cx.game.policy;

import java.util.Set;

import org.cx.game.card.CardFactory;
import org.cx.game.card.LifeCard;
import org.cx.game.core.ContextFactory;
import org.cx.game.core.IContext;
import org.cx.game.core.IPlayer;
import org.cx.game.exception.RuleValidatorException;
import org.cx.game.widget.GroundFactory;
import org.cx.game.widget.IGround;
import org.cx.game.widget.IPlace;

/**
 * 放置中立部队的NPC
 * @author chenxian
 *
 */
public class DisposeNPCPolicy extends Policy {

	private Boolean already = false;
	
	@Override
	public void calculate() {
		// TODO Auto-generated method stub
		IContext context = ContextFactory.getContext();
		setPri(IPolicy.PRI_Min);
		
		if(!already && 1==context.getBout()){
			setPri(IPolicy.PRI_High);
		}
	}
	
	@Override
	public void execute() {
		// TODO Auto-generated method stub
		this.already = true;
		
		/*
		 * NPC登场
		 */
		IGround ground = GroundFactory.getGround();
		IPlayer neutral = ground.getNeutral();
		Set<Integer> posSet = ground.getNpcMap().keySet();
		for(Integer pos : posSet){
			Integer npcId = ground.getNpcMap().get(pos);
			LifeCard npc = (LifeCard) CardFactory.getInstance(npcId, neutral);
			IPolicyGroup groupPolicy = ground.getPolicyMap().get(pos);
			if(null!=groupPolicy)
				npc.setGroupPolicy(groupPolicy);
			
			IPlace place = ground.getPlace(pos);
			try {
				npc.call(place);
			} catch (RuleValidatorException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
