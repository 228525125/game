package org.cx.game.widget;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.cx.game.card.ICard;
import org.cx.game.card.LifeCard;
import org.cx.game.core.IPlayer;
import org.cx.game.exception.PlayException;
import org.cx.game.intercepter.IInterceptable;
import org.cx.game.intercepter.IIntercepter;
import org.cx.game.observer.Observable;

/**
 * 容器
 * @author chenxian
 *
 */
public interface IContainer extends IInterceptable, Observable
{
	/**
	 * 加入容器
	 * 如果是Ground，card只能是life，为什么trick不经过该方法，因为1、ground保存了所有card；2、trick不是card类型
	 * @param position
	 * @param card
	 */
	public void add(Integer position, ICard card);
	
	/**
	 * 从容器中移出
	 * 如果是Ground，card只能是life
	 */
	public void remove(ICard card);
	
	/**
	 * 
	 * @return 容器当前元素的个数
 	 */
	public Integer getSize();
	
	/**
	 * 根据位置查找card
	 * @param card
	 * @return 
	 */
	public ICard getCard(Integer position);
	
	/**
	 * 根据卡片查找位置
	 * @param card
	 * @return
	 */
	public Integer getPosition(ICard card);
	
	/**
	 * 所属玩家
	 */
	public IPlayer getPlayer();
	
	public void setPlayer(IPlayer player);
	
	public List<ICard> list();
	
	public String getName();
	
	public Boolean contains(ICard card);
	
	/**
	 * 用于show
	 * @return 以map形式返回容器所有card
	 */
	public List toList();
	
	/**
	 * 保存一个该类的装饰类，当该类内部需要使用this的时候，只能使用装饰类的引用
	 * @param decorator 装饰
	 */
	public void setDecorator(ContainerDecorator decorator);
	
	public ContainerDecorator getDecorator();

}
