package org.cx.game.npc;

import java.util.List;

/**
 * 战场策略
 * @author chenxian
 *
 */
public interface IGroundPolicy extends IPolicy {

	public List<IActionPolicy> getActionPolicy();
}
