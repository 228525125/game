package org.cx.game.widget.building;

import java.util.ArrayList;
import java.util.List;

import org.cx.game.action.IMove;
import org.cx.game.card.CardFactory;
import org.cx.game.card.LifeCard;
import org.cx.game.exception.RuleValidatorException;
import org.cx.game.exception.ValidatorException;
import org.cx.game.tools.I18n;
import org.cx.game.validator.CallConsumeValidator;
import org.cx.game.validator.CallRangeValidator;
import org.cx.game.validator.RationLimitValidator;
import org.cx.game.validator.SelectPlaceEmptyValidator;
import org.cx.game.widget.IGround;
import org.cx.game.widget.IPlace;

public class CallOption extends Option implements IOption {

	private Integer cardId = 0;
	private String name = null;
	
	public CallOption(Integer cardId) {
		// TODO Auto-generated constructor stub
		this.cardId = cardId;
		
		setParameterTypeValidator(new Class[]{IPlace.class}, new String[]{"empty"}, new Object[]{true});
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
		positionList.retainAll(ground.getEmptyList());
		return positionList;
	}
	
	@Override
	public void execute(Object...objects) throws RuleValidatorException {
		// TODO Auto-generated method stub
		super.execute(objects);
		
		IPlace place = (IPlace) objects[0];
		LifeCard life = (LifeCard) CardFactory.getInstance(cardId, getOwner().getPlayer());
		
		addValidator(new CallConsumeValidator(life));
		addValidator(new CallRangeValidator(getOwner(), place));
		addValidator(new RationLimitValidator(life));
		
		doValidator();
		if(hasError())
			throw new RuleValidatorException(getErrors().getMessage());
		
		life.call(place);
	}
}
