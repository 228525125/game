package org.cx.game.core;

import java.util.List;

import org.cx.game.card.LifeCard;
import org.cx.game.command.CommandBuffer;
import org.cx.game.intercepter.IInterceptable;
import org.cx.game.widget.IGround;
import org.cx.game.widget.IUseCard;

/**
 * 游戏玩家
 * @author chenxian
 *
 */
public interface IPlayer extends IInterceptable
{		
	public Integer getId();
	
	public String getName();
	
	public IContext getContext();
	
	public void setContext(IContext context);
	
	public IGround getGround();
	
	public void setGround(IGround ground);
	
	public IUseCard getUseCard();
	
	public Object getObject(String type);
	
	public Integer getResource();
	
	/**
	 * 隐式的改变资源
	 * @param power
	 */
	public void setResource(Integer res);
	
	/**
	 * 显示的改变资源
	 * @param resource
	 */
	public void addToResource(Integer res);
	
	public CommandBuffer getCommandBuffer();
	
	/**
	 * 英雄
	 * @return
	 */
	public LifeCard getHero();
	
	public void setHero(LifeCard hero);
	
	/**
	 * 英雄编号
	 * @return
	 */
	public Integer getHeroCardID();
	
	public void setHeroCardID(Integer cardID);
	
	/**
	 * 英雄入口，必须是玩家主城
	 * @return
	 */
	public Integer getHeroEntry();
	
	public void setHeroEntry(Integer position);
	
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
	 * 获取所有随从
	 * @return
	 */
	public List<LifeCard> getAttendantList();
	
	/**
	 * 获取激活状态的随从
	 * @param activate
	 * @return
	 */
	public List<LifeCard> getAttendantList(Boolean activate);
	
	/**
	 * 人口限制
	 * @return
	 */
	public Integer getRationLimit();
	
	public void setRationLimit(Integer ration);

	/**
	 * 人口总数
	 * @return
	 */
	public Integer getRation();

	public void addToRation(Integer ration);
	
	/**
	 * 游戏分为公共回合和玩家回合，公共回合 = 玩家数 * 玩家回合
	 */
	public Integer getBout();
	
	public void addBout();

}
