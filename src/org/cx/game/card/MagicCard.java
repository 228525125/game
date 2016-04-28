package org.cx.game.card;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.cx.game.action.ApplyDecorator;
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
import org.cx.game.policy.IUseCardPolicy;
import org.cx.game.tools.I18n;
import org.cx.game.validator.Errors;
import org.cx.game.validator.IValidatable;
import org.cx.game.validator.IValidator;
import org.cx.game.validator.ParameterTypeValidator;
import org.cx.game.widget.IContainer;
import org.cx.game.widget.IGround;

public abstract class MagicCard extends java.util.Observable implements ICard, IMagic, Observable, IValidatable {
	
	private Map<String,List<IIntercepter>> intercepterList = new HashMap<String,List<IIntercepter>>();
	private Integer style = 0;
	private Integer func = 0;
	
	private String action = null;
	
	private List<IValidator> validatorList = new ArrayList<IValidator>();
	private Errors errors = new Errors();
	
	private Class[] parameterTypeValidator = new Class[]{};      //用于参数的验证
	
	public MagicCard(Integer id, Integer consume) {
		// TODO Auto-generated constructor stub
		addObserver(new JsonOut());
		this.id = id;
		this.consume = consume;
		
		setAction("Magic");
		
		/* 取类名 */
		String allName = this.getClass().getName();
		this.func = Context.getMagicFunction(allName);
		this.style = Context.getMagicStyle(allName);
		
		
	}
	
	private final static String Apply = "_Apply";
	
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
	 * 魔法使用范围
	 * @return
	 */
	public List<Integer> getApplyRange(IGround ground){
		List<Integer> positionList = new ArrayList<Integer>();
		LifeCard conjure = getConjurer();
		Integer position = conjure.getContainerPosition();
		positionList = ground.easyAreaForDistance(position, conjure.getAttack().getRange(), IGround.Contain);
		return positionList;
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
	public IPlayer getOwner(){
		return player;
	}
	
	private LifeCard conjurer = null;
	
	/**
	 * 使用魔法卡的生物
	 * @return
	 */
	public LifeCard getConjurer() {
		return conjurer;
	}

	public void setConjurer(LifeCard conjurer) {
		this.conjurer = conjurer;
	}
	
	/**
	 * 是否需要施法者，例如传送，需要施法者法师在场，这时法师就是施法者
	 * @return
	 */
	public abstract Boolean needConjurer();
	
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
	private IApply apply;

	public IApply getApply() {
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
	private IChuck chuck;

	public IChuck getChuck() {
		return chuck;
	}

	public void setChuck(IChuck chuck) {
		chuck.setOwner(this);
		this.chuck = new ChuckDecorator(chuck);
	}
	
	private ParameterTypeValidator parameterValidator = null;

	protected void setParameterTypeValidator(Class[] parameterTypeValidator) {
		this.parameterTypeValidator = parameterTypeValidator;
	}
	
	/**
	 * 使用
	 */
	public void apply(Object...objects) throws RuleValidatorException {

		deleteValidator(parameterValidator);
		this.parameterValidator = new ParameterTypeValidator(objects,parameterTypeValidator); 
		addValidator(parameterValidator);
		
		/* 
		 * 执行规则验证
		 */
		doValidator();
		
		if(hasError())
			throw new RuleValidatorException(getErrors().getMessage());
		
		apply.action(objects);
	}
	
	@Override
	public void chuck() throws RuleValidatorException {
		// TODO Auto-generated method stub
		chuck.action();
	}
	
	@Override
	public void affect(Object... objects) throws RuleValidatorException {
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
	
}
