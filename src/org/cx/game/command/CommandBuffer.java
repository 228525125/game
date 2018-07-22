package org.cx.game.command;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

import org.cx.game.command.expression.Calculator;
import org.cx.game.core.AbstractPlayer;
import org.cx.game.corps.AbstractCorps;
import org.cx.game.magic.skill.AbstractSkill;
import org.cx.game.magic.trick.ITrick;
import org.cx.game.widget.AbstractGround;
import org.cx.game.widget.AbstractOption;
import org.cx.game.widget.Cemetery;
import org.cx.game.widget.AbstractPlace;
import org.cx.game.widget.TrickList;
import org.cx.game.widget.building.AbstractBuilding;

/**
 * CommandBuffer是对命令中选择的对象进行缓存
 * @author chenxian
 *
 */
public class CommandBuffer {
	
	private Queue<Map<String,Object>> bufferQueue = new LinkedList<Map<String,Object>>();

	private Integer BufferQueue_Size = 2;
	
	public static final String PLAYER = "player";
	
	public static final String CEMETERY = "cemetery";
	
	public static final String TRICKLIST = "tricklist";
	
	public static final String GROUND = "ground";
	
	public static final String PLACE = "place"; 
	
	public static final String BUILDING = "building";
	
	public static final String OPTION = "option";
	
	public static final String CORPS = "corps";
	
	public static final String SKILL = "skill";
	
	public static final String TRICK = "trick";
	
	private AbstractPlayer player;	
	
