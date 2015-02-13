package org.cx.game.widget;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.cx.game.card.ICard;
import org.cx.game.card.LifeCard;
import org.cx.game.card.skill.IActiveSkill;
import org.cx.game.card.skill.ISkill;
import org.cx.game.core.IPlayer;
import org.cx.game.out.JsonOut;

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
				Integer curPos = Integer.valueOf(""+i+Ground.space+j);
				IPlace place = new Place(this, curPos);
				ground.addPlace(place);
			}
		}
		
		/*
		 * getPlace 需要初始化 Place
		 */
		for(ICamp camp : ground.getCampList())
			camp.setOwner(getPlace(camp.getPosition()));
		
		for(IStrongHold strongHold : ground.getStrongHoldList())
			strongHold.setGround(this);
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
	public Integer distance(Integer start, Integer stop) {
		// TODO Auto-generated method stub
		return ground.distance(start, stop);
	}

	@Override
	public IPlace getPlace(Integer position) {
		// TODO Auto-generated method stub
		return ground.getPlace(position);
	}

	@Override
	public List<Integer> areaForDistance(Integer position, Integer step,
			Integer type) {
		// TODO Auto-generated method stub
		return ground.areaForDistance(position, step, type);
	}

	@Override
	public void addCamp(ICamp camp) {
		// TODO Auto-generated method stub
		ground.addCamp(camp);
	}

	@Override
	public List<Integer> getCampPosition(IPlayer player) {
		// TODO Auto-generated method stub
		return ground.getCampPosition(player);
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
	public void move(LifeCard life, Integer position, Integer type) {
		// TODO Auto-generated method stub
		ground.move(life, position,type);
	}

	@Override
	public Integer easyDistance(Integer start, Integer stop) {
		// TODO Auto-generated method stub
		return ground.easyDistance(start, stop);
	}

	@Override
	public List<Integer> easyAreaForDistance(Integer position, Integer step,
			Integer type) {
		// TODO Auto-generated method stub
		return ground.easyAreaForDistance(position, step, type);
	}

	@Override
	public List<Integer> route(Integer start, Integer stop) {
		// TODO Auto-generated method stub
		return ground.route(start, stop);
	}

	@Override
	public List<Integer> easyRoute(Integer start, Integer stop) {
		// TODO Auto-generated method stub
		return ground.easyRoute(start, stop);
	}

	@Override
	public List<Integer> easyAreaForDistanceDiagonal(Integer position,
			Integer step, Integer type) {
		// TODO Auto-generated method stub
		return ground.easyAreaForDistanceDiagonal(position, step, type);
	}

	@Override
	public Integer easyDistanceDiagonal(Integer start, Integer stop) {
		// TODO Auto-generated method stub
		return ground.easyDistanceDiagonal(start, stop);
	}

	@Override
	public List<Integer> arc(Integer attack, Integer defend,
			Integer range) {
		// TODO Auto-generated method stub
		return ground.arc(attack, defend, range);
	}
	
	@Override
	public Integer queryDirection(Integer stand, Integer target) {
		// TODO Auto-generated method stub
		return ground.queryDirection(stand, target);
	}

	@Override
	public List<Integer> twoFlanks(Integer stand, Integer target) {
		// TODO Auto-generated method stub
		return ground.twoFlanks(stand, target);
	}

	@Override
	public List<Integer> line(Integer stand, Integer orientation, Integer range) {
		// TODO Auto-generated method stub
		return ground.line(stand, orientation, range);
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
	public void setPlayerToCamp(Integer campIndex, IPlayer player) {
		// TODO Auto-generated method stub
		ground.setPlayerToCamp(campIndex, player);
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
	public Integer easyOuDistance(Integer start, Integer stop) {
		// TODO Auto-generated method stub
		return ground.easyOuDistance(start, stop);
	}

	@Override
	public List<ICamp> getCampList() {
		// TODO Auto-generated method stub
		return ground.getCampList();
	}
	
	@Override
	public Integer getRandomEntry(IPlayer player) {
		// TODO Auto-generated method stub
		return ground.getRandomEntry(player);
	}
}
