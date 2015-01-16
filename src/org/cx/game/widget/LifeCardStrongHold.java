package org.cx.game.widget;

import java.util.ArrayList;
import java.util.List;

import org.cx.game.card.CardFactory;
import org.cx.game.card.ICard;
import org.cx.game.card.LifeCard;
import org.cx.game.core.IPlayer;
import org.cx.game.exception.RuleValidatorException;

public class LifeCardStrongHold extends StrongHold {

	private List<LifeCard> lifeList = new ArrayList<LifeCard>();
	private List<Integer> cardIDList = new ArrayList<Integer>();
	private List<Integer> existCardIDList = new ArrayList<Integer>();
	
	public LifeCardStrongHold(Integer position, Integer range, Integer bout) {
		super(position, range, bout);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void refurbish() {
		// TODO Auto-generated method stub
		if(isRefurbish()){
			List<Integer> list = new ArrayList<Integer>();
			list.addAll(cardIDList);
			list.removeAll(existCardIDList);
			for(ICard card : CardFactory.getInstances(list)){
				LifeCard life = (LifeCard) card;
				life.setPlayer(getPlayer());
				life.setPlayId(getPlayer().getContext().newCardPlayId());
				IPlace place = getUsablePlace();
				if(null!=place){
					try {
						life.renew(place);
						lifeList.add(life);
					} catch (RuleValidatorException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
	}

	public List<Integer> getCardIDList() {
		return cardIDList;
	}

	public void setCardIDList(List<Integer> cardIDList) {
		this.cardIDList = cardIDList;
	}
	
	public List<LifeCard> getLifeList() {
		return lifeList;
	}

	@Override
	public void setPlayer(IPlayer player) {
		// TODO Auto-generated method stub
		super.setPlayer(player);
	}

}