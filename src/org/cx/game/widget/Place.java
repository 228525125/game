package org.cx.game.widget;

import org.cx.game.action.Action;
import org.cx.game.action.IAction;
import org.cx.game.card.LifeCard;
import org.cx.game.card.trick.ITrick;
import org.cx.game.exception.RuleValidatorException;
import org.cx.game.widget.building.IBuilding;
import org.cx.game.widget.treasure.ITreasure;

public class Place {

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
	private LifeCard life = null;
	private Integer position = 0;
	private IGround ground = null;
	private IBuilding building  = null;
	private Boolean disable = false;
	private Boolean empty = true;
	private Integer landform = Place.Landform_Sward;
	private ITreasure treasure = null;
	
	public Place(IGround ground,Integer position) {
		// TODO Auto-generated constructor stub		
		this.ground = ground;
		this.position = position;
	}
	
	public IGround getOwner(){
		return ground;
	}

	public LifeCard getLife() {
		// TODO Auto-generated method stub
		return life;
	}
	
	private void setLife(LifeCard life){
		this.life = life;
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
	
	private IAction placeInAction = null;
	
	public IAction getPlaceInAction() {
		if(null==this.placeInAction){
			this.placeInAction = new PlaceInAction();
			this.placeInAction.setOwner(this);
		}
		return this.placeInAction;
	}

	void in(LifeCard life) throws RuleValidatorException {
		// TODO Auto-generated method stub
		getPlaceInAction().action(life);
	}
	
	void inCemetery(LifeCard life) {
		// TODO Auto-generated method stub
		getCemetery().add(life);
		
		life.setPosition(getPosition());
	}
	
	void inTrickList(ITrick trick) {
		// TODO Auto-generated method stub
		getTrickList().add(trick);
	}
	
	LifeCard out() throws RuleValidatorException {
		// TODO Auto-generated method stub
		LifeCard life = getLife();
		setLife(null);
		setEmpty(true);
		life.setPosition(null);
		getOwner().getEmptyList().add(position);
		
		return life;
	}
	
	void outCemetery(LifeCard life) {
		// TODO Auto-generated method stub
		getCemetery().remove(life);
		
		life.setPosition(null);
	}
	
	void outTrickList(ITrick trick) {
		// TODO Auto-generated method stub
		getTrickList().remove(trick);
	}

	/**
	 * 是否可用，有一些因为地形原因而导致不可用(暂时没用)
	 * @return
	 */
	public Boolean getDisable() {
		// TODO Auto-generated method stub
		return this.disable;
	}
	
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
	
	public class PlaceInAction extends Action implements IAction {

		@Override
		public void action(Object... objects) throws RuleValidatorException {
			// TODO Auto-generated method stub
			LifeCard life = (LifeCard) objects[0];
			
			life.setPosition(position);
			setLife(life);
			
			setEmpty(false);
			getOwner().getOwner().getEmptyList().remove(position);
			
			/*
			 * 生成地形优势
			 */
			Integer profession = life.queryTagForCategory(LifeCard.Profession).get(0);
			life.getAttack().setLandformAtk(life.getAtk()*LandformEffect.getAttackAdvantage(profession, getLandform())/100);
			life.getAttacked().setLandformDef(life.getDef()*LandformEffect.getDefendAdvantage(profession, getLandform())/100);
			
			/*
			 * 添加路径
			 */
			life.getMove().addMovePath(getPosition());
		}
		
		@Override
		public Place getOwner() {
			// TODO Auto-generated method stub
			return (Place) super.getOwner();
		}
	}
}
