package org.cx.game.validator;

import org.cx.game.card.skill.IActiveSkill;
import org.cx.game.command.CommandBuffer;
import org.cx.game.tools.I18n;

/**
 * 验证缓存的是否为ActiveSkill
 * @author chenxian
 *
 */
public class SelectActiveSkillValidator extends SelectSkillValidator {

	public SelectActiveSkillValidator(CommandBuffer buffer) {
		super(buffer);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public Boolean validate() {
		// TODO Auto-generated method stub
		Boolean ret =  super.validate();
		if(ret){
			if (getSkill() instanceof IActiveSkill) {
				ret = true;
			}else{
				addMessage(I18n.getMessage(this));
				ret = false;
			}
		}
		
		return ret;
	}

}
