package org.cx.game.card.trick;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Map.Entry;

import org.cx.game.card.LifeCard;
import org.cx.game.card.buff.IBuff;
import org.cx.game.card.magic.IMagic;
import org.cx.game.core.Context;
import org.cx.game.core.IPlayer;
import org.cx.game.exception.RuleValidatorException;
import org.cx.game.intercepter.IInterceptable;
import org.cx.game.intercepter.IIntercepter;
import org.cx.game.intercepter.Intercepter;
import org.cx.game.observer.NotifyInfo;
import org.cx.game.tools.I18n;
import org.cx.game.widget.IPlace;
import org.cx.game.widget.ITrickList;

/**
 * 陷阱
 * @author chenxian
 *
 */
public abstract class Trick extends Observable implements ITrick {

	private String name = null;
	private ITrickList owner;
	private IPlayer player;
	private Map<String,List<IIntercepter>> intercepterList = new HashMap<String,List<IIntercepter>>();
	private List<Map<IInterceptable, IIntercepter>> resetList = new ArrayList<Map<IInterceptable, IIntercepter>>();
	private Boolean isDelete = false;
	private String action = null;
	private Integer bout = 0; 
	private Integer beginBout = 0;
	
	private Integer touchNumberOfTimes = DEFAULT_TOUCHNUMBEROFTIMES;         //陷阱触发次数
	private Integer countTouchNumberOfTimes = 0;    //陷阱触发计数
	
	public final static Integer DEFAULT_TOUCHNUMBEROFTIMES = 1;
	
	public Trick(Integer bout, Integer touchNumberOfTimes, IPlace place, IPlayer player) {
		// TODO Auto-generated constructor stub
		this.player = player;
		this.owner = place.getTrickList();
		this.bout = bout;
		this.touchNumberOfTimes = touchNumberOfTimes;

		recordIntercepter(player.getContext(), this);
		
		String allName = this.getClass().getName();
		String packageName = this.getClass().getPackage().getName();
		String name = allName.substring(packageName.length()+1);
		setAction("Trick");
		
		setup();
	}
	
	@Override
	public void setup() {
		// TODO Auto-generated method stub
		owner.add(this);
		
		IIntercepter placeIn = new Intercepter("in"){
			@Override
			public void finish(Object[] args) {
				// TODO Auto-generated method stub
				LifeCard life = (LifeCard) args[0];
				try {
					if(countTouchNumberOfTimes<touchNumberOfTimes)
						life.affected(Trick.this);
					if(countTouchNumberOfTimes==touchNumberOfTimes)
						invalid();
				} catch (RuleValidatorException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		};
		recordIntercepter(getOwner().getOwner(), placeIn);
	}
	
	public String getName() {
		if(null==name)
			name = I18n.getMessage(this, "name");
		return name;
	}
	
	@Override
	public IPlayer getPlayer() {
		// TODO Auto-generated method stub
		return player;
	}

	@Override
	public Integer getBout() {
		// TODO Auto-generated method stub
		return bout;
	}
	
	@Override
	public void setBout(Integer bout) {
		// TODO Auto-generated method stub
		this.bout = bout;
	}

	public ITrickList getOwner() {
		return owner;
	}

	@Override
	public void after(Object[] args) {
		// TODO Auto-generated method stub
		
	}
	
	private final static String Affect = "_Affect";
	
	@Override
	public void affect(Object...objects) throws RuleValidatorException {
		// TODO Auto-generated method stub
		if(0==beginBout)
			beginBout = getPlayer().getContext().getBout();
		
		this.countTouchNumberOfTimes++;
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("player", getPlayer());
		map.put("container", getPlayer().getGround());
		map.put("trick", this);
		map.put("position", getOwner().getOwner().getPosition());
		NotifyInfo info = new NotifyInfo(getAction()+Affect,map);
		notifyObservers(info);
	}
	
	@Override
	public void invalid() {
		// TODO Auto-generated method stub
		resetIntercepter();           //清除buff的拦截器
		owner.remove(this);
	}
	
	public void resetIntercepter() {
		// TODO Auto-generated method stub
		resetIntercepter(IIntercepter.Level_Current);
	}
	
	@Override
	public void resetIntercepter(Integer level) {
		// TODO Auto-generated method stub
		for(Map<IInterceptable, IIntercepter> map : resetList){
			for(Entry<IInterceptable, IIntercepter> entry : map.entrySet()){
				IInterceptable interceptable = entry.getKey();
				IIntercepter intercepter = entry.getValue();	
				if(level.equals(intercepter.getLevel()))
					interceptable.deleteIntercepter(intercepter);
			}
		}
	}
	
	public void recordIntercepter(IInterceptable interceptable, IIntercepter intercepter){
		interceptable.addIntercepter(intercepter);
		Map entry = new HashMap<IInterceptable, IIntercepter>();
		entry.put(interceptable, intercepter);
		resetList.add(entry);
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	@Override
	public String getIntercepterMethod() {
		// TODO Auto-generated method stub
		return "addBout";
	}

	@Override
	public Boolean isInvoke() {
		// TODO Auto-generated method stub
		return true;
	}
	
	@Override
	public void finish(Object[] args) {
		// TODO Auto-generated method stub
		if(0==beginBout)
			beginBout = getPlayer().getContext().getBout();
		
		Integer curBout = getPlayer().getContext().getBout();
		if((curBout-beginBout)==bout){
			invalid();
		}
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
		/*List<IIntercepter> list = intercepterList.get(intercepter.getIntercepterMethod());
		if(null!=list){
			list.remove(intercepter);
		}*/
		/* 这两种编码方式效果一样，但后一种避免了java.util.ConcurrentModificationException 异常  */
		
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
	public Integer getOrder() {
		// TODO Auto-generated method stub
		return IIntercepter.Order_Default;
	}
	
	@Override
	public Integer getLevel() {
		// TODO Auto-generated method stub
		return IIntercepter.Level_Current;
	}
	
	@Override
	public void notifyObservers(Object arg) {
		// TODO Auto-generated method stub
		super.setChanged();
		super.notifyObservers(arg);
	}

	@Override
	public void before(Object[] args) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public Boolean isDelete() {
		// TODO Auto-generated method stub
		return this.isDelete;
	}
	
	@Override
	public void delete() {
		// TODO Auto-generated method stub
		this.isDelete = true;
	}
	
	@Override
	public Boolean contains(Integer tag) {
		// TODO Auto-generated method stub
		List<Integer> objectList = Context.queryForTag(tag);
		return objectList.contains(getId());
	}

	@Override
	public List<Integer> queryForCategory(Integer category) {
		// TODO Auto-generated method stub
		return Context.queryForCategory(category);
	}
}
