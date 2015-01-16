package org.cx.game.core;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.cx.game.card.CardFactory;
import org.cx.game.card.ICard;
import org.cx.game.command.CommandBuffer;
import org.cx.game.observer.NotifyInfo;
import org.cx.game.observer.Observable;
import org.cx.game.out.JsonOut;
import org.cx.game.tools.Debug;
import org.cx.game.widget.CardGroup;
import org.cx.game.widget.CardGroupDecorator;
import org.cx.game.widget.ICardGroup;
import org.cx.game.widget.IGround;
import org.cx.game.widget.IUseCard;
import org.cx.game.widget.UseCard;
import org.cx.game.widget.UseCardDecorator;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public abstract class Player extends java.util.Observable implements IPlayer ,Observable{
	
	private Integer id = 0;    //这里的id不是玩家的唯一标号，它根据比赛中的位置来定的，仅针对一场比赛是唯一
	
	@Override
	public Integer getId() {
		return id;
	}

	@Override
	public void setId(Integer id) {
		this.id = id;
	}

	private ICardGroup cardGroup = null;
	
	@Override
	public ICardGroup getCardGroup() {
		// TODO Auto-generated method stub
		return cardGroup;
	}

	private IGround ground = null;
	
	@Override
	public IGround getGround() {
		// TODO Auto-generated method stub
		return ground;
	}
	
	public void setGround(IGround ground) {
		this.ground = ground;
	}
	
	private IUseCard useCard = null;
	
	@Override
	public IUseCard getUseCard() {
		// TODO Auto-generated method stub
		return useCard;
	}
	
	private Map<String,Object> data = new HashMap<String,Object>();
	
	@Override
	public Object getObject(String type) {
		// TODO Auto-generated method stub
		return data.get(type);
	}
	
	private CommandBuffer commandBuffer = null;
	
	@Override
	public CommandBuffer getCommandBuffer() {
		// TODO Auto-generated method stub
		return commandBuffer;
	}
	
	private IContext context;

	@Override
	public IContext getContext() {
		// TODO Auto-generated method stub
		return context;
	}
	
	@Override
	public void setContext(IContext context) {
		// TODO Auto-generated method stub
		addObserver(new JsonOut());
		
		this.context = context;
		
		this.cardGroup = new CardGroup(getDecks());
		this.cardGroup = new CardGroupDecorator(this.cardGroup);
		this.cardGroup.setPlayer(this);
		
		this.useCard = new UseCard();
		this.useCard = new UseCardDecorator(this.useCard);
		this.useCard.setPlayer(this);
		
		//command会用到
		data.put(CommandBuffer.CARDGROUP, this.cardGroup);
		data.put(CommandBuffer.GROUND, this.ground);
		data.put(CommandBuffer.OWN, this);
		data.put(CommandBuffer.USECARD, this.useCard);
		data.put(CommandBuffer.OTHER, context.getOtherPlayer(this));
		
		commandBuffer = new CommandBuffer(this);
	}
	
	private Integer power = 1;

	@Override
	public Integer getResource() {
		// TODO Auto-generated method stub
		if(Debug.isDebug)
			return Debug.power;
		return power;
	}
	
	@Override
	public void setResource(Integer power) {
		// TODO Auto-generated method stub
		this.power = power;
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("player", this);
		map.put("power", power);
		NotifyInfo info = new NotifyInfo(NotifyInfo.Player_Power,map);
		notifyObservers(info);
	}
	
	@Override
	public void notifyObservers(Object arg0) {
		// TODO Auto-generated method stub
		super.setChanged();
		super.notifyObservers(arg0);
	}
	
	@Override
	public boolean equals(Object arg0) {
		// TODO Auto-generated method stub
		if (arg0 instanceof IPlayer) {
			IPlayer player = (IPlayer) arg0;
			return player.getId().equals(getId());
		}
		return super.equals(arg0);
	}
}
