package org.cx.game.validator;

import org.cx.game.tools.I18n;
import org.cx.game.widget.IGround;
import org.cx.game.widget.IPlace;

public class SelectPlaceDisableValidator extends Validator {

	private Boolean disable = false;
	private IPlace place = null;
	
	public SelectPlaceDisableValidator(IPlace place, Boolean disable) {
		// TODO Auto-generated constructor stub
		this.disable = disable;
		this.place = place;
	}
	
	@Override
	public Boolean validate() {
		// TODO Auto-generated method stub
		Boolean ret = super.validate();
		if(ret){
			if(place.getDisable().equals(disable))
				ret = true;
			else{
				addMessage(I18n.getMessage(this));
				ret = false;
			}
		}
		
		return ret;
	}
}
