package org.cx.game.action;

import java.io.InputStream;
import java.util.Iterator;

import org.cx.game.card.CardFactory;
import org.cx.game.card.LifeCard;
import org.cx.game.exception.RuleValidatorException;
import org.cx.game.intercepter.IIntercepter;
import org.cx.game.intercepter.Intercepter;
import org.cx.game.intercepter.ProxyFactory;
import org.cx.game.tools.Util;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

/**
 * attacked的包装类，包含了一些比赛规则、代理模式等
 * @author jiuhuan
 *
 */
public class AttackedDecorator extends ActionDecorator implements IAttacked {

	private IAttacked attacked = null;
	
	private static String filePath = "/org/cx/game/action/attack.xml";
	
	private static Element getRoot() {
		SAXReader saxReader = new SAXReader();
		InputStream is=CardFactory.class.getResourceAsStream(filePath); 
		try {
			Document document = saxReader.read(is);
			return document.getRootElement();
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	private static Double getAttackScale(Integer atkType, Integer armourType){
		Element type = getRoot().element("attacktype");
		for(Iterator it = type.elementIterator("attack");it.hasNext();){
			Element attack = (Element) it.next();
			if(atkType.equals(Integer.valueOf(attack.attribute("type").getText()))){
				for(Iterator itr = attack.elementIterator("armour");it.hasNext();){
					Element armour = (Element) itr.next();
					if(armourType.equals(Integer.valueOf(armour.attribute("type").getText())))
						return Double.valueOf(armour.getText())/100;
				}
			}else
				continue;
		}
		return null;
	}
	
	public AttackedDecorator(IAttacked attacked) {
		// TODO Auto-generated constructor stub
		super(attacked);
		this.attacked = attacked;
		this.attacked.addIntercepter(new Intercepter() {
			private Integer atk = 0;
			
			@Override
			public void before(Object[] args) {
				// TODO Auto-generated method stub
				LifeCard attack = (LifeCard) ((Object[]) args[0])[0];
				LifeCard attacked = (LifeCard) getOwner();
				
				this.atk = attack.getAttack().getAtk();
				
				/*
				 * 攻击类型与防御类型相克的处理
				 */
				attack.getAttack().setAtk(Util.convertInteger(atk*getAttackScale(attack.getAttack().getType(), attacked.getAttacked().getArmourType())));
			}
			
			@Override
			public void after(Object[] args) {
				// TODO Auto-generated method stub
				LifeCard attacked = (LifeCard) getOwner();
				
				if (attacked.getDeath().getHp()<1) {
					try {
						attacked.death();
					} catch (RuleValidatorException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			
			@Override
			public void finish(Object[] args) {
				// TODO Auto-generated method stub
				LifeCard attack = (LifeCard) ((Object[]) args[0])[0];
				
				attack.getAttack().setAtk(atk);
			}
			
			@Override
			public Integer getOrder() {
				// TODO Auto-generated method stub
				return Intercepter.Order_Default - 10;          //对死亡的判断应该优先与反击的判断
			}
			
			@Override
			public Integer getLevel() {
				// TODO Auto-generated method stub
				return IIntercepter.Level_Rule;
			}
		});
	}

	@Override
	public Integer getArmourType() {
		// TODO Auto-generated method stub
		return attacked.getArmourType();
	}

	@Override
	public void setArmourType(Integer armourType) {
		// TODO Auto-generated method stub
		attacked.setArmourType(armourType);
	}

	@Override
	public Integer getAttackBackChance() {
		// TODO Auto-generated method stub
		return attacked.getAttackBackChance();
	}

	@Override
	public Integer getDodgeChance() {
		// TODO Auto-generated method stub
		return attacked.getDodgeChance();
	}

	@Override
	public Integer getParryChance() {
		// TODO Auto-generated method stub
		return attacked.getParryChance();
	}

	@Override
	public void setAttackBackChance(Integer attackBackChance) {
		// TODO Auto-generated method stub
		attacked.setAttackBackChance(attackBackChance);
	}

	@Override
	public void setDodgeChance(Integer dodgeChance) {
		// TODO Auto-generated method stub
		attacked.setDodgeChance(dodgeChance);
	}

	@Override
	public void setParryChance(Integer parryChance) {
		// TODO Auto-generated method stub
		attacked.setParryChance(parryChance);
	}

	@Override
	public Double getImmuneDamageRatio() {
		// TODO Auto-generated method stub
		return attacked.getImmuneDamageRatio();
	}

	@Override
	public void setImmuneDamageRatio(Double def) {
		// TODO Auto-generated method stub
		attacked.setImmuneDamageRatio(def);
	}

	@Override
	public void addToImmuneDamageRatio(Double damageChance) {
		// TODO Auto-generated method stub
		attacked.addToImmuneDamageRatio(damageChance);
	}

	@Override
	public void addToDodgeChance(Integer dodgeChance) {
		// TODO Auto-generated method stub
		attacked.addToDodgeChance(dodgeChance);
	}
}
