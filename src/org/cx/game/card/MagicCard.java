package org.cx.game.card;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.cx.game.action.ApplyDecorator;
import org.cx.game.action.ChuckDecorator;
import org.cx.game.action.DeathDecorator;
import org.cx.game.action.IApply;
import org.cx.game.action.IChuck;
import org.cx.game.action.IDeath;
import org.cx.game.card.skill.IMagic;
import org.cx.game.core.IPlayer;
import org.cx.game.exception.RuleValidatorException;
import org.cx.game.intercepter.IIntercepter;
import org.cx.game.observer.Observable;
import org.cx.game.out.JsonOut;
import org.cx.game.policy.IUseCardPolicy;
import org.cx.game.tools.I18n;
import org.cx.game.widget.IContainer;

public abstract class MagicCard extends java.util.Observable implements ICard, IMagic, Observable {
	
	private Map<String,List<IIntercepter>> intercepterList = new HashMap<String,List<IIntercepter>>();
	private Integer style = 0;
	private Integer func = 0;
	
	public MagicCard(Integer id, Integer consume, Integer style, Integer func) {
		// TODO Auto-generated constructor stub
		addObserver(new JsonOut());
		this.id = id;
		this.style = style;
		this.func = func;
		this.consume = consume;
	}
	
	private Integer id;
	
	public Integer getId() {
		return id;
	}
	
	private String name = null;

	public String getName() {
		if(null==name)
			name = I18n.getCardName(this, id);
		return name;
	}
	
	@Override
	public Integer getStyle() {
		// TODO Auto-generated method stub
		return style;
	}
	
	@Override
	public Integer getFunc() {
		// TODO Auto-generated method stub
		return func;
	}
	
	/**
	 * 比赛中的ID，临时的
	 */
	private Long playId;

	@Override
	public Long getPlayId() {
		// TODO Auto-generated method stub
		return playId;
	}
	
	@Override
	public void setPlayId(Long playId) {
		// TODO Auto-generated method stub
		this.playId = playId;
	}
	
	private IPlayer player;

	@Override
	public IPlayer getPlayer() {
		// TODO Auto-generated method stub
		return player;
	}
	
	@Override
	public void setPlayer(IPlayer player) {
		// TODO Auto-generated method stub
		this.player = player;
	}
	
	/**
	 * 容器（手牌、战场、墓地等）
	 */
	private IContainer container;

	public IContainer getContainer() {
		return container;
	}

	public void setContainer(IContainer container) {
		this.container = container;
	}
	
	@Override
	public Integer getContainerPosition() {
		// TODO Auto-generated method stub
		return container.getPosition(this);
	}
	
	private IUseCardPolicy useCardPolicy = null;
	
	@Override
	public IUseCardPolicy getUseCardPolicy() {
		// TODO Auto-generated method stub
		return this.useCardPolicy;
	}
	
	@Override
	public void setUseCardPolicy(IUseCardPolicy useCardPolicy) {
		// TODO Auto-generated method stub
		useCardPolicy.setOwner(this);
		this.useCardPolicy = useCardPolicy;
	}
	
	private Integer consume =1;
	
	public Integer getConsume() {
		return consume;
	}

	public void setConsume(Integer consume) {
		this.consume = consume;
	}

	/**
	 * 卡片类型
	 */
	private Integer type = Type_Magic;

	@Override
	public Integer getType() {
		// TODO Auto-generated method stub
		return type;
	}
	
	private Integer star = 1;
	
	@Override
	public Integer getStar() {
		// TODO Auto-generated method stub
		return star;
	}

	@Override
	public void setStar(Integer star) {
		// TODO Auto-generated method stub
		this.star = star;
	}
	
	/**
	 * 所属对象
	 * @return
	 */
	public Object getOwner(){
		return player;
	}
	
	@Override
	public void initState() {
		// TODO Auto-generated method stub
		this.apply.setConsume(consume);
	}
	
	/**
	 * 使用
	 */
	private IApply apply;

	public IApply getApply() {
		return apply;
	}

	public void setApply(IApply apply) {
		apply.setOwner(this);
		this.apply = new ApplyDecorator(apply);
	}
	
	/**
	 * 丢弃
	 */
	private IChuck chuck;

	public IChuck getChuck() {
		return chuck;
	}

	public void setChuck(IChuck chuck) {
		chuck.setOwner(this);
		this.chuck = new ChuckDecorator(chuck);
	}
	
	private IDeath death = null;
	
	public IDeath getDeath() {
		return death;
	}

	public void setDeath(IDeath death) {
		death.setOwner(this);
		this.death = new DeathDecorator(death);
	}

	/**
	 * 使用
	 */
	public void apply(Object...objects) throws RuleValidatorException {
		apply.action(objects);
	}
	
	@Override
	public void chuck() throws RuleValidatorException {
		// TODO Auto-generated method stub
		chuck.action();
	}
	
	@Override
	public void death() throws RuleValidatorException {
		// TODO Auto-generated method stub
		death.action();
	}
	
	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return playId.intValue();
	}
	
	@Override
	public void notifyObservers(Object arg0) {
		// TODO Auto-generated method stub
		super.setChanged();
		super.notifyObservers(arg0);
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
	public Map<String,List<IIntercepter>> getIntercepterList() {
		// TODO Auto-generated method stub
		return intercepterList;
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		intercepterList.clear();
	}
	
}
