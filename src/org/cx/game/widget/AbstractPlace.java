package org.cx.game.widget;

import java.util.ArrayList;
import java.util.List;

import org.cx.game.action.ActionProxyHelper;
import org.cx.game.action.IAction;
import org.cx.game.corps.AbstractCorps;
import org.cx.game.exception.RuleValidatorException;
import org.cx.game.magic.trick.ITrick;
import org.cx.game.widget.building.AbstractBuilding;
import org.cx.game.widget.treasure.Treasure;

public abstract class AbstractPlace {

	/**
	 * 草地
	 */
	public static final Integer Landform_Sward = 401;
	
	/**
	 * 丘林
	 */
	public static final Integer Landform_Massif = 402;
	
	/**
	 * 森林
	 */
	public static final Integer Landform_Forest = 403;
	
	/**
	 * 河
	 */
	public static final Integer Landform_River = 404;
	
	/**
	 * 山
	 */
	public static final Integer Landform_Hill = 405;
	
	/**
	 * 沼泽
	 */
	public static final Integer Landform_marsh = 406;
	
	private TrickList trickList = new TrickList(this);
	private Cemetery cemetery = new Cemetery(this);
	private Integer position = 0;
	private AbstractGround ground = null;
	private List<AbstractCorps> corpsList = new ArrayList<AbstractCorps>();
	private AbstractBuilding building  = null;
	private Integer landform = AbstractPlace.Landform_Sward;
	private Treasure treasure = null;
	
	public AbstractPlace(AbstractGround ground,Integer position) {
		// TODO Auto-generated constructor stub		
		this.ground = ground;
		this.position = position;
	}
	
	public AbstractGround getOwner(){
		return ground;
	}

	/**
	 * 非隐藏Corps
	 * @return
	 */
	public abstract AbstractCorps getCorps();
	
	public List<AbstractCorps> getCorpsList() {
		List<AbstractCorps> retList = new ArrayList<AbstractCorps>();
		retList.addAll(this.corpsList);
		return retList;
	}
	
	public Boolean contains(AbstractCorps corps) {
		return this.corpsList.contains(corps);
	}
	
	protected void addCorps(AbstractCorps corps) {
		this.corpsList.add(corps);
	}

	/**
	 * 坐标系位置
	 * @return
	 */
	public Integer getPosition() {
		// TODO Auto-generated method stub
		return position;
	}

	public TrickList getTrickList() {
		// TODO Auto-generated method stub
		return trickList;
	}
	
	public Cemetery getCemetery() {
		// TODO Auto-generated method stub
		return cemetery;
	}
	
	public abstract IAction getPlaceInAction();

	void in(AbstractCorps corps) {
		// TODO Auto-generated method stub
		IAction action = new ActionProxyHelper(getPlaceInAction());
		action.action(corps);
	}
	
	void inCemetery(AbstractCorps corps) {
		// TODO Auto-generated method stub
		this.cemetery.add(corps);
	}
	
	void inTrickList(ITrick trick) {
		// TODO Auto-generated method stub
		this.trickList.add(trick);
	}
	
	Boolean out(AbstractCorps corps) {
		// TODO Auto-generated method stub
		Boolean ret = this.corpsList.remove(corps);
		
		return ret;
	}
	
	void outCemetery(AbstractCorps corps) {
		// TODO Auto-generated method stub
		this.cemetery.remove(corps);
	}
	
	void outTrickList(ITrick trick) {
		// TODO Auto-generated method stub
		this.trickList.remove(trick);
	}
	
	/**
	 * 位置上是否为空
	 * @return
	 */
	public Boolean isEmpty() {
		// TODO Auto-generated method stub
		return null==getCorps();
	}
	
	public Integer getLandform() {
		// TODO Auto-generated method stub
		return this.landform;
	}
	
	void setLandform(Integer langform) {
		// TODO Auto-generated method stub
		this.landform = langform;
	}

	/**
	 * 建筑物
	 * @return 
	 */
	public AbstractBuilding getBuilding() {
		// TODO Auto-generated method stub
		return this.building;
	}

	void setBuilding(AbstractBuilding building) {
		// TODO Auto-generated method stub
		this.building = building;
		building.setPlace(this);
	}
	
	public Treasure getTreasure() {
		// TODO Auto-generated method stub
		return this.treasure;
	}
	
	/**
	 * 放置物品
	 */
	void setTreasure(Treasure treasure) {
		// TODO Auto-generated method stub
		if(null!=treasure)
			treasure.setPosition(position);
		
		this.treasure = treasure;
	}
}
