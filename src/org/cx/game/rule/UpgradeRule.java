package org.cx.game.rule;

import java.util.Observable;

import org.cx.game.action.IUpgrade;
import org.cx.game.action.LifeUpgrade;
import org.cx.game.card.LifeCard;
import org.cx.game.card.skill.ISkill;
import org.cx.game.core.IPlayer;
import org.cx.game.exception.RuleValidatorException;
import org.cx.game.observer.NotifyInfo;
import org.cx.game.widget.building.IBuilding;
import org.cx.game.widget.building.IProduct;

public class UpgradeRule implements IRule {
	
	private IUpgrade upgrade = null;

	public UpgradeRule(IUpgrade upgrade) {
		// TODO Auto-generated constructor stub
		this.upgrade = upgrade;
	}
	
	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		if (arg instanceof NotifyInfo) {
			NotifyInfo info = (NotifyInfo) arg;
			
			if(NotifyInfo.Card_LifeCard_State_EmpiricValue.equals(info.getType())) {
				if(this.upgrade.getProcess()>=100){
					
					LifeUpgrade up = (LifeUpgrade) this.upgrade;
					
					try {
						up.getOwner().upgrade();
					} catch (RuleValidatorException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
			}else if(NotifyInfo.Card_LifeCard_Action_Upgrade.equals(info.getType())) {
				LifeUpgrade upgrade = (LifeUpgrade) this.upgrade;
				upgrade.addToEmpiricValue(-upgrade.getConsume());
				
			}else if(NotifyInfo.Card_LifeCard_Skill_Upgrade.equals(info.getType())){
				ISkill skill = (ISkill) this.upgrade.getOwner();
				LifeCard life = skill.getOwner();
				LifeUpgrade up = (LifeUpgrade) life.getUpgrade();
				up.addToSkillCount(-this.upgrade.getConsume());
				
			}else if(NotifyInfo.Building_Action_Upgrade_Begin.equals(info.getType())){
				IBuilding building = (IBuilding) this.upgrade.getOwner();
				IPlayer player = building.getPlayer();
				if(null!=player){
					player.addToResource(-this.upgrade.getConsume());
				}
				
			}else if(NotifyInfo.Building_Action_Upgrade_Product_Begin.equals(info.getType())){
				IProduct product = (IProduct) this.upgrade.getOwner();
				IBuilding building = product.getOwner();
				IPlayer player = building.getPlayer();
				if(null!=player){
					player.addToResource(-this.upgrade.getConsume());
				}
			}
		}
	}

	@Override
	public IUpgrade getOwner() {
		// TODO Auto-generated method stub
		return this.upgrade;
	}

}