	public CommandBuffer(AbstractPlayer player) {
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
			setPlayer((AbstractPlayer)object, bufferMap);
		}else if(GROUND.equals(item)){
			AbstractGround ground = (AbstractGround) object;
			setGround(ground, bufferMap);
		}else if(PLACE.equals(item)){
			AbstractPlace place = (AbstractPlace) object;
			setPlace(place, bufferMap);
		}else if(BUILDING.equals(item)){
			AbstractBuilding building = (AbstractBuilding) object;
			setBuilding(building, bufferMap);
		}else if(OPTION.equals(item)){
			AbstractOption option = (AbstractOption) object;
			setOption(option, bufferMap);
		}else if(CEMETERY.equals(item)){
			Cemetery cemetery = (Cemetery) object;
			setCemetery(cemetery, bufferMap);
		}else if(TRICKLIST.equals(item)){
			TrickList trickList = (TrickList) object;
			setTrickList(trickList, bufferMap);
		}else if(CORPS.equals(item)){
			AbstractCorps corps = (AbstractCorps) object;
			setCorps(corps, bufferMap);
		}else if(SKILL.equals(item)){
			AbstractSkill skill = (AbstractSkill) object;
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
	public AbstractPlayer getPlayer(){
		if(null==element().get(PLAYER))
			return player;
		else
			return (AbstractPlayer) element().get(PLAYER);
	}
	
	public AbstractGround getGround(){
		return (AbstractGround) element().get(GROUND);
	}
	
	public AbstractPlace getPlace(){
		return (AbstractPlace) element().get(PLACE);
	}
	
	public AbstractBuilding getBuilding(){
		return (AbstractBuilding) element().get(BUILDING);
	}
	
	public AbstractOption getOption(){
		return (AbstractOption) element().get(OPTION);
	}
	
	public Cemetery getCemetery(){
		return (Cemetery) element().get(CEMETERY);
	}
	
	public TrickList getTrickList(){
		return (TrickList) element().get(TRICKLIST);
	}
	
	public AbstractCorps getCorps(){
		return (AbstractCorps) element().get(CORPS);
	}
	
	public AbstractSkill getSkill(){
		return (AbstractSkill) element().get(SKILL);
	}
	
	public ITrick getTrick(){
		return (ITrick) element().get(TRICK);
	}
	
	public void clear(){
		element().clear();
	}
	
	public AbstractCorps lastCorps(){
		return (AbstractCorps) last().get(CORPS);
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
		if (CORPS.equals(item)) {
			return getCorps();
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
		if (item instanceof AbstractPlayer) {
			return PLAYER;
		}
		if (item instanceof AbstractGround) {
			return GROUND;
		}
		if (item instanceof AbstractPlace) {
			return PLACE;
		}
		if (item instanceof AbstractBuilding) {
			return BUILDING;
		}
		if (item instanceof AbstractOption) {
			return OPTION;
		}
		if(item instanceof Cemetery){
			return CEMETERY;
		}
		if(item instanceof TrickList){
			return TRICKLIST;
		}
		if (item instanceof AbstractCorps) {
			return CORPS;
		}
		if (item instanceof AbstractSkill) {
			return SKILL;
		}
		if (item instanceof ITrick) {
			return TRICK;
		}
		return null;
	}
	
	private void setPlayer(AbstractPlayer player, Map<String, Object> bufferMap){
		if(null!=player){
			bufferMap.remove(GROUND);
			bufferMap.remove(PLACE);
			bufferMap.remove(BUILDING);
			bufferMap.remove(OPTION);
			bufferMap.remove(CEMETERY);
			bufferMap.remove(TRICKLIST);
			bufferMap.remove(CORPS);
			bufferMap.remove(SKILL);
			bufferMap.remove(TRICK);
			
			bufferMap.put(PLAYER, player);
		}
	}
	
	private void setGround(AbstractGround ground, Map<String, Object> bufferMap){
		if(null!=ground){
			player = getPlayer();
			setPlayer(player, bufferMap);
			
			bufferMap.put(GROUND, ground);
		}
	}
	
	private void setPlace(AbstractPlace place, Map<String, Object> bufferMap){
		if(null!=place){
			setGround(place.getOwner(), bufferMap);
			
			bufferMap.put(PLACE, place);
		}
	}
	
	private void setBuilding(AbstractBuilding building, Map<String, Object> bufferMap){
		if(null!=building){
			setPlace(building.getPlace(), bufferMap);
			
			bufferMap.put(BUILDING, building);
		}
	}
	
	private void setOption(AbstractOption option, Map<String, Object> bufferMap){
		if(null!=option){
			if (option.getOwner() instanceof AbstractBuilding) {
				AbstractBuilding building = (AbstractBuilding) option.getOwner();
				setBuilding(building, bufferMap);
			}
			
			if (option.getOwner() instanceof AbstractCorps) {
				AbstractCorps corps = (AbstractCorps) option.getOwner();
				setCorps(corps, bufferMap);
			}
			
			bufferMap.put(OPTION, option);
		}
	}
	
	private void setCemetery(Cemetery cemetery, Map<String, Object> bufferMap){
		if(null!=cemetery){
			setPlace(cemetery.getOwner(), bufferMap);
			
			bufferMap.put(CEMETERY, cemetery);
		}
	}
	
	private void setTrickList(TrickList tricklist, Map<String, Object> bufferMap){
		if(null!=tricklist){
			setPlace(tricklist.getOwner(), bufferMap);
			
			bufferMap.put(TRICKLIST, tricklist);
		}
	}
	
	private void setCorps(AbstractCorps corps, Map<String, Object> bufferMap){
		if(null!=corps){
			setGround(corps.getGround(), bufferMap);
			
			AbstractGround ground = corps.getGround();
			
			AbstractPlace place = ground.getPlace(ground.getPosition(corps));
			setPlace(place, bufferMap);
			
			/*if(IDeath.Status_Death.equals(corps.getDeath().getStatus())){
				setCemetery(place.getCemetery(), bufferMap);
			}*/

			bufferMap.put(CORPS, corps);
		}
	}
	
	private void setSkill(AbstractSkill skill, Map<String, Object> bufferMap){
		if(null!=skill){
			setCorps(skill.getOwner(), bufferMap);
			
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
