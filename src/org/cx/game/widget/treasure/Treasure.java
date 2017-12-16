package org.cx.game.widget.treasure;

import org.cx.game.card.LifeCard;
import org.cx.game.exception.RuleValidatorException;
import org.cx.game.tools.I18n;

/**
 * 物品
 * @author chenxian
 *
 */
public abstract class Treasure implements ITreasure {

	private String name = null;
	private Integer position = null;
	
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		if(null==name)
			name = I18n.getMessage(this, "name");
		return name;
	}
	
	public Integer getPosition() {
		return position;
	}

	public void setPosition(Integer position) {
		this.position = position;
	}

	@Override
	public void picked(LifeCard life) throws RuleValidatorException {
		// TODO Auto-generated method stub
		getPicked().execute(life);
	}

}
