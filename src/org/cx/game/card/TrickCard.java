package org.cx.game.card;

import org.cx.game.action.AttackDecorator;
import org.cx.game.action.Chuck;
import org.cx.game.action.ChuckDecorator;
import org.cx.game.action.IAttack;
import org.cx.game.action.IChuck;
import org.cx.game.action.ISetup;
import org.cx.game.action.ITrigger;
import org.cx.game.action.SetupDecorator;
import org.cx.game.action.TriggerDecorator;
import org.cx.game.card.skill.ITrick;
import org.cx.game.core.IPlayer;
import org.cx.game.exception.RuleValidatorException;
import org.cx.game.out.JsonOut;
import org.cx.game.out.Response;

/**
 * 仅表示陷阱卡，它与Trick有本质区别，陷阱卡只能放置在专用区域，作用范围是所有对象，而
 * Trick可以放置在place上，并只对place相关区域进行作用，另外，TrickCard是卡片的一种，
 * Trick不属于卡片，它是一种环境。
 * @author chenxian
 *
 */
public abstract class TrickCard implements ICard, ITrick {

	/**
	 * 比赛中的ID，临时的
	 */
	private Long playId;

	@Override
	public Long getPlayId() {
		// TODO Auto-generated method stub
		return playId;
	}
	
	@Override
	public void setPlayId(Long playId) {
		// TODO Auto-generated method stub
		this.playId = playId;
	}
	
	private IPlayer player;

	@Override
	public IPlayer getPlayer() {
		// TODO Auto-generated method stub
		return player;
	}
	
	@Override
	public void setPlayer(IPlayer player) {
		// TODO Auto-generated method stub
		this.player = player;
	}
	
	/**
	 * 卡片类型
	 */
	private Integer type = Type_Trap;

	@Override
	public Integer getType() {
		// TODO Auto-generated method stub
		return type;
	}
	
	/**
	 * 陷阱卡发动
	 */
	private ITrigger trigger;

	public ITrigger getTrigger() {
		return trigger;
	}

	public void setTrigger(ITrigger trigger) {
		this.trigger = new TriggerDecorator(trigger);
	}

	public void trigger() throws RuleValidatorException {
		trigger.action();
	}
	
	/**
	 * 是否处于显示状态
	 */
	boolean display = false;
	
	public boolean getDisplay() {
		return display;
	}

	public void setDisplay(boolean display) {
		this.display = display;
	}

	/**
	 * 放置
	 */
	private ISetup setup;

	public ISetup getSetup() {
		return setup;
	}

	public void setSetup(ISetup setup) {
		setup.setOwner(this);
		this.setup = new SetupDecorator(setup);
	}
	
	public void setup(Integer position) throws RuleValidatorException {
		setup.action(position);
	}
	
	/**
	 * 丢弃
	 */
	private IChuck chuck;

	public IChuck getChuck() {
		return chuck;
	}

	public void setChuck(IChuck chuck) {
		chuck.setOwner(this);
		this.chuck = new ChuckDecorator(chuck);
	}
	
	@Override
	public void chuck() throws RuleValidatorException {
		// TODO Auto-generated method stub
		chuck.action();
	}

}
