package org.cx.game.command.expression;

import java.util.HashMap;
import java.util.Map;

import org.cx.game.command.CommandBuffer;
import org.cx.game.core.AbstractPlayer;
import org.cx.game.corps.AbstractCorps;
import org.cx.game.magic.skill.AbstractSkill;
import org.cx.game.magic.trick.ITrick;
import org.cx.game.widget.AbstractOption;
import org.cx.game.widget.Cemetery;
import org.cx.game.widget.AbstractGround;
import org.cx.game.widget.AbstractPlace;
import org.cx.game.widget.TrickList;
import org.cx.game.widget.building.AbstractBuilding;

/**
 * 参数表达式缓存，与命令缓存（CommandBuffer）进行了区分
 * @author chenxian
 *
 */
public class ParameterExpressionBuffer {

	private Map<String,Object> bufferMap = new HashMap<String,Object>();
	
	private AbstractPlayer player;
	
	public ParameterExpressionBuffer(AbstractPlayer player) {
		// TODO Auto-generated constructor stub
		this.player = player;
	}
	
	/**
	 * 
	 * @return 当前具有控制权的玩家
	 */
	public AbstractPlayer getPlayer(){
		if(null==bufferMap.get(CommandBuffer.PLAYER))
			return player;
		else
			return (AbstractPlayer) bufferMap.get(CommandBuffer.PLAYER);
	}
	
	public AbstractGround getGround(){
		return (AbstractGround) bufferMap.get(CommandBuffer.GROUND);
	}
	
	public AbstractPlace getPlace(){
		return (AbstractPlace) bufferMap.get(CommandBuffer.PLACE);
	}
	
	public AbstractBuilding getBuilding(){
		return (AbstractBuilding) bufferMap.get(CommandBuffer.BUILDING);
	}
	
	public AbstractOption getOption(){
		return (AbstractOption) bufferMap.get(CommandBuffer.OPTION);
	}
	
	public AbstractCorps getCorps(){
		return (AbstractCorps)bufferMap.get(CommandBuffer.CORPS);
	}
	
	public AbstractSkill getSkill(){
		return (AbstractSkill) bufferMap.get(CommandBuffer.SKILL);
	}
	
	public void clear(){
		bufferMap.clear();
	}
	
	public void setPlayer(AbstractPlayer player){
		if(null!=player){
			bufferMap.remove(CommandBuffer.GROUND);
			bufferMap.remove(CommandBuffer.PLACE);
			bufferMap.remove(CommandBuffer.CORPS);
			bufferMap.remove(CommandBuffer.SKILL);
			
			bufferMap.put(CommandBuffer.PLAYER, player);
		}
	}
	
	public void setGround(AbstractGround ground){
		if(null!=ground){
			player = getPlayer();
			
			setPlayer(player);
			
			bufferMap.put(CommandBuffer.GROUND, ground);
		}
	}
	
	public void setPlace(AbstractPlace place){
		if(null!=place){
			setGround(place.getOwner());
			
			bufferMap.put(CommandBuffer.PLACE, place);
		}
	}
	
	public void setBuilding(AbstractBuilding building){
		if(null!=building){
			setPlace(building.getPlace());
			
			bufferMap.put(CommandBuffer.BUILDING, building);
		}
	}
	
	public void setOption(AbstractOption option){
		if(null!=option){
			if (option.getOwner() instanceof AbstractBuilding) {
				AbstractBuilding building = (AbstractBuilding) option.getOwner();
				setBuilding(building);
			}
			
			if (option.getOwner() instanceof AbstractCorps) {
				AbstractCorps corps = (AbstractCorps) option.getOwner();
				setCorps(corps);
			}
			
			bufferMap.put(CommandBuffer.OPTION, option);
		}
	}
	
	public void setCorps(AbstractCorps corps){
		if(null!=corps){
			setGround(corps.getGround());
			
			AbstractGround ground = corps.getGround();
			
			AbstractPlace place = ground.getPlace(ground.getPosition(corps));
			setPlace(place);
			
			bufferMap.put(CommandBuffer.CORPS, corps);
		}		
	}
	
	public void setSkill(AbstractSkill skill){
		if(null!=skill){
			setCorps(skill.getOwner());
			
			bufferMap.put(CommandBuffer.SKILL, skill);
		}
	}
}
