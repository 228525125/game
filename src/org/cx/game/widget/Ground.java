package org.cx.game.widget;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.Set;

import org.cx.game.action.IMove;
import org.cx.game.card.ICard;
import org.cx.game.card.LifeCard;
import org.cx.game.card.TrickCard;
import org.cx.game.card.skill.IActiveSkill;
import org.cx.game.card.skill.ISkill;
import org.cx.game.card.skill.ITrick;
import org.cx.game.core.IPlayer;
import org.cx.game.observer.NotifyInfo;
import org.cx.game.out.JsonOut;
import org.cx.game.tools.Node;
import org.cx.game.tools.PathFinding;
import org.cx.game.tools.Util;

public class Ground extends Container implements IGround
{
	private Integer xBorder = 15;                                       //边界x轴长度
	private Integer yBorder = 15;                                       //边界y轴长度
	private String imagePath = "";                                      //背景图片
	public static String space = "1001";                               //位置坐标间隔符
	private Map<Integer,IPlace> ground = new HashMap<Integer,IPlace>();
	private List<ICamp> campList = new ArrayList<ICamp>();              //营地
	private List<Integer> disableList = new ArrayList<Integer>();       //不可用的单元格
	private List<IStrongHold> strongHoldList = new ArrayList<IStrongHold>();           //据点
	private int [][] MAP = new int[xBorder+2][yBorder+2];               //用于查询路线
	private int[] hit = new int []{1};                                  //1表示障碍物

