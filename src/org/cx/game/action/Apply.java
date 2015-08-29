package org.cx.game.action;

import java.util.HashMap;
import java.util.Map;

import org.cx.game.card.ICard;
import org.cx.game.card.MagicCard;
import org.cx.game.card.skill.IActiveSkill;
import org.cx.game.core.IPlayer;
import org.cx.game.exception.RuleValidatorException;
import org.cx.game.observer.NotifyInfo;
import org.cx.game.out.JsonOut;
import org.cx.game.tools.Logger;
import org.cx.game.validator.ApplyConsumeValidator;

public class Apply extends Action implements IApply {

	private Integer consume = 1;
	
	public Apply() {
		// TODO Auto-generated constructor stub
		super();
		setParameterTypeValidator(new Class[]{Object.class});
	}
	
	@Override
	public MagicCard getOwner() {
		// TODO Auto-generated method stub
		return (MagicCard) super.getOwner();
	}
	
	@Override
	public ConjureDecorator getDecorator() {
		// TODO Auto-generated method stub
		return (ConjureDecorator) super.getDecorator();
	}
	
	@Override
	public void action(Object...objects) throws RuleValidatorException {
		// TODO Auto-generated method stub
		super.action(objects);
		
		addValidator(new ApplyConsumeValidator(getOwner()));
		
		doValidator();		
			
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("player", getOwner().getPlayer());
		map.put("container", getOwner().getContainer());
		map.put("card", getOwner());
		map.put("position", getOwner().getContainerPosition());
		NotifyInfo info = new NotifyInfo(NotifyInfo.Card_MagicCard_Apply,map);
		super.notifyObservers(info);
		
		IPlayer player = getOwner().getPlayer();
		player.setResource(player.getResource()-consume);
		getOwner().initState();
	}
	
	@Override
	public Integer getConsume() {
		// TODO Auto-generated method stub
		return consume;
	}
	
	public void setConsume(Integer consume) {
		this.consume = consume;
	}
}
