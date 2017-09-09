package org.cx.game.card;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.cx.game.action.ApplyDecorator;
import org.cx.game.action.Chuck;
import org.cx.game.action.ChuckDecorator;
import org.cx.game.action.IApply;
import org.cx.game.action.IChuck;
import org.cx.game.card.magic.IMagic;
import org.cx.game.core.Context;
import org.cx.game.core.IPlayer;
import org.cx.game.exception.RuleValidatorException;
import org.cx.game.intercepter.IIntercepter;
import org.cx.game.observer.NotifyInfo;
import org.cx.game.observer.Observable;
import org.cx.game.out.JsonOut;
import org.cx.game.rule.RuleGroupFactory;
import org.cx.game.tools.I18n;
import org.cx.game.validator.Errors;
import org.cx.game.validator.IValidatable;
import org.cx.game.validator.IValidator;
import org.cx.game.validator.ParameterTypeValidator;
import org.cx.game.widget.IContainer;
import org.cx.game.widget.IGround;

public abstract class MagicCard extends java.util.Observable implements ICard, IMagic, Observable, IValidatable {
	
	private Map<String,List<IIntercepter>> intercepterList = new HashMap<String,List<IIntercepter>>();
	
	private String action = null;
	
	private List<IValidator> validatorList = new ArrayList<IValidator>();
	private Errors errors = new Errors();
	
	public MagicCard(Integer id, Integer consume) {
		// TODO Auto-generated constructor stub
		addObserver(JsonOut.getInstance());
		addObserver(RuleGroupFactory.getRuleGroup());
		this.id = id;
		this.consume = consume;
		
		setAction("Magic");
	}
	
	private final static String Apply = "_Apply";
	
	private Integer id;
	
	public Integer getId() {
		return id;
	}
	
	private String name = null;

	public String getName() {
		if(null==name)
			name = I18n.getMessage(this, id, "name");
		return name;
	}
	
	private String depiction = null;
	
