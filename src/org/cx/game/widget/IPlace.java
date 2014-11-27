package org.cx.game.widget;

import java.util.List;

import org.cx.game.card.LifeCard;
import org.cx.game.card.TrickCard;
import org.cx.game.card.skill.ITrick;
import org.cx.game.intercepter.IInterceptable;
import org.cx.game.observer.Observable;

/**
 * 战场位置
 * @author chenxian
 *
 */
public interface IPlace extends IInterceptable, Observable{
	
	public IContainer getContainer();
	
	public ICamp getCamp();
	
	public void setCamp(ICamp camp);

	public void in(LifeCard life);
	
	public LifeCard out();
	
	public LifeCard getLife();	
	
	public ICemetery getCemetery();
	
	/**
	 * 添加尸体
	 * @param life
	 */
	public void addCorpse(LifeCard life);
	
	public void removeCorpse(LifeCard life);
	
	public ITrickList getTrickList();
	
	public void addTrick(ITrick trick);
	
	public void removeTrick(ITrick trick);
	
	/**
	 * 坐标系位置
	 * @return
	 */
	public Integer getPosition();
	
	/**
	 * 距离指定位置相隔步数
	 * @param position 指定位置
	 * @return
	 */
	public Integer getStep(IPlace place);
	
	/**
	 * 是否可用，有一些因为地形原因而导致不可用
	 * @return
	 */
	public Boolean isDisable();
	
	public void setDisable(Boolean disable);
}
