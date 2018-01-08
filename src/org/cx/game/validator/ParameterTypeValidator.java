package org.cx.game.validator;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.cx.game.magic.skill.IActiveSkill;
import org.cx.game.tools.I18n;

/**
 * 技能被使用时，需要验证参数的类型和个数是否与预设的一致
 * @author chenxian
 *
 */
public class ParameterTypeValidator extends Validator {

	private Object[] parameter = null;
	private Class[] type = null;
	private String[] propertyName = null;
	private List propertyValueList = new ArrayList();
	
	/**
	 * 
	 * @param parameter 参数
	 * @param type 参数类型
	 * @param propertyName 参数属性
	 * @param propertyValue 包含属性值的集合
	 */
	public ParameterTypeValidator(Object[] parameter, Class[] type, String[] propertyName, Object[] propertyValue){
		this.parameter = parameter;
		this.type = type;
		this.propertyName = propertyName;
		if(null!=propertyValue)
			Collections.addAll(this.propertyValueList, propertyValue);
	}
	
	@Override
	public Boolean validate() {
		// TODO Auto-generated method stub
		Boolean ret = true;
		
		if(type.length==0)
			return ret;
		
		if(parameter.length==type.length){
			for(int i=0;i<parameter.length;i++){
				if(type[i].isAssignableFrom(parameter[i].getClass())){
					if(null!=this.propertyName && null!=this.propertyName[i]){
						try {
							String pName = propertyName[i].substring(0, 1).toUpperCase() + propertyName[i].substring(1);    //首字母大写
							Method method = parameter[i].getClass().getDeclaredMethod("get"+pName);
							Object pValue = method.invoke(parameter[i]);
							
							if(this.propertyValueList.contains(pValue)){
								;
							}else{
								addMessage(I18n.getMessage(ParameterTypeValidator.class.getName()));
								ret = false;
								break;
							}
						}catch (SecurityException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (IllegalArgumentException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (IllegalAccessException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (NoSuchMethodException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (InvocationTargetException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}else{
					addMessage(I18n.getMessage(ParameterTypeValidator.class.getName()));
					ret = false;
					break;
				}
			}
		}else{
			addMessage(I18n.getMessage(ParameterTypeValidator.class.getName()));
			ret = false;
		}
		
		return ret;
	}
	
	protected Object[] getParameter() {
		return this.parameter;
	}
	
	public static void main(String[] args) {
		/*ShieldHit sh = new ShieldHit(0,0,0,0,0,0,0);
		System.out.println(new Object []{}.getClass());
		System.out.println(new Object []{sh}.getClass());
		System.out.println(Object.class.isAssignableFrom(String.class));*/
	}
}
