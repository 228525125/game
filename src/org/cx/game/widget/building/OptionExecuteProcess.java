package org.cx.game.widget.building;

import org.cx.game.action.ActionProxyHelper;
import org.cx.game.action.IAction;
import org.cx.game.exception.RuleValidatorException;
import org.cx.game.intercepter.AbstractIntercepter;

public class OptionExecuteProcess extends AbstractProcess {

	private Object[] parameter = null; 
	
	public OptionExecuteProcess(Integer waitBout, IOption option) {
		super(waitBout, option.getOwner().getPlayer(), option);
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
	public IOption getOwner() {
		// TODO Auto-generated method stub
		return (IOption) super.getOwner();
	}

	@Override
	public void finish(Object[] args) {
		// TODO Auto-generated method stub
		if(Integer.valueOf(0).equals(getRemainBout())){
			invalid();
			getOwner().setAllow(true);

			try {
				IAction action = new ActionProxyHelper(getOwner().getExecute());
				action.action(parameter);
			} catch (RuleValidatorException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
