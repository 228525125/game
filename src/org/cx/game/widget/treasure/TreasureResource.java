package org.cx.game.widget.treasure;

import java.util.HashMap;
import java.util.Map;

import org.cx.game.action.IPicked;
import org.cx.game.action.Picked;
import org.cx.game.action.PickedDecorator;
import org.cx.game.card.LifeCard;
import org.cx.game.core.IPlayer;
import org.cx.game.exception.RuleValidatorException;
import org.cx.game.widget.IGround;
import org.cx.game.widget.IPlace;

public class TreasureResource extends Treasure implements ITreasure {

	private Map<String, Integer> resource = new HashMap<String, Integer>();
	
	public TreasureResource(Integer gold, Integer wood, Integer stone, Integer ore) {
		// TODO Auto-generated constructor stub
		this.resource.put(IPlayer.Gold, gold);
		this.resource.put(IPlayer.Wood, wood);
		this.resource.put(IPlayer.Stone, stone);
		this.resource.put(IPlayer.Ore, ore);
	}
	
	public Map<String, Integer> getResource() {
		return resource;
	}
	
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		String name = super.getName();
		name += "("+getResource().get(IPlayer.Gold)+"/"+getResource().get(IPlayer.Wood)+"/"+getResource().get(IPlayer.Stone)+"/"+getResource().get(IPlayer.Ore)+")";
		return name;
	}
	
	private IPicked picked = null;
	
	@Override
	public IPicked getPicked() {
		// TODO Auto-generated method stub
		if(null==this.picked){
			IPicked picked = new TreasureResourcePicked();
			picked.setOwner(this);
			this.picked = new PickedDecorator(picked);
		}
		return picked;
	}

	public class TreasureResourcePicked extends Picked implements IPicked {

		@Override
		public void action(Object... objects) throws RuleValidatorException {
			// TODO Auto-generated method stub
			super.action(objects);
			
			LifeCard life = (LifeCard) objects[0];
			life.getPlayer().addToResource(getResource());
			
			IGround ground = life.getPlayer().getGround();
			IPlace place = ground.getPlace(getOwner().getPosition());
			place.setTreasure(null);
		}
	}
}
