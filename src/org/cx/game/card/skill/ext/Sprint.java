package org.cx.game.card.skill.ext;

import org.cx.game.card.LifeCard;
import org.cx.game.card.skill.SimplePassiveSkill;
import org.cx.game.intercepter.IIntercepter;
import org.cx.game.intercepter.Intercepter;
import org.cx.game.observer.NotifyInfo;
import org.cx.game.widget.IPlace;

/**
 * 冲刺，按移动步数增加攻击力，仅本回合
 * @author chenxian
 *
 */
public class Sprint extends SimplePassiveSkill {

	private Integer upAtkScale;   //攻击提升比例
	
	/**
	 * 
	 * @param style
	 * @param type
	 * @param upAtkScale 攻击提升比例
	 * @param life
	 */
	public Sprint(Integer style, Integer atkScale) {
		super(style);
		// TODO Auto-generated constructor stub
		this.upAtkScale = atkScale;
	}
	
	@Override
	public void setOwner(LifeCard life) {
		// TODO Auto-generated method stub
		super.setOwner(life);
		
		IIntercepter moveIn = new Intercepter(){

			
			private Integer begin = null;
			private Integer end = null;
			
			@Override
			public void finish(Object[] args) {
				// TODO Auto-generated method stub
				Integer step = getOwner().getPlayer().getGround().distance(begin, end);
				Integer upAtkValue = step*Sprint.this.upAtkScale/100;
				addToEruptAtk(upAtkValue);
				affect();
			}

			@Override
			public void before(Object[] args) {
				// TODO Auto-generated method stub
				this.begin = getOwner().getContainerPosition();
				this.end = ((IPlace)((Object[]) args[0])[0]).getPosition();
			}
			
			@Override
			public Integer getLevel() {
				// TODO Auto-generated method stub
				return IIntercepter.Level_Rule;
			}
		};
		life.getAttack().addIntercepter(moveIn);
	}

	@Override
	public String getIntercepterMethod() {
		// TODO Auto-generated method stub
		return "attack";
	}

	@Override
	public Boolean isInvoke() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public void before(Object[] args) {
		// TODO Auto-generated method stub
		
	}

}
