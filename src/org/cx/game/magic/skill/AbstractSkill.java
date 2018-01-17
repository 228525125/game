package org.cx.game.magic.skill;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observable;

import org.cx.game.corps.AbstractCorps;
import org.cx.game.magic.skill.ISkill;
import org.cx.game.core.AbstractContext;
import org.cx.game.exception.RuleValidatorException;
import org.cx.game.observer.NotifyInfo;
import org.cx.game.out.ResponseFactory;
import org.cx.game.tag.TagHelper;
import org.cx.game.tools.I18n;

public abstract class AbstractSkill extends Observable implements ISkill {

	private Integer type;
	private String name;
	private String depiction = null;
	private AbstractCorps owner;
	private String action = null;
	
	protected final static String UseSkill = "_UseSkill";
	
	public AbstractSkill(Integer type) {
		// TODO Auto-generated constructor stub
		this.type = type;
		
		addObserver(ResponseFactory.getResponse());
		
		setAction("Skill");
	}
	
	public String getAction() {
		return action;
	}
	
	public void setAction(String action) {
		this.action = action;
	}

	@Override
	public void affect(Object... objects) {
		// TODO Auto-generated method stub
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("player", owner.getPlayer());
		map.put("container", owner.getGround());
		map.put("corps", owner);
		map.put("skill", this);
		map.put("position", owner.getPosition());
		NotifyInfo info = new NotifyInfo(getAction()+UseSkill,map);
		notifyObservers(info);
	}
	
	@Override
	public Integer getType() {
		// TODO Auto-generated method stub
		return type;
	}
	
	public String getName() {
		if(null==name)
			name = I18n.getMessage(this, "name");
		return name;
	}
	
	public String getDepiction() {
		// TODO Auto-generated method stub
		if(null==depiction)
			depiction = I18n.getMessage(this, "depiction");
		return depiction;
	}

	public AbstractCorps getOwner() {
		return owner;
	}
	
	@Override
	public void setOwner(AbstractCorps corps) {
		// TODO Auto-generated method stub
		this.owner = corps;		
	}

	@Override
	public void notifyObservers(Object arg) {
		// TODO Auto-generated method stub
		super.setChanged();
		super.notifyObservers(arg);
	}

	@Override
	public Boolean contains(Integer tag) {
		// TODO Auto-generated method stub
		List<Integer> objectList = TagHelper.queryForTag(tag);
		return objectList.contains(getType());
	}

	@Override
	public List<Integer> queryTagForCategory(Integer category) {
		// TODO Auto-generated method stub
		List<Integer> list1 =  TagHelper.queryForCategory(category);
		List<Integer> list2 = TagHelper.queryForObject(getType());
		list2.retainAll(list1);
		return list2;
	}

	@Override
	public List<Integer> queryTagForObject() {
		// TODO Auto-generated method stub
		return TagHelper.queryForObject(getType());
	}
	
	@Override
	public Boolean isTrigger(Object[] args) {
		// TODO Auto-generated method stub
		return true;
	}
	
	@Override
	public void upgrade() throws RuleValidatorException {
		// TODO Auto-generated method stub
		getUpgrade().execute();
	}

}
