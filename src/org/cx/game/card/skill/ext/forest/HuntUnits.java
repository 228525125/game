package org.cx.game.card.skill.ext.forest;

import java.util.ArrayList;
import java.util.List;

import org.cx.game.card.LifeCard;
import org.cx.game.card.skill.IBuff;
import org.cx.game.card.skill.PassiveSkill;
import org.cx.game.intercepter.Intercepter;
import org.cx.game.widget.IGround;

/**
 * 围猎
 * @author chenxian
 *
 */
public abstract class HuntUnits extends PassiveSkill {

	private Integer range = 0;
	
	public HuntUnits(Integer style, Integer range) {
		super(style);
		// TODO Auto-generated constructor stub
		this.range = range;
	}
	
	@Override
	public void setOwner(LifeCard life) {
		// TODO Auto-generated method stub
		super.setOwner(life);
	}
	
	@Override
	public Integer getRange() {
		// TODO Auto-generated method stub
		return range;
	}

	public Integer getNumber() {
		Integer number = 0;
		
		IGround ground = getOwner().getPlayer().getGround();
		List<Integer> list = ground.easyAreaForDistance(getOwner().getContainerPosition(), getRange(), IGround.Contain);
		for(Integer position : list){
			LifeCard life = ground.getCard(position);
			if(null!=life && life.getSkillList().contains(this)){
				number++;
			}
		}
		return number;
	}

}