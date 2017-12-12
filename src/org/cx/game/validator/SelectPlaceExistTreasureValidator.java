package org.cx.game.validator;

import org.cx.game.tools.I18n;
import org.cx.game.widget.IPlace;

/**
 * 验证某个位置上是否有物品
 * @author chenxian
 *
 */
public class SelectPlaceExistTreasureValidator extends Validator {
	
	private IPlace place = null;

	public SelectPlaceExistTreasureValidator(IPlace place) {
		// TODO Auto-generated constructor stub
		this.place = place;
	}
	
	@Override
	public Boolean validate() {
		// TODO Auto-generated method stub
		Boolean ret = null!=place.getTreasure();
		
		if(!ret){
			addMessage(I18n.getMessage(this));
		}
		
		return ret;
	}
}
