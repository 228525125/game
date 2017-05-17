package org.cx.game.policy;

import java.util.ArrayList;
import java.util.List;

import org.cx.game.card.ICard;
import org.cx.game.card.LifeCard;
import org.cx.game.core.IPlayer;
import org.cx.game.exception.RuleValidatorException;

public class UseCardPolicy implements IUseCardPolicy {

	@Override
	public void refreshPri() {
		// TODO Auto-generated method stub
		Integer pri = 0;
		for(IFormula formula : this.formulaList){
			pri += formula.execute();
		}
		this.pri = pri;
	}
	
	private Integer pri = 0;

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
		for(IFormula formula : formulaList)
			formula.setPriority(this);
	}
	
	private ICard owner = null;

	@Override
	public ICard getOwner() {
		// TODO Auto-generated method stub
		return this.owner;
	}
	
	public void setOwner(Object owner){
		this.owner = (ICard) owner;
	}
	
	@Override
	public void execute() {
		// TODO Auto-generated method stub
		//使用owner
		if (this.owner instanceof LifeCard) {
			LifeCard life = (LifeCard) this.owner;
			IPlayer player = this.owner.getPlayer();
			Integer entry = null; //player.getGround().getRandomEntry(life);
			if(null!=entry){
				try {
					life.call(player.getGround().getPlace(entry));
				} catch (RuleValidatorException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
}
