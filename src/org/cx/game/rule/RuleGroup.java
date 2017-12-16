package org.cx.game.rule;

import java.util.ArrayList;
import java.util.List;

import org.cx.game.action.Attack;
import org.cx.game.action.IAction;
import org.cx.game.intercepter.IInterceptable;

/**
 * 一整套规则；
 * @author chenxian
 *
 */
public class RuleGroup {

	public final static Integer RuleGroup_System = 10500001;
	
	private List<IRule> ruleList = new ArrayList<IRule>();
	
	public void setRuleList(List<IRule> ruleList) {
		this.ruleList = ruleList;
	}
	
	public void bindingRule(IInterceptable interceptable){
		Class clazz = interceptable.getClass();
		
		for(IRule rule : ruleList){
			if(rule.getInterceptable().equals(clazz)){
				interceptable.addIntercepter(rule);
				rule.setOwner(interceptable);
			}
		}
		
		this.ruleList.clear();          //清除多余规则，释放内存
	}
	
	public static void main(String[] args) {
		IAction ac = new Attack();
		Class acl = ac.getClass();
		/*System.out.println("Attack的父类是："+acl.getSuperclass());
		for(Class clazz : acl.getInterfaces())
			System.out.println("Attack的接口包括："+clazz);*/
		
		System.out.println(ac.getClass().equals(Attack.class));
	}
}
