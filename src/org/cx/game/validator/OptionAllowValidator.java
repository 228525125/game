package org.cx.game.validator;

import org.cx.game.tools.I18n;
import org.cx.game.widget.building.IOption;

/**
 * 验证选项是否能被执行
 * @author chenxian
 *
 */
public class OptionAllowValidator extends Validator {
	
	private IOption option = null; 

	public OptionAllowValidator(IOption option) {
		// TODO Auto-generated constructor stub
		this.option = option;
	}
	
	@Override
	public Boolean validate() {
		// TODO Auto-generated method stub
		Boolean ret = this.option.getAllow();
		
		if(!ret)
			addMessage(I18n.getMessage(this));
		
		return ret;
	}
}
