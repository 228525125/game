package org.cx.game.card;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.cx.game.action.AffectedDecorator;
import org.cx.game.action.AttackDecorator;
import org.cx.game.action.AttackedDecorator;
import org.cx.game.action.CallDecorator;
import org.cx.game.action.ChuckDecorator;
import org.cx.game.action.ConjureDecorator;
import org.cx.game.action.DeathDecorator;
import org.cx.game.action.IAffected;
import org.cx.game.action.IAttack;
import org.cx.game.action.IAttacked;
import org.cx.game.action.ICall;
import org.cx.game.action.IChuck;
import org.cx.game.action.IConjure;
import org.cx.game.action.IDeath;
import org.cx.game.action.IMove;
import org.cx.game.action.IRenew;
import org.cx.game.action.ISwap;
import org.cx.game.action.MoveDecorator;
import org.cx.game.action.RenewDecorator;
import org.cx.game.action.SwapDecorator;
import org.cx.game.card.skill.Accurate;
import org.cx.game.card.skill.ActiveSkill;
import org.cx.game.card.skill.AttackBack;
import org.cx.game.card.skill.AttackLock;
import org.cx.game.card.skill.Dodge;
import org.cx.game.card.skill.IActiveSkill;
import org.cx.game.card.skill.IBuff;
import org.cx.game.card.skill.IMagic;
import org.cx.game.card.skill.ISkill;
import org.cx.game.card.skill.Parry;
import org.cx.game.card.skill.ShortRangeAmerce;
import org.cx.game.card.skill.Thump;
import org.cx.game.core.IPlayer;
import org.cx.game.exception.RuleValidatorException;
import org.cx.game.intercepter.IIntercepter;
import org.cx.game.observer.NotifyInfo;
import org.cx.game.observer.Observable;
import org.cx.game.out.JsonOut;
import org.cx.game.policy.ILifeCardPolicy;
import org.cx.game.policy.IUseCardPolicy;
import org.cx.game.policy.LifeCardPolicy;
import org.cx.game.tools.Debug;
import org.cx.game.tools.I18n;
import org.cx.game.widget.IContainer;
import org.cx.game.widget.IControlQueue;
import org.cx.game.widget.IGround;
import org.cx.game.widget.IPlace;

/**
 * 所有生物卡的父类，
 * 注意：在子类的构造函数中要添加动作（如IAttack）的装饰器（AttackDecorator）
 * this.attack = new AttackDecorator(attack);
 * @author cx
 *
 */
public class LifeCard extends java.util.Observable implements ICard, Observable
{
	private Map<String,List<IIntercepter>> intercepterList = new HashMap<String,List<IIntercepter>>();
	
