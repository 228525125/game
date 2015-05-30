package org.cx.game.validator;

import java.util.List;

import org.cx.game.card.skill.ActiveSkill;
import org.cx.game.card.skill.IActiveSkill;
import org.cx.game.card.skill.ext.ShieldHit;
import org.cx.game.tools.I18n;

/**
 * 技能被使用时，需要验证参数的类型是否与预设的一致
 * @author chenxian
 *
 */
public class ParameterTypeValidator extends Validator {

	private Object[] parameter = null;
	private Class[] type = null;
	
	public ParameterTypeValidator(Object[] parameter, Class[] type) {
		// TODO Auto-generated constructor stub
		this.parameter = parameter;
		this.type = type;
	}
	
	@Override
	public Boolean validate() {
		// TODO Auto-generated method stub
		Boolean ret = true;
		
		if(type.length==0)
			return ret;
		
		if(parameter.length==type.length){
			for(int i=0;i<parameter.length;i++){
				if(!type[i].isAssignableFrom(parameter[i].getClass())){
					addMessage(I18n.getMessage(this));
					ret = false;
					break;
				}
			}
		}
		
		return ret;
	}
	
	public static void main(String[] args) {
		ShieldHit sh = new ShieldHit(0,0,0,0,0,0,0);
		System.out.println(new Object []{}.getClass());
		System.out.println(new Object []{sh}.getClass());
		System.out.println(Object.class.isAssignableFrom(String.class));
	}
}
