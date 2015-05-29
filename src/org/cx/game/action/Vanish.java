package org.cx.game.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observer;
import java.util.Map.Entry;

import org.cx.game.card.ICard;
import org.cx.game.exception.RuleValidatorException;
import org.cx.game.intercepter.IInterceptable;
import org.cx.game.intercepter.IIntercepter;
import org.cx.game.policy.IActionPolicy;
import org.cx.game.validator.Errors;
import org.cx.game.validator.IValidator;

public class Vanish extends Action implements IDeath {

	private List<Map<IInterceptable, IIntercepter>> resetList = new ArrayList<Map<IInterceptable, IIntercepter>>();
	
	@Override
	public void action(Object... objects) throws RuleValidatorException {
		// TODO Auto-generated method stub
		super.action(objects);
	}
	
	@Override
	public void resetIntercepter(Integer level) {
		// TODO Auto-generated method stub
		for(Map<IInterceptable, IIntercepter> map : resetList){
			for(Entry<IInterceptable, IIntercepter> entry : map.entrySet()){
				IInterceptable interceptable = entry.getKey();
				IIntercepter intercepter = entry.getValue();	
				if(level.equals(intercepter.getLevel()))
					interceptable.deleteIntercepter(intercepter);
			}
		}
	}
	
	public void recordIntercepter(IInterceptable interceptable, IIntercepter intercepter){
		interceptable.addIntercepter(intercepter);
		Map entry = new HashMap<IInterceptable, IIntercepter>();
		entry.put(interceptable, intercepter);
		resetList.add(entry);
	}

}