	public Ground(Integer xBorder, Integer yBorder, String imagePath) {
		// TODO Auto-generated constructor stub
		this.xBorder = xBorder;
		this.yBorder = yBorder;
		this.imagePath = imagePath;
		
		addObserver(new JsonOut());
		for(int i=0;i<xBorder+2;i++)
			for(int j=0;j<yBorder+2;j++)
				MAP[i][j] = 1;
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
	public void remove(ICard card) {
		// TODO Auto-generated method stub
		setAction(NotifyInfo.Container_Ground_Remove);
		super.remove(card);
		
		Integer position = getPosition(card);
		IPlace place = getPlace(position);
		place.out();
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
	
	public Integer easyDistance(Integer start, Integer stop) {
		// TODO Auto-generated method stub
		/*
		 * 计算坐标系中两点距离 = 绝对值(起始坐标X - 目标坐标X) + 绝对值(起始坐标Y - 目标坐标Y)  
		 * 这个算法没有考虑障碍物，因此取消
		 */
		Integer [] starts = integerToPoint(start);
		Integer [] stops = integerToPoint(stop);
		
		return Math.abs(starts[0]-stops[0])+Math.abs(starts[1]-stops[1]); 
	}
	
	public Integer easyOuDistance(Integer start, Integer stop){
		Integer [] starts = integerToPoint(start);
		Integer [] stops = integerToPoint(stop);
		return Util.convertInteger(Math.sqrt(Math.pow(starts[0].doubleValue()-stops[0].doubleValue(),2)+Math.pow(starts[1].doubleValue()-stops[1].doubleValue(),2)));
	}	
	
	@Override
	public Integer easyDistanceDiagonal(Integer start, Integer stop) {
		// TODO Auto-generated method stub
		Integer [] starts = integerToPoint(start);
		Integer [] stops = integerToPoint(stop);
		Integer x = Math.abs(starts[0]-stops[0]);
		Integer y = Math.abs(starts[1]-stops[1]);
		return Math.max(x, y);
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
	public List<Integer> easyAreaForDistanceDiagonal(Integer position,
			Integer step, Integer type) {
		// TODO Auto-generated method stub
		List<Integer> list = new ArrayList<Integer>();
		for(int i=1;i<xBorder+1;i++){
			for(int j=1;j<yBorder+1;j++){
				Integer curPos = Integer.valueOf(""+i+space+j);
				switch (type) {
				case 0:
					if(step==easyDistanceDiagonal(curPos, position))
						list.add(curPos);
					break;
					
				case 1:
					if(step>=easyDistanceDiagonal(curPos, position))
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
	
	@Override
	public IPlace getPlace(Integer position) {
		// TODO Auto-generated method stub
		return ground.get(position);
	}

	public List<ICamp> getCampList() {
		return campList;
	}

	public void setCampList(List<ICamp> campList) {
		this.campList = campList;
	}
	
	@Override
	public void setPlayerToCamp(Integer campIndex, IPlayer player) {
		// TODO Auto-generated method stub
		campList.get(campIndex).setPlayer(player);
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
	public List<Integer> getCampPosition(IPlayer player) {
		// TODO Auto-generated method stub
		List<Integer> list = new ArrayList<Integer>();
		for(ICamp camp : campList){
			if(camp.getPlayer().equals(player))
				list.add(camp.getPosition());
		}
		
		return list;
	}
	
	@Override
	public Integer getRandomEntry(IPlayer player) {
		// TODO Auto-generated method stub
		List<Integer> list = new ArrayList<Integer>();
		for(ICamp camp : campList){
			if(camp.getPlayer().equals(player)){
				List<Integer> clist = camp.getEntryList();
				for(Integer p : clist){
					if(!getPlace(p).isDisable())
						list.add(p);
				}
				
			}
		}
		if(list.isEmpty())
			return null;
		else{
			Random r = new Random();
			return list.get(r.nextInt(list.size()-1));
		}
	}
	
	@Override
	public List<Integer> getEntryList(IPlayer player) {
		// TODO Auto-generated method stub
		List<Integer> list = new ArrayList<Integer>();
		for(ICamp camp : campList){
			if(camp.getPlayer().equals(player)){
				List<Integer> clist = camp.getEntryList();
				list.addAll(clist);
			}
		}
		return list;
	}
	
	@Override
	public void addCamp(ICamp camp) {
		// TODO Auto-generated method stub
		campList.add(camp);
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
			positionList = easyAreaForDistance(life.getContainerPosition(), range, Contain);  //1：表示范围内的所有单元格，0：表示等于范围的单元格
			positionList.remove(life.getContainerPosition());
		}
		if(NotifyInfo.Command_Query_Call == action && card instanceof LifeCard){
			IPlayer player = card.getPlayer();
			positionList.addAll(this.getEntryList(player));
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
			LifeCard card = (LifeCard) skill.getOwner();
			Integer position = card.getContainerPosition();
			positionList = easyAreaForDistance(position, skill.getRange(), 1);
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
		map.put("campList", campList);
		map.put("disableList", disableList);
		map.put("strongHoldList", strongHoldList);
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
	public void move(LifeCard life, Integer position, Integer type) {
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
		
		int[] hit = new int []{1};
		
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
	
	private Integer[] integerToPoint(Integer point){
		String [] points = point.toString().split(space);
		Integer x = Integer.valueOf(points[0]);
		Integer y = Integer.valueOf(points[1]);

		return new Integer[]{x,y}; 
	}
	
	private Integer pointToInteger(Integer x,Integer y){
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

	@Override
	public List<Integer> arc(Integer stand, Integer direction, Integer range) {
		// TODO Auto-generated method stub
		List<Integer> list = new ArrayList<Integer>();
		Integer [] as = integerToPoint(stand);
		Integer defend = line(stand, direction, 1).get(0);		
		Integer [] ds = integerToPoint(defend);
		Integer x = ds[0];
		Integer y = ds[1];
		
		if(as[0]>ds[0]){               //当atk站在def的下面
			for(int i=1;i<=range;i++){
				Integer lefttop = Integer.valueOf((x-i)+space+(y-i));  //左上
				if(!isOver(lefttop))
					list.add(lefttop);
				Integer righttop = Integer.valueOf((x-i)+space+(y+i));  //右上
				if(!isOver(righttop))
					list.add(righttop);
				if(isOver(lefttop)&&isOver(righttop))
					continue;
				list.addAll(queryShade_1(lefttop, righttop));          //左上 和 右上 两个点中间的位置
			}
		}else if(as[0]<ds[0]){         //当atk站在def的上面
			for(int i=1;i<=range;i++){
				
				Integer lefttop = Integer.valueOf((x+i)+space+(y-i));
				list.add(lefttop);
				Integer righttop = Integer.valueOf((x+i)+space+(y+i));
				list.add(righttop);
				list.addAll(queryShade_1(lefttop, righttop));
			}
		}
		
		if(as[1]>ds[1]){              //当atk站在def的右面
			for(int i=1;i<=range;i++){
				Integer lefttop = Integer.valueOf((x+i)+space+(y-i));
				list.add(lefttop);
				Integer righttop = Integer.valueOf((x-i)+space+(y-i));
				list.add(righttop);
				list.addAll(queryShade_1(lefttop, righttop));
			}
		}else if(as[1]<ds[1]){        //当atk站在def的左面
			for(int i=1;i<=range;i++){
				Integer lefttop = Integer.valueOf((x+i)+space+(y+i));
				list.add(lefttop);
				Integer righttop = Integer.valueOf((x-i)+space+(y+i));
				list.add(righttop);
				list.addAll(queryShade_1(lefttop, righttop));
			}
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
	
	/**
	 * 平行的两点中间的位置
	 * @return
	 */
	private List<Integer> queryShade_1(Integer p1, Integer p2){
		List<Integer> list = new ArrayList<Integer>();
		Integer [] p1s = integerToPoint(p1);
		Integer [] p2s = integerToPoint(p2);
		Integer p1_x = p1s[0];
		Integer p1_y = p1s[1];
		Integer p2_x = p2s[0];
		Integer p2_y = p2s[1];
		if(p1_x==p2_x){
			if(p1_y>p2_y)
				for(int i=1;i<(p1_y-p2_y);i++){
					Integer p = Integer.valueOf(p1_x+space+(p2_y+i));
					if(!isOver(p))
						list.add(p);
				}
			else if(p1_y<p2_y)
				for(int i=1;i<(p2_y-p1_y);i++){
					Integer p = Integer.valueOf(p1_x+space+(p1_y+i));
					if(!isOver(p))
						list.add(p);
				}
		}
		
		if(p1_y==p2_y){
			if(p1_x>p2_x)
				for(int i=1;i<(p1_x-p2_x);i++){
					Integer p = Integer.valueOf((p2_x+i)+space+p1_y);
					if(!isOver(p))
						list.add(p);
				}
			else if(p1_x<p2_x)
				for(int i=1;i<(p2_x-p1_x);i++){
					Integer p = Integer.valueOf((p1_x+i)+space+p1_y);
					if(!isOver(p))
						list.add(p);
				}
		}
		
		return list;
	}
	
	@Override
	public Integer queryDirection(Integer stand, Integer target) {
		// TODO Auto-generated method stub
		Integer [] p1 = integerToPoint(stand);
		Integer [] p2 = integerToPoint(target);
		if(p1[0]==p2[0]){
			if(p1[1]>p2[1])
				return IGround.Relative_Top;
			else
				return IGround.Relative_Bottom;
		}
		
		if(p1[1]==p2[1]){
			if(p1[0]>p2[0])
				return IGround.Relative_Left;
			else
				return IGround.Relative_Right;
		}
		return null;
	}
	
	@Override
	public List<Integer> twoFlanks(Integer stand, Integer direction) {
		// TODO Auto-generated method stub
		List<Integer> list = new ArrayList<Integer>();
		Integer target = line(stand, direction, 1).get(0);
		Integer [] p2 = integerToPoint(target);
		if(IGround.Relative_Top==queryDirection(stand, target) || IGround.Relative_Bottom==queryDirection(stand, target)){
			Integer position = pointToInteger(p2[0]-1,p2[1]);
			if(!isOver(position))
				list.add(position);
			
			position = pointToInteger(p2[0]+1,p2[1]);
			if(!isOver(position))
				list.add(position);
		}
		
		if(IGround.Relative_Left==queryDirection(stand, target) || IGround.Relative_Right==queryDirection(stand, target)){
			Integer position = pointToInteger(p2[0], p2[1]-1);
			if(!isOver(position))
				list.add(position);
			
			position = pointToInteger(p2[0],p2[1]+1);
			if(!isOver(position))
				list.add(position);
		}
		
		return list;
	}
	
	@Override
	public List<Integer> line(Integer stand, Integer direction,
			Integer range) {
		// TODO Auto-generated method stub
		List<Integer> list = new ArrayList<Integer>();
		Integer [] p = integerToPoint(stand);
		if(IGround.Relative_Top==direction){
			for(int i=1;i<=range;i++){
				Integer position = pointToInteger(p[0], p[1]-i);
				if(!isOver(position))
					list.add(position);
			}
		}
		
		if(IGround.Relative_Left==direction){
			for(int i=1;i<=range;i++){
				Integer position = pointToInteger(p[0]-i, p[1]);
				if(!isOver(position))
					list.add(position);
			}
		}
		
		if(IGround.Relative_Bottom==direction){
			for(int i=1;i<=range;i++){
				Integer position = pointToInteger(p[0], p[1]+i);
				if(!isOver(position))
					list.add(position);
			}
		}
		
		if(IGround.Relative_Right==direction){
			for(int i=1;i<=range;i++){
				Integer position = pointToInteger(p[0]+i, p[1]);
				if(!isOver(position))
					list.add(position);
			}
		}

		return list;
	}
	
	public static void main(String[] args) {
		/*Integer x1=1,y1=1,x2=2,y2=2;
		System.out.println(Util.convertInteger(Math.sqrt(Math.pow(x1.doubleValue()-x2.doubleValue(),2)+Math.pow(y1.doubleValue()-y2.doubleValue(),2))));
		
		System.out.println(Math.abs(x1-x2)+Math.abs(y1-y2)) ;*/
		Random r = new Random();
		System.out.println(r.nextInt(10));
	}
}
