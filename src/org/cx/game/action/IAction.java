package org.cx.game.action;

import org.cx.game.card.ICard;
import org.cx.game.exception.RuleValidatorException;
import org.cx.game.intercepter.IInterceptable;
import org.cx.game.observer.Observable;
import org.cx.game.validator.IValidatable;
import org.cx.game.validator.IValidator;

/**
 * 比赛规则中的所有动作都必须继承该接口
 * @author jiuhuan
 *
 */
public interface IAction extends IInterceptable, Observable, IValidatable {

	public ICard getOwner();
	
	public void setOwner(ICard card);
	
	public void action(Object...objects) throws RuleValidatorException;
}
