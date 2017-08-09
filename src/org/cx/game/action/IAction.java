package org.cx.game.action;

import org.cx.game.exception.RuleValidatorException;
import org.cx.game.intercepter.IInterceptable;
import org.cx.game.observer.Observable;
import org.cx.game.validator.IValidatable;

/**
 * 比赛规则中的所有动作都必须继承该接口
 * @author jiuhuan
 *
 */
public interface IAction extends IInterceptable, Observable, IValidatable, Cloneable{

	public Object getOwner();
	
	public void setOwner(Object card);
	
	public void setDecorator(ActionDecorator decorator);
	
	public ActionDecorator getDecorator();
	
	/**
	 * 由具体子类提供实现
	 * @param objects 由于objects是数组，因此在拦截器的写法如：LifeCard attacked = (LifeCard) ((Object[]) args[0])[0];
	 * @throws RuleValidatorException
	 */
	public void action(Object...objects) throws RuleValidatorException;
	
}
