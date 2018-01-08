package org.cx.game.command.expression;

import java.util.HashMap;
import java.util.Map;

import org.cx.game.action.IDeath;
import org.cx.game.command.CommandBuffer;
import org.cx.game.core.IPlayer;
import org.cx.game.corps.Corps;
import org.cx.game.magic.skill.ISkill;
import org.cx.game.magic.trick.ITrick;
import org.cx.game.widget.Cemetery;
import org.cx.game.widget.IGround;
import org.cx.game.widget.Place;
import org.cx.game.widget.TrickList;
import org.cx.game.widget.building.IBuilding;
import org.cx.game.widget.building.IOption;

/**
 * 参数表达式缓存，与命令缓存（CommandBuffer）进行了区分
 * @author chenxian
 *
 */
public class ParameterExpressionBuffer {

	private Map<String,Object> bufferMap = new HashMap<String,Object>();
	
	private IPlayer player;
	
	public ParameterExpressionBuffer(IPlayer player) {
		// TODO Auto-generated constructor stub
		this.player = player;
	}
	
	/**
	 * 
	 * @return 当前具有控制权的玩家
	 */
	public IPlayer getPlayer(){
		if(null==bufferMap.get(CommandBuffer.PLAYER))
			return player;
		else
			return (IPlayer) bufferMap.get(CommandBuffer.PLAYER);
	}
	
	public IGround getGround(){
		return (IGround) bufferMap.get(CommandBuffer.GROUND);
	}
	
	public Place getPlace(){
		return (Place) bufferMap.get(CommandBuffer.PLACE);
	}
	
	public IBuilding getBuilding(){
		return (IBuilding) bufferMap.get(CommandBuffer.BUILDING);
	}
	
	public IOption getOption(){
		return (IOption) bufferMap.get(CommandBuffer.OPTION);
	}
	
	public Cemetery getCemetery(){
		return (Cemetery) bufferMap.get(CommandBuffer.CEMETERY);
	}
	
	public TrickList getTrickList(){
		return (TrickList) bufferMap.get(CommandBuffer.TRICKLIST);
	}
	
	public Corps getCorps(){
		return (Corps)bufferMap.get(CommandBuffer.CORPS);
	}
	
	public ISkill getSkill(){
		return (ISkill) bufferMap.get(CommandBuffer.SKILL);
	}
	
	public ITrick getTrick(){
		return (ITrick) bufferMap.get(CommandBuffer.TRICK);
	}
	
	public void clear(){
		bufferMap.clear();
	}
	
	public void setPlayer(IPlayer player){
		if(null!=player){
			bufferMap.remove(CommandBuffer.GROUND);
			bufferMap.remove(CommandBuffer.PLACE);
			bufferMap.remove(CommandBuffer.CEMETERY);
			bufferMap.remove(CommandBuffer.TRICKLIST);
			bufferMap.remove(CommandBuffer.CORPS);
			bufferMap.remove(CommandBuffer.SKILL);
			bufferMap.remove(CommandBuffer.TRICK);
			
			bufferMap.put(CommandBuffer.PLAYER, player);
		}
	}
	
	public void setGround(IGround ground){
		if(null!=ground){
			player = getPlayer();
			
			setPlayer(player);
			
			bufferMap.put(CommandBuffer.GROUND, ground);
		}
	}
	
	public void setPlace(Place place){
		if(null!=place){
			setGround(place.getOwner());
			
			bufferMap.put(CommandBuffer.PLACE, place);
		}
	}
	
	public void setBuilding(IBuilding building){
		if(null!=building){
			IGround ground = getPlayer().getContext().getGround();
			setPlace(ground.getPlace(building.getPosition()));
			
			bufferMap.put(CommandBuffer.BUILDING, building);
		}
	}
	
	public void setOption(IOption option){
		if(null!=option){
			setBuilding(option.getOwner());
			
			bufferMap.put(CommandBuffer.OPTION, option);
		}
	}
	
	public void setCemetery(Cemetery cemetery){
		if(null!=cemetery){
			setPlace(cemetery.getOwner());
			
			bufferMap.put(CommandBuffer.CEMETERY, cemetery);
		}
	}
	
	public void setTrickList(TrickList tricklist){
		if(null!=tricklist){
			setPlace(tricklist.getOwner());
			
			bufferMap.put(CommandBuffer.TRICKLIST, tricklist);
		}
		
	}
	
	public void setCorps(Corps corps){
		if(null!=corps){
			setGround(corps.getGround());
			
			IGround ground = corps.getGround();
			
			Place place = ground.getPlace(ground.getPosition(corps));
			setPlace(place);
			
			if(IDeath.Status_Death.equals(corps.getDeath().getStatus())){
				setCemetery(place.getCemetery());
			}
			
			bufferMap.put(CommandBuffer.CORPS, corps);
		}		
	}
	
	public void setSkill(ISkill skill){
		if(null!=skill){
			setCorps(skill.getOwner());
			
			bufferMap.put(CommandBuffer.SKILL, skill);
		}
	}
	
	public void setTrick(ITrick trick){
		if(null!=trick){
			setTrickList(trick.getOwner());
			
			bufferMap.put(CommandBuffer.TRICK, trick);
		}
	}
}
