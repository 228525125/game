package org.cx.game.card;

import java.util.Map;

import org.cx.game.action.Apply;
import org.cx.game.action.ApplyDecorator;
import org.cx.game.action.Chuck;
import org.cx.game.action.ChuckDecorator;
import org.cx.game.action.IApply;
import org.cx.game.action.IChuck;
import org.cx.game.action.ISetup;
import org.cx.game.action.ITrigger;
import org.cx.game.action.SetupDecorator;
import org.cx.game.action.TriggerDecorator;
import org.cx.game.core.IPlayer;
import org.cx.game.exception.RuleValidatorException;
import org.cx.game.npc.ICardPolicy;
import org.cx.game.out.JsonOut;
import org.cx.game.out.Response;
import org.cx.game.widget.IContainer;

public abstract class MagicCard implements ICard {
	
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
	private Integer type = Type_Magic;

	@Override
	public Integer getType() {
		// TODO Auto-generated method stub
		return type;
	}
	
	/**
	 * 用于保存受控的卡片
	 */
	private ICard controlCard = null;

	public ICard getControlCard() {
		return controlCard;
	}

	public void setControlCard(ICard controlCard) {
		this.controlCard = controlCard;
	}
	
	/**
	 * 使用
	 */
	private IApply apply;

	public IApply getApply() {
		return apply;
	}

	public void setApply(IApply apply) {
		apply.setOwner(this);
		this.apply = new ApplyDecorator(apply);
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
	
	/**
	 * 使用
	 */
	public void apply() throws RuleValidatorException {
		apply.action();
	}
	
	@Override
	public void chuck() throws RuleValidatorException {
		// TODO Auto-generated method stub
		chuck.action();
	}
	
}
