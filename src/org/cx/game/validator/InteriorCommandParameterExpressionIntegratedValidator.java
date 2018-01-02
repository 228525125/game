package org.cx.game.validator;

import org.cx.game.card.ICard;
import org.cx.game.card.LifeCard;
import org.cx.game.card.skill.IActiveSkill;
import org.cx.game.card.skill.ISkill;
import org.cx.game.card.trick.ITrick;
import org.cx.game.command.CommandBuffer;
import org.cx.game.command.expression.Calculator;
import org.cx.game.command.expression.ParameterExpressionBuffer;
import org.cx.game.core.IPlayer;
import org.cx.game.tools.I18n;
import org.cx.game.tools.Util;
import org.cx.game.widget.ICemetery;
import org.cx.game.widget.IGround;
import org.cx.game.widget.IPlace;
import org.cx.game.widget.ITrickList;
import org.cx.game.widget.building.IBuilding;
import org.cx.game.widget.building.IOption;
import org.cx.game.widget.building.BuildingTown;

/**
 * 验证参数是否能够通过buffer进行完整的解析
 * @author chenxian
 *
 */
public class InteriorCommandParameterExpressionIntegratedValidator extends InteriorCommandParameterExpressionFormatValidator {
	
	private IPlayer player = null;
	
	public InteriorCommandParameterExpressionIntegratedValidator(String parameter, IPlayer player, ParameterExpressionBuffer buffer) {
		// TODO Auto-generated constructor stub
		super(parameter, buffer);
		this.player = player;
	}
	
	private Object parameterObject = null;
	
	@Override
	public Boolean validate() {
		// TODO Auto-generated method stub
		Boolean ret = super.validate();
		
		if(ret){
			String [] ps = getParameter().split(Calculator.SPACE);
			for(String param : ps){                            //所有的位置信息都这样表示：类型+位置 = 字母+数字
				String item = Util.filterAlphabet(param);
				String position = Util.filterNumber(param);    //如果没有找到，返回""
				
				if(CommandBuffer.OWN.equals(item)){
					parameterObject = player;
					getBuffer().setPlayer(player);
				}
				
				if(CommandBuffer.GROUND.equals(item)){
					if(null==getBuffer().getPlayer()){
						addMessage(I18n.getMessage(InteriorCommandParameterExpressionIntegratedValidator.class.getName()));
						ret = false;
						break;
					}else{
						IGround ground = getBuffer().getPlayer().getContext().getGround();
						parameterObject = ground;
						getBuffer().setContainer(ground);
					}
				}
				
				if(CommandBuffer.PLACE.equals(item)){
					if(null==getBuffer().getGround()){
						addMessage(I18n.getMessage(InteriorCommandParameterExpressionIntegratedValidator.class.getName()));
						ret = false;
						break;
					}else{
						IPlace place = getBuffer().getGround().getPlace(Integer.valueOf(position));
						parameterObject = place;
						getBuffer().setPlace(place);
					}
				}
				
				if(CommandBuffer.BUILDING.equals(item)){
					if(null==getBuffer().getPlace()){
						addMessage(I18n.getMessage(InteriorCommandParameterExpressionIntegratedValidator.class.getName()));
						ret = false;
						break;
					}else{
						if("".equals(position)){              //position=""表示只缓存外部建筑
							IBuilding building = getBuffer().getPlace().getBuilding();
							parameterObject = building;
							getBuffer().setBuilding(building);
						}else{                                //否则缓存内部建筑
							if(null!=getBuffer().getBuilding() && getBuffer().getBuilding() instanceof BuildingTown){
								BuildingTown town = (BuildingTown) getBuffer().getBuilding();
								IBuilding building = town.getBuilding(Integer.valueOf(position));
								parameterObject = building;
								getBuffer().setBuilding(building);
							}else{
								addMessage(I18n.getMessage(InteriorCommandParameterExpressionIntegratedValidator.class.getName()));
								ret = false;
								break;
							}
						}
					}
				}
				
				if(CommandBuffer.OPTION.equals(item)){
					if(null==getBuffer().getBuilding()){
						addMessage(I18n.getMessage(InteriorCommandParameterExpressionIntegratedValidator.class.getName()));
						ret = false;
						break;
					}else{
						IBuilding building = getBuffer().getBuilding();
						IOption option = building.getOption(Integer.valueOf(position));
						parameterObject = option;
						getBuffer().setOption(option);
					}
				}
				
				if(CommandBuffer.CEMETERY.equals(item)){
					if(null==getBuffer().getPlace()){
						addMessage(I18n.getMessage(InteriorCommandParameterExpressionIntegratedValidator.class.getName()));
						ret = false;
						break;
					}else{
						ICemetery cemetery = getBuffer().getPlace().getCemetery();
						parameterObject = cemetery;
						getBuffer().setCemetery(cemetery);
					}
				}
				
				if(CommandBuffer.TRICKLIST.equals(item)){
					if(null==getBuffer().getPlace()){
						addMessage(I18n.getMessage(InteriorCommandParameterExpressionIntegratedValidator.class.getName()));
						ret = false;
						break;
					}else{
						ITrickList trick = getBuffer().getPlace().getTrickList();
						parameterObject = trick;
						getBuffer().setTrickList(trick);
					}
				}
				
				if(CommandBuffer.CARD.equals(item)){
					if(null==getBuffer().getContainer()){
						addMessage(I18n.getMessage(InteriorCommandParameterExpressionIntegratedValidator.class.getName()));
						ret = false;
						break;
					}else{
						if(null!=getBuffer().getGround()){
							IPlace place = getBuffer().getPlace();
							if(null==place){
								addMessage(I18n.getMessage(InteriorCommandParameterExpressionIntegratedValidator.class.getName()));
								ret = false;
								break;
							}
							LifeCard life = null;
							if(null!=place){
								if(null!=getBuffer().getCemetery())
									life = getBuffer().getCemetery().getLife(Integer.valueOf(position));
								else
									life = place.getLife();
							}
							parameterObject = life;
							getBuffer().setCard(life);
						}
					}
				}
				
				if(CommandBuffer.SKILL.equals(item)){
					if(!(getBuffer().getCard() instanceof LifeCard)){
						addMessage(I18n.getMessage(InteriorCommandParameterExpressionIntegratedValidator.class.getName()));
						ret = false;
						break;
					}else{
						LifeCard life = (LifeCard) getBuffer().getCard();
						ISkill skill = life.getSkill(Integer.valueOf(position));
						parameterObject = skill;
						getBuffer().setSkill(skill);
					}
				}
				
				if(CommandBuffer.TRICK.equals(item)){
					if(null==getBuffer().getTrickList()){
						addMessage(I18n.getMessage(InteriorCommandParameterExpressionIntegratedValidator.class.getName()));
						ret = false;
						break;
					}else{
						ITrick trick = getBuffer().getTrickList().getTrick(Integer.valueOf(position));
						parameterObject = trick;
						getBuffer().setTrick(trick);
					}
				}
			}
		}
		
		return ret;
	}

	protected Object getParameterObject() {
		return parameterObject;
	}
	
}
