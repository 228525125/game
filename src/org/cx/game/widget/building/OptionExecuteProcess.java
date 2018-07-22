package org.cx.game.widget.building;

import org.cx.game.action.ActionProxyHelper;
import org.cx.game.action.IAction;
import org.cx.game.core.AbstractPlayer;
import org.cx.game.exception.RuleValidatorException;
import org.cx.game.intercepter.AbstractIntercepter;
import org.cx.game.widget.AbstractOption;

public class OptionExecuteProcess extends AbstractProcess {

	private Object[] parameter = null; 
	
	public OptionExecuteProcess(Integer waitBout, AbstractPlayer player, AbstractOption option) {
		super(waitBout, player, option);
		// TODO Auto-generated constructor stub
		
		recordIntercepter(getOwner().getExecute(), new AbstractIntercepter() {
			
			@Override
			public void before(Object[] args) {
				// TODO Auto-generated method stub
				OptionExecuteProcess.this.parameter = (Object[]) args[0];
			}
			
			@Override
			public Boolean isInvoke() {
				// TODO Auto-generated method stub
				return Integer.valueOf(0).equals(getRemainBout());
			}
		});
	}
	
	@Override
	public AbstractOption getOwner() {
		// TODO Auto-generated method stub
		return (AbstractOption) super.getOwner();
	}

	@Override
	public void finish(Object[] args) {
		// TODO Auto-generated method stub
		if(Integer.valueOf(0).equals(getRemainBout())){
			invalid();

			IAction action = new ActionProxyHelper(getOwner().getExecute());
			action.action(parameter);
		}
	}

}
