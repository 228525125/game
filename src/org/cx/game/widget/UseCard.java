package org.cx.game.widget;

import java.util.HashMap;
import java.util.Map;

import org.cx.game.card.ICard;
import org.cx.game.observer.NotifyInfo;
import org.cx.game.out.JsonOut;

/**
 * 卡片显示队列
 * @author chenxian
 *
 */
public class UseCard extends Container implements IUseCard {
	
	public UseCard() {
		// TODO Auto-generated constructor stub
		addObserver(new JsonOut());
	}
	
	public void add(ICard card) {
		// TODO Auto-generated method stub
		add(0, card);
	}
	
	@Override
	public void add(Integer position, ICard card) {
		// TODO Auto-generated method stub
		super.setAction(NotifyInfo.Container_UseCard_Add);
		super.add(position, card);
	}
	
	@Override
	public void remove(ICard card) {
		// TODO Auto-generated method stub
		super.setAction(NotifyInfo.Container_UseCard_Remove);
		super.remove(card);
	}
	
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return Container.UseCard;
	}
	
}
