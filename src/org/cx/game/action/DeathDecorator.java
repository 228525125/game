package org.cx.game.action;

import java.util.List;
import java.util.Observer;

import org.cx.game.card.LifeCard;
import org.cx.game.exception.RuleValidatorException;
import org.cx.game.intercepter.IInterceptable;
import org.cx.game.intercepter.IIntercepter;
import org.cx.game.intercepter.Intercepter;
import org.cx.game.intercepter.ProxyFactory;

public class DeathDecorator extends ActionDecorator implements IDeath {
	
	private IDeath death = null;
	
	public DeathDecorator(IDeath death) {
		// TODO Auto-generated constructor stub
		super(death);
		this.death = death;
		
		this.death.addIntercepter(new Intercepter("addToHp"){
			
			@Override
			public void finish(Object[] args) {
				// TODO Auto-generated method stub
				if(0==getHp()){
					try {
						action();
					} catch (RuleValidatorException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		});
	}

	@Override
	public Integer getHp() {
		// TODO Auto-generated method stub
		return death.getHp();
	}

	@Override
	public void setHp(Integer hp) {
		// TODO Auto-generated method stub
		death.setHp(hp);
	}

	@Override
	public void attackToDamage(Integer hp) {
		// TODO Auto-generated method stub
		death.attackToDamage(hp);
	}

	@Override
	public void magicToHp(Integer hp) {
		// TODO Auto-generated method stub
		death.magicToHp(hp);
	}

	@Override
	public void recordIntercepter(IInterceptable interceptable,
			IIntercepter intercepter) {
		// TODO Auto-generated method stub
		death.recordIntercepter(interceptable, intercepter);
	}

	@Override
	public void resetIntercepter(Integer level) {
		// TODO Auto-generated method stub
		death.resetIntercepter(level);
	}

	@Override
	public Integer getStatus() {
		// TODO Auto-generated method stub
		return death.getStatus();
	}

	@Override
	public void setStatus(Integer status) {
		// TODO Auto-generated method stub
		death.setStatus(status);
	}

}
