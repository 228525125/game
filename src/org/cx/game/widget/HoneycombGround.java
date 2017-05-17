package org.cx.game.widget;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Observer;
import java.util.Random;
import java.util.Set;
import java.util.Map.Entry;

import org.cx.game.action.IAttack;
import org.cx.game.action.IMove;
import org.cx.game.card.ICard;
import org.cx.game.card.LifeCard;
import org.cx.game.card.MagicCard;
import org.cx.game.card.skill.ActiveSkill;
import org.cx.game.card.skill.ISkill;
import org.cx.game.core.IPlayer;
import org.cx.game.intercepter.IIntercepter;
import org.cx.game.observer.NotifyInfo;
import org.cx.game.out.JsonOut;
import org.cx.game.tools.CellularDistrict;
import org.cx.game.tools.Node;
import org.cx.game.tools.PathFinding;
import org.cx.game.tools.Util;

public class HoneycombGround extends Container implements IGround {

	private Integer xBorder = 0;                                       //边界x轴长度
	private Integer yBorder = 0;                                       //边界y轴长度
	private String imagePath = "";                                      //背景图片
	
	private Map<Integer,IPlace> ground = new HashMap<Integer,IPlace>();
	private List<ITown> townList = new ArrayList<ITown>();              //营地
	private List<Integer> disableList = new ArrayList<Integer>();       //不可用的单元格
	private List<IStrongHold> strongHoldList = new ArrayList<IStrongHold>();           //据点
	private int [][] MAP = null;               //用于查询路线
	private int[] hit = new int []{1};                                  //1表示障碍物
	
	private CellularDistrict cellularDistrict = new CellularDistrict();
	private Map<Integer, Integer> coordinateMap = new HashMap<Integer, Integer>();       //序号与坐标系映射
	private Map<Integer, Integer> serialNumberMap = new HashMap<Integer, Integer>();     //坐标系与序号映射
	
	public HoneycombGround(Integer xBorder, Integer yBorder, String imagePath) {
		// TODO Auto-generated constructor stub
		addObserver(new JsonOut());
		
		this.xBorder = xBorder;
		CellularDistrict.xBorder = xBorder;
		this.yBorder = yBorder;
		CellularDistrict.yBorder = yBorder;
		
		this.imagePath = imagePath;
		
		/*
		 * 初始化寻路算法
		 */
		this.MAP = new int[xBorder+2][yBorder+2];
		for(int i=0;i<xBorder+2;i++)
			for(int j=0;j<yBorder+2;j++)
				MAP[i][j] = 1;
		
		/*
		 * 初始化六边形地图算法
		 */
		cellularDistrict.initCellularDistrict(getMaxSerial(Math.max(xBorder, yBorder)));
		initCoordinateSystem(getCentrePoint(xBorder, yBorder),Math.max(xBorder, yBorder));
		
	}

	public void add(Integer position, ICard card) {
		// TODO 自动生成方法存根
		super.setAction(NotifyInfo.Container_Ground_Add);
		super.add(position, card);
		
		if (card instanceof LifeCard) {
			LifeCard life = (LifeCard) card;
			IPlace place = getPlace(position);
			place.in(life);
		}
	}
	
	@Override
	public Boolean remove(ICard card) {
		// TODO Auto-generated method stub
		setAction(NotifyInfo.Container_Ground_Remove);
		Boolean ret = super.remove(card);
		
		if(ret){
			Integer position = getPosition(card);
			IPlace place = getPlace(position);
			place.out();
		}
		
		return ret;
	}
	
	@Override
	public LifeCard getCard(Integer position) {
		// TODO Auto-generated method stub
		if(null!=ground.get(position))
			return ground.get(position).getLife();
		else
			return null;
	}
	
	@Override
	public Integer getSize() {
		// TODO Auto-generated method stub
		return cardList.size();
	}
	
	@Override
	public IPlace getPlace(Integer position) {
		// TODO Auto-generated method stub
		return ground.get(position);
	}

	public List<ITown> getTownList() {
		return townList;
	}

	public void setTownList(List<ITown> townList) {
		this.townList = townList;
	}

