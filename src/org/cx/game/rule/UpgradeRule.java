package org.cx.game.rule;

import java.util.Map;
import java.util.Observable;

import org.cx.game.action.ILifeUpgrade;
import org.cx.game.action.IUpgrade;
import org.cx.game.action.LifeUpgrade;
import org.cx.game.card.LifeCard;
import org.cx.game.card.skill.ISkill;
import org.cx.game.core.IPlayer;
import org.cx.game.exception.RuleValidatorException;
import org.cx.game.observer.NotifyInfo;
import org.cx.game.widget.building.BuildingUpgrade;
import org.cx.game.widget.building.IBuilding;
import org.cx.game.widget.building.IProduct;
import org.cx.game.widget.building.ProductUpgrade;
import org.cx.game.widget.building.ReviveOption;

public class UpgradeRule implements IRule {

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
		if (arg instanceof NotifyInfo) {
			NotifyInfo info = (NotifyInfo) arg;
			Map bean = (Map) info.getInfo(); 
			LifeCard owner = (LifeCard) bean.get("card");
			
			if(NotifyInfo.Card_LifeCard_State_EmpiricValue.equals(info.getType())) {
				ILifeUpgrade upgrade = (ILifeUpgrade) ((RuleGroup) o).getMessageSource();
				 
				
				if(upgrade.getEmpiricValue()>=upgrade.getConsume()){
					
					try {
						owner.upgrade();
					} catch (RuleValidatorException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
			}else if(NotifyInfo.Card_LifeCard_Action_Upgrade.equals(info.getType())) {
				LifeUpgrade upgrade = (LifeUpgrade) ((RuleGroup) o).getMessageSource();
				
				LifeCard life = upgrade.getOwner();
				Integer hplimit = life.getDeath().getHplimit();
				Integer atk = life.getAttack().getAtk();
				Integer def = life.getAttacked().getDef();
				Integer consume = life.getCall().getConsume();
				
				life.getDeath().setHplimit(hplimit+hplimit*IUpgrade.LifeCardRiseRatio/100);    //增加HP上限
				life.getDeath().addToHp(hplimit*IUpgrade.LifeCardRiseRatio/100);               //增加HP
				life.getAttack().addToAtk(atk*IUpgrade.LifeCardRiseRatio/100);                 //增加Atk
				life.getAttacked().addToDef(def*IUpgrade.LifeCardRiseRatio/100);               //增加Def
				
				life.getCall().addToConsume(consume*IUpgrade.LifeCardRiseRatio/100);           //增加call消耗
				
			}else if(NotifyInfo.Card_LifeCard_Skill_Upgrade.equals(info.getType())){
				IUpgrade upgrade = (IUpgrade) ((RuleGroup) o).getMessageSource();
				
				ISkill skill = (ISkill) upgrade.getOwner();
				LifeCard life = skill.getOwner();
				LifeUpgrade up = (LifeUpgrade) life.getUpgrade();
				up.addToSkillCount(-upgrade.getConsume());
				
			}else if(NotifyInfo.Building_Action_Upgrade.equals(info.getType())){
				BuildingUpgrade upgrade = (BuildingUpgrade) ((RuleGroup) o).getMessageSource();
				
				IBuilding building = (IBuilding) upgrade.getOwner();
				IPlayer player = building.getPlayer();
				if(null!=player){
					player.addToResource(-upgrade.getConsume());
				}
				
			}else if(NotifyInfo.Building_Action_Upgrade_Product.equals(info.getType())){
				ProductUpgrade upgrade = (ProductUpgrade) ((RuleGroup) o).getMessageSource();
				
				IProduct product = (IProduct) upgrade.getOwner();
				IBuilding building = product.getOwner();
				IPlayer player = building.getPlayer();
				if(null!=player){
					player.addToResource(-upgrade.getConsume());
				}
			}
		}
	}

}
