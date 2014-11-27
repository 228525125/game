package org.cx.game.command;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.cx.game.card.ICard;
import org.cx.game.card.LifeCard;
import org.cx.game.card.skill.IActiveSkill;
import org.cx.game.core.IPlayer;
import org.cx.game.exception.CommandValidatorException;
import org.cx.game.exception.ValidatorException;
import org.cx.game.observer.NotifyInfo;
import org.cx.game.validator.LifeCardActivateValidator;
import org.cx.game.validator.QueryCommandValidator;
import org.cx.game.validator.SelectLifeCardValidator;
import org.cx.game.widget.Ground;
import org.cx.game.widget.IGround;

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
	}
	
	private List<Integer> positionList = new ArrayList<Integer>();
	
	@Override
	public void execute() throws ValidatorException {
		// TODO Auto-generated method stub
		addValidator(new QueryCommandValidator(parameter.toString(),buffer.getCard()));
		super.execute();
		
		IGround ground = player.getGround();
		
		if("conjure".equals(parameter)){
			positionList = ground.queryRange(buffer.getSkill(), map.get(parameter));
		}else if("attack".equals(parameter) || "call".equals(parameter) || "move".equals(parameter)){
			LifeCard life = (LifeCard) buffer.getCard();           //暂时只有life
			positionList = ground.queryRange(life, map.get(parameter));   //这里需要计算
		}
	
		Map<String,Object> bean = new HashMap<String,Object>();
		bean.put("player", buffer.getPlayer());
		bean.put("container", ground);
		bean.put("positionList", positionList);
		NotifyInfo info = new NotifyInfo(map.get(parameter),bean);
		super.notifyObservers(info);    //通知观察者
	}
}
