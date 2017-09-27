package org.cx.game.validator;

import org.cx.game.command.CommandBuffer;
import org.cx.game.tools.I18n;
import org.cx.game.widget.building.IOption;

/**
 * 验证选项间隔周期
 * @author chenxian
 *
 */
public class OptionSpacingValidate extends SelectOptionValidator {

	public OptionSpacingValidate(CommandBuffer buffer) {
		super(buffer);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public Boolean validate() {
		// TODO Auto-generated method stub
		Boolean ret = super.validate();
		
		if(ret){
			IOption option = getOption();
			if(Integer.valueOf(0).equals(option.getSpacingRemainBout())){
				ret = true;
			}else{
				addMessage(I18n.getMessage(this));
				ret = false;
			}
		}
		
		return ret;
	}

}
