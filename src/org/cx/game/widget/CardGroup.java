package org.cx.game.widget;

import java.util.Collections;
import java.util.List;

import org.cx.game.card.ICard;
import org.cx.game.observer.NotifyInfo;
import org.cx.game.out.JsonOut;
import org.cx.game.tools.Debug;

public class CardGroup extends Container implements ICardGroup
{
	/**
	 * 
	 * @param list classname的列表
	 */
	public CardGroup(List<ICard> list) {
		// TODO 自动生成构造函数存根
		super.cardList = shuffle(list);
		addObserver(new JsonOut());
	}
	
	private List<ICard> shuffle(List<ICard> list){
		//洗牌
		for(ICard card : list)
			card.setContainer(this);
		
		if(Debug.isShuffle)
			Collections.shuffle(list);
		return list;
	}
	
	@Override
	public void add(Integer position, ICard card) {
		// TODO Auto-generated method stub
		setAction(NotifyInfo.Container_CardGroup_Add);
		super.add(position, card);
	}
	
	@Override
	public Boolean remove(ICard card) {
		// TODO Auto-generated method stub
		setAction(NotifyInfo.Container_CardGroup_Remove);
		return super.remove(card);
	}
	
	@Override
	public ICard out() {
		// TODO Auto-generated method stub
		ICard card = getCard(0);
		if(null!=card)
			remove(card);
		return card;
	}
	
	@Override
	public ICard out(Integer position) {
		// TODO Auto-generated method stub
		ICard card = getCard(position);
		if(null!=card)
			remove(card);
		return card;
	}
	
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return Container.CardGroup;
	}
}
