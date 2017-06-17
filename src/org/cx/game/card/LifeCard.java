package org.cx.game.card;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.cx.game.action.Activate;
import org.cx.game.action.ActivateDecorator;
import org.cx.game.action.Affected;
import org.cx.game.action.AffectedDecorator;
import org.cx.game.action.Attack;
import org.cx.game.action.AttackDecorator;
import org.cx.game.action.Attacked;
import org.cx.game.action.AttackedDecorator;
import org.cx.game.action.Call;
import org.cx.game.action.CallDecorator;
import org.cx.game.action.Chuck;
import org.cx.game.action.ChuckDecorator;
import org.cx.game.action.Conjure;
import org.cx.game.action.ConjureDecorator;
import org.cx.game.action.Death;
import org.cx.game.action.DeathDecorator;
import org.cx.game.action.IActivate;
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
import org.cx.game.action.IUpgrade;
import org.cx.game.action.Move;
import org.cx.game.action.MoveDecorator;
import org.cx.game.action.Renew;
import org.cx.game.action.RenewDecorator;
import org.cx.game.action.Swap;
import org.cx.game.action.SwapDecorator;
import org.cx.game.action.LifeUpgrade;
import org.cx.game.action.UpgradeDecorator;
import org.cx.game.card.buff.IBuff;
import org.cx.game.card.magic.IMagic;
import org.cx.game.card.skill.IActiveSkill;
import org.cx.game.card.skill.ISkill;
import org.cx.game.core.Context;
import org.cx.game.core.IPlayer;
import org.cx.game.exception.RuleValidatorException;
import org.cx.game.intercepter.IIntercepter;
import org.cx.game.observer.NotifyInfo;
import org.cx.game.observer.Observable;
import org.cx.game.out.JsonOut;
import org.cx.game.policy.ILifeCardPolicy;
import org.cx.game.policy.IUseCardPolicy;
import org.cx.game.policy.LifeCardPolicy;
import org.cx.game.tag.ITag;
import org.cx.game.tools.Debug;
import org.cx.game.tools.I18n;
import org.cx.game.widget.IContainer;
import org.cx.game.widget.IControlQueue;
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
			name = I18n.getMessage(this, id, "name");
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
	 * 人口
	 */
	private Integer ration = 1;

	public Integer getRation() {
		return ration;
	}

	public void setRation(Integer ration) {
		this.ration = ration;
	}

	private Integer energy = 100;

	/**
	 * 精力、与移动范围相关
	 */
	public Integer getEnergy() {
		return energy;
	}

	public void setEnergy(Integer energy) {
		this.energy = energy;
	}
	
	private Integer moveType = IMove.Type_Walk;
	
	/**
	 * 移动类型
	 * @return
	 */
	public Integer getMoveType() {
		return moveType;
	}

	public void setMoveType(Integer moveType) {
		this.moveType = moveType;
	}
	
	private Boolean hide = false;

	/**
	 * 隐形状态
	 */
	public Boolean getHide() {
		return hide;
	}

	public void setHide(Boolean hide) {
		this.hide = hide;
	}
	
	private Boolean immuneMagic = false;

	/**
	 * 法术免疫状态
	 */
	public Boolean getImmuneMagic() {
		return immuneMagic;
	}

	public void setImmuneMagic(Boolean immuneMagic) {
		this.immuneMagic = immuneMagic;
	}
	
	private Boolean immunePhysical = false;

	/**
	 * 物理免疫状态
	 */
	public Boolean getImmunePhysical() {
		return immunePhysical;
	}

	public void setImmunePhysical(Boolean immunePhysical) {
		this.immunePhysical = immunePhysical;
	}

	private Integer atk=0;

	/**
	 * 攻击力
	 */
	public Integer getAtk() {
		// TODO 自动生成方法存根
		return atk;
	}
	
	public void setAtk(Integer atk){
		this.atk = atk;
	}
	
	private Integer attackRange = 0;

	/**
	 * 攻击范围
	 */
	public Integer getAttackRange() {
		return attackRange;
	}

	public void setAttackRange(Integer attackRange) {
		this.attackRange = attackRange;
	}

	private Integer attackMode = 0;
	
	/**
	 * 攻击模式，远/近
	 */
	public Integer getAttackMode() {
		return attackMode;
	}

	public void setAttackMode(Integer attackMode) {
		this.attackMode = attackMode;
	}
	
	private Integer speed = 100;
	
	/**
	 * 速度，基准100，为什么速度是int型，因为在ControlQueue中使用int方便计算
	 */
	public Integer getSpeed() {
		return speed;
	}

	public void setSpeed(Integer speed) {
		this.speed = speed;
	}
	
	private Integer fleeChance = 0;
	
	/**
	 * 逃脱几率
	 */
	public Integer getFleeChance() {
		return fleeChance;
	}

	public void setFleeChance(Integer fleeChance) {
		this.fleeChance = fleeChance;
	}
	
	private Integer lockChance = 0;

	/**
	 * 锁定几率
	 */
	public Integer getLockChance() {
		return lockChance;
	}

	public void setLockChance(Integer lockChance) {
		this.lockChance = lockChance;
	}
	
	private List<IBuff> nexusBuffList = new ArrayList<IBuff>();
	
	/**
	 * 发起方状态
	 */
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

	private List<IBuff> buffList = new ArrayList<IBuff>();

	/**
	 * 自身Buff
	 */
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
	
	public List<IBuff> getBuff(String className){
		List<IBuff> ret = new ArrayList<IBuff>();
		List<IBuff> buffs = new ArrayList<IBuff>();
		buffs.addAll(buffList);
		for(IBuff buff : buffs)
			if(buff.getClass().getName().equals(className))
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
	
	private Boolean hero = false;
	
	/**
	 * 是否为英雄卡
	 * @return
	 */
	public Boolean getHero() {
		return hero;
	}

	public void setHero(Boolean hero) {
		this.hero = hero;
	}

	/**
	 * 稀有度
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
	 * 种族
	 */
	public final static Integer Stirps = 1004;
	
	/**
	 * 人类
	 */
	public final static Integer Stirps_Human = 171;
	
	/**
	 * 天神
	 */
	public final static Integer Stirps_Angel = 173;
	
	/**
	 * 亡灵
	 */
	public final static Integer Stirps_Die = 172;
	
	/**
	 * 恶魔
	 */
	public final static Integer Stirps_Daimon = 174;
	
	/**
	 * 野兽
	 */
	public final static Integer Stirps_Beast = 175;
	
	/**
	 * 昆虫
	 */
	public final static Integer Stirps_Insect = 176;
	
	/**
	 * 植物
	 */
	public final static Integer Stirps_Plant = 177;
	
	/**
	 * 机械
	 */
	public final static Integer Stirps_Machine = 178;
	
	/**
	 * 龙
	 */
	public final static Integer Stirps_Dragon = 179;
	
	/**
	 * 鱼
	 */
	public final static Integer Stirps_Fish = 180;
	
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
	 * 激活状态
	 */
	private Boolean activation = false;
	
	private IActivate activate = null;
	
	/**
	 * 激活
	 * @return
	 */
	public IActivate getActivate() {
		if(null==activate){
			IActivate activate = new Activate();
			activate.setActivation(activation);
			activate.setSpeed(speed);
			activate.setOwner(this);
			this.activate = new ActivateDecorator(activate);
		}
		return activate;
	}

	public void setActivate(IActivate activate) {
		activate.setSpeed(speed);
		activate.setOwner(this);
		this.activate = new ActivateDecorator(activate);
	}

	/**
	 * 攻击
	 */
	private IAttack attack = null;
	
	public IAttack getAttack() {
		if(null==attack){
			IAttack attack = new Attack();
			attack.setAtk(atk);
			attack.setRange(attackRange);
			attack.setLockChance(lockChance);
			attack.setMode(attackMode);
			attack.setOwner(this);
			this.attack = new AttackDecorator(attack);
		}
		return attack;
	}

	public void setAttack(IAttack attack) {
		attack.setAtk(atk);
		attack.setRange(attackRange);
		attack.setLockChance(lockChance);
		attack.setMode(attackMode);
		attack.setOwner(this);
		this.attack = new AttackDecorator(attack);
	}

	/**
	 * 受到攻击,也是普通攻击的入口
	 */
	private IAttacked attacked = null;
	
	public IAttacked getAttacked() {
		if(null==attacked){
			IAttacked attacked = new Attacked();
			attacked.setOwner(this);
			this.attacked = new AttackedDecorator(attacked);
		}
		return attacked;
	}

	public void setAttacked(IAttacked attacked) {
		attacked.setOwner(this);
		this.attacked = new AttackedDecorator(attacked);
	}
	
	/**
	 * 使用技能，注意：法师普通攻击不属于技能攻击
	 */
	private IConjure conjure = null;
	
	public IConjure getConjure() {
		if(null==conjure){
			IConjure conjure = new Conjure();
			conjure.setOwner(this);
			this.conjure = new ConjureDecorator(conjure);
		}
		return conjure;
	}

	public void setConjure(IConjure conjure) {
		conjure.setOwner(this);
		this.conjure = new ConjureDecorator(conjure);
	}

	/**
	 * 受到法术影响，法术攻击的入口
	 */
	private IAffected affected = null;
	
	public IAffected getAffected() {
		if(null==affected){
			IAffected affected = new Affected();
			affected.setOwner(this);
			this.affected = new AffectedDecorator(affected);
		}
		return affected;
	}

	public void setAffected(IAffected affected) {
		affected.setOwner(this);
		this.affected = new AffectedDecorator(affected);
	}

	/**
	 * 移动
	 */
	private IMove move = null;
	
	public IMove getMove() {
		if(null==move){
			IMove move = new Move();
			move.setEnergy(energy);
			move.setFlee(fleeChance);
			move.setType(moveType);
			move.setHide(hide);
			move.setOwner(this);
			this.move = new MoveDecorator(move);
		}
		return move;
	}

	public void setMove(IMove move) {
		move.setEnergy(energy);
		move.setFlee(fleeChance);
		move.setType(moveType);
		move.setHide(hide);
		move.setOwner(this);
		this.move = new MoveDecorator(move);
	}

	/**
	 * 交换
	 */
	private ISwap swap = null;
	
	public ISwap getSwap() {
		if(null==swap){
			ISwap swap = new Swap();
			swap.setOwner(this);
			this.swap = new SwapDecorator(swap);
		}
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
		if(null==call){
			ICall call = new Call();
			call.setConsume(consume);
			call.setOwner(this);
			this.call = new CallDecorator(call);
		}
		
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
		if(null==renew){
			IRenew renew = new Renew();
			renew.setOwner(this);
			this.renew = new RenewDecorator(renew);
		}
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
		if(null==death){
			IDeath death = new Death();
			death.setHp(hp);
			death.setHplimit(hp);
			death.setOwner(this);
			this.death = new DeathDecorator(death);
		}
		return death;
	}

	public void setDeath(IDeath death) {
		death.setHp(hp);
		death.setHplimit(hp);
		death.setOwner(this);
		this.death = new DeathDecorator(death);
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
	
	/**
	 * 升级
	 */
	private IUpgrade upgrade = null;
	
	public IUpgrade getUpgrade() {
		if(null==upgrade){
			IUpgrade upgrade = new LifeUpgrade();
			upgrade.setOwner(this);
			this.upgrade = new UpgradeDecorator(upgrade);
		}
		return this.upgrade;
	}
	
	public void setUpgrade(IUpgrade upgrade) {
		upgrade.setOwner(this);
		this.upgrade = new UpgradeDecorator(upgrade);
	}
	
	/**
	 * 激活
	 * @param activate
	 * @throws RuleValidatorException
	 */
	public void activate(Boolean activate) throws RuleValidatorException {
		getActivate().action(activate);
	}

	/**
	 * 攻击
	 * @param attacked 被攻击的卡片
	 */
	public void attack(LifeCard attacked) throws RuleValidatorException {
		getAttack().action(attacked);
	}
	
	/**
	 * 受攻击
	 * @param attack
	 */
	public void attacked(LifeCard life, IAttack attack) throws RuleValidatorException {
		getAttacked().action(life,attack);
	}
	
	/**
	 * 受到法术影响
	 * @param magic
	 */
	public void affected(IMagic magic) throws RuleValidatorException {
		getAffected().action(magic);
	}
	
	/**
	 * 施法
	 * @param skill
	 * @param objects
	 */
	public void conjure(IActiveSkill skill, Object...objects) throws RuleValidatorException {
		getConjure().action(skill, objects);
	}
	
	/**
	 * 移动到指定位置
	 * @param position 指定位置
	 */
	public void move(IPlace place) throws RuleValidatorException {
		getMove().action(place);
	}
	
	/**
	 * 召唤
	 *
	 */
	public void call(IPlace place) throws RuleValidatorException {
		getCall().action(place);
	}
	
	/**
	 * 刷新
	 * @param place
	 * @throws RuleValidatorException
	 */
	public void renew(IPlace place) throws RuleValidatorException {
		getRenew().action(place);
	}

	/**
	 * 死亡
	 */
	public void death() throws RuleValidatorException {
		// TODO 自动生成方法存根
		getDeath().action();
	}
	
	/**
	 * 交换
	 * @param life 用于交换的卡片
	 */
	public void swap(LifeCard life) throws RuleValidatorException {
		getSwap().action(life);
	}
	
	/**
	 * 丢弃
	 */
	public void chuck() throws RuleValidatorException {
		// TODO Auto-generated method stub
		getChuck().action();
	}
	
	/**
	 * 升级
	 * @throws RuleValidatorException
	 */
	public void upgrade() throws RuleValidatorException {
		getUpgrade().action();
	}
	
	/**
	 * 初始化状态
	 */
	public void initState() {
		// TODO Auto-generated method stub

		clearBuff();            //首先执行是因为，Buff.invalid会影响死者属性
		
		getActivate().setActivation(activation);
		
		getAttack().setAtk(atk);
		getAttack().setMode(attackMode);
		
		getActivate().setSpeed(speed);
		getAttack().setLockChance(lockChance);
		
		getCall().setConsume(consume);
		
		getMove().setEnergy(energy);
		getMove().setType(moveType);
		getMove().setFlee(fleeChance);
		getMove().setHide(hide);
		
		getDeath().setHp(hp);
		getDeath().setHplimit(hp);
		
		setHide(false);
		
		List<IBuff> buffs = new ArrayList<IBuff>();     //与自己相关的buff，不是自己发起的buff，例如AttackLockBuff
		buffs.addAll(this.nexusBuffList);
		for(IBuff buff : buffs){
			buff.invalid();
		}
		
		/* 随从已取消主动技能，因此冷却也被取消
		for(ISkill skill : skillList){            //刷新技能冷却时间
			if (skill instanceof ActiveSkill) {
				ActiveSkill as = (ActiveSkill) skill;
				as.setCooldownBout(0);
			}
		}*/
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

	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		if (obj instanceof LifeCard) {
			LifeCard life = (LifeCard) obj;
			return getPlayId().equals(life.getPlayId());
		}else{
			return false;
		}		
	}
	
}
