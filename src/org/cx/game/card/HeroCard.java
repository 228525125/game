package org.cx.game.card;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.cx.game.action.UpgradeHero;
import org.cx.game.action.UpgradeHeroDecorator;
import org.cx.game.action.IUpgradeHero;
import org.cx.game.action.IUpgradeLife;
import org.cx.game.action.IUpgrade;
import org.cx.game.action.UpgradeLife;
import org.cx.game.action.UpgradeLifeDecorator;
import org.cx.game.action.UpgradeDecorator;
import org.cx.game.card.buff.IBuff;
import org.cx.game.card.skill.ISkill;

public class HeroCard extends LifeCard {

	public HeroCard(Integer id) {
		super(id);
		// TODO Auto-generated constructor stub
		setHero(true);
	}
	
	@Override
	public void initState() {
		// TODO Auto-generated method stub
		clearBuff();            //首先执行是因为，Buff.invalid会影响死者属性
		
		getActivate().setActivation(false);
		
		getDeath().setHp(getHp());
		
		setHide(false);
		
		List<IBuff> buffs = new ArrayList<IBuff>();     //与自己相关的buff，不是自己发起的buff，例如AttackLockBuff
		buffs.addAll(super.nexusBuffList);
		for(IBuff buff : buffs){
			buff.invalid();
		}
	}
	
	/**
	 * 技能点
	 */
	private Integer skillCount = 1;
	
	public Integer getSkillCount() {
		return skillCount;
	}

	public void setSkillCount(Integer skillCount) {
		this.skillCount = skillCount;
	}
	
	public IUpgrade getUpgrade() {
		if(null==upgrade){
			IUpgradeHero upgrade = new UpgradeHero();
			upgrade.setLevel(getLevel());
			upgrade.setSkillCount(skillCount);
			upgrade.setOwner(this);
			this.upgrade = new UpgradeHeroDecorator(upgrade);
		}
		return this.upgrade;
	}
	
	public void setUpgrade(IUpgrade upgrade) {
		upgrade.setLevel(getLevel());
		upgrade.setOwner(this);
		this.upgrade = new UpgradeDecorator(upgrade);
	}
	
	@Override
	public void setAtk(Integer atk) {
		// TODO Auto-generated method stub
		super.setAtk(atk);
	}
	
	@Override
	public void setDef(Integer def) {
		// TODO Auto-generated method stub
		super.setDef(def);
	}
	
	@Override
	public void setHp(Integer hp) {
		// TODO Auto-generated method stub
		super.setHp(hp);
	}
	
	@Override
	public void setConsume(Map<String,Integer> consume) {
		// TODO Auto-generated method stub
		super.setConsume(consume);
	}
	
	@Override
	public void setAttackRange(Integer attackRange) {
		// TODO Auto-generated method stub
		super.setAttackRange(attackRange);
	}
	
	@Override
	public void setAttackMode(Integer attackMode) {
		// TODO Auto-generated method stub
		super.setAttackMode(attackMode);
	}
	
	@Override
	public void setSpeed(Integer speed) {
		// TODO Auto-generated method stub
		super.setSpeed(speed);
	}
	
	@Override
	public void setEnergy(Integer energy) {
		// TODO Auto-generated method stub
		super.setEnergy(energy);
	}
	
	@Override
	public void setMoveType(Integer moveType) {
		// TODO Auto-generated method stub
		super.setMoveType(moveType);
	}
	
	@Override
	public void setStar(Integer star) {
		// TODO Auto-generated method stub
		super.setStar(star);
	}
	
	@Override
	public void setLockChance(Integer lockChance) {
		// TODO Auto-generated method stub
		super.setLockChance(lockChance);
	}
	
	@Override
	public void setFleeChance(Integer fleeChance) {
		// TODO Auto-generated method stub
		super.setFleeChance(fleeChance);
	}
	
	@Override
	public void setSkillList(List<ISkill> skillList) {
		// TODO Auto-generated method stub
		super.setSkillList(skillList);
	}
	
	public void setLevel(Integer level){
		super.setLevel(level);
	}

}
