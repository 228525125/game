package org.cx.game.builder;

import org.cx.game.exception.BuilderException;

public interface IBuilder {

	public Object builder() throws BuilderException;
}
