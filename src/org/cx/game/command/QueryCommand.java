package org.cx.game.command;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.cx.game.card.ICard;
import org.cx.game.card.LifeCard;
import org.cx.game.card.MagicCard;
import org.cx.game.card.skill.IActiveSkill;
import org.cx.game.core.IPlayer;
import org.cx.game.exception.CommandValidatorException;
import org.cx.game.exception.ValidatorException;
import org.cx.game.observer.NotifyInfo;
import org.cx.game.validator.LifeCardActivateValidator;
import org.cx.game.validator.NeedConjurerValidator;
import org.cx.game.validator.QueryCommandValidator;
import org.cx.game.validator.SelectLifeCardValidator;
import org.cx.game.validator.SelectMagicCardValidator;
import org.cx.game.widget.IGround;
import org.cx.game.widget.building.IOption;

public class QueryCommand extends InteriorCommand {

	private Map<String, String> map = new HashMap<String, String>();
	
	public QueryCommand(IPlayer player) {
		// TODO Auto-generated constructor stub
		super(player);
		map.put("attack", NotifyInfo.Command_Query_Attack);
		map.put("call", NotifyInfo.Command_Query_Call);
		map.put("move", NotifyInfo.Command_Query_Move);
		map.put("conjure", NotifyInfo.Command_Query_Conjure);
		map.put("swap", NotifyInfo.Command_Query_Swap);
		map.put("apply", NotifyInfo.Command_Query_Apply);
		map.put("execute", NotifyInfo.Command_Query_Execute);
	}
	
	private List<Integer> positionList = new ArrayList<Integer>();
	
	@Override
	public void execute() throws ValidatorException {
		// TODO Auto-generated method stub
		addValidator(new QueryCommandValidator(parameter.toString(),buffer));
		super.execute();
		
		IGround ground = player.getGround();
		
		if("conjure".equals(parameter)){
			positionList = ground.queryRange(buffer.getSkill(), map.get(parameter));
		}else if("attack".equals(parameter) || "move".equals(parameter)){
			doValidator(new SelectLifeCardValidator(buffer));
			if(hasError())
				throw new CommandValidatorException(getErrors().getMessage());
			
			LifeCard life = (LifeCard) buffer.getCard();           
			positionList = ground.queryRange(life, map.get(parameter));   //这里需要计算
		}else if("execute".equals(parameter)){
			IOption option = buffer.getOption();
			positionList = ground.queryRange(option, map.get(parameter));
		}else if("apply".equals(parameter)){
			doValidator(new SelectMagicCardValidator(buffer));
			if(hasError())
				throw new CommandValidatorException(getErrors().getMessage());
			
			MagicCard magic = (MagicCard) buffer.getCard();
			positionList = ground.queryRange(magic, map.get(parameter));
		}
	
		Map<String,Object> bean = new HashMap<String,Object>();
		bean.put("player", buffer.getPlayer());
		bean.put("container", ground);
		bean.put("positionList", positionList);
		NotifyInfo info = new NotifyInfo(map.get(parameter),bean);
		super.notifyObservers(info);    //通知观察者
	}
}
