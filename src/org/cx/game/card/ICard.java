package org.cx.game.card;

import org.cx.game.core.IPlayer;
import org.cx.game.exception.RuleValidatorException;
import org.cx.game.intercepter.IInterceptable;
import org.cx.game.policy.IUseCardPolicy;
import org.cx.game.widget.IContainer;

public interface ICard extends IInterceptable
{
	
	public String getName();
	
	public final static Integer Type_Life = 1;//生物卡
	
	public final static Integer Type_Magic = 2;//魔法卡
	
	public final static Integer Type_Trap = 3;//陷阱卡
	
	public Integer getType();
	
	/**
	 * 比赛环境中初始化状态，主要用于复原属性值
	 *
	 */
	public void initState();
	
	/**
	 * 
	 * @return 返回该卡片的持有者
	 */
	public IPlayer getPlayer();
	
	public void setPlayer(IPlayer player);
	
	/**
	 * 卡片从战场上送往墓地
	 *
	 */
	public void death() throws RuleValidatorException;
	
	/**
	 * 卡片被丢弃，即直接从手牌中放入墓地
	 *
	 */
	public void chuck() throws RuleValidatorException;
	
	/**
	 * 非比赛环境时，卡片的编号
	 * @return
	 */
	public Integer getId();
	
	/**
	 * 比赛环境时，使用的编号
	 * @return
	 */
	public Long getPlayId();
	
	public void setPlayId(Long playId);
	
	/**
	 * 星表示级别
	 * @return
	 */
	public Integer getStar();
	
	public void setStar(Integer star);
	
	/**
	 * 消耗
	 * @return
	 */
	public Integer getConsume();
	
	public void setConsume(Integer consume);
	
	/**
	 * 
	 * @return 放回该卡所在的容器
	 */
	public IContainer getContainer();

	/**
	 * 设置该卡的容器
	 * @param container 容器
	 */
	public void setContainer(IContainer container);
	
	/**
	 * 在容器中的位置
	 * @return
	 */
	public Integer getContainerPosition();
	
	/**
	 * 出牌策略
	 * @return
	 */
	public IUseCardPolicy getUseCardPolicy();
	
	public void setUseCardPolicy(IUseCardPolicy useCardPolicy);

}
