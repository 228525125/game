package org.cx.game.widget.building;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

import org.cx.game.card.HeroCard;
import org.cx.game.card.LifeCard;
import org.cx.game.exception.RuleValidatorException;
import org.cx.game.observer.NotifyInfo;
import org.cx.game.tools.I18n;
import org.cx.game.widget.IGround;
import org.cx.game.widget.IPlace;

/**
 * 英雄复活
 * @author chenxian
 *
 */
public class ReviveOption extends Option implements IOption {

	private String name = null;
	private LifeCard hero = null;

	public ReviveOption(LifeCard hero) {
		// TODO Auto-generated constructor stub
		this.hero = hero;
		this.hero.addObserver(new OptionObserver());
		
		setParameterTypeValidator(new Class[]{IPlace.class}, new String[]{"empty"}, new Object[]{true});
	}
	
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		if(null==name){
			name = super.getName();
			name += I18n.getMessage(HeroCard.class, hero.getId(), "name");
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
	public void execute(Object... objects) throws RuleValidatorException {
		// TODO Auto-generated method stub
		setSpacingWait(10000);              //英雄复活后，该选项被锁
		
		super.execute(objects);
		
		IPlace place = (IPlace) objects[0];
		
		this.hero.call(place);
	}
	
	class OptionObserver implements Observer {

		@Override
		public void update(Observable o, Object arg) {
			// TODO Auto-generated method stub
			
			if (arg instanceof NotifyInfo) {
				NotifyInfo info = (NotifyInfo) arg;
				
				if(NotifyInfo.Card_LifeCard_Action_Death.equals(info.getType())){
					Map bean = (Map) info.getInfo();
					LifeCard hero = (LifeCard) bean.get("card");
					
					setSpacingWait(hero.getUpgrade().getLevel());
					cooling();
				}
			}
			
		}
		
		
	}

}
