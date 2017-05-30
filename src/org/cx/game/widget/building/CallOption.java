package org.cx.game.widget.building;

import java.util.ArrayList;
import java.util.List;

import org.cx.game.card.CardFactory;
import org.cx.game.card.LifeCard;
import org.cx.game.exception.RuleValidatorException;
import org.cx.game.exception.ValidatorException;
import org.cx.game.tools.I18n;
import org.cx.game.validator.SelectPlaceDisableValidator;
import org.cx.game.widget.IGround;
import org.cx.game.widget.IPlace;

public class CallOption extends Option implements IOption {

	private Integer cardId = 0;
	private String name = null;
	
	public CallOption(Integer cardId) {
		// TODO Auto-generated constructor stub
		this.cardId = cardId;
		
		setParameterTypeValidator(new Class[]{IPlace.class}, new String[]{"disable"}, new Object[]{false});
	}
	
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		if(null==name){
			name = super.getName();
			name += I18n.getMessage(LifeCard.class, this.cardId, "name");
		}
		return name;
	}
	
	@Override
	public List<Integer> getExecuteRange(IGround ground) {
		// TODO Auto-generated method stub
		List<Integer> positionList = new ArrayList<Integer>();
		positionList = ground.areaForDistance(getOwner().getPosition(), 1, IGround.Contain);
		return positionList;
	}
	
	@Override
	public void execute(Object...objects) throws RuleValidatorException {
		// TODO Auto-generated method stub
		super.execute();
		
		IPlace place = (IPlace) parameter;
		LifeCard life = (LifeCard) CardFactory.getInstance(cardId);
		life.call(place);
	}
}
