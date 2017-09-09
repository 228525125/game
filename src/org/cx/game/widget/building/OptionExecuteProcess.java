package org.cx.game.widget.building;

import java.util.HashMap;
import java.util.Map;

import org.cx.game.exception.RuleValidatorException;
import org.cx.game.intercepter.Intercepter;
import org.cx.game.observer.NotifyInfo;

public class OptionExecuteProcess extends Process {

	private IOption option = null;
	private Object[] parameter = null; 
	
	public OptionExecuteProcess(Integer waitBout, IOption option) {
		super(waitBout, option);
		// TODO Auto-generated constructor stub
		this.option = option;
		
		recordIntercepter(this.option.getExecute(), new Intercepter() {
			
			@Override
			public void before(Object[] args) {
				// TODO Auto-generated method stub
				OptionExecuteProcess.this.parameter = (Object[]) args[0];
			}
			
			@Override
			public Boolean isInvoke() {
				// TODO Auto-generated method stub
				return Integer.valueOf(100).equals(getProcess());
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
		if(Integer.valueOf(100).equals(getProcess())){
			invalid();

			try {
				this.option.execute(parameter);         //参数 可能有问题
			} catch (RuleValidatorException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
