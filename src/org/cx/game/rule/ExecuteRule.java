package org.cx.game.rule;

import java.util.Observable;

import org.cx.game.action.IAttacked;
import org.cx.game.action.IExecute;
import org.cx.game.observer.NotifyInfo;

public class ExecuteRule implements IRule {

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub		
		
		if (arg instanceof NotifyInfo) {
			NotifyInfo info = (NotifyInfo) arg;
			
			if(NotifyInfo.Building_Option_Execute.equals(info.getType())){
				IExecute execute = (IExecute) ((RuleGroup) o).getMessageSource();
				
				/*
				 * 执行选项间隔周期
				 */
				execute.getOwner().cooling();
			}
		}
	}
}
