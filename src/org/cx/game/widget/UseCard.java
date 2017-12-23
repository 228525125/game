package org.cx.game.widget;

import org.cx.game.card.ICard;
import org.cx.game.core.IPlayer;
import org.cx.game.observer.NotifyInfo;
import org.cx.game.out.JsonOut;

/**
 * 卡片显示队列
 * @author chenxian
 *
 */
public class UseCard extends Container implements IUseCard {
	
	private IPlayer player = null;
	
	public UseCard(IPlayer player) {
		// TODO Auto-generated constructor stub
		this.player = player;
	}
	
	@Override
	public void add(Integer position, ICard card) {
		// TODO Auto-generated method stub
		super.setAction(NotifyInfo.Container_UseCard_Add);
		super.add(position, card);
	}
	
	@Override
	public Boolean remove(ICard card) {
		// TODO Auto-generated method stub
		super.setAction(NotifyInfo.Container_UseCard_Remove);
		return super.remove(card);
	}
	
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return Container.UseCard;
	}
	
	public IPlayer getPlayer() {
		return player;
	}

	public void setPlayer(IPlayer player) {
		this.player = player;
	}
	
}
