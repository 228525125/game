package org.cx.game.widget.building;

import java.util.ArrayList;
import java.util.List;

import org.cx.game.action.Execute;
import org.cx.game.action.IExecute;
import org.cx.game.card.CardFactory;
import org.cx.game.card.LifeCard;
import org.cx.game.exception.RuleValidatorException;
import org.cx.game.tools.I18n;
import org.cx.game.validator.CallConsumeValidator;
import org.cx.game.validator.CallNopValidator;
import org.cx.game.validator.CallRangeValidator;
import org.cx.game.validator.CallUnitEqualValidator;
import org.cx.game.validator.RationLimitValidator;
import org.cx.game.widget.IGround;
import org.cx.game.widget.Place;
import org.cx.game.widget.building.OptionBuild.OptionBuildExecute;

public class OptionCall extends Option implements IOption {

	private Integer cardID = 0;
	private String name = null;
	
	public OptionCall(Integer cardId) {
		// TODO Auto-generated constructor stub
		this.cardID = cardId;
		
		//setParameterTypeValidator(new Class[]{IPlace.class}, new String[]{"empty"}, new Object[]{true});
	}
	
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		if(null==name){
			name = super.getName();
			name += I18n.getMessage(LifeCard.class, this.cardID, "name");
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
	
	private IExecute execute = null;

	public IExecute getExecute() {
		if(null==this.execute){
			IExecute execute = new OptionCallExecute(this.cardID);
			execute.setOwner(this);
			this.execute = execute;
		}
		return this.execute;
	}
	
	@Override
	public void execute(Object...objects) throws RuleValidatorException {
		// TODO Auto-generated method stub
		Place place = (Place) objects[0];
		LifeCard life = (LifeCard) CardFactory.getInstance(cardID, getOwner().getPlayer());
		
		if(null!=place.getLife())            //如果是补充兵源，就判断招募的兵源是否一致
			getExecute().addValidator(new CallUnitEqualValidator(place.getLife(), life));
		
		getExecute().addValidator(new CallConsumeValidator(life, getNumber()));
		getExecute().addValidator(new CallRangeValidator(getOwner(), place));
		getExecute().addValidator(new RationLimitValidator(life, getNumber()));
		//验证单个队伍人口上限
		
		getExecute().addValidator(new CallNopValidator(life, getNumber(), getOwner()));
		
		super.execute(objects);		
	}
	
	@Override
	public void setSpacingWait(Integer spacing) {
		// TODO Auto-generated method stub
		super.setSpacingWait(spacing);
	}
	
	@Override
	public void setNumber(Integer number) {
		// TODO Auto-generated method stub
		super.setNumber(number);
	}
	
	@Override
	public Boolean getAllow() {
		// TODO Auto-generated method stub
		return super.getAllow() && IBuilding.Building_Status_Complete.equals(getOwner().getStatus());
	}
	
	public class OptionCallExecute extends Execute implements IExecute {
		
		private Integer cardID = null;

		public OptionCallExecute(Integer cardID) {
			// TODO Auto-generated constructor stub
			this.cardID = cardID;
		}
		
		@Override
		public void action(Object... objects) throws RuleValidatorException {
			// TODO Auto-generated method stub
			super.action(objects);
			
			Place place = (Place) objects[0];
			
			LifeCard life = (LifeCard) CardFactory.getInstance(cardID, getOwner().getOwner().getPlayer());
			life.call(place, getNumber());
		}
	}
}
