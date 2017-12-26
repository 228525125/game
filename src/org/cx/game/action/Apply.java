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
import org.cx.game.rule.ApplyRule;
import org.cx.game.rule.IRule;
import org.cx.game.tools.Logger;
import org.cx.game.validator.ApplyConsumeValidator;
import org.cx.game.widget.treasure.IResource;
import org.cx.game.widget.treasure.Resource;

public class Apply extends Action implements IApply {

	private IResource consume = new Resource();
	
	@Override
	public MagicCard getOwner() {
		// TODO Auto-generated method stub
		return (MagicCard) super.getOwner();
	}
	
	@Override
	public void action(Object...objects) throws RuleValidatorException {
		// TODO Auto-generated method stub
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("player", getOwner().getPlayer());
		map.put("container", getOwner().getContainer());
		map.put("card", getOwner());
		map.put("position", getOwner().getPosition());
		NotifyInfo info = new NotifyInfo(NotifyInfo.Card_MagicCard_Apply,map);
		super.notifyObservers(info);
	}
	
	@Override
	public IResource getConsume() {
		// TODO Auto-generated method stub
		return consume;
	}
	
	public void setConsume(IResource consume) {
		this.consume = consume;
	}
}
