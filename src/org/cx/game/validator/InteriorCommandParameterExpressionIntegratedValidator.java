package org.cx.game.validator;

import org.cx.game.command.CommandBuffer;
import org.cx.game.command.expression.Calculator;
import org.cx.game.command.expression.ParameterExpressionBuffer;
import org.cx.game.core.AbstractPlayer;
import org.cx.game.corps.AbstractCorps;
import org.cx.game.magic.skill.AbstractSkill;
import org.cx.game.magic.trick.ITrick;
import org.cx.game.tools.I18n;
import org.cx.game.tools.Util;
import org.cx.game.widget.AbstractGround;
import org.cx.game.widget.AbstractOption;
import org.cx.game.widget.Cemetery;
import org.cx.game.widget.AbstractPlace;
import org.cx.game.widget.TrickList;
import org.cx.game.widget.building.AbstractBuilding;

/**
 * 验证参数是否能够通过buffer进行完整的解析
 * @author chenxian
 *
 */
public class InteriorCommandParameterExpressionIntegratedValidator extends InteriorCommandParameterExpressionFormatValidator {
	
	private AbstractPlayer player = null;
	
	public InteriorCommandParameterExpressionIntegratedValidator(String parameter, AbstractPlayer player, ParameterExpressionBuffer buffer) {
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
				
				if(CommandBuffer.PLAYER.equals(item)){
					parameterObject = player;
					getBuffer().setPlayer(player);
				}
				
				if(CommandBuffer.GROUND.equals(item)){
					if(null==getBuffer().getPlayer()){
						addMessage(I18n.getMessage(InteriorCommandParameterExpressionIntegratedValidator.class.getName()));
						ret = false;
						break;
					}else{
						AbstractGround ground = getBuffer().getPlayer().getContext().getGround();
						parameterObject = ground;
						getBuffer().setGround(ground);
					}
				}
				
				if(CommandBuffer.PLACE.equals(item)){
					if(null==getBuffer().getGround()){
						addMessage(I18n.getMessage(InteriorCommandParameterExpressionIntegratedValidator.class.getName()));
						ret = false;
						break;
					}else{
						AbstractPlace place = getBuffer().getGround().getPlace(Integer.valueOf(position));
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
							AbstractBuilding building = getBuffer().getPlace().getBuilding();
							parameterObject = building;
							getBuffer().setBuilding(building);
						}else{                                //否则缓存内部建筑
							if(null!=getBuffer().getBuilding() && !getBuffer().getBuilding().getBuildings().isEmpty()){
								AbstractBuilding building = getBuffer().getBuilding().getBuilding(Integer.valueOf(position));
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
					if(null!=getBuffer().getBuilding()){
						AbstractBuilding building = getBuffer().getBuilding();
						AbstractOption option = building.getOption(Integer.valueOf(position));
						parameterObject = option;
						getBuffer().setOption(option);
					}else if(null!=getBuffer().getCorps()){
						AbstractCorps corps = getBuffer().getCorps();
						AbstractOption option = corps.getOption(Integer.valueOf(position));
						parameterObject = option;
						getBuffer().setOption(option);
					}else{
						addMessage(I18n.getMessage(InteriorCommandParameterExpressionIntegratedValidator.class.getName()));
						ret = false;
						break;
					}
				}
				
				if(CommandBuffer.CEMETERY.equals(item)){
					if(null==getBuffer().getPlace()){
						addMessage(I18n.getMessage(InteriorCommandParameterExpressionIntegratedValidator.class.getName()));
						ret = false;
						break;
					}else{
						Cemetery cemetery = getBuffer().getPlace().getCemetery();
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
						TrickList trick = getBuffer().getPlace().getTrickList();
						parameterObject = trick;
						getBuffer().setTrickList(trick);
					}
				}
				
				if(CommandBuffer.CORPS.equals(item)){
					if(null==getBuffer().getGround()){
						addMessage(I18n.getMessage(InteriorCommandParameterExpressionIntegratedValidator.class.getName()));
						ret = false;
						break;
					}else{
						if(null!=getBuffer().getGround()){
							AbstractPlace place = getBuffer().getPlace();
							if(null==place){
								addMessage(I18n.getMessage(InteriorCommandParameterExpressionIntegratedValidator.class.getName()));
								ret = false;
								break;
							}
							AbstractCorps corps = null;
							if(null!=place){
								if(null!=getBuffer().getCemetery())
									corps = getBuffer().getCemetery().getCorps(Integer.valueOf(position));
								else
									corps = place.getCorps();
							}
							parameterObject = corps;
							getBuffer().setCorps(corps);
						}
					}
				}
				
				if(CommandBuffer.SKILL.equals(item)){
					if(!(getBuffer().getCorps() instanceof AbstractCorps)){
						addMessage(I18n.getMessage(InteriorCommandParameterExpressionIntegratedValidator.class.getName()));
						ret = false;
						break;
					}else{
						AbstractCorps corps = (AbstractCorps) getBuffer().getCorps();
						AbstractSkill skill = corps.getSkill(Integer.valueOf(position));
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
