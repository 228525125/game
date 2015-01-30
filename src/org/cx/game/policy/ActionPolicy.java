package org.cx.game.policy;

import java.util.ArrayList;
import java.util.List;

import org.cx.game.action.IAction;

public class ActionPolicy implements IActionPolicy {

	@Override
	public void refreshPri() {
		// TODO Auto-generated method stub
		Integer pri = 0;
		
		for(IFormula formula : formulaList){
			pri += formula.execute();
		}
		
		this.pri = pri;
	}
	
	private Integer pri = 0;;

	@Override
	public Integer getPri() {
		// TODO Auto-generated method stub
		return this.pri;
	}

	@Override
	public void setPri(int pri) {
		// TODO Auto-generated method stub
		this.pri = pri;
	}
	
	private List<IFormula> formulaList = new ArrayList<IFormula>();

	@Override
	public void setFormulaList(List<IFormula> formulaList) {
		// TODO Auto-generated method stub
		this.formulaList = formulaList;
	}
	
	private IAction owner = null;

	@Override
	public IAction getOwner() {
		// TODO Auto-generated method stub
		return this.owner;
	}
	
	@Override
	public void setOwner(Object owner) {
		// TODO Auto-generated method stub
		this.owner = (IAction) owner;
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		//执行owner
	}


}