	public String getDepiction() {
		// TODO Auto-generated method stub
		if(null==depiction)
			depiction = I18n.getMessage(this, id, "depiction");
		return depiction;
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
	
	private Integer consume =1;
	
	public Integer getConsume() {
		return consume;
	}

	public void setConsume(Integer consume) {
		this.consume = consume;
	}
	
	/**
	 * 魔法使用范围
	 * @return
	 */
	public List<Integer> getApplyRange(IGround ground){
		List<Integer> positionList = new ArrayList<Integer>();
		if(needConjurer()){    //如果需要施法者，就默认施法者的攻击距离
			LifeCard conjure = getConjurer();
			Integer position = conjure.getContainerPosition();
			positionList = ground.areaForDistance(position, conjure.getAttack().getRange(), IGround.Contain);
		}else{                //如果不需要施法者，就默认我方所有战场上的单位
			List<LifeCard> cardList = ground.list(getPlayer());
			for(LifeCard life : cardList){
				positionList.add(life.getContainerPosition());
			}
		}
		
		return positionList;
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
	public IPlayer getOwner(){
		return player;
	}
	
	private LifeCard conjurer = null;
	
	/**
	 * 使用魔法卡的生物
	 * 现行游戏设定为英雄
	 * @return
	 */
	public LifeCard getConjurer() {
		//return getOwner().getHero();
		return null;
	}
	
	/**
	 * 是否需要施法者，例如传送，需要施法者法师在场，这时法师就是施法者
	 * 现游戏设定为施法者即英雄，因此needConjurer方法也无意义
	 * @return
	 */
	@Deprecated
	public Boolean needConjurer(){
		return true;
	};
	
	/**
	 * 能够使用该魔法卡的施法者们
	 */
	private List<Integer> conjurerList = new ArrayList<Integer>();

	public List<Integer> getConjurerList() {
		return conjurerList;
	}

	public void setConjurerList(List<Integer> conjurerList) {
		this.conjurerList = conjurerList;
	}

	@Override
	public void initState() {
		// TODO Auto-generated method stub
		this.apply.setConsume(consume);
	}
	
	/**
	 * 使用
	 */
	private IApply apply = null;

	public IApply getApply() {
		if(null==apply){
			IApply apply = new org.cx.game.action.Apply();
			apply.setConsume(consume);
			apply.setOwner(this);
			this.apply = new ApplyDecorator(apply);
		}
		return apply;
	}

	public void setApply(IApply apply) {
		apply.setConsume(consume);
		apply.setOwner(this);
		this.apply = new ApplyDecorator(apply);
	}
	
	/**
	 * 丢弃
	 */
	private IChuck chuck = null;

	public IChuck getChuck() {
		if(null==chuck){
			IChuck chuck = new Chuck();
			chuck.setOwner(this);
			this.chuck = new ChuckDecorator(chuck);
		}
		return chuck;
	}

	public void setChuck(IChuck chuck) {
		chuck.setOwner(this);
		this.chuck = new ChuckDecorator(chuck);
	}
	
	private ParameterTypeValidator parameterValidator = null;
	private Class[] parameterType = new Class[]{};      //用于参数的验证
	private String[] proertyName = null;
	private Object[] validatorValue = null;

	protected void setParameterTypeValidator(Class[] parameterType) {
		this.parameterType = parameterType;
	}
	
	/**
	 * 
	 * @param parameterType 参数类型
	 * @param proertyName 属性名称
	 * @param validatorValue 属性值，但必须为基本类型
	 */
	protected void setParameterTypeValidator(Class[] parameterType, String[] proertyName, Object[] validatorValue) {
		this.parameterType = parameterType;
		this.proertyName = proertyName;
		this.validatorValue = validatorValue;
	}
	
	public Boolean isTrigger(Object[] args){
		return true;
	}
	
	/**
	 * 使用
	 */
	public void apply(Object...objects) throws RuleValidatorException {

		deleteValidator(parameterValidator);
		this.parameterValidator = new ParameterTypeValidator(objects,parameterType,proertyName,validatorValue); 
		addValidator(parameterValidator);
		
		/* 
		 * 执行规则验证
		 */
		doValidator();
		
		if(hasError())
			throw new RuleValidatorException(getErrors().getMessage());
		
		/*
		 * 魔法卡只有在没有指定对象的时候，即命令验证不通过时，才会被回退
		 * 如果验证通过，即使魔法卡在没有目标对象的情况下，也会认定被使用
		 */
		apply.action(objects);
	}
	
	@Override
	public void chuck() throws RuleValidatorException {
		// TODO Auto-generated method stub
		chuck.action();
	}
	
	@Override
	public void affect(Object... objects) {
		// TODO Auto-generated method stub
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("player", getPlayer());
		map.put("container", getContainer());
		map.put("card", this);
		map.put("position", getContainerPosition());
		NotifyInfo info = new NotifyInfo(getAction()+Apply,map);
		notifyObservers(info);
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
		/*List<IIntercepter> list = intercepterList.get(intercepter.getIntercepterMethod());
		if(null!=list){
			list.remove(intercepter);
		}*/
		
		intercepter.delete();
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
	
	@Override
	public void addValidator(IValidator validator) {
		// TODO Auto-generated method stub
		validatorList.add(validator);
	}

	@Override
	public void deleteValidator(IValidator validator) {
		// TODO Auto-generated method stub
		validatorList.remove(validator);
	}

	@Override
	public List<IValidator> getValidators() {
		// TODO Auto-generated method stub
		return validatorList;
	}
	
	@Override
	public void doValidator() {
		// TODO Auto-generated method stub
		for(IValidator v : validatorList)
			if(!v.validate())
				errors.addError(v);
	}
	
	@Override
	public void doValidator(IValidator validator) {
		// TODO Auto-generated method stub
		if(!validator.validate())
			errors.addError(validator);
	}
	
	@Override
	public Errors getErrors() {
		// TODO Auto-generated method stub
		return errors;
	}
	
	@Override
	public Boolean hasError() {
		// TODO Auto-generated method stub
		return errors.hasError();
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}
	
	@Override
	public Boolean contains(Integer tag) {
		// TODO Auto-generated method stub
		List<Integer> objectList = Context.queryForTag(tag);
		return objectList.contains(getId());
	}
	
	@Override
	public List<Integer> queryTagForCategory(Integer category) {
		// TODO Auto-generated method stub
		return Context.queryForCategory(category);
	}
	
}
