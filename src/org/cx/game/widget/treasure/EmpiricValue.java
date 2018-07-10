package org.cx.game.widget.treasure;

import org.cx.game.tools.CommonIdentifier;

public class EmpiricValue extends Resource {

	public EmpiricValue() {
		// TODO Auto-generated constructor stub
	}
	
	public EmpiricValue(Integer empValue) {
		add(CommonIdentifier.EmpiricValue, empValue);
	}
	
	public void add(Integer value){
		add(CommonIdentifier.EmpiricValue, value);
	}
	
	public Integer get(){
		return get(CommonIdentifier.EmpiricValue);
	}
	
	public Integer getValue(){
		return get();
	}
}