	public List<Integer> getDisableList() {
		return disableList;
	}

	public void setDisableList(List<Integer> disableList) {
		this.disableList = disableList;
	}

	public List<IStrongHold> getStrongHoldList() {
		return strongHoldList;
	}

	public void setStrongHoldList(List<IStrongHold> strongHoldList) {
		this.strongHoldList = strongHoldList;
	}

	@Override
	public List<Integer> getTownPosition(IPlayer player) {
		// TODO Auto-generated method stub
		List<Integer> list = new ArrayList<Integer>();
		for(ITown town : townList){
			if(town.getBuildingPlayer().equals(player))
				list.add(town.getPosition());
		}
		
		return list;
	}
	
	@Override
	public List<Integer> getTownPosition(IPlayer player, Integer level) {
		// TODO Auto-generated method stub
		List<Integer> list = new ArrayList<Integer>();
		for(ITown town : townList){
			if(town.getBuildingPlayer().equals(player) && town.getLevel()>=level)
				list.add(town.getPosition());
		}
		
		return list;
	}
	
	@Override
	public Integer getMainTownPosition(IPlayer player) {
		// TODO Auto-generated method stub
		for(ITown town : townList){
			if(town.getBuildingPlayer().equals(player) && town.isMain())
				return town.getPosition();
		}
		return null;
	}
	
	@Override
	public void addTown(ITown town) {
		// TODO Auto-generated method stub
		townList.add(town);
	}
	
