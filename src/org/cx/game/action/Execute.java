package org.cx.game.action;

import org.cx.game.widget.AbstractOption;

public class Execute extends AbstractAction implements IAction {
	
	public AbstractOption getOwner() {
		// TODO Auto-generated method stub
		return (AbstractOption) super.getOwner();
	}
	
	@Override
	public void action(Object... objects) {
		// TODO Auto-generated method stub
		
		/*
		 * 执行选项间隔周期
		 */
		getOwner().cooling();
	}

}
