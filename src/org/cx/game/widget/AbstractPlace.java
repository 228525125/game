package org.cx.game.widget;

import org.cx.game.action.ActionProxyHelper;
import org.cx.game.action.IAction;
import org.cx.game.corps.AbstractCorps;
import org.cx.game.exception.RuleValidatorException;
import org.cx.game.magic.trick.ITrick;
import org.cx.game.widget.building.IBuilding;
import org.cx.game.widget.treasure.ITreasure;

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
	private AbstractCorps corps = null;
	private Integer position = 0;
	private IGround ground = null;
	private IBuilding building  = null;
	private Boolean disable = false;
	private Boolean empty = true;
	private Integer landform = AbstractPlace.Landform_Sward;
	private ITreasure treasure = null;
	
	public AbstractPlace(IGround ground,Integer position) {
		// TODO Auto-generated constructor stub		
		this.ground = ground;
		this.position = position;
	}
	
	public IGround getOwner(){
		return ground;
	}

	public AbstractCorps getCorps() {
		// TODO Auto-generated method stub
		return corps;
	}
	
	protected void setCorps(AbstractCorps corps){
		this.corps = corps;
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

	void in(AbstractCorps corps) throws RuleValidatorException {
		// TODO Auto-generated method stub
		IAction action = new ActionProxyHelper(getPlaceInAction());
		action.action(corps);
	}
	
	void inCemetery(AbstractCorps corps) {
		// TODO Auto-generated method stub
		getCemetery().add(corps);
		
		corps.setPosition(getPosition());
	}
	
	void inTrickList(ITrick trick) {
		// TODO Auto-generated method stub
		getTrickList().add(trick);
	}
	
	AbstractCorps out() throws RuleValidatorException {
		// TODO Auto-generated method stub
		AbstractCorps corps = getCorps();
		setCorps(null);
		setEmpty(true);
		corps.setPosition(null);
		
		return corps;
	}
	
	void outCemetery(AbstractCorps corps) {
		// TODO Auto-generated method stub
		getCemetery().remove(corps);
		
		corps.setPosition(null);
	}
	
	void outTrickList(ITrick trick) {
		// TODO Auto-generated method stub
		getTrickList().remove(trick);
	}

	/**
	 * 是否可用，有一些因为地形原因而导致不可用(暂时没用)
	 * @return
	 
	public Boolean getDisable() {
		// TODO Auto-generated method stub
		return this.disable;
	}*/
	
	/**
	 * 位置上是否为空
	 * @return
	 */
	public Boolean getEmpty() {
		// TODO Auto-generated method stub
		return this.empty;
	}
	
	void setEmpty(Boolean empty) {
		// TODO Auto-generated method stub
		this.empty = empty;
	}

	void setDisable(Boolean disable) {
		// TODO Auto-generated method stub
		this.disable = disable;
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
	public IBuilding getBuilding() {
		// TODO Auto-generated method stub
		return this.building;
	}

	void setBuilding(IBuilding building) {
		// TODO Auto-generated method stub
		this.building = building;
		building.setPosition(position);
	}
	
	public ITreasure getTreasure() {
		// TODO Auto-generated method stub
		return this.treasure;
	}
	
	/**
	 * 放置物品
	 */
	void setTreasure(ITreasure treasure) {
		// TODO Auto-generated method stub
		if(null!=treasure)
			treasure.setPosition(position);
		this.treasure = treasure;
	}
}
