package org.cx.game.command;

import org.cx.game.card.LifeCard;
import org.cx.game.core.IPlayer;
import org.cx.game.exception.ValidatorException;
import org.cx.game.validator.SelectContainerValidator;
import org.cx.game.validator.SelectLifeCardValidator;
import org.cx.game.validator.SelectPlaceEmptyValidator;
import org.cx.game.widget.IPlace;

/**
 * 暂停使用，已被CallOption替代
 * @author chenxian
 *
 */
@Deprecated
public class CallCommand extends InteriorCommand {

	public CallCommand(IPlayer player) {
		// TODO Auto-generated constructor stub
		super(player);
		addValidator(new SelectLifeCardValidator(buffer));
	}
	
	@Override
	public void setParameter(Object parameter) {
		// TODO Auto-generated method stub
		super.setParameter(parameter);
		addValidator(new SelectPlaceEmptyValidator((IPlace) parameter,true));
	}
	
	@Override
	public void execute() throws ValidatorException {
		// TODO Auto-generated method stub
		super.execute();
		
		IPlace place = (IPlace) parameter;
		LifeCard life = (LifeCard) buffer.getCard();
		//life.call(place);
		buffer.clear();
	}
}
