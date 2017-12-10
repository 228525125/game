package org.cx.game.command;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

import org.cx.game.action.IDeath;
import org.cx.game.card.ICard;
import org.cx.game.card.LifeCard;
import org.cx.game.card.TrickCard;
import org.cx.game.card.skill.IActiveSkill;
import org.cx.game.card.skill.ISkill;
import org.cx.game.card.trick.ITrick;
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
import org.cx.game.widget.building.IBuilding;
import org.cx.game.widget.building.IOption;

/**
 * CommandBuffer是对命令中选择的对象进行缓存
 * @author chenxian
 *
 */
public class CommandBuffer {
	
	private Queue<Map<String,Object>> bufferQueue = new LinkedList<Map<String,Object>>();

	private Integer BufferQueue_Size = 2;
	
	public static final String PLAYER = "player";
	
	public static final String OWN = "own";
	
	public static final String OTHER = "other";
	
	public static final String CEMETERY = "cemetery";
	
	public static final String TRICKLIST = "tricklist";
	
	public static final String USECARD = "use";
	
	public static final String GROUND = "ground";
	
	public static final String CONTAINER = "container";
	
	public static final String PLACE = "place"; 
	
	public static final String BUILDING = "building";
	
	public static final String OPTION = "option";
	
	public static final String PROPERTY = "property";
	
	public static final String CARD = "card";
	
	public static final String SKILL = "skill";
	
	public static final String TRICK = "trick";
	
	public static final String ACTION = "action";
	
	public static final String DOMAIN = "domain";
	
	private IPlayer player;	
	
	public CommandBuffer(IPlayer player) {
		// TODO Auto-generated constructor stub
		this.player = player;
		
		for(int i=0;i<BufferQueue_Size;i++){
			Map<String,Object> bufferMap = new HashMap<String,Object>();
			bufferQueue.offer(bufferMap);
		}
	}
	
	/**
	 * 缓存一个对象
	 * @param object
	 */
	public void set(Object object){
		Map<String, Object> bufferMap = new HashMap<String, Object>();
		
		String item = Calculator.itemToType(objectToItem(object));
		
		if(PLAYER.equals(item)){
			setPlayer((IPlayer)object, bufferMap);
		}else if(CONTAINER.equals(item)){
			IContainer container = (IContainer) object;
			setContainer(container, bufferMap);
		}else if(PLACE.equals(item)){
			IPlace place = (IPlace) object;
			setPlace(place, bufferMap);
		}else if(BUILDING.equals(item)){
			IBuilding building = (IBuilding) object;
			setBuilding(building, bufferMap);
		}else if(OPTION.equals(item)){
			IOption option = (IOption) object;
			setOption(option, bufferMap);
		}else if(CEMETERY.equals(item)){
			ICemetery cemetery = (ICemetery) object;
			setCemetery(cemetery, bufferMap);
		}else if(TRICKLIST.equals(item)){
			ITrickList trickList = (ITrickList) object;
			setTrickList(trickList, bufferMap);
		}else if(CARD.equals(item)){
			ICard card = (ICard) object;
			setCard(card, bufferMap);
		}else if(SKILL.equals(item)){
			ISkill skill = (ISkill) object;
			setSkill(skill, bufferMap);
		}else if(TRICK.equals(item)){
			ITrick trick = (ITrick) object;
			setTrick(trick, bufferMap);
		}		
		
		poll();
		
		offer(bufferMap);
	}
	
	/**
	 * 当前具有控制权的玩家
	 * @return
	 */
	public IPlayer getPlayer(){
		if(null==element().get(PLAYER))
			return player;
		else
			return (IPlayer) element().get(PLAYER);
	}
	
	public IContainer getContainer(){
		return (IContainer) element().get(CONTAINER);
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
		return (IPlace) element().get(PLACE);
	}
	
	public IBuilding getBuilding(){
		return (IBuilding) element().get(BUILDING);
	}
	
	public IOption getOption(){
		return (IOption) element().get(OPTION);
	}
	
	public ICemetery getCemetery(){
		return (ICemetery) element().get(CEMETERY);
	}
	
	public ITrickList getTrickList(){
		return (ITrickList) element().get(TRICKLIST);
	}
	
	public ICard getCard(){
		return (ICard) element().get(CARD);
	}
	
	public ISkill getSkill(){
		return (ISkill) element().get(SKILL);
	}
	
	public ITrick getTrick(){
		return (ITrick) element().get(TRICK);
	}
	
	public void clear(){
		element().clear();
	}
	
	public ICard lastCard(){
		return (ICard) last().get(CARD);
	}
	
