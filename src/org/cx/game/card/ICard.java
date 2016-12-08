package org.cx.game.card;

import org.cx.game.core.IPlayer;
import org.cx.game.exception.RuleValidatorException;
import org.cx.game.intercepter.IInterceptable;
import org.cx.game.policy.IUseCardPolicy;
import org.cx.game.tag.ITag;
import org.cx.game.widget.IContainer;

public interface ICard extends IInterceptable, ITag
{
	/**
	 * 主键/唯一标识
	 * @return
	 */
	public Integer getId();
	
	public String getName();
	
	public final static Integer Type_Life = 131;//生物卡
	
	public final static Integer Type_Magic = 132;//魔法卡
	
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
	 * 即直接从手牌丢弃一张卡片
	 *
	 */
	public void chuck() throws RuleValidatorException;
	
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
