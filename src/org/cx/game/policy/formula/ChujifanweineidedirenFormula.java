package org.cx.game.policy.formula;

import java.util.ArrayList;
import java.util.List;

import org.cx.game.card.LifeCard;
import org.cx.game.tools.I18n;
import org.cx.game.validator.Validator;
import org.cx.game.widget.GroundFactory;
import org.cx.game.widget.IGround;

/**
 * 出击范围内是否有敌人
 * @author chenxian
 *
 */
public class ChujifanweineidedirenFormula extends Validator implements IFormula {

	private LifeCard life = null;
	
	private List<LifeCard> enemyList = new ArrayList<LifeCard>();
	
	public ChujifanweineidedirenFormula(LifeCard life) {
		// TODO Auto-generated constructor stub
		this.life = life;
	}
	
	@Override
	public Boolean validate() {
		// TODO Auto-generated method stub
		Integer range = this.life.getMove().getEnergy() + this.life.getAttackRange();
		IGround ground = GroundFactory.getGround();
		List<Integer> list =ground.areaForDistance(this.life.getContainerPosition(), range, IGround.Contain);
		for(Integer p : list){
			LifeCard life = ground.getCard(p);
			if(null!=life && !this.life.getPlayer().equals(life.getPlayer()))
				this.enemyList.add(life);
		}
		
		Boolean ret = false;
		if(this.enemyList.isEmpty()){
			addMessage(I18n.getMessage(this));
		}else{
			ret = true;
		}
		
		return ret;
	}
	
	/**
	 * 
	 * @return 最近敌人附近的位置
	 */
	public Integer getPosition(){
		IGround ground = GroundFactory.getGround();
		Integer distance = this.life.getMove().getEnergy() + this.life.getAttackRange();
		LifeCard enemy = null;
		for(LifeCard life : this.enemyList){
			Integer d = ground.distance(this.life.getContainerPosition(), life.getContainerPosition());
			if(d<=distance){
				distance = d;
				enemy = life;
			}
		}
		List<Integer> l1 = ground.areaForDistance(enemy.getContainerPosition(), 1, IGround.Contain);
		List<Integer> l2 = ground.areaForDistance(this.life.getContainerPosition(), this.life.getMove().getEnergy(), IGround.Contain, this.life.getMove().getType());
		l1.retainAll(l2);
		return l1.get(0);
	}
}
