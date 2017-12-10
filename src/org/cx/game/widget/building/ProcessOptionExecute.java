package org.cx.game.widget.building;

import org.cx.game.exception.RuleValidatorException;
import org.cx.game.intercepter.Intercepter;

public class ProcessOptionExecute extends Process {

	private IOption option = null;
	private Object[] parameter = null; 
	
	public ProcessOptionExecute(Integer waitBout, IOption option) {
		super(waitBout, option);
		// TODO Auto-generated constructor stub
		this.option = option;
		
		recordIntercepter(this.option.getExecute(), new Intercepter() {
			
			@Override
			public void before(Object[] args) {
				// TODO Auto-generated method stub
				ProcessOptionExecute.this.parameter = (Object[]) args[0];
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

			try {
				this.option.getExecute().action(parameter);         //参数 可能有问题
			} catch (RuleValidatorException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
