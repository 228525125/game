package org.cx.game.rule;

import org.cx.game.action.Execute;
import org.cx.game.action.IExecute;

public class ExecuteRule extends Rule implements IRule {
	
	@Override
	public String getIntercepterMethod() {
		// TODO Auto-generated method stub
		return "action";
	}
	
	@Override
	public void after(Object[] args) {
		// TODO Auto-generated method stub
		IExecute execute = getOwner();
		
		/*
		 * 执行选项间隔周期
		 */
		execute.getOwner().cooling();
	}
	
	@Override
	public Class getInterceptable() {
		// TODO Auto-generated method stub
		return Execute.class;
	}
	
	@Override
	public Execute getOwner() {
		// TODO Auto-generated method stub
		return (Execute) super.getOwner();
	}
}
