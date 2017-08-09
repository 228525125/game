package org.cx.game.policy.formula;

import java.util.ArrayList;
import java.util.List;

import org.cx.game.card.LifeCard;
import org.cx.game.tools.I18n;
import org.cx.game.validator.Validator;
import org.cx.game.widget.GroundFactory;
import org.cx.game.widget.IGround;

/**
 * 攻击范围内的敌人
 * @author chenxian
 *
 */
public class ShechengneidedirenFormula extends Validator implements IFormula {
	
	private LifeCard life = null;
	
	private List<LifeCard> enemyList = new ArrayList<LifeCard>();
	
	public ShechengneidedirenFormula(LifeCard life) {
		// TODO Auto-generated constructor stub
		this.life = life;
	}
	
	@Override
	public Boolean validate() {
		// TODO Auto-generated method stub
		Integer range = this.life.getAttackRange();
		IGround ground = GroundFactory.getInstance();
		List<Integer> list =ground.areaForDistance(this.life.getContainerPosition(), range, IGround.Contain);
		for(Integer p : list){
			LifeCard life = ground.getCard(p);
			if(null!=life)
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
	
	public LifeCard getNearEnemy(){
		IGround ground = GroundFactory.getInstance();
		Integer distance = this.life.getAttackRange();
		LifeCard enemy = null;
		for(LifeCard life : this.enemyList){
			Integer d = ground.distance(this.life.getContainerPosition(), life.getContainerPosition());
			if(d<=distance){
				distance = d;
				enemy = life;
			}
		}
		return enemy;
	}
	
}