	public Object get(String item){
		if(PLAYER.equals(item)){
			return getPlayer();
		}
		if (GROUND.equals(item)) {
			return getGround();
		}
		if (PLACE.equals(item)) {
			return getPlace();
		}
		if (BUILDING.equals(item)) {
			return getBuilding();
		}
		if (OPTION.equals(item)) {
			return getOption();
		}
		if(CEMETERY.equals(item)){
			return getCemetery();
		}
		if(TRICKLIST.equals(item)){
			return getTrickList();
		}
		if (USECARD.equals(item)) {
			return getUseCard();
		}
		if (CARD.equals(item)) {
			return getCard();
		}
		if (SKILL.equals(item)) {
			return getSkill();
		}
		if (TRICK.equals(item)) {
			return getTrick();
		}
		return null;
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
		if (item instanceof IBuilding) {
			return BUILDING;
		}
		if (item instanceof IOption) {
			return OPTION;
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
	
	private void setPlayer(IPlayer player, Map<String, Object> bufferMap){
		if(null!=player){
			bufferMap.remove(CONTAINER);
			bufferMap.remove(PLACE);
			bufferMap.remove(BUILDING);
			bufferMap.remove(OPTION);
			bufferMap.remove(CEMETERY);
			bufferMap.remove(TRICKLIST);
			bufferMap.remove(CARD);
			bufferMap.remove(SKILL);
			bufferMap.remove(TRICK);
			
			bufferMap.put(PLAYER, player);
		}
	}
	
	private void setContainer(IContainer container, Map<String, Object> bufferMap){
		if(null!=container){
			/*IPlayer player = container.getPlayer();;
			if (container instanceof IGround) {
				player = getPlayer();
			}*/
			
			player = getPlayer();
			setPlayer(player, bufferMap);
			
			bufferMap.put(CONTAINER, container);
		}
	}
	
	private void setPlace(IPlace place, Map<String, Object> bufferMap){
		if(null!=place){
			setContainer(place.getContainer(), bufferMap);
			
			bufferMap.put(PLACE, place);
		}
	}
	
	private void setBuilding(IBuilding building, Map<String, Object> bufferMap){
		if(null!=building){
			setPlace(building.getPlace(), bufferMap);
			
			bufferMap.put(BUILDING, building);
		}
	}
	
	private void setOption(IOption option, Map<String, Object> bufferMap){
		if(null!=option){
			setBuilding(option.getOwner(), bufferMap);
			
			bufferMap.put(OPTION, option);
		}
	}
	
	private void setCemetery(ICemetery cemetery, Map<String, Object> bufferMap){
		if(null!=cemetery){
			setPlace(cemetery.getOwner(), bufferMap);
			
			bufferMap.put(CEMETERY, cemetery);
		}
	}
	
	private void setTrickList(ITrickList tricklist, Map<String, Object> bufferMap){
		if(null!=tricklist){
			setPlace(tricklist.getOwner(), bufferMap);
			
			bufferMap.put(TRICKLIST, tricklist);
		}
	}
	
	private void setCard(ICard card, Map<String, Object> bufferMap){
		if(null!=card){
			setContainer(card.getContainer(), bufferMap);
			
			if (card.getContainer() instanceof IGround) {
				IGround ground = (IGround) card.getContainer();
				
				IPlace place = ground.getPlace(ground.getPosition(card));
				setPlace(place, bufferMap);
				
				if (card instanceof LifeCard) {
					LifeCard life = (LifeCard) card;
					if(IDeath.Status_Death.equals(life.getDeath().getStatus())){
						setCemetery(place.getCemetery(), bufferMap);
					}
				}
			}

			bufferMap.put(CARD, card);
		}
	}
	
	private void setSkill(ISkill skill, Map<String, Object> bufferMap){
		if(null!=skill){
			setCard(skill.getOwner(), bufferMap);
			
			bufferMap.put(SKILL, skill);
		}
	}
	
	private void setTrick(ITrick trick, Map<String, Object> bufferMap){
		if(null!=trick){
			setTrickList(trick.getOwner(), bufferMap);
			
			bufferMap.put(TRICK, trick);
		}
	}
	
	private Map<String, Object> element(){
		Object [] array = bufferQueue.toArray();
		Object bufferMap = array[BufferQueue_Size-1];
		return (Map<String, Object>) bufferMap;
	}
	
	private Map<String, Object> poll(){
		return bufferQueue.poll();
	}
	
	private void offer(Map<String, Object> bufferMap){
		bufferQueue.offer(bufferMap);
	}
	
	private Map<String, Object> last(){
		Object [] array = bufferQueue.toArray();
		Object bufferMap = array[BufferQueue_Size-2];
		return (Map<String, Object>) bufferMap;
	}
}
