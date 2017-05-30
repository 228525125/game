package org.cx.game.rule;

import java.util.Observable;

import org.cx.game.action.IExecute;

public class ExecuteRule implements IRule {

	private IExecute execute = null;
	
	public ExecuteRule(IExecute execute) {
		// TODO Auto-generated constructor stub
		this.execute = execute;
	}
	
	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public Object getOwner() {
		// TODO Auto-generated method stub
		return null;
	}

}
