package org.cx.game.card.skill;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Map.Entry;

import org.cx.game.card.ICard;
import org.cx.game.card.LifeCard;
import org.cx.game.exception.RuleValidatorException;
import org.cx.game.exception.CommandValidatorException;
import org.cx.game.intercepter.IInterceptable;
import org.cx.game.intercepter.IIntercepter;
import org.cx.game.intercepter.Intercepter;
import org.cx.game.intercepter.IntercepterAscComparator;
import org.cx.game.observer.NotifyInfo;
import org.cx.game.out.JsonOut;
import org.cx.game.tools.I18n;
import org.cx.game.validator.Errors;
import org.cx.game.validator.IValidator;
import org.cx.game.validator.ParameterTypeValidator;

public abstract class ActiveSkill extends Observable implements IActiveSkill {

	private String cType = null;
	private String name = null;
	private LifeCard owner;
	private Map<String,List<IIntercepter>> intercepterList = new HashMap<String,List<IIntercepter>>();
	private String action = null;
	private String code = "";
	private Integer consume = 0;              //消耗能量
	private Integer cooldown = 0;             //冷却回合
	private Integer cooldownBout = 0;         //冷却剩余回合数
	private Integer velocity = 0;             //瞬发/蓄力
	private Integer style = 0;                //魔法/物理
	private Integer func = IMagic.Func_Other; //限制/直接伤害/其他
	
	private List<IValidator> validatorList = new ArrayList<IValidator>();
	private Errors errors = new Errors();
	
	private Class[] parameterTypeValidator = new Class[]{};      //用于参数的验证
	
	private IIntercepter cooldownBoutIntercepter = new Intercepter("addBout") {      //当回合数变化时，计算冷却时间
		@Override
		public void after(Object[] args) {
			// TODO Auto-generated method stub
			cooldownBout = cooldownBout>0 ? --cooldownBout : 0;
		}
	};
	
	/**
	 * 
	 * @param consume 消耗能量
	 * @param cooldown 冷却回合
	 * @param velocity 瞬发/蓄力
	 * @param style 魔法/物理
	 * @param func 限制/直接伤害/其他 
	 */
	public ActiveSkill(Integer consume, Integer cooldown, Integer velocity, Integer style, Integer func) {
		// TODO Auto-generated constructor stub
		this.consume = consume;
		this.cooldown = cooldown;
		this.velocity = velocity;
		this.style = style;
		this.func = func;
		
		addObserver(new JsonOut());
		
		/* 取类名 */
		String allName = this.getClass().getName();
		String packageName = this.getClass().getPackage().getName();
		this.cType = allName.substring(packageName.length()+1);
		setAction("Skill");
	}
	
	public IIntercepter getCooldownBoutIntercepter() {
		return cooldownBoutIntercepter;
	}

	@Override
	public void affect(Object... objects)  throws RuleValidatorException {
		// TODO Auto-generated method stub
		
	}
	
	private final static String UseSkill = "_UseSkill";
	private ParameterTypeValidator parameterValidator = null;
	
	public void useSkill(Object...objects) throws RuleValidatorException {
		// TODO Auto-generated method stub
		deleteValidator(parameterValidator);
		this.parameterValidator = new ParameterTypeValidator(objects,parameterTypeValidator); 
		addValidator(parameterValidator);
		
		/* 
		 * 执行规则验证
		 */
		doValidator();
		
		if(hasError())
			throw new RuleValidatorException(getErrors().getMessage());
		
		cooldownBout = cooldown;
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("player", owner.getPlayer());
		map.put("container", owner.getContainer());
		map.put("card", owner);
		map.put("skill", this);
		map.put("position", owner.getContainerPosition());
		NotifyInfo info = new NotifyInfo(getAction()+UseSkill,map);
		notifyObservers(info);           //通知所有卡片对象，被动技能发动		
	}
	
	@Override
	public String getCode() {
		// TODO Auto-generated method stub
		return code;
	}

	@Override
	public Integer getConsume() {
		// TODO Auto-generated method stub
		return consume;
	}
	
	@Override
	public void setConsume(Integer consume) {
		// TODO Auto-generated method stub
		this.consume = consume;
	}
	
	public Integer getStyle() {
		return style;
	}

	public Integer getCooldown() {
		return cooldown;
	}

	public void setCooldown(Integer cooldown) {
		this.cooldown = cooldown;
	}
	
	public Integer getCooldownBout() {
		return cooldownBout;
	}

	public void setCooldownBout(Integer cooldownBout) {
		this.cooldownBout = cooldownBout;
	}

	public Integer getFunc() {
		return func;
	}

	@Override
	public Integer getVelocity() {
		// TODO Auto-generated method stub
		return velocity;
	}

	public LifeCard getOwner() {
		return owner;
	}
	
	@Override
	public void setOwner(LifeCard life) {
		// TODO Auto-generated method stub
		this.owner = life;
	}

	public String getName() {
		if(null==name)
			name = I18n.getMessage(this, "name");
		return name;
	}

	protected void setParameterTypeValidator(Class[] parameterTypeValidator) {
		this.parameterTypeValidator = parameterTypeValidator;
	}

	@Override
	public String getCType() {
		// TODO Auto-generated method stub
		return cType;
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

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}
	
	@Override
	public void notifyObservers(Object arg) {
		// TODO Auto-generated method stub
		super.setChanged();
		super.notifyObservers(arg);
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
	public Errors getErrors() {
		// TODO Auto-generated method stub
		return errors;
	}
	
	@Override
	public Boolean hasError() {
		// TODO Auto-generated method stub
		return errors.hasError();
	}
}
