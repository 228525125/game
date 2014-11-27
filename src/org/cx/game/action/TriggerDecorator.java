package org.cx.game.action;

public class TriggerDecorator extends ActionDecorator implements ITrigger {

	private ITrigger trigger;
	
	public TriggerDecorator(ITrigger trigger) {
		// TODO Auto-generated constructor stub
		super(trigger);
		this.trigger = trigger;
	}
}
