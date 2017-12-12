package org.cx.game.validator;

import java.util.Map;
import java.util.Map.Entry;

import org.cx.game.core.IPlayer;
import org.cx.game.tools.I18n;
import org.cx.game.widget.building.IBuilding;

/**
 * 验证建造资源是否足够
 * @author chenxian
 *
 */
public class BuildConsumeValidator extends Validator {
	
	private IBuilding building = null;
	
	public BuildConsumeValidator(IBuilding building) {
		// TODO Auto-generated constructor stub
		this.building = building;
	}
	
	@Override
	public Boolean validate() {
		// TODO Auto-generated method stub
		Boolean ret = true;
		
		IPlayer player = building.getPlayer();
		Map<String,Integer> consume = building.getConsume();
		Map<String,Integer> res = player.getResource();
		
		for(Entry<String,Integer> entry : consume.entrySet()){
			String resType = entry.getKey();
			Integer value = res.get(resType);
			if(value<entry.getValue()){
				ret = false;
				addMessage(I18n.getMessage(BuildConsumeValidator.class.getName()));
				break;
			}
		}
			
		return ret;
	}

}
