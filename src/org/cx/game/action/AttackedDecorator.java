package org.cx.game.action;

/**
 * attacked的包装类，包含了一些比赛规则、代理模式等
 * @author jiuhuan
 *
 */
public class AttackedDecorator extends ActionDecorator implements IAttacked {

	private IAttacked attacked = null;
	
	public AttackedDecorator(IAttacked attacked) {
		// TODO Auto-generated constructor stub
		super(attacked);
		this.attacked = attacked;
		/*this.attacked.addIntercepter(new Intercepter() {
			
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
			public Integer getLevel() {
				// TODO Auto-generated method stub
				return IIntercepter.Level_Rule;
			}
		});*/
	}

	@Override
	public Boolean getAttackBack() {
		// TODO Auto-generated method stub
		return attacked.getAttackBack();
	}

	@Override
	public void setAttackBack(Boolean attackBack) {
		// TODO Auto-generated method stub
		attacked.setAttackBack(attackBack);
	}
}
