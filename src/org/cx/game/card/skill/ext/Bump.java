package org.cx.game.card.skill.ext;

import java.util.ArrayList;
import java.util.List;

import org.cx.game.card.LifeCard;
import org.cx.game.card.skill.ActiveSkill;
import org.cx.game.card.skill.DizzyBuff;
import org.cx.game.exception.RuleValidatorException;
import org.cx.game.widget.IGround;
import org.cx.game.widget.IPlace;

/**
 * 冲撞
 * @author chenxian
 *
 */
public class Bump extends ActiveSkill {

	private Integer step = 0;
	private Integer atkScale = 0;
	private Integer atkValue = 0;
	private Integer dizzyBout = 0;
	
	/**
	 * 
	 * @param consume
	 * @param cooldown
	 * @param velocity
	 * @param style
	 * @param func
	 * @param step  前冲距离
	 * @param atkScale 撞击造成的伤害，按atk*atkScale/100
	 * @param dizzyBout 眩晕回合
	 * @param life
	 */
	public Bump(Integer consume, Integer cooldown, Integer velocity,
			Integer style, Integer func, Integer step, Integer atkScale, Integer dizzyBout) {
		super(consume, cooldown, velocity, style, func);
		// TODO Auto-generated constructor stub
		this.step = step;
		this.dizzyBout = dizzyBout;
		this.atkScale = atkScale;
		setParameterTypeValidator(new Class[]{LifeCard.class});
	}
	
	@Override
	public void setOwner(LifeCard life) {
		// TODO Auto-generated method stub
		super.setOwner(life);
		this.atkValue = life.getAttack().getAtk()*atkScale/100;
	}

	@Override
	public Integer getRange() {
		// TODO Auto-generated method stub
		return 1;
	}
	
	@Override
	public void useSkill(Object... objects) throws RuleValidatorException {
		// TODO Auto-generated method stub
		super.useSkill(objects);
		
		LifeCard life = (LifeCard) objects[0];      //life必须相对于owner前后左右四个方位
		life.affected(this);
	}
	
	@Override
	public void affect(Object... objects)  throws RuleValidatorException {
		// TODO Auto-generated method stub
		super.affect(objects);
		
		LifeCard life = (LifeCard) objects[0];
		life.getDeath().magicToHp(atkValue);
		
		IGround ground = getOwner().getPlayer().getGround();
		List<Integer> list = new ArrayList<Integer>();
		Integer stand = getOwner().getContainerPosition();
		Integer target = life.getContainerPosition();
		list.add(target);
		for(int i=0; i<step;i++){
			List<Integer> l = ground.line(stand, ground.queryDirection(stand, target), 2);
			if(2==l.size()){
				Integer behind = l.get(1);
				list.add(behind);
				stand = target;
				target = behind;
			}
		}
		
		for(int i=1; i<list.size(); i++){
			Integer p = list.get(i);			
			
			IPlace place = ground.getPlace(p);			
			if(place.isDisable()||null!=place.getLife()){      //碰到障碍物				
				life.getDeath().magicToHp(atkValue);
				new DizzyBuff(dizzyBout,life).effect();
				break;
			}else{
				getOwner().move(ground.getPlace(list.get(i-1)));
				life.move(ground.getPlace(p));
			}
		}
	}
}
