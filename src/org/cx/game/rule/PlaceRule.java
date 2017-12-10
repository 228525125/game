package org.cx.game.rule;

import java.util.List;

import org.cx.game.card.LifeCard;
import org.cx.game.widget.IGround;
import org.cx.game.widget.IPlace;
import org.cx.game.widget.LandformEffect;

public class PlaceRule extends Rule implements IRule {
	
	private Integer position = null;        //从哪个位置移动过来
	
	@Override
	public String getIntercepterMethod() {
		// TODO Auto-generated method stub
		return "in";
	}
	
	@Override
	public void before(Object[] args) {
		// TODO Auto-generated method stub
		LifeCard life = (LifeCard) args[0];
		
		List<Integer> path = life.getMove().getMovePath();
		this.position = path.isEmpty() ? null : path.get(path.size()-1);
	}
	
	public void after(Object[] args) {
		IPlace place = getOwner();
		
		/*
		 * 生成地形优势
		 */
		LifeCard life = (LifeCard) args[0];
		Integer profession = life.queryTagForCategory(LifeCard.Profession).get(0);
		life.getAttack().setLandformAtk(life.getAtk()*LandformEffect.getAttackAdvantage(profession, place.getLandform())/100);
		life.getAttacked().setLandformDef(life.getDef()*LandformEffect.getDefendAdvantage(profession, place.getLandform())/100);
		
		/*
		 * 生成朝向信息
		 */
		if(null!=this.position){       //call时 position=null
			IGround ground = life.getPlayer().getGround();
			Integer direction = ground.getDirection(this.position, place.getPosition());
			life.getMove().changeDirection(direction);
		}
		
		/*
		 * 添加路径
		 */
		life.getMove().getMovePath().add(place.getPosition());
	};

	@Override
	public Class getInterceptable() {
		// TODO Auto-generated method stub
		return IPlace.class;
	}
	
	@Override
	public IPlace getOwner() {
		// TODO Auto-generated method stub
		return (IPlace) super.getOwner();
	}
	

}
