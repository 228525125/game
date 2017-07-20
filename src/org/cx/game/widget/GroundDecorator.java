package org.cx.game.widget;

import java.util.List;
import java.util.Map;

import org.cx.game.card.ICard;
import org.cx.game.card.LifeCard;
import org.cx.game.card.MagicCard;
import org.cx.game.card.skill.ISkill;
import org.cx.game.core.IPlayer;
import org.cx.game.widget.building.IBuilding;
import org.cx.game.widget.building.IOption;

public class GroundDecorator extends ContainerDecorator implements IGround {
	
	private IGround ground;
	
	public GroundDecorator(IGround ground) {
		// TODO Auto-generated constructor stub
		super(ground);
		this.ground = ground;
		
		/*
		 * new Place 需要用到Decorator对象的this
		 */
		for(int i=1;i<ground.getXBorder()+1;i++){
			for(int j=1;j<ground.getYBorder()+1;j++){
				Integer curPos = Integer.valueOf(""+i+IGround.space+j);
				IPlace place = new Place(this, curPos);
				Integer landform = ground.getLandformMap().get(curPos);
				place.setLandform(null!=landform ? landform : IPlace.Landform_Sward);
				ground.addPlace(new PlaceDecorator(place));
				
				getEmptyList().add(curPos);      //初始化空位置
			}
		}
		
		/*
		 * getPlace 需要初始化 Place
		 */
		for(IBuilding building : ground.getBuildingList()){
			IPlace place = getPlace(building.getPosition());
			place.setBuilding(building);
			
			for(IOption option : building.getOptions()){
				option.setOwner(building);
			}
		}
		
		for(IStrongHold strongHold : ground.getStrongHoldList())
			strongHold.setGround(this);
		
		/*
		 * 设置地形
		 */
		for(Integer disable : ground.getDisableList()){
			ground.getPlace(disable).setDisable(true);
		}
	}
	
	@Override
	public LifeCard getCard(Integer position) {
		// TODO Auto-generated method stub
		return ground.getCard(position);
	}
	
	@Override
	public void add(Integer position, ICard card) {
		// TODO Auto-generated method stub
		super.add(position, card);
	}
	
	@Override
	public Integer getSize() {
		// TODO Auto-generated method stub
		return ground.getSize();
	}


	@Override
	public IPlace getPlace(Integer position) {
		// TODO Auto-generated method stub
		return ground.getPlace(position);
	}

	@Override
	public List<Integer> areaForDistance(Integer position, Integer step,
			Integer type, Integer moveType) {
		// TODO Auto-generated method stub
		return ground.areaForDistance(position, step, type, moveType);
	}

	@Override
	public void addBuilding(IBuilding building) {
		// TODO Auto-generated method stub
		ground.addBuilding(building);
	}

	@Override
	public List<Integer> getBuildingPosition(IPlayer player) {
		// TODO Auto-generated method stub
		return ground.getBuildingPosition(player);
	}

	@Override
	public List<Integer> queryRange(ICard card, String action) {
		// TODO Auto-generated method stub
		return ground.queryRange(card, action);
	}

	@Override
	public List<Integer> queryRange(ISkill skill, String action) {
		// TODO Auto-generated method stub
		return ground.queryRange(skill, action);
	}
	
	@Override
	public List<Integer> queryRange(MagicCard magic, String action) {
		// TODO Auto-generated method stub
		return ground.queryRange(magic, action);
	}
	
	@Override
	public List<Integer> queryRange(IOption option, String action) {
		// TODO Auto-generated method stub
		return ground.queryRange(option, action);
	}
	
	@Override
	public List<Integer> move(LifeCard life, Integer position, Integer type) {
		// TODO Auto-generated method stub
		return ground.move(life, position,type);
	}

	@Override
	public Integer distance(Integer start, Integer stop) {
		// TODO Auto-generated method stub
		return ground.distance(start, stop);
	}

	@Override
	public List<Integer> areaForDistance(Integer position, Integer step,
			Integer type) {
		// TODO Auto-generated method stub
		return ground.areaForDistance(position, step, type);
	}
	
	@Override
	public Integer getDirection(Integer stand, Integer target) {
		// TODO Auto-generated method stub
		return ground.getDirection(stand, target);
	}

	@Override
	public List<Integer> twoFlanks(Integer stand, Integer target) {
		// TODO Auto-generated method stub
		return ground.twoFlanks(stand, target);
	}

	@Override
	public Integer getXBorder() {
		// TODO Auto-generated method stub
		return ground.getXBorder();
	}

	@Override
	public Integer getYBorder() {
		// TODO Auto-generated method stub
		return ground.getYBorder();
	}
	
	@Override
	public List<IStrongHold> getStrongHoldList() {
		// TODO Auto-generated method stub
		return ground.getStrongHoldList();
	}

	@Override
	public void addPlace(IPlace place) {
		// TODO Auto-generated method stub
		ground.addPlace(place);
	}

	@Override
	public List<IBuilding> getBuildingList() {
		// TODO Auto-generated method stub
		return ground.getBuildingList();
	}
	
	@Override
	public List<IBuilding> getBuildingList(IPlayer player) {
		// TODO Auto-generated method stub
		return ground.getBuildingList(player);
	}
	
	@Override
	public List<IBuilding> getBuildingList(IPlayer player, Integer type) {
		// TODO Auto-generated method stub
		return ground.getBuildingList(player, type);
	}
	
	@Override
	public List<Integer> getDisableList() {
		// TODO Auto-generated method stub
		return ground.getDisableList();
	}

	@Override
	public void loadMap() {
		// TODO Auto-generated method stub
		ground.loadMap();
	}

	@Override
	public List<LifeCard> list(IPlayer player) {
		// TODO Auto-generated method stub
		return ground.list(player);
	}
	
	@Override
	public List<LifeCard> list(Integer stand, Integer step, Integer type) {
		// TODO Auto-generated method stub
		return ground.list(stand, step, type);
	}

	@Override
	public Integer getPosition(Integer stand, Integer direction) {
		// TODO Auto-generated method stub
		return ground.getPosition(stand, direction);
	}

	@Override
	public void captureBuilding(Integer townID, IPlayer player) {
		// TODO Auto-generated method stub
		ground.captureBuilding(townID, player);
	}

	@Override
	public void setLandformMap(Map<Integer, Integer> landformMap) {
		// TODO Auto-generated method stub
		ground.setLandformMap(landformMap);
	}

	@Override
	public Map<Integer, Integer> getLandformMap() {
		// TODO Auto-generated method stub
		return ground.getLandformMap();
	}

	@Override
	public List<Integer> getEmptyList() {
		// TODO Auto-generated method stub
		return ground.getEmptyList();
	}

	@Override
	public List<Integer> getBuildingPosition(IPlayer player,
			Integer buildingType) {
		// TODO Auto-generated method stub
		return ground.getBuildingPosition(player, buildingType);
	}

	@Override
	public List<Integer> getBuildingPosition(IPlayer player,
			Integer buildingType, Integer level) {
		// TODO Auto-generated method stub
		return ground.getBuildingPosition(player, buildingType, level);
	}

	@Override
	public Integer distance(Integer start, Integer stop, Integer moveType) {
		// TODO Auto-generated method stub
		return ground.distance(start, stop, moveType);
	}
}