	@Override
	public void setPlayerToTown(Integer townID, IPlayer player) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void addPlace(IPlace place) {
		// TODO Auto-generated method stub
		ground.put(place.getPosition(), place);
	}
	
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return Container.Ground;
	}
	
	public List<Integer> queryRange(ICard card, String action){
		List<Integer> positionList = new ArrayList<Integer>();
		if(NotifyInfo.Command_Query_Attack == action && card instanceof LifeCard){
			LifeCard life = (LifeCard) card;
			Integer range = life.getAttack().getRange();
			/*
			 * 这里要考虑远程单位射程切换为近战时的变化
			 * 当远程单位在战场上时，如果附近有敌方单位，则只能近身攻击
			 */
			if(IAttack.Mode_Far.equals(life.getAttack().getMode())){
				List<Integer> list = easyAreaForDistance(life.getContainerPosition(), 1, IGround.Equal);
				for(Integer position : list){
					LifeCard lifeCard = getCard(position);
					if(null!=lifeCard && !lifeCard.getPlayer().equals(lifeCard.getPlayer())){
						range = 1;
						break;
					}
				}
			}
			
			positionList = easyAreaForDistance(life.getContainerPosition(), range, Contain);  //1：表示范围内的所有单元格，0：表示等于范围的单元格
			positionList.remove(life.getContainerPosition());
		}
		if(NotifyInfo.Command_Query_Call == action && card instanceof LifeCard){
			LifeCard life = (LifeCard) card;
			positionList.addAll(this.getEntryList(life));
		}
		if(NotifyInfo.Command_Query_Move == action && card instanceof LifeCard){
			LifeCard life = (LifeCard) card;
			Integer step = life.getMove().getEnergy()/life.getMove().getConsume();
			switch (life.getMove().getType()) {
			case 0:    //步行
				positionList = areaForDistance(life.getContainerPosition(), step, Contain);
				break;
			case 1:    //飞行
				positionList = easyAreaForDistance(life.getContainerPosition(), step, Contain);
				break;
			case 2:    //瞬移
				positionList = easyAreaForDistance(life.getContainerPosition(), step, Contain);
				break;

			default:
				break;
			}
			positionList.remove(life.getContainerPosition());
		}
		return positionList;
	}
	
	public List<Integer> queryRange(ISkill skill, String action){
		List<Integer> positionList = new ArrayList<Integer>();
		if(NotifyInfo.Command_Query_Conjure == action && skill.getOwner() instanceof LifeCard){
			
			//ActiveSkill的技能范围由自己给出逻辑
			if (skill instanceof ActiveSkill) {
				ActiveSkill as = (ActiveSkill) skill;
				positionList = as.getConjureRange(this);
			}else{
				LifeCard card = (LifeCard) skill.getOwner();
				Integer position = card.getContainerPosition();
				positionList = easyAreaForDistance(position, skill.getRange(), Contain);
			}
		}
		return positionList;
	}
	
	@Override
	public List<Integer> queryRange(MagicCard magic, String action) {
		// TODO Auto-generated method stub
		List<Integer> positionList = new ArrayList<Integer>();
		if(NotifyInfo.Command_Query_Apply == action){
			positionList = magic.getApplyRange(this);
		}
		return positionList;
	}

	public Integer getXBorder() {
		return xBorder;
	}

	public Integer getYBorder() {
		return yBorder;
	}
	
	@Override
	public void loadMap() {
		// TODO Auto-generated method stub
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("xBorder", xBorder);
		map.put("yBorder", yBorder);
		map.put("imagePath", imagePath);
		map.put("townList", townList);
		map.put("disableList", disableList);
		//map.put("strongHoldList", strongHoldList);
		NotifyInfo info = new NotifyInfo(NotifyInfo.Container_Ground_LoadMap,map);
		
		notifyObservers(info);    //通知观察者
	}
	
	/**
	 * 只能查找战场上的生物位置，包含墓地
	 */
	public Integer getPosition(ICard card) {
		// TODO Auto-generated method stub
		LifeCard life = (LifeCard) card;
		for(Entry<Integer, IPlace> entry : ground.entrySet()){
			if(life.equals(entry.getValue().getLife())){
				return entry.getKey();
			}else if(entry.getValue().getCemetery().contains(life)){
				return entry.getKey();
			}
		}
		return null;
	}

	@Override
	public List<Integer> move(LifeCard life, Integer position, Integer type) {
		// TODO Auto-generated method stub
		List<Integer> route = new ArrayList<Integer>();
		if(IMove.Type_Walk.equals(type)){
			route = route(getPosition(life), position);
		}else if(IMove.Type_Fly.equals(type)){
			route = easyRoute(getPosition(life), position);
		}else if(IMove.Type_Flash.equals(type)){
			route.add(position);
		}
		for(Integer p : route){
			Integer curPos = getPosition(life);
			if(null!=p){
				IPlace place = getPlace(curPos);
				place.out();
				place = getPlace(p);
				place.in(life);
			}
		}
		
		return route;
	}
	
	private Integer[] integerToPoint(Integer point){
		String [] points = point.toString().split(space);
		Integer x = Integer.valueOf(points[0]);
		Integer y = Integer.valueOf(points[1]);

		return new Integer[]{x,y}; 
	}
	
	/**
	 * 用于表示越界的点，isOver = true
	 */
	private static final Integer OverPoint = 110010;
	
	private Integer pointToInteger(Integer x,Integer y){
		if(x<1 || y<1)
			return OverPoint;
		else
			return Integer.valueOf(x+space+y);
	}
	
	@Override
	public List toList() {
		// TODO Auto-generated method stub
		List<IPlace> list = new ArrayList<IPlace>();
		Set<Integer> keySet = ground.keySet();
		Iterator<Integer> it = keySet.iterator();
		while (it.hasNext()) {
			IPlace place = ground.get(it.next());
			list.add(place);
		}
		return list;
	}

	/**
	 * 判断是否超出边界
	 * @param position
	 * @return
	 */
	private Boolean isOver(Integer position){
		Integer [] ps = integerToPoint(position);
		if(ps[0]<1||ps[0]>xBorder)
			return true;
		if(ps[1]<1||ps[1]>yBorder)
			return true;
		return false;
	}
	
	@Override
	public List<LifeCard> list(IPlayer player) {
		// TODO Auto-generated method stub
		List<LifeCard> ret = new ArrayList<LifeCard>();
		List<ICard> list = list();
		
		for(ICard card : list){
			if(player.equals(card.getPlayer()))
				ret.add((LifeCard) card);
		}
		
		return ret;
	}
	
	@Override
	public List<LifeCard> list(Integer stand, Integer step, Integer type) {
		// TODO Auto-generated method stub
		List<LifeCard> ls = new ArrayList<LifeCard>();
		
		List<Integer> list = this.easyAreaForDistance(stand, step, type);
		for(Integer position : list){
			LifeCard life = this.getCard(position);
			if(null!=life){
				ls.add(life);
			}
		}
		
		return ls;
	}

	public Integer easyDistance(Integer start, Integer stop) {
		// TODO Auto-generated method stub
    	return cellularDistrict.getShortestPathLength(getSerialNumber(start), getSerialNumber(stop));
	}
	
	@Override
	public Integer distance(Integer start, Integer stop) {
		// TODO Auto-generated method stub
		return route(start, stop).size();
	}

	public List<Integer> easyAreaForDistance(Integer position, Integer step, Integer type) {
		// TODO Auto-generated method stub
		List<Integer> list = new ArrayList<Integer>();
		for(int i=1;i<xBorder+1;i++){
			for(int j=1;j<yBorder+1;j++){
				Integer curPos = Integer.valueOf(""+i+space+j);
				switch (type) {
				case 0:
					if(step==easyDistance(curPos, position))
						list.add(curPos);
					break;
					
				case 1:
					if(step>=easyDistance(curPos, position))
						list.add(curPos);
					break;		
					
				default:
					break;
				}
				
			}
		}
		return list;
	}
	
	@Override
	public List<Integer> areaForDistance(Integer position, Integer step,
			Integer type) {
		// TODO Auto-generated method stub
		List<Integer> list = new ArrayList<Integer>();
		for(int i=1;i<xBorder+1;i++){
			for(int j=1;j<yBorder+1;j++){
				Integer curPos = Integer.valueOf(i+space+j);
				switch (type) {
				case 0:
					if(step==distance(curPos, position))
						if(!getPlace(curPos).isDisable())
							list.add(curPos);
					break;
					
				case 1:
					if(step>=distance(curPos, position))
						if(!getPlace(curPos).isDisable())
							list.add(curPos);
					break;	
					
				default:
					break;
				}
				
			}
		}
		return list;
		
	}
	
	/**
	 * 两点之间的矩阵，包含起点和终点
	 * @param start 左上角
	 * @param stop  右下角
	 * @return
	 */
	private List<Integer> rectangle(Integer start, Integer stop){
		String [] starts = start.toString().split(space);
		Integer startx = Integer.valueOf(starts[0]);
		Integer starty = Integer.valueOf(starts[1]);
		
		String [] stops = stop.toString().split(space);
		Integer stopx = Integer.valueOf(stops[0]);
		Integer stopy = Integer.valueOf(stops[1]);
		
		List<Integer> list = new ArrayList<Integer>();
		for(int i=startx;i<=stopx;i++)
			for(int j=starty;j<=stopy;j++)
				list.add(Integer.valueOf(i+space+j));
		return list;
	}
	
	public List<Integer> route(Integer start, Integer stop){
		List<Integer> m = rectangle(Integer.valueOf(1+space+1), Integer.valueOf(xBorder+space+yBorder));
		
		
		IPlace place = getPlace(stop);
		Boolean disable = place.isDisable();
		place.setDisable(false);                //终点必须可以到达才有意义
		
		for(Integer i : m){
			String [] is = i.toString().split(space);
			Integer ix = Integer.valueOf(is[0]);
			Integer iy = Integer.valueOf(is[1]);
			if(getPlace(i).isDisable())
				MAP[ix][iy] = 1;
			else
				MAP[ix][iy] = 0;
		}

		place.setDisable(disable);              //恢复终点状态
		
		PathFinding pathFinding = new PathFinding(MAP,hit);
		
		Integer[] starts = integerToPoint(start);
		Integer[] stops = integerToPoint(stop);
		Point startPos = new Point(starts[0], starts[1]);
		Point stopPos = new Point(stops[0],stops[1]);
		List path = pathFinding.searchPath(startPos, stopPos);
		
		List<Integer> list = new ArrayList<Integer>();
		for(int i=0;null!=path&&i<path.size();i++){
			Node node = (Node) path.get(i);
			Point point = node._Pos;
			Integer pos = pointToInteger(point.x,point.y);
			list.add(pos);
		}
		list.remove(start);  //结果包含起点，因此要删除
		return list;
	}
	
	@Override
	public List<Integer> easyRoute(Integer start, Integer stop) {
		// TODO Auto-generated method stub
		List<Integer> m = rectangle(Integer.valueOf(1+space+1), Integer.valueOf(xBorder+space+yBorder));
		
		for(Integer i : m){
			String [] is = i.toString().split(space);
			Integer ix = Integer.valueOf(is[0]);
			Integer iy = Integer.valueOf(is[1]);
			MAP[ix][iy] = 0;
		}
		
		PathFinding pathFinding = new PathFinding(MAP,hit);
		
		Integer[] starts = integerToPoint(start);
		Integer[] stops = integerToPoint(stop);
		Point startPos = new Point(starts[0], starts[1]);
		Point stopPos = new Point(stops[0],stops[1]);
		List path = pathFinding.searchPath(startPos, stopPos);
		
		List<Integer> list = new ArrayList<Integer>();
		for(int i=0;null!=path&&i<path.size();i++){
			Node node = (Node) path.get(i);
			Point point = node._Pos;
			Integer pos = pointToInteger(point.x,point.y);
			list.add(pos);
		}
		list.remove(start);  //结果包含起点，因此要删除
		return list;
	}
	
	@Override
	public Integer getDirection(Integer stand, Integer target) {
		// TODO Auto-generated method stub
		Integer [] p1 = integerToPoint(stand);
		Integer [] p2 = integerToPoint(target);
		
		Integer ret = null;
		
		if(p1[1]%2==0){
			if(p1[0]-p2[0]==1 && p1[1]-p2[1]==1)
				ret = IGround.Relative_LeftTop;
			
			if(p1[0]-p2[0]==0 && p1[1]-p2[1]==1)
				ret = IGround.Relative_RightTop;
				
			if(p1[0]-p2[0]==1 && p1[1]-p2[1]==0)
				ret = IGround.Relative_Left;
					
			if(p1[0]-p2[0]==-1 && p1[1]-p2[1]==0)
				ret = IGround.Relative_Right;
						
			if(p1[0]-p2[0]==1 && p1[1]-p2[1]==-1)
				ret = IGround.Relative_LeftBottom;
							
			if(p1[0]-p2[0]==0 && p1[1]-p2[1]==-1)
				ret = IGround.Relative_RightBottom;
		}else{
			if(p1[0]-p2[0]==0 && p1[1]-p2[1]==1)
				ret = IGround.Relative_LeftTop;
			
			if(p1[0]-p2[0]==-1 && p1[1]-p2[1]==1)
				ret = IGround.Relative_RightTop;
				
			if(p1[0]-p2[0]==1 && p1[1]-p2[1]==0)
				ret = IGround.Relative_Left;
					
			if(p1[0]-p2[0]==-1 && p1[1]-p2[1]==0)
				ret = IGround.Relative_Right;
						
			if(p1[0]-p2[0]==0 && p1[1]-p2[1]==-1)
				ret = IGround.Relative_LeftBottom;
							
			if(p1[0]-p2[0]==-1 && p1[1]-p2[1]==-1)
				ret = IGround.Relative_RightBottom;
		}
		
		return ret;
	}
	
	@Override
	public Integer getPosition(Integer stand, Integer direction) {
		// TODO Auto-generated method stub
		Integer [] p1 = integerToPoint(stand);
		Integer ret = null;
		
		if(p1[1]%2==0){
			switch (direction) {
			case 10:
				ret = pointToInteger(p1[0]-1, p1[1]-1);
				break;
			case 2:
				ret = pointToInteger(p1[0], p1[1]-1);
				break;
			case 9:
				ret = pointToInteger(p1[0]-1, p1[1]);
				break;
			case 3:
				ret = pointToInteger(p1[0]+1, p1[1]);
				break;
			case 8:
				ret = pointToInteger(p1[0]-1, p1[1]+1);
				break;
			case 4:
				ret = pointToInteger(p1[0], p1[1]+1);
				break;

			default:
				break;
			}
		}else{
			switch (direction) {
			case 10:
				ret = pointToInteger(p1[0], p1[1]-1);
				break;
			case 2:
				ret = pointToInteger(p1[0]+1, p1[1]-1);
				break;
			case 9:
				ret = pointToInteger(p1[0]-1, p1[1]);
				break;
			case 3:
				ret = pointToInteger(p1[0]+1, p1[1]);
				break;
			case 8:
				ret = pointToInteger(p1[0], p1[1]+1);
				break;
			case 4:
				ret = pointToInteger(p1[0]+1, p1[1]+1);
				break;

			default:
				break;
			}
		}
		
		return ret ;
	}
	
	@Override
	public List<Integer> twoFlanks(Integer stand, Integer direction) {
		// TODO Auto-generated method stub
		List<Integer> list = new ArrayList<Integer>();
		
		Integer position  = null;
		
		switch (direction) {
		case 10:
			position = getPosition(stand, IGround.Relative_Left);
			if(!isOver(position))
				list.add(position);
			
			position = getPosition(stand, IGround.Relative_RightTop);
			if(!isOver(position))
				list.add(position);
			
			break;
		case 2:
			position = getPosition(stand, IGround.Relative_LeftTop);
			if(!isOver(position))
				list.add(position);
			
			position = getPosition(stand, IGround.Relative_Right);
			if(!isOver(position))
				list.add(position);
			
			break;
		case 9:
			position = getPosition(stand, IGround.Relative_LeftTop);
			if(!isOver(position))
				list.add(position);
			
			position = getPosition(stand, IGround.Relative_LeftBottom);
			if(!isOver(position))
				list.add(position);
			
			break;
		case 3:
			position = getPosition(stand, IGround.Relative_RightTop);
			if(!isOver(position))
				list.add(position);
			
			position = getPosition(stand, IGround.Relative_RightBottom);
			if(!isOver(position))
				list.add(position);
			
			break;
		case 8:
			position = getPosition(stand, IGround.Relative_Left);
			if(!isOver(position))
				list.add(position);
			
			position = getPosition(stand, IGround.Relative_RightBottom);
			if(!isOver(position))
				list.add(position);
			
			break;
		case 4:
			position = getPosition(stand, IGround.Relative_Right);
			if(!isOver(position))
				list.add(position);
			
			position = getPosition(stand, IGround.Relative_LeftBottom);
			if(!isOver(position))
				list.add(position);
			
			break;

		default:
			break;
		}
		
		return list;
	}
	
	@Override
	public Integer getRandomEntry(ITown town, LifeCard life) {
		// TODO Auto-generated method stub
		List<Integer> list = new ArrayList<Integer>();

		list.addAll(areaForDistance(town.getPosition(), 1, IGround.Contain));
		
		if(list.isEmpty())
			return null;
		else{
			Random r = new Random();
			return list.get(r.nextInt(list.size()-1));
		}	
	}
	
	@Override
	public List<Integer> getEntryList(LifeCard life) {
		// TODO Auto-generated method stub
		IPlayer player = life.getPlayer();
		List<Integer> list = new ArrayList<Integer>();
		
		for(Integer position : getTownPosition(player, life.getStar())){
			list.addAll(areaForDistance(position, 1, IGround.Contain));
		}
	
		LifeCard hero = player.getHeroCard();
		list.addAll(areaForDistance(hero.getContainerPosition(), 1, IGround.Contain));
		
		return list;
	}
	
	/**
	 * 根据圈数计算最大编号
	 * @param circleNumber 圈数，原点不算，即第一圈为7
	 * @return
	 */
	private Integer getMaxSerial(Integer circleNumber){
		Integer number = 1;
    	for (int i = 1; i < circleNumber+1; i++) {
			number += i*6;
		}
    	return number;
    }
	
	/**
	 * 根据边界计算中心点
	 * @param xBorder x边界
	 * @param yBorder y边界
	 * @return
	 */
	private Integer getCentrePoint(Integer xBorder, Integer yBorder){
		return pointToInteger(xBorder/2, yBorder/2);
    }
	
	public static final Integer base = 100;
	
	/**
	 * 判断坐标是否越界，只用于初始化坐标系与序号映射时使用
	 * @param point 坐标
	 * @return
	 */
	private Boolean isCoordinateOver(Integer point){
    	Integer [] ps = integerToPoint(point);
		if(ps[0]<base+1)
			return true;
		if(ps[1]<base+1)
			return true;
		return false;
    }
    
	/**
	 * 添加坐标系与序号的映射
	 * @param serialNumber 序号
	 * @param point 坐标
	 */
    private void addCoordinateMap(Integer serialNumber, Integer point){
    	if(!isCoordinateOver(point)){
			coordinateMap.put(serialNumber, point);
    		serialNumberMap.put(point, serialNumber);
		}
    }
    
    /**
     * 根据坐标查找序号
     * @param coordinate 坐标
     * @return
     */
    private Integer getSerialNumber(Integer coordinate){
    	Integer [] ps = integerToPoint(coordinate);
    	Integer point = pointToInteger(base+ps[0], base+ps[1]);
    	return serialNumberMap.get(point);
    }
    
    /**
     * 根据序号查找坐标
     * @param serialNumber 序号
     * @return
     */
    private Integer getCoordinate(Integer serialNumber){
    	Integer point = coordinateMap.get(serialNumber);
    	Integer [] ps = integerToPoint(point);
    	return pointToInteger(ps[0]%base, ps[1]%base);
    }
	
	/**
	 * 初始化坐标系与序号的映射
	 * @param centrePoint 原点/中心点
	 * @param circleNumber 圈数，原点不算，即第一圈为7
	 */
	private void initCoordinateSystem(Integer centrePoint, Integer circleNumber){
		Integer n = 0;
    	Integer [] point = integerToPoint(centrePoint);
    	
    	Integer starPoint = pointToInteger(base+point[0], base+point[1]);
    	n += 1;
    	addCoordinateMap(n, starPoint);
    	
    	starPoint = getPosition(starPoint, IGround.Relative_Left);
		n += 1;
		addCoordinateMap(n, starPoint);
    	
    	for (int i = 1; i < circleNumber+1; i++) {
			for (int j = 1; j < i; j++) {
				starPoint = getPosition(starPoint, IGround.Relative_LeftTop);
				n += 1;
				addCoordinateMap(n, starPoint);
			}
			
			for (int j = 0; j < i; j++) {
				starPoint = getPosition(starPoint, IGround.Relative_RightTop);
				n += 1;
				addCoordinateMap(n, starPoint);
			}
			
			for (int j = 0; j < i; j++) {
				starPoint = getPosition(starPoint, IGround.Relative_Right);
				n += 1;
				addCoordinateMap(n, starPoint);
			}
			
			for (int j = 0; j < i; j++) {
				starPoint = getPosition(starPoint, IGround.Relative_RightBottom);
				n += 1;
				addCoordinateMap(n, starPoint);
			}
			
			for (int j = 0; j < i; j++) {
				starPoint = getPosition(starPoint, IGround.Relative_LeftBottom);
				n += 1;
				addCoordinateMap(n, starPoint);
			}
			
			for (int j = 0; j < i+1; j++) {
				starPoint = getPosition(starPoint, IGround.Relative_Left);
				n += 1;
				addCoordinateMap(n, starPoint);
			}
		}
    }
	
	public static void main(String[] args) {
		/*Integer x1=1,y1=1,x2=2,y2=2;
		System.out.println(Util.convertInteger(Math.sqrt(Math.pow(x1.doubleValue()-x2.doubleValue(),2)+Math.pow(y1.doubleValue()-y2.doubleValue(),2))));
		
		System.out.println(Math.abs(x1-x2)+Math.abs(y1-y2)) ;*/
		Random r = new Random();
		System.out.println(r.nextInt(10));
		
		IGround ground = new HoneycombGround(21,13,null);
		System.out.println(ground);
	}

}
