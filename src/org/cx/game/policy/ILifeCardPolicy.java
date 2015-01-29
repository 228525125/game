package org.cx.game.policy;

public interface ILifeCardPolicy {

	public IActionPolicy getActionPolicy();
	
	public Boolean hasNext();
}
