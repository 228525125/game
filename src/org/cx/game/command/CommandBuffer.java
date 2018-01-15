package org.cx.game.command;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

import org.cx.game.command.expression.Calculator;
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
		}else if(GROUND.equals(item)){
			IGround ground = (IGround) object;
			setGround(ground, bufferMap);
		}else if(PLACE.equals(item)){
			Place place = (Place) object;
			setPlace(place, bufferMap);
		}else if(BUILDING.equals(item)){
			IBuilding building = (IBuilding) object;
			setBuilding(building, bufferMap);
		}else if(OPTION.equals(item)){
			IOption option = (IOption) object;
			setOption(option, bufferMap);
		}else if(CEMETERY.equals(item)){
			Cemetery cemetery = (Cemetery) object;
			setCemetery(cemetery, bufferMap);
		}else if(TRICKLIST.equals(item)){
			TrickList trickList = (TrickList) object;
			setTrickList(trickList, bufferMap);
		}else if(CORPS.equals(item)){
			Corps corps = (Corps) object;
			setCorps(corps, bufferMap);
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
	
	public IGround getGround(){
		return (IGround) element().get(GROUND);
	}
	
	public Place getPlace(){
		return (Place) element().get(PLACE);
	}
	
	public IBuilding getBuilding(){
		return (IBuilding) element().get(BUILDING);
	}
	
	public IOption getOption(){
		return (IOption) element().get(OPTION);
	}
	
	public Cemetery getCemetery(){
		return (Cemetery) element().get(CEMETERY);
	}
	
	public TrickList getTrickList(){
		return (TrickList) element().get(TRICKLIST);
	}
	
	public Corps getCorps(){
		return (Corps) element().get(CORPS);
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
	
	public Corps lastCorps(){
		return (Corps) last().get(CORPS);
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
		if (item instanceof IPlayer) {
			return PLAYER;
		}
		if (item instanceof IGround) {
			return GROUND;
		}
		if (item instanceof Place) {
			return PLACE;
		}
		if (item instanceof IBuilding) {
			return BUILDING;
		}
		if (item instanceof IOption) {
			return OPTION;
		}
		if(item instanceof Cemetery){
			return CEMETERY;
		}
		if(item instanceof TrickList){
			return TRICKLIST;
		}
		if (item instanceof Corps) {
			return CORPS;
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
	
	private void setGround(IGround ground, Map<String, Object> bufferMap){
		if(null!=ground){
			/*IPlayer player = container.getPlayer();;
			if (container instanceof IGround) {
				player = getPlayer();
			}*/
			
			player = getPlayer();
			setPlayer(player, bufferMap);
			
			bufferMap.put(GROUND, ground);
		}
	}
	
	private void setPlace(Place place, Map<String, Object> bufferMap){
		if(null!=place){
			setGround(place.getOwner(), bufferMap);
			
			bufferMap.put(PLACE, place);
		}
	}
	
	private void setBuilding(IBuilding building, Map<String, Object> bufferMap){
		if(null!=building){
			IGround ground = getPlayer().getContext().getGround();
			setPlace(ground.getPlace(building.getPosition()), bufferMap);
			
			bufferMap.put(BUILDING, building);
		}
	}
	
	private void setOption(IOption option, Map<String, Object> bufferMap){
		if(null!=option){
			setBuilding(option.getOwner(), bufferMap);
			
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
	
	private void setCorps(Corps corps, Map<String, Object> bufferMap){
		if(null!=corps){
			setGround(corps.getGround(), bufferMap);
			
			IGround ground = corps.getGround();
			
			Place place = ground.getPlace(ground.getPosition(corps));
			setPlace(place, bufferMap);
			
			/*if(IDeath.Status_Death.equals(corps.getDeath().getStatus())){
				setCemetery(place.getCemetery(), bufferMap);
			}*/

			bufferMap.put(CORPS, corps);
		}
	}
	
	private void setSkill(ISkill skill, Map<String, Object> bufferMap){
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
