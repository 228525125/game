package org.cx.game.action;

import java.util.List;
import java.util.Observer;

import org.cx.game.card.ICard;
import org.cx.game.card.LifeCard;
import org.cx.game.card.skill.ActiveSkill;
import org.cx.game.card.skill.ISkill;
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
	public void addToHp(Integer hp) {
		// TODO Auto-generated method stub
		Object proxy = ProxyFactory.getProxy(this.death);     
		((IDeath)proxy).addToHp(hp);
	}
	
	@Override
	public Integer getHplimit() {
		// TODO Auto-generated method stub
		return death.getHplimit();
	}

	@Override
	public void setHplimit(Integer hplimit) {
		// TODO Auto-generated method stub
		death.setHplimit(hplimit);
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
	
	@Override
	public LifeCard getOwner() {
		// TODO Auto-generated method stub
		return death.getOwner();
	}

}
