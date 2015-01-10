package org.cx.game.widget;

import org.cx.game.core.Context;

/**
 *  据点
 * @author chenxian
 *
 */
public interface IStrongHold {

	public void setContext(Context context);

	public Integer getPosition();

	public Integer getRange();

	public Integer getBout();

	public void refurbish();

}