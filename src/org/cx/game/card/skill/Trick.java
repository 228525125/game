package org.cx.game.card.skill;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Map.Entry;

import org.cx.game.card.LifeCard;
import org.cx.game.core.IPlayer;
import org.cx.game.exception.RuleValidatorException;
import org.cx.game.intercepter.IInterceptable;
import org.cx.game.intercepter.IIntercepter;
import org.cx.game.intercepter.Intercepter;
import org.cx.game.widget.IPlace;
import org.cx.game.widget.ITrickList;

/**
 * 陷阱
 * @author chenxian
 *
 */
public abstract class Trick extends Observable implements ITrick {

	private ITrickList owner;
	private IPlayer player;
	private Map<String,List<IIntercepter>> intercepterList = new HashMap<String,List<IIntercepter>>();
	private List<Map<IInterceptable, IIntercepter>> resetList = new ArrayList<Map<IInterceptable, IIntercepter>>();
	private String action = null;
	private Integer bout = 0;
	private Integer style = 0;       //风格，法术、物理   
	private Integer type = 0;        //类型，受益、受损、中性    
	private Integer beginBout = 0;
	private Integer func = IMagic.Func_Damage;      //功能类型
	
	public Trick(Integer bout, Integer style, Integer type, Integer func, IPlace place, IPlayer player) {
		// TODO Auto-generated constructor stub
		this.player = player;
		this.owner = place.getTrickList();
		this.bout = bout;
		this.style = style;
		this.type = type;
		this.func = func;
		recordIntercepter(player.getContext(), this);
		
		String allName = this.getClass().getName();
		String packageName = this.getClass().getPackage().getName();
		String name = allName.substring(packageName.length()+1);
		setAction("Trick_"+name);
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
					life.affected(Trick.this);
				} catch (RuleValidatorException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		};
		recordIntercepter(getOwner(), placeIn);
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
	
	@Override
	public void affect(Object...objects) throws RuleValidatorException {
		// TODO Auto-generated method stub
		if(0==beginBout)
			beginBout = getPlayer().getContext().getBout();
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
		Map entry = new HashMap<IInterceptable, IIntercepter>();
		entry.put(interceptable, intercepter);
		resetList.add(entry);
	}
	
	public Integer getStyle() {
		return style;
	}

	public Integer getType() {
		return type;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public Integer getFunc() {
		return func;
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
		List<IIntercepter> list = intercepterList.get(intercepter.getIntercepterMethod());
		if(null!=list){
			list.remove(intercepter);
		}
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
}
