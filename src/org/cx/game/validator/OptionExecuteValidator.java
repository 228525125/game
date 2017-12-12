package org.cx.game.validator;

import org.cx.game.command.CommandBuffer;
import org.cx.game.tools.I18n;
import org.cx.game.widget.building.IOption;

public class OptionExecuteValidator extends SelectOptionValidator {

	public OptionExecuteValidator(CommandBuffer buffer) {
		super(buffer);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public Boolean validate() {
		// TODO Auto-generated method stub
		Boolean ret = super.validate();
		
		if(ret){
			IOption option = getOption();
			if(Integer.valueOf(0).equals(option.getExecuteRemainBout())){
				ret = true;
			}else{
				addMessage(I18n.getMessage(OptionExecuteValidator.class.getName()));
				ret = false;
			}
		}
		
		return ret;
	}

}
