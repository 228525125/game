package org.cx.game.command;

import java.util.HashMap;
import java.util.Map;

import org.cx.game.action.IDeath;
import org.cx.game.card.ICard;
import org.cx.game.card.LifeCard;
import org.cx.game.card.TrickCard;
import org.cx.game.card.skill.IActiveSkill;
import org.cx.game.card.skill.ISkill;
import org.cx.game.card.skill.ITrick;
import org.cx.game.command.expression.Calculator;
import org.cx.game.core.IPlayer;
import org.cx.game.tools.Util;
import org.cx.game.widget.ICardGroup;
import org.cx.game.widget.ICemetery;
import org.cx.game.widget.IContainer;
import org.cx.game.widget.IGround;
import org.cx.game.widget.IPlace;
import org.cx.game.widget.ITrickList;
import org.cx.game.widget.IUseCard;

/**
 * CommandBuffer是对命令中选择的对象进行缓存
 * @author chenxian
 *
 */
public class CommandBuffer {

	private Map<String,Object> bufferMap = new HashMap<String,Object>();
	
	public static final String PLAYER = "player";
	
	public static final String OWN = "own";
	
	public static final String OTHER = "other";
	
	public static final String CARDGROUP = "group";
	
	public static final String CEMETERY = "cemetery";
	
	public static final String TRICKLIST = "tricklist";
	
	public static final String USECARD = "use";
	
	public static final String GROUND = "ground";
	
	public static final String CONTAINER = "container";
	
	public static final String PLACE = "place"; 
	
	public static final String CARD = "card";
	
	public static final String SKILL = "skill";
	
	public static final String TRICK = "trick";
	
	public static final String ACTION = "action";
	
	public static final String DOMAIN = "domain";
	
	private IPlayer player;
	
	public CommandBuffer(IPlayer player) {
		// TODO Auto-generated constructor stub
		this.player = player;
	}
	
	/**
	 * 缓存一个对象
	 * @param object
	 */
	public void set(Object object){
		String item = Calculator.itemToType(objectToItem(object));
		
		if(PLAYER.equals(item)){
			setPlayer((IPlayer)object);
		}else if(CONTAINER.equals(item)){
			IContainer container = (IContainer) object;
			setContainer(container);
		}else if(PLACE.equals(item)){
			IPlace place = (IPlace) object;
			setPlace(place);
		}else if(CEMETERY.equals(item)){
			ICemetery cemetery = (ICemetery) object;
			setCemetery(cemetery);
		}else if(TRICKLIST.equals(item)){
			ITrickList trickList = (ITrickList) object;
			setTrickList(trickList);
		}else if(CARD.equals(item)){
			ICard card = (ICard) object;
			setCard(card);
		}else if(SKILL.equals(item)){
			ISkill skill = (ISkill) object;
			setSkill(skill);
		}else if(TRICK.equals(item)){
			ITrick trick = (ITrick) object;
			setTrick(trick);
		}			
	}
	
	public IPlayer getPlayer(){
		if(null==bufferMap.get(PLAYER))
			return player;
		else
			return (IPlayer) bufferMap.get(PLAYER);
	}
	
	public IContainer getContainer(){
		return (IContainer) bufferMap.get(CONTAINER);
	}
	
	public IGround getGround(){
		IContainer container = getContainer(); 
		if (container instanceof IGround) {
			return (IGround) container;
		}else{
			return null;
		}
	}
	
	public IUseCard getUseCard(){
		IContainer container = getContainer(); 
		if (container instanceof IUseCard) {
			return (IUseCard) container;
		}else{
			return null;
		}
	}
	
	public ICardGroup getCardGroup(){
		IContainer container = getContainer(); 
		if (container instanceof ICardGroup) {
			return (ICardGroup) container;
		}else{
			return null;
		}
	}
	
	public IPlace getPlace(){
		return (IPlace) bufferMap.get(PLACE);
	}
	
	public ICemetery getCemetery(){
		return (ICemetery) bufferMap.get(CEMETERY);
	}
	
	public ITrickList getTrickList(){
		return (ITrickList) bufferMap.get(TRICKLIST);
	}
	
	public ICard getCard(){
		return (ICard)bufferMap.get(CARD);
	}
	
	public ISkill getSkill(){
		return (ISkill) bufferMap.get(SKILL);
	}
	
	public ITrick getTrick(){
		return (ITrick) bufferMap.get(TRICK);
	}
	
	public void clear(){
		bufferMap.clear();
	}
	
	/**
	 * 将object翻译成item
	 * @param item
	 * @return
	 */
	public static String objectToItem(Object item){
		if (item instanceof IPlayer) {
			return PLAYER;
		}
		if (item instanceof IGround) {
			return GROUND;
		}
		if (item instanceof IPlace) {
			return PLACE;
		}
		if(item instanceof ICemetery){
			return CEMETERY;
		}
		if(item instanceof ITrickList){
			return TRICKLIST;
		}
		if (item instanceof IUseCard) {
			return USECARD;
		}
		if (item instanceof ICardGroup) {
			return CARDGROUP;
		}
		if (item instanceof ICard) {
			return CARD;
		}
		if (item instanceof ISkill) {
			return SKILL;
		}
		if (item instanceof ITrick) {
			return TRICK;
		}
		return null;
	}
	
	public void setPlayer(IPlayer player){
		if(null!=player){
			bufferMap.remove(CONTAINER);
			bufferMap.remove(PLACE);
			bufferMap.remove(CEMETERY);
			bufferMap.remove(TRICKLIST);
			bufferMap.remove(CARD);
			bufferMap.remove(SKILL);
			bufferMap.remove(TRICK);
			
			bufferMap.put(PLAYER, player);
		}
	}
	
	public void setContainer(IContainer container){
		if(null!=container){
			IPlayer player = container.getPlayer();;
			if (container instanceof IGround) {
				player = getPlayer();
			}
			setPlayer(player);
			
			bufferMap.put(CONTAINER, container);
		}
	}
	
	public void setPlace(IPlace place){
		if(null!=place){
			setContainer(place.getContainer());
			
			bufferMap.put(PLACE, place);
		}
	}
	
	public void setCemetery(ICemetery cemetery){
		if(null!=cemetery){
			setPlace(cemetery.getOwner());
			
			bufferMap.put(CEMETERY, cemetery);
		}
	}
	
	public void setTrickList(ITrickList tricklist){
		if(null!=tricklist){
			setPlace(tricklist.getOwner());
			
			bufferMap.put(TRICKLIST, tricklist);
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

			bufferMap.put(CARD, card);
		}
	}
	
	public void setSkill(ISkill skill){
		if(null!=skill){
			setCard(skill.getOwner());
			
			bufferMap.put(SKILL, skill);
		}
	}
	
	public void setTrick(ITrick trick){
		if(null!=trick){
			setTrickList(trick.getOwner());
			
			bufferMap.put(TRICK, trick);
		}
	}
}
