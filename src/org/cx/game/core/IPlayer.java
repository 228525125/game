package org.cx.game.core;

import java.util.List;
import java.util.Map;

import org.cx.game.action.IAction;
import org.cx.game.card.LifeCard;
import org.cx.game.command.CommandBuffer;
import org.cx.game.exception.RuleValidatorException;
import org.cx.game.intercepter.IInterceptable;
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
	
	public String getName();
	
	public IContext getContext();
	
	public void setContext(IContext context);
	
	public IUseCard getUseCard();
	
	//public Object getObject(String type);
	
	public Map<String, Integer> getResource();
	
	public void setResource(Map<String, Integer> res);
	
	/**
	 * 显示的改变资源
	 * @param res [资源类型][资源数]
	 */
	public void addToResource(Map<String, Integer> res);
	
	/**
	 * 显示的改变资源
	 * @param resType 资源类型
	 * @param res 资源数
	 */
	public void addToResource(String resType, Integer res);
	
	public final static String Gold = "701";                    //金币
	
	public final static String Wood = "702";                    //木材
	
	public final static String Stone = "703";                   //石材
	
	public final static String Ore = "704";                     //矿石
	
	public final static String EmpiricValue = "710";             //经验值
	
	public final static String SkillCount = "720";              //技能点
	
	public CommandBuffer getCommandBuffer();
	
	/**
	 * 英雄编号
	 * @return
	 */
	public List<Integer> getHeroCardIDList();
	
	public void addHeroCardID(Integer cardID);
	
	/**
	 * 玩家主城坐标
	 * @return
	 */
	public Integer getHomePosition();
	
	public void setHomePosition(Integer position);
	
	/**
	 * 玩家拥有的英雄
	 * @return
	 */
	public List<LifeCard> getHeroList();
	
	public void addHero(LifeCard hero);
	
	/**
	 * 获取所有随从
	 * @param status
	 * @return
	 */
	public List<LifeCard> getAttendantList(Integer status);
	
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
	
	public void addBout() throws RuleValidatorException;
	
	public IAction getAddBoutAction();
	
	/**
	 * 是否为非玩家控制角色
	 * @return
	 */
	public Boolean getComputer();
	
	public void setComputer(Boolean isComputer);
	
	/**
	 * AI自动操作
	 */
	public void automation();

}
