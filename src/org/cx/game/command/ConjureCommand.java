package org.cx.game.command;

import org.cx.game.card.LifeCard;
import org.cx.game.card.skill.IActiveSkill;
import org.cx.game.card.skill.ISkill;
import org.cx.game.core.IPlayer;
import org.cx.game.exception.ValidatorException;
import org.cx.game.tools.Debug;
import org.cx.game.validator.ParameterTypeValidator;
import org.cx.game.validator.SelectContainerValidator;
import org.cx.game.validator.SelectLifeCardValidator;
import org.cx.game.validator.SelectSkillValidator;

/**
 * 使用技能
 * @author chenxian
 *
 */
public class ConjureCommand extends InteriorCommand {

	public ConjureCommand(IPlayer player) {
		super(player);
		// TODO Auto-generated constructor stub
		addValidator(new SelectContainerValidator(player.getGround(),buffer));
		addValidator(new SelectSkillValidator(buffer));
		addValidator(new ParameterTypeValidator(new Object[]{buffer.getSkill()}, new Class[]{IActiveSkill.class},null,null));
	}

	@Override
	public void execute() throws ValidatorException {
		// TODO Auto-generated method stub
		super.execute();
		
		LifeCard life = (LifeCard) buffer.getCard();		
		IActiveSkill skill = (IActiveSkill) buffer.getSkill();
		life.conjure(skill, new Object[]{parameter});
		
		if(!Debug.isDebug)
			player.getContext().done();         //结束本回合
	}
	
}
