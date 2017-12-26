package org.cx.game.widget;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observable;

import org.cx.game.card.LifeCard;
import org.cx.game.card.trick.ITrick;
import org.cx.game.intercepter.IIntercepter;
import org.cx.game.intercepter.IntercepterAscComparator;
import org.cx.game.observer.NotifyInfo;
import org.cx.game.out.JsonOut;
import org.cx.game.rule.CallRule;
import org.cx.game.rule.RuleGroupFactory;
import org.cx.game.widget.building.IBuilding;
import org.cx.game.widget.treasure.ITreasure;

public class Place extends Observable implements IPlace {

	private Map<String,List<IIntercepter>> intercepterList = new HashMap<String,List<IIntercepter>>();
	private ITrickList trickList = new TrickList(this);
	private ICemetery cemetery = new Cemetery(this);
	private LifeCard life = null;
	private Integer position = 0;
	private IGround ground = null;
	private IBuilding building  = null;
	private Boolean disable = false;
	private Boolean empty = true;
	private Integer landform = IPlace.Landform_Sward;
	private ITreasure treasure = null;
	
	public Place(IGround ground,Integer position) {
		// TODO Auto-generated constructor stub
		addObserver(JsonOut.getInstance());
		
		this.ground = ground;
		this.position = position;
	}
	
	public IGround getContainer(){
		return ground;
	}

	@Override
	public LifeCard getLife() {
		// TODO Auto-generated method stub
		return life;
	}

	@Override
	public Integer getPosition() {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public ITrickList getTrickList() {
		// TODO Auto-generated method stub
		return trickList;
	}
	
	@Override
	public ICemetery getCemetery() {
		// TODO Auto-generated method stub
		return cemetery;
	}

	@Override
	public void in(LifeCard life) {
		// TODO Auto-generated method stub
		life.setPosition(position);
		this.life = life;
		
		this.empty = false;
		getContainer().getEmptyList().remove(position);
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("player", life.getPlayer());
		map.put("container", ground);
		map.put("card", life);
		map.put("position", position);
		NotifyInfo info = new NotifyInfo(NotifyInfo.Container_Place_In,map);
		notifyObservers(info);    //通知观察者
		
		/*
		 * 生成地形优势
		 */
		Integer profession = life.queryTagForCategory(LifeCard.Profession).get(0);
		life.getAttack().setLandformAtk(life.getAtk()*LandformEffect.getAttackAdvantage(profession, getLandform())/100);
		life.getAttacked().setLandformDef(life.getDef()*LandformEffect.getDefendAdvantage(profession, getLandform())/100);
		
		/*
		 * 添加路径
		 */
		life.getMove().getMovePath().add(getPosition());
	}
	
	@Override
	public LifeCard out() {
		// TODO Auto-generated method stub
		LifeCard life = this.life;
		life.setPosition(null);
		this.life = null;
		
		this.empty = true;
		getContainer().getEmptyList().add(position);
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("player", life.getPlayer());
		map.put("container", ground);
		map.put("card", life);
		map.put("position", position);
		NotifyInfo info = new NotifyInfo(NotifyInfo.Container_Place_Out,map);
		notifyObservers(info);    //通知观察者
		return life;
	}
	
	@Override
	public Integer getStep(IPlace place, Integer moveType) {
		// TODO Auto-generated method stub
		return ground.distance(position, place.getPosition(), moveType);
	}

	@Override
	public Boolean getDisable() {
		// TODO Auto-generated method stub
		return this.disable;
	}
	
	@Override
	public Boolean getEmpty() {
		// TODO Auto-generated method stub
		return this.empty;
	}
	
	@Override
	public void setEmpty(Boolean empty) {
		// TODO Auto-generated method stub
		this.empty = empty;
	}

	@Override
	public void setDisable(Boolean disable) {
		// TODO Auto-generated method stub
		this.disable = disable;
	}
	
	@Override
	public Integer getLandform() {
		// TODO Auto-generated method stub
		return this.landform;
	}
	
	@Override
	public void setLandform(Integer langform) {
		// TODO Auto-generated method stub
		this.landform = langform;
	}
	
	@Override
	public void addIntercepter(IIntercepter intercepter) {
		// TODO Auto-generated method stub
		List<IIntercepter> intercepters = intercepterList.get(intercepter.getIntercepterMethod());
		if(null!=intercepters){
			intercepters.add(intercepter);
		}else{
			intercepters = new ArrayList<IIntercepter>();
			intercepters.add(intercepter);
			intercepterList.put(intercepter.getIntercepterMethod(), intercepters);
		}
	}

	@Override
	public void deleteIntercepter(IIntercepter intercepter) {
		// TODO Auto-generated method stub
		intercepter.delete();
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		intercepterList.clear();
	}

	@Override
	public Map<String,List<IIntercepter>> getIntercepterList() {
		// TODO Auto-generated method stub
		return intercepterList;
	}
	
	@Override
	public void notifyObservers(Object arg0) {
		// TODO Auto-generated method stub
		super.setChanged();
		super.notifyObservers(arg0);
	}

	@Override
	public IBuilding getBuilding() {
		// TODO Auto-generated method stub
		return this.building;
	}

	@Override
	public void setBuilding(IBuilding building) {
		// TODO Auto-generated method stub
		this.building = building;
		building.setPosition(position);
	}
	
	@Override
	public ITreasure getTreasure() {
		// TODO Auto-generated method stub
		return this.treasure;
	}
	
	@Override
	public void setTreasure(ITreasure treasure) {
		// TODO Auto-generated method stub
		if(null!=treasure)
			treasure.setPosition(position);
		this.treasure = treasure;
	}
}
