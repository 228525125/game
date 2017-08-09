package org.cx.game.policy;

import java.util.ArrayList;
import java.util.List;

import org.cx.game.card.LifeCard;
import org.cx.game.command.CommandFactory;
import org.cx.game.command.Invoker;
import org.cx.game.exception.ValidatorException;
import org.cx.game.policy.formula.LockFormula;
import org.cx.game.policy.formula.ShechengneidedirenFormula;

/**
 * 攻击策略
 * @author chenxian
 *
 */
public class AttackPolicy extends Policy {
	
	private String cmdStr = "";
	
	@Override
	public void calculate() {
		// TODO Auto-generated method stub
		LifeCard owner = (LifeCard) getOwner().getOwner();
		
		setPri(IPolicy.PRI_Min);		
		
		/*
		 * 判断是否被锁定
		 */
		List<LifeCard> lockerList = new ArrayList<LifeCard>();
		
		LockFormula lockFormula = new LockFormula(owner);
		doValidator(lockFormula);
		if(hasError()){       //没有被锁，搜索射程范围内的最近的敌人
			
			/*
			 * 判断在攻击范围内，是否有敌人
			 */
			ShechengneidedirenFormula scFormula = new ShechengneidedirenFormula(owner);
			doValidator(scFormula);
			
			if(hasError())
				return;
			
			LifeCard enemy = scFormula.getNearEnemy();
			this.cmdStr = "attack ground place"+enemy.getContainerPosition()+" card";
			
			validator();
		}else{              //已经被锁，就在锁定的敌人中随机找
			lockerList = lockFormula.getLockerList();
			LifeCard locker = lockerList.get(0);
			this.cmdStr = "attack ground place"+locker.getContainerPosition()+" card";
			
			validator();
		}
	}
	
	private void validator(){
		LifeCard owner = (LifeCard) getOwner().getOwner();
		Invoker invoker = new Invoker();
		String cmd = "select ground place"+owner.getContainerPosition()+" card;";
		try {
			invoker.receiveCommand(owner.getPlayer(), cmd);
			
			super.command = CommandFactory.createCommand(owner.getPlayer(),this.cmdStr);
			super.command.doValidator();
			if(!super.command.hasError()){
				setPri(IPolicy.PRI_High);
			}
		} catch (ValidatorException e) {
			// TODO Auto-generated catch block
			return;
		}
	}
}
