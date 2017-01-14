package org.cx.game.core;

import java.util.List;

import org.cx.game.card.ICard;
import org.cx.game.card.LifeCard;
import org.cx.game.command.CommandBuffer;
import org.cx.game.policy.IPlayerPolicy;
import org.cx.game.widget.ICamp;
import org.cx.game.widget.ICardGroup;
import org.cx.game.widget.ICemetery;
import org.cx.game.widget.IGround;
import org.cx.game.widget.IUseCard;

/**
 * 游戏玩家
 * @author chenxian
 *
 */
public interface IPlayer
{		
	public Integer getId();
	
	public void setId(Integer id);
	
	public IContext getContext();
	
	public void setContext(IContext context);
	
	public ICardGroup getCardGroup();
	
	public IGround getGround();
	
	public void setGround(IGround ground);
	
	public IUseCard getUseCard();
	
	public Object getObject(String type);
	
	public Integer getResource();
	
	/**
	 * 隐式的改变资源
	 * @param power
	 */
	public void setResource(Integer power);
	
	/**
	 * 显示的改变资源
	 * @param resource
	 */
	public void addToResource(Integer power);
	
	public CommandBuffer getCommandBuffer();
	
	public List<ICard> decksList();
	
	public String getName();
	
	public IPlayerPolicy getPolicy();
	
	/**
	 * 英雄卡
	 * @return
	 */
	public LifeCard getHeroCard();
	
	public void setHeroCard(LifeCard hero);
	
	/**
	 * 玩家本次比赛召唤随从次数
	 * @return
	 */
	public Integer getCallCountOfPlay();
	
	/**
	 * call计数器增加time次
	 * @param time
	 */
	public void addCallCountOfPlay(Integer time);
	
	/**
	 * 玩家本回合召唤随从次数
	 * @return
	 */
	public Integer getCallCountOfBout();
	
	/**
	 * call计数器增加time次
	 * @param time
	 */
	public void addCallCountOfBout(Integer time);
	
	/**
	 * 重置call计数器
	 */
	public void resetCallCountOfBout();

}
