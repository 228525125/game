package org.cx.game.validator;

import org.cx.game.tools.I18n;
import org.cx.game.widget.IContainer;

/**
 * 验证指定的位置是否存在对象
 * @author chenxian
 *
 */
public class NullPointValidator extends Validator {

	private IContainer container = null;
	private Integer position = null;
	
	public NullPointValidator(IContainer container, Integer position) {
		// TODO Auto-generated constructor stub
		this.container = container;
		this.position = position;
	}
	
	@Override
	public Boolean validate() {
		// TODO Auto-generated method stub
		if(null!=container.getCard(position)){
			return true;
		}else{
			addMessage(I18n.getMessage(this));
			return false;
		}
	}
}
