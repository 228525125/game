package org.cx.game.command.expression;

import java.util.HashMap;
import java.util.Map;

import org.cx.game.action.IDeath;
import org.cx.game.card.ICard;
import org.cx.game.card.LifeCard;
import org.cx.game.card.skill.ISkill;
import org.cx.game.card.trick.ITrick;
import org.cx.game.command.CommandBuffer;
import org.cx.game.core.IPlayer;
import org.cx.game.widget.ICemetery;
import org.cx.game.widget.IContainer;
import org.cx.game.widget.IGround;
import org.cx.game.widget.IPlace;
import org.cx.game.widget.ITrickList;
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
	
	public IContainer getContainer(){
		return (IContainer) bufferMap.get(CommandBuffer.CONTAINER);
	}
	
	public IGround getGround(){
		IContainer container = getContainer(); 
		if (container instanceof IGround) {
			return (IGround) container;
		}else{
			return null;
		}
	}
	
	public IPlace getPlace(){
		return (IPlace) bufferMap.get(CommandBuffer.PLACE);
	}
	
	public IBuilding getBuilding(){
		return (IBuilding) bufferMap.get(CommandBuffer.BUILDING);
	}
	
	public IOption getOption(){
		return (IOption) bufferMap.get(CommandBuffer.OPTION);
	}
	
	public ICemetery getCemetery(){
		return (ICemetery) bufferMap.get(CommandBuffer.CEMETERY);
	}
	
	public ITrickList getTrickList(){
		return (ITrickList) bufferMap.get(CommandBuffer.TRICKLIST);
	}
	
	public ICard getCard(){
		return (ICard)bufferMap.get(CommandBuffer.CARD);
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
			bufferMap.remove(CommandBuffer.CONTAINER);
			bufferMap.remove(CommandBuffer.PLACE);
			bufferMap.remove(CommandBuffer.CEMETERY);
			bufferMap.remove(CommandBuffer.TRICKLIST);
			bufferMap.remove(CommandBuffer.CARD);
			bufferMap.remove(CommandBuffer.SKILL);
			bufferMap.remove(CommandBuffer.TRICK);
			
			bufferMap.put(CommandBuffer.PLAYER, player);
		}
	}
	
	public void setContainer(IContainer container){
		if(null!=container){
			player = getPlayer();
			
			setPlayer(player);
			
			bufferMap.put(CommandBuffer.CONTAINER, container);
		}
	}
	
	public void setPlace(IPlace place){
		if(null!=place){
			setContainer(place.getContainer());
			
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
	
	public void setCemetery(ICemetery cemetery){
		if(null!=cemetery){
			setPlace(cemetery.getOwner());
			
			bufferMap.put(CommandBuffer.CEMETERY, cemetery);
		}
	}
	
	public void setTrickList(ITrickList tricklist){
		if(null!=tricklist){
			setPlace(tricklist.getOwner());
			
			bufferMap.put(CommandBuffer.TRICKLIST, tricklist);
		}
		
	}
	
	public void setCard(ICard card){
		if(null!=card){
			setContainer(card.getContainer());
			
			if (card.getContainer() instanceof IGround) {
				IGround ground = (IGround) card.getContainer();
				
				IPlace place = ground.getPlace(ground.getPosition(card));
				setPlace(place);
				
				if (card instanceof LifeCard) {
					LifeCard life = (LifeCard) card;
					if(IDeath.Status_Death.equals(life.getDeath().getStatus())){
						setCemetery(place.getCemetery());
					}
				}
			}
			
			bufferMap.put(CommandBuffer.CARD, card);
		}		
	}
	
	public void setSkill(ISkill skill){
		if(null!=skill){
			setCard(skill.getOwner());
			
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