	public LifeCard(Integer id) {
		// TODO Auto-generated constructor stub
		addObserver(new JsonOut());
		this.id = id;
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
	 * 生命值
	 */
	private Integer hp = 0;
	
	public Integer getHp() {
		return hp;
	}

	public void setHp(Integer hp) {
		this.hp = hp;
	}

	/**
	 * 精力
	 */
	private Integer energy = 100;
	
	public Integer getEnergy() {
		return energy;
	}

	public void setEnergy(Integer energy) {
		this.energy = energy;
	}
	
	/**
	 * 能量
	 */
	private Integer power = Debug.isDebug ? IConjure.Max_Power : 0;

	public Integer getPower() {
		return power;
	}

	public void setPower(Integer power) {
		this.power = power;
	}

	/**
	 * 激活
	 */
	private Boolean activate = false;

	public Boolean getActivate() {
		if(Debug.isDebug)
			return Debug.activate;
		return activate;
	}

	public void setActivate(Boolean activate) {
		this.activate = activate;
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("player", getPlayer());
		map.put("container", getContainer());
		map.put("card", this);
		map.put("position", getContainerPosition());
		map.put("activate", activate);
		NotifyInfo info = new NotifyInfo(NotifyInfo.Card_LifeCard_State_Activate,map);
		notifyObservers(info);
		
		if(activate){
			getMove().setMoveable(true);
			getAttacked().setAttackBackChance(100); //恢复反击能力，每回合只有一次
		}else{
			getMove().setMoveable(false);
		}
	}
	
	/**
	 * 隐形状态
	 */
	private Boolean hide = false;

	public Boolean getHide() {
		return hide;
	}

	public void setHide(Boolean hide) {
		this.hide = hide;
		
		/*
		 * 隐身状态的改变在战场上才有意义
		 */
		if (getContainer() instanceof IGround) {
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("player", getPlayer());
			map.put("container", getContainer());
			map.put("card", this);
			map.put("position", getContainerPosition());
			map.put("hide", hide);
			NotifyInfo info = new NotifyInfo(NotifyInfo.Card_LifeCard_State_Hide,map);
			notifyObservers(info);
		}
	}
	
	/**
	 * 法术免疫状态
	 */
	private Boolean immuneMagic = false;

	public Boolean getImmuneMagic() {
		return immuneMagic;
	}

	public void setImmuneMagic(Boolean immuneMagic) {
		this.immuneMagic = immuneMagic;
	}
	
	/**
	 * 物理免疫状态
	 */
	private Boolean immunePhysical = false;

	public Boolean getImmunePhysical() {
		return immunePhysical;
	}

	public void setImmunePhysical(Boolean immunePhysical) {
		this.immunePhysical = immunePhysical;
	}

	/**
	 * 攻击力
	 */
	private Integer atk=0;
	
	public Integer getAtk() {
		// TODO 自动生成方法存根
		return atk;
	}
	
	public void setAtk(Integer atk){
		this.atk = atk;
	}
	
	/**
	 * 攻击范围
	 */
	private Integer range = 0;

	public Integer getRange() {
		return range;
	}

	public void setRange(Integer range) {
		this.range = range;
	}

	/**
	 * 免伤比 基准1.00
	 */
	private Double immuneDamageRatio=0d;
	
	public Double getImmuneDamageRatio() {
		return immuneDamageRatio;
	}

	public void setImmuneDamageRatio(Double damageChance) {
		this.immuneDamageRatio = damageChance;
	} 

	/**
	 * 卡片类型
	 */
	private Integer type = Type_Life;

	@Override
	public Integer getType() {
		// TODO Auto-generated method stub
		return type;
	}
	
	/**
	 * 反击概率 基准100.00
	 */
	private Integer attackBackChance = 100;
	
	public Integer getAttackBackChance() {
		return attackBackChance;
	}

	public void setAttackBackChance(Integer attackBackChance) {
		this.attackBackChance = attackBackChance;
	}

	/**
	 * 闪避概率 基准100.00
	 */
	private Integer dodgeChance = 0;

	public Integer getDodgeChance() {
		return dodgeChance;
	}

	public void setDodgeChance(Integer dodgeChance) {
		this.dodgeChance = dodgeChance;
	}
	
	/**
	 * 暴击几率 基准100.00
	 */
	private Integer thumpChance = 0;

	public Integer getThumpChance() {
		return thumpChance;
	}

	public void setThumpChance(Integer criticalChance) {
		this.thumpChance = criticalChance;
	}
	
	/**
	 * 命中 基准100.00
	 */
	private Integer accurateChance = 100;

	public Integer getAccurateChance() {
		return accurateChance;
	}

	public void setAccurateChance(Integer accurateChance) {
		this.accurateChance = accurateChance;
	}
	
	/**
	 * 速度，基准100，为什么速度是int型，因为在ControlQueue中使用int方便计算
	 */
	private Integer speedChance = 100;
	
	public Integer getSpeedChance() {
		return speedChance;
	}

	public void setSpeedChance(Integer speed) {
		this.speedChance = speed;
	}
	
	/**
	 * 格挡概率
	 */
	private Integer parryChance = 0;

	public Integer getParryChance() {
		return parryChance;
	}

	public void setParryChance(Integer parryChance) {
		this.parryChance = parryChance;
	}
	
	/**
	 * 逃脱几率
	 */
	private Integer fleeChance = 0;
	
	public Integer getFleeChance() {
		return fleeChance;
	}

	public void setFleeChance(Integer fleeChance) {
		this.fleeChance = fleeChance;
	}
	
	/**
	 * 锁定几率
	 */
	private Integer lockChance = 0;

	public Integer getLockChance() {
		return lockChance;
	}

	public void setLockChance(Integer lockChance) {
		this.lockChance = lockChance;
	}
	
	/**
	 * 发起方状态
	 */
	private List<IBuff> nexusBuffList = new ArrayList<IBuff>();
	
	public void addNexusBuff(IBuff buff){
		this.nexusBuffList.add(buff);
	}
	
	public void removeNexusBuff(IBuff buff){	
		this.nexusBuffList.remove(buff);
	}
	
	public List<IBuff> getNexusBuff(Class clazz){
		List<IBuff> ret = new ArrayList<IBuff>();
		List<IBuff> buffs = new ArrayList<IBuff>();
		buffs.addAll(nexusBuffList);
		for(IBuff buff : buffs)
			if(buff.getClass().equals(clazz))
				ret.add(buff);
		return ret;
	}
	
	public void clearNexusBuff(){
		List<IBuff> buffs = new ArrayList<IBuff>();
		buffs.addAll(nexusBuffList);
		for(IBuff buff : buffs){
			buff.invalid();
		}
	}

	/**
	 * 状态
	 */
	private List<IBuff> buffList = new ArrayList<IBuff>();
	
	public List<IBuff> getBuffList() {
		return buffList;
	}
	
	private static final String Effect = "_Effect";

	public void addBuff(IBuff buff){
		for(IBuff b : this.buffList){     //当添加一个已有的buff,并且不能叠加时，要先删除之前的buff
			if(b.getClass().equals(buff.getClass())&&!b.isDuplication()){
				removeBuff(b);
				break;
			}
		}
		
		this.buffList.add(buff);
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("player", getPlayer());
		map.put("container", getContainer());
		map.put("card", this);
		map.put("buff", buff);
		map.put("position", getContainerPosition());
		NotifyInfo info = new NotifyInfo(buff.getAction()+Effect,map);
		notifyObservers(info);
	}
	
	private static final String Invalid = "_Invalid";
	
	/**
	 * 该方法仅用于Buff.invalid
	 * @param buff
	 */
	public void removeBuff(IBuff buff){	
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("player", getPlayer());
		map.put("container", getContainer());
		map.put("card", this);
		map.put("buff", buff);
		map.put("position", getContainerPosition());
		NotifyInfo info = new NotifyInfo(buff.getAction()+Invalid,map);
		notifyObservers(info);

		this.buffList.remove(buff);
	}
	
	public List<IBuff> getBuff(Class clazz){
		List<IBuff> ret = new ArrayList<IBuff>();
		List<IBuff> buffs = new ArrayList<IBuff>();
		buffs.addAll(buffList);
		for(IBuff buff : buffs)
			if(buff.getClass().equals(clazz))
				ret.add(buff);
		return ret;
	}
	
	public void clearBuff(){
		List<IBuff> buffs = new ArrayList<IBuff>();
		buffs.addAll(buffList);
		for(IBuff buff : buffs){
			buff.invalid();
		}
	}
	
	public Boolean containsBuff(Class clazz){
		for(IBuff buff : this.buffList)
			if(buff.getClass().equals(clazz))
				return true;
		return false;
	}

	/**
	 * 卡片的星数
	 */
	private Integer star = 1;
	
	public Integer getStar() {
		return star;
	}

	public void setStar(Integer star) {
		this.star = star;
	}
	
	/**
	 * 消耗
	 */
	private Integer consume = 0;
	
	public Integer getConsume() {
		// TODO Auto-generated method stub
		return consume;
	}
	
	public void setConsume(Integer consume) {
		// TODO Auto-generated method stub
		this.consume = consume;
	}
	
	/**
	 * 人类
	 */
	public final static Integer Race_Human = 1;
	
	/**
	 * 天使
	 */
	public final static Integer Race_Angel = 2;
	
	/**
	 * 亡灵
	 */
	public final static Integer Race_Die = 3;
	
	/**
	 * 恶魔
	 */
	public final static Integer Race_Daimon = 4;
	
	/**
	 * 种族
	 */
	private Integer race;
	
	public Integer getRace() {
		return race;
	}

	public void setRace(Integer race) {
		this.race = race;
	}
	
	/**
	 * 技能
	 */
	private List<ISkill> skillList = new ArrayList<ISkill>();

	public List<ISkill> getSkillList() {
		return skillList;
	}
	
	public void setSkillList(List<ISkill> skillList) {
		for(ISkill skill : skillList)
			skill.setOwner(this);
		this.skillList = skillList;
	}

	public ISkill getSkill(Integer index){
		if(index<skillList.size())
			return (ISkill) skillList.get(index);
		else
			return null;
	}
	
	public IActiveSkill getActiveSkill(Integer index){
		ISkill skill = getSkill(index);
		if (skill instanceof IActiveSkill) {
			return (IActiveSkill) skill;
		}else{
			return null;
		}
	}
	
	public void addSkill(ISkill skill){
		skill.setOwner(this);
		skillList.add(skill);
	}
	
	public Boolean containsSkill(Class clazz){
		for(Object skill : this.skillList){
			if(skill.getClass().equals(clazz))
				return true;
		}
		return false;
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
	
	/**
	 * 攻击
	 */
	private IAttack attack;
	
	public IAttack getAttack() {
		return attack;
	}

	public void setAttack(IAttack attack) {
		attack.addIntercepter(new Accurate(IMagic.Style_physical, this));
		attack.addIntercepter(new Thump(IMagic.Style_physical, 150, this));
		//attack.addIntercepter(new AttackLock(IMagic.Style_physical, this));
		attack.addIntercepter(new ShortRangeAmerce(IMagic.Style_physical, 50, this));
		attack.setAccurateChance(accurateChance);
		attack.setAtk(atk);
		attack.setRange(range);
		attack.setSpeedChance(speedChance);
		attack.setThumpChance(thumpChance);
		attack.setLockChance(lockChance);
		attack.setOwner(this);
		this.attack = new AttackDecorator(attack);
	}
	
	/**
	 * 受到攻击,也是普通攻击的入口
	 */
	private IAttacked attacked;
	
	public IAttacked getAttacked() {
		return attacked;
	}

	public void setAttacked(IAttacked attacked) {
		attacked.addIntercepter(new Dodge(IMagic.Style_physical,this));
		attacked.addIntercepter(new AttackBack(IMagic.Style_physical,this));
		attacked.addIntercepter(new Parry(IMagic.Style_physical,this));
		attacked.setAttackBackChance(attackBackChance);
		attacked.setDodgeChance(dodgeChance);
		attacked.setImmuneDamageRatio(immuneDamageRatio);
		attacked.setParryChance(parryChance);
		attacked.setOwner(this);
		this.attacked = new AttackedDecorator(attacked);
	}
	
	/**
	 * 使用技能，注意：法师普通攻击不属于技能攻击
	 */
	private IConjure conjure;
	
	public IConjure getConjure() {
		return conjure;
	}

	public void setConjure(IConjure conjure) {
		conjure.setOwner(this);
		this.conjure = new ConjureDecorator(conjure);
	}

	/**
	 * 受到法术影响，法术攻击的入口
	 */
	private IAffected affected;
	
	public IAffected getAffected() {
		return affected;
	}

	public void setAffected(IAffected affected) {
		affected.setOwner(this);
		this.affected = new AffectedDecorator(affected);
	}

	/**
	 * 移动
	 */
	private IMove move;
	
	public IMove getMove() {
		return move;
	}

	public void setMove(IMove move) {
		move.setEnergy(energy);
		move.setFleeChance(fleeChance);
		move.setOwner(this);
		this.move = new MoveDecorator(move);
	}

	/**
	 * 交换
	 */
	private ISwap swap = null;
	
	public ISwap getSwap() {
		return swap;
	}

	public void setSwap(ISwap swap) {
		swap.setOwner(this);
		this.swap = new SwapDecorator(swap);
	}

	/**
	 * 召唤
	 */
	private ICall call = null;
	
	public ICall getCall() {
		return call;
	}

	public void setCall(ICall call) {
		call.setConsume(consume);
		call.setOwner(this);
		this.call = new CallDecorator(call);
	}
	
	/**
	 * 刷新
	 */
	private IRenew renew = null;
	
	public IRenew getRenew(){
		return renew;
	}
	
	public void setRenew(IRenew renew){
		renew.setOwner(this);
		this.renew = new RenewDecorator(renew);
	}
	
	/**
	 * 死亡
	 */
	private IDeath death = null;
	
	public IDeath getDeath() {
		return death;
	}

	public void setDeath(IDeath death) {
		death.setHp(hp);
		death.setOwner(this);
		this.death = new DeathDecorator(death);
	}
	
	/**
	 * 丢弃
	 */
	private IChuck chuck = null;

	public IChuck getChuck() {
		return chuck;
	}

	public void setChuck(IChuck chuck) {
		chuck.setOwner(this);
		this.chuck = new ChuckDecorator(chuck);
	}

	/**
	 * 攻击
	 * @param attacked 被攻击的卡片
	 */
	public void attack(LifeCard attacked) throws RuleValidatorException {
		attack.action(attacked);
	}
	
	/**
	 * 受攻击
	 * @param attack
	 */
	public void attacked(LifeCard attack) throws RuleValidatorException {
		attacked.action(attack);
	}
	
	/**
	 * 受到法术影响
	 * @param magic
	 */
	public void affected(IMagic magic) throws RuleValidatorException {
		affected.action(magic);
	}
	
	/**
	 * 施法
	 * @param skill
	 * @param objects
	 */
	public void conjure(IActiveSkill skill, Object...objects) throws RuleValidatorException {
		conjure.action(skill, objects);
	}
	
	/**
	 * 移动到指定位置
	 * @param position 指定位置
	 */
	public void move(IPlace place) throws RuleValidatorException {
		move.action(place);
	}
	
	/**
	 * 召唤
	 *
	 */
	public void call(IPlace place) throws RuleValidatorException {
		call.action(place);
	}
	
	/**
	 * 刷新
	 * @param place
	 * @throws RuleValidatorException
	 */
	public void renew(IPlace place) throws RuleValidatorException {
		renew.action(place);
	}

	/**
	 * 死亡
	 */
	public void death() throws RuleValidatorException {
		// TODO 自动生成方法存根
		death.action();
	}
	
	/**
	 * 交换
	 * @param life 用于交换的卡片
	 */
	public void swap(LifeCard life) throws RuleValidatorException {
		swap.action(life);
	}
	
	/**
	 * 丢弃
	 */
	public void chuck() throws RuleValidatorException {
		// TODO Auto-generated method stub
		chuck.action();
	}
	
	/**
	 * 初始化状态
	 */
	public void initState() {
		// TODO Auto-generated method stub

		this.attack.setAtk(atk);
		this.attack.setAccurateChance(accurateChance);
		this.attack.setThumpChance(thumpChance);
		this.attack.setSpeedChance(IControlQueue.consume);
		this.attack.setLockChance(lockChance);
		
		this.attacked.setImmuneDamageRatio(immuneDamageRatio);
		this.attacked.setAttackBackChance(attackBackChance);
		this.attacked.setDodgeChance(dodgeChance);
		this.attacked.setParryChance(parryChance);
		
		this.conjure.setPower(power);
		
		this.call.setConsume(consume);
		
		this.move.setEnergy(energy);
		this.move.setFleeChance(fleeChance);
		
		this.death.setHp(hp);
		
		setHide(false);
		
		clearBuff();
		
		for(ISkill skill : skillList){            //刷新技能冷却时间
			if (skill instanceof ActiveSkill) {
				ActiveSkill as = (ActiveSkill) skill;
				as.setCooldownBout(0);
			}
		}
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
	public void clear() {
		// TODO Auto-generated method stub
		intercepterList.clear();
	}

	@Override
	public Map<String,List<IIntercepter>> getIntercepterList() {
		// TODO Auto-generated method stub
		return intercepterList;
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
	
	private ILifeCardPolicy policy = new LifeCardPolicy();
	
	public ILifeCardPolicy getPolicy() {
		return policy;
	}
	
}
