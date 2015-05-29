package org.cx.game.card.ext;

import org.cx.game.card.LifeCard;
import org.cx.game.card.MagicCard;
import org.cx.game.card.skill.IBuff;
import org.cx.game.card.skill.ext.CureTiredBuff;
import org.cx.game.exception.RuleValidatorException;

/**
 * 治疗
 * @author chenxian
 *
 */
public class Cure extends MagicCard {

	private Integer cureScale;
	private Integer tireBout;
	
	public Cure(Integer id, Integer consume, Integer style, Integer func, Integer cureScale, Integer tireBout) {
		super(id, consume, style, func);
		// TODO Auto-generated constructor stub
		this.cureScale = cureScale;
		this.tireBout = tireBout;
		
		setParameterTypeValidator(new Class[]{LifeCard.class});
	}

	@Override
	public void affect(Object... objects) throws RuleValidatorException {
		// TODO Auto-generated method stub
		
		LifeCard life = (LifeCard) objects[0];
		
		Integer cureValue = life.getHp()*cureScale/100;  //保持下限
		life.getDeath().magicToHp(cureValue);
		new CureTiredBuff(tireBout,getStyle(),IBuff.Type_Benefit, getFunc(),life).effect();
	}
	
	@Override
	public void apply(Object... objects) throws RuleValidatorException {
		// TODO Auto-generated method stub
		super.apply(objects);
		
		LifeCard life = (LifeCard) objects[0];
		life.affected(this);
	}
	
}
