package org.cx.game.corps;

import java.util.List;

import org.cx.game.action.ActionProxyHelper;
import org.cx.game.action.IAction;
import org.cx.game.core.AbstractPlayer;
import org.cx.game.magic.buff.AbstractBuff;
import org.cx.game.magic.skill.AbstractSkill;
import org.cx.game.tag.ITag;
import org.cx.game.tag.TagHelper;
import org.cx.game.tools.Util;
import org.cx.game.widget.AbstractGround;
import org.cx.game.widget.treasure.Resource;

/**
 * 所有生物卡的父类，
 * 注意：在子类的构造函数中要添加动作（如IAttack）的装饰器（AttackDecorator）
 * this.attack = new AttackDecorator(attack);
 * @author chenxian
 *
 */
public abstract class AbstractCorps implements ITag
{
	private Integer type = null;
	private Integer id = null;
	private Integer position = null;
	
	private AbstractPlayer player = null;
	private AbstractGround ground = null;
	
	public AbstractCorps(Integer type) {
		// TODO Auto-generated constructor stub
		this.type = type;
		this.id = Util.newCount();
	}
	
	public Integer getType() {
		return type;
	}
	
	/**
	 * 比赛中的ID，临时的
	 */
	public Integer getId() {
		// TODO Auto-generated method stub
		return id;
	}

	public AbstractPlayer getPlayer() {
		// TODO Auto-generated method stub
		return player;
	}
	
	public void setPlayer(AbstractPlayer player) {
		// TODO Auto-generated method stub
		this.player = player;
	}
	
	public Integer getPosition() {
		// TODO Auto-generated method stub
		return position;
	}
	
	public void setPosition(Integer position) {
		// TODO Auto-generated method stub
		this.position = position;
	}
	
	public AbstractGround getGround() {
		return ground;
	}

	public void setGround(AbstractGround ground) {
		this.ground = ground;
	}
	
	public abstract String getName();
	public abstract Integer getHp();
	public abstract Integer getRation();
	public abstract Integer getEnergy();	
	public abstract Integer getMoveType();
	public abstract Boolean getHide();
	public abstract Integer getAtk();
	public abstract Integer getDmg();
	public abstract Integer getAttackRange();
	public abstract Integer getAttackMode();
	public abstract Boolean getMobile();
	public abstract Integer getDef();
	public abstract Integer getSpeed();
	public abstract Integer getFleeChance();
	public abstract Integer getLockChance();
	public abstract Boolean isHero();
	public abstract Integer getStar();	
	public abstract Resource getConsume();
	public abstract Integer getLevel();	
	
	public abstract List<AbstractBuff> getNexusBuffList();
	/**
	 * 发起方状态
	 */
	public abstract void addNexusBuff(AbstractBuff buff);
	public abstract void removeNexusBuff(AbstractBuff buff);
	public abstract List<AbstractBuff> getNexusBuff(Class clazz);
	public abstract void clearNexusBuff();
	
	/**
	 * 自身Buff
	 */
	public abstract List<AbstractBuff> getBuffList();	
	public abstract IAction getAddBuffAction();
	public abstract IAction getRemoveBuffAction();
	public abstract List<AbstractBuff> getBuff(Class clazz);
	public abstract List<AbstractBuff> getBuff(String className);
	public abstract void clearBuff();
	public abstract Boolean containsBuff(Class clazz);
	
	public abstract List<AbstractSkill> getSkillList();
	public abstract void setSkillList(List<AbstractSkill> skillList);
	public abstract AbstractSkill getSkill(Integer type);
	public abstract IAction getAddSkillAction();
	public abstract Boolean containsSkill(Class clazz);

	/*public abstract IAction getActivate();
	public abstract IAction getAttack();
	public abstract IAction getAttacked();
	public abstract IAction getConjure();
	public abstract IAction getAffected();
	public abstract IAction getMove();
	public abstract IAction getCall();
	public abstract IAction getDeath();
	public abstract IAction getChuck();
	public abstract IAction getUpgrade();
	public abstract IAction getPick();
	public abstract IAction getMerge();
	public abstract IAction getLeave();*/
	
	public void addBuff(AbstractBuff buff) {
		IAction action = new ActionProxyHelper(getAddBuffAction());
		action.action(buff);
	}
	
	/**
	 * 该方法仅用于Buff.invalid
	 * @param buff
	 */
	public void removeBuff(AbstractBuff buff) {
		IAction action = new ActionProxyHelper(getRemoveBuffAction());
		action.action(buff);
	}
	
	public void addSkill(AbstractSkill skill) {
		IAction action = new ActionProxyHelper(getAddSkillAction());
		action.action(skill);
	}
	
	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return id.intValue();
	}
	
	public Boolean contains(Integer tag) {
		// TODO Auto-generated method stub
		List<Integer> objectList = TagHelper.queryForTag(tag);
		return objectList.contains(getType());
	}

	public List<Integer> queryTagForCategory(Integer category) {
		// TODO Auto-generated method stub
		List<Integer> list1 =  TagHelper.queryForCategory(category);
		List<Integer> list2 = TagHelper.queryForObject(getType());
		list2.retainAll(list1);
		return list2;
	}
	
	public List<Integer> queryTagForObject() {
		// TODO Auto-generated method stub
		return TagHelper.queryForObject(getType());
	}

	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		if (obj instanceof AbstractCorps) {
			AbstractCorps corps = (AbstractCorps) obj;
			return getId().equals(corps.getId());
		}else{
			return false;
		}
	}
	
	/**
	 * 初始化状态
	 
	public void initState() {
		// TODO Auto-generated method stub

		clearBuff();            //首先执行是因为，Buff.invalid会影响死者属性
		
		getActivate().setActivation(activation);
		
		getAttack().setMode(attackMode);
		
		getActivate().setSpeed(speed);
		getAttack().setLockChance(lockChance);
		
		getCall().setConsume(consume);
		
		getMove().setEnergy(energy);
		getMove().setType(moveType);
		getMove().setFlee(fleeChance);
		getMove().setHide(hide);
		
		getDeath().setHp(hp);
		
		IUpgradeLife up = (IUpgradeLife) getUpgrade();
		
		up.setLevel(level);
		
		setHide(false);
		
		List<AbstractBuff> buffs = new ArrayList<AbstractBuff>();     //与自己相关的buff，不是自己发起的buff，例如AttackLockBuff
		buffs.addAll(this.nexusBuffList);
		for(AbstractBuff buff : buffs){
			buff.invalid();
		}
	}*/
	
}
