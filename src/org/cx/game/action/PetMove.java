package org.cx.game.action;

import java.util.ArrayList;
import java.util.List;

import org.cx.game.card.LifeCard;
import org.cx.game.card.Pet;
import org.cx.game.observer.NotifyInfo;
import org.cx.game.widget.IGround;

/**
 * 宠物的移动范围，宠物必须主人保持一定距离
 * @author chenxian
 *
 */
public class PetMove extends Move {

	/**
	 * 宠物与主人的距离关系
	 */
	public final static Integer Move_Range = 8;
	
	/**
	 * 回到主人身边
	 */
	public void homing(LifeCard owner){
		IGround ground = getOwner().getPlayer().getGround();
		List<Integer> ps = ground.easyAreaForDistance(owner.getContainerPosition(), 1, IGround.Equal);
		for(Integer p : ps){
			if(null==ground.getCard(p)){
				ground.move(getOwner(), p, IMove.Type_Flash);
			}
		}
	}
}
