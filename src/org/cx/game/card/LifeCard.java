package org.cx.game.card;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.cx.game.action.Activate;
import org.cx.game.action.Affected;
import org.cx.game.action.Attack;
import org.cx.game.action.Attacked;
import org.cx.game.action.Call;
import org.cx.game.action.Chuck;
import org.cx.game.action.Conjure;
import org.cx.game.action.Death;
import org.cx.game.action.IActivate;
import org.cx.game.action.IAffected;
import org.cx.game.action.IAttack;
import org.cx.game.action.IAttacked;
import org.cx.game.action.ICall;
import org.cx.game.action.IChuck;
import org.cx.game.action.IConjure;
import org.cx.game.action.IDeath;
import org.cx.game.action.IPick;
import org.cx.game.action.IUpgradeLife;
import org.cx.game.action.IMove;
import org.cx.game.action.IRenew;
import org.cx.game.action.IUpgrade;
import org.cx.game.action.Pick;
import org.cx.game.action.Move;
import org.cx.game.action.Renew;
import org.cx.game.action.UpgradeLife;
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
import org.cx.game.policy.DonePolicy;
import org.cx.game.policy.PolicyGroupFactory;
import org.cx.game.policy.GuardPolicy;
import org.cx.game.policy.IPolicyGroup;
import org.cx.game.policy.IPolicy;
import org.cx.game.rule.RuleGroupFactory;
import org.cx.game.tools.I18n;
import org.cx.game.widget.IContainer;
import org.cx.game.widget.IPlace;
import org.cx.game.widget.treasure.ITreasure;

/**
 * 所有生物卡的父类，
 * 注意：在子类的构造函数中要添加动作（如IAttack）的装饰器（AttackDecorator）
 * this.attack = new AttackDecorator(attack);
 * @author chenxian
 *
 */
public class LifeCard extends java.util.Observable implements ICard, Observable
{
	
	public final static Integer Life = 1007; 
	
	public LifeCard(Integer id) {
		// TODO Auto-generated constructor stub
		addObserver(JsonOut.getInstance());
		this.id = id;
		
		/*
		 * 初始化
		 */
		this.consume.put(IPlayer.Gold, 0);
		this.consume.put(IPlayer.Wood, 0);
		this.consume.put(IPlayer.Stone, 0);
		this.consume.put(IPlayer.Ore, 0);
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
	 * 初始生命值
	 */
	private Integer hp = 0;
	
	/**
	 * 初始生命值
	 * @return
	 */
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

	/**
	 * 人口
	 * @return
	 */
	public Integer getRation() {
		return ration;
	}

	public void setRation(Integer ration) {
		this.ration = ration;
	}
	
	/**
	 * 人数，LifeCard可以理解为一个小队
	 */
	private Integer nop = 1;

	/**
	 * 人数，LifeCard可以理解为一个小队
	 * @return
	 */
	public Integer getNop() {
		return nop;
	}

	public void setNop(Integer nop) {
		this.nop = nop;
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

	/**
	 * 初始攻击力
	 */
	private Integer atk=0;

	/**
	 * 初始攻击力
	 */
	public Integer getAtk() {
		// TODO 自动生成方法存根
		return atk;
	}
	
	public void setAtk(Integer atk){
		this.atk = atk;
	}
	
	/**
	 * 伤害
	 */
	private Integer dmg = 180081;
	
	/**
	 * 伤害
	 * @return
	 */
	public Integer getDmg() {
		return dmg;
	}

	public void setDmg(Integer dmg) {
		this.dmg = dmg;
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

	private Integer attackMode = IAttack.Mode_Near;
	
	/**
	 * 攻击模式，远/近
	 */
	public Integer getAttackMode() {
		return attackMode;
	}

	public void setAttackMode(Integer attackMode) {
		this.attackMode = attackMode;
	}

	private Boolean mobile = false;
	
	/**
	 * 移动攻击，例如，骑兵
	 * @return
	 */
	public Boolean getMobile() {
		return mobile;
	}

	public void setMobile(Boolean mobile) {
		this.mobile = mobile;
	}
	
	/**
	 * 初始防御力
	 */
	private Integer def = 0;
	
	/**
	 * 初始防御力
	 * @return
	 */
	public Integer getDef(){
		return this.def;
	}
	
	public void setDef(Integer def){
		this.def = def;
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
	
	protected List<IBuff> nexusBuffList = new ArrayList<IBuff>();
	
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
		
		getAttack().updateExtraAtk();
		getAttacked().updateExtraDef();
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("player", getPlayer());
		map.put("container", getContainer());
		map.put("card", this);
		map.put("buff", buff);
		map.put("position", getPosition());
		NotifyInfo info = new NotifyInfo(buff.getAction()+Effect,map);
		notifyObservers(info);
	}
	
	private static final String Invalid = "_Invalid";
	
	/**
	 * 该方法仅用于Buff.invalid
	 * @param buff
	 */
	public void removeBuff(IBuff buff){
		this.buffList.remove(buff);
		
		getAttack().updateExtraAtk();
		getAttacked().updateExtraDef();
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("player", getPlayer());
		map.put("container", getContainer());
		map.put("card", this);
		map.put("buff", buff);
		map.put("position", getPosition());
		NotifyInfo info = new NotifyInfo(buff.getAction()+Invalid,map);
		notifyObservers(info);
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
	 * 消耗资源
	 */
	private Map<String,Integer> consume = new HashMap<String,Integer>();
	
	public Map<String,Integer> getConsume() {
		// TODO Auto-generated method stub
		return consume;
	}
	
	public void setConsume(Map<String,Integer> consume) {
		// TODO Auto-generated method stub
		for(String resType : consume.keySet())
			getConsume().put(resType, consume.get(resType));
	}
	
	private Integer level = 1;
	
	/**
	 * 等级
	 * @return
	 */
	public Integer getLevel(){
		return level;
	}
	
	public void setLevel(Integer level){
		this.level = level;
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
	 * 职业
	 */
	public final static Integer Profession = 1006;
	
	/**
	 * 战士
	 */
	public final static Integer Profession_Soldier = 301;
	
	/**
	 * 法师
	 */
	public final static Integer Profession_Magic = 302;
	
	/**
	 * 牧师
	 */
	public final static Integer Profession_Pastor = 303;
	
	/**
	 * 圣骑士
	 */
	public final static Integer Profession_Paladin = 304;
	
	/**
	 * 猎人
	 */
	public final static Integer Profession_Hunter = 305;
	
	/**
	 * 盗贼
	 */
	public final static Integer Profession_Thief = 306;
	
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

	/**
	 * 
	 * @param code skill的id，这里为了兼容一部分程序，增加code也作为index
	 * @return
	 */
	public ISkill getSkill(Integer code){
		ISkill skill = null;
		if(code<skillList.size()){
			skill = (ISkill) skillList.get(code);
		}else{
			for(ISkill s : skillList){
				if(code.equals(s.getId()))
					skill = s;
			}
		}
		
		return skill;
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
		getAttack().updateExtraAtk();
		getAttacked().updateExtraDef();
	}
	
	public Boolean containsSkill(Class clazz){
		for(Object skill : this.skillList){
			if(skill.getClass().equals(clazz))
				return true;
		}
		return false;
	}
	
	private IPolicyGroup groupPolicy = null;
	
	public void setGroupPolicy(IPolicyGroup gp){
		this.groupPolicy = gp;
		this.groupPolicy.setOwner(this);
	}
	
	/**
	 * 使用AI自动操作
	 */
	public void automation(){
		while (true) {
			IPolicy policy = this.groupPolicy.getPolicy();
			if(null!=policy){
				policy.execute();
				
				if(policy instanceof GuardPolicy)
					break;
			}else
				break;
		}
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
	
	private Integer position = null;
	
	@Override
	public Integer getPosition() {
		// TODO Auto-generated method stub
		return position;
	}
	
	@Override
	public void setPosition(Integer position) {
		// TODO Auto-generated method stub
		this.position = position;
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
			activate = new Activate();
			activate.setActivation(activation);
			activate.setSpeed(speed);
			activate.setOwner(this);
		}
		return activate;
	}

	/**
	 * 攻击
	 */
	private IAttack attack = null;
	
	public IAttack getAttack() {
		if(null==attack){
			attack = new Attack();
			attack.setAtk(atk);
			attack.setDmg(dmg);
			attack.setRange(attackRange);
			attack.setLockChance(lockChance);
			attack.setMode(attackMode);
			attack.setOwner(this);
		}
		return attack;
	}
	
	/**
	 * 受到攻击,也是普通攻击的入口
	 */
	private IAttacked attacked = null;
	
	public IAttacked getAttacked() {
		if(null==attacked){
			attacked = new Attacked();
			attacked.setDef(def);
			attacked.setOwner(this);
		}
		return attacked;
	}
	
	/**
	 * 使用技能，注意：法师普通攻击不属于技能攻击
	 */
	private IConjure conjure = null;
	
	public IConjure getConjure() {
		if(null==conjure){
			conjure = new Conjure();
			conjure.setOwner(this);
		}
		return conjure;
	}

	/**
	 * 受到法术影响，法术攻击的入口
	 */
	private IAffected affected = null;
	
	public IAffected getAffected() {
		if(null==affected){
			affected = new Affected();
			affected.setOwner(this);
		}
		return affected;
	}
	
	/**
	 * 移动
	 */
	private IMove move = null;
	
	public IMove getMove() {
		if(null==move){
			move = new Move();
			move.setEnergy(energy);
			move.setFlee(fleeChance);
			move.setType(moveType);
			move.setHide(hide);
			move.setOwner(this);
		}
		return move;
	}

	/**
	 * 召唤
	 */
	private ICall call = null;
	
	public ICall getCall() {
		if(null==call){
			call = new Call();
			call.setConsume(consume);
			call.setRation(ration);
			call.setNop(nop);
			call.setOwner(this);
		}
		
		return call;
	}

	/**
	 * 刷新
	 */
	private IRenew renew = null;
	
	public IRenew getRenew(){
		if(null==renew){
			renew = new Renew();
			renew.setOwner(this);
		}
		return renew;
	}
	
	/**
	 * 死亡
	 */
	private IDeath death = null;
	
	public IDeath getDeath() {
		if(null==death){
			death = new Death();
			death.setHp(hp);
			death.setOwner(this);
		}
		return death;
	}

	/**
	 * 丢弃
	 */
	private IChuck chuck = null;

	public IChuck getChuck() {
		if(null==chuck){
			chuck = new Chuck();
			chuck.setOwner(this);
		}
		return chuck;
	}

	/**
	 * 升级
	 */
	protected IUpgrade upgrade = null;
	
	public IUpgrade getUpgrade() {
		if(null==upgrade){
			upgrade = new UpgradeLife();
			upgrade.setLevel(level);
			upgrade.setOwner(this);
		}
		return this.upgrade;
	}
	
	private IPick pick = null;
	
	public IPick getPick() {
		if(null==pick){
			pick = new Pick();
			pick.setOwner(this);
		}
		return this.pick;
	}
	
	/**
	 * 激活
	 * @param activate
	 * @throws RuleValidatorException
	 */
	public void activate(Boolean activate) throws RuleValidatorException {
		getActivate().execute(activate);
	}

	/**
	 * 攻击
	 * @param attacked 被攻击的卡片
	 */
	public void attack(LifeCard attacked) throws RuleValidatorException {
		getAttack().execute(attacked);
	}
	
	/**
	 * 受攻击
	 * @param attack
	 */
	public void attacked(LifeCard life, IAttack attack) throws RuleValidatorException {
		getAttacked().execute(life,attack);
	}
	
	/**
	 * 受到法术影响
	 * @param magic
	 */
	public void affected(IMagic magic) throws RuleValidatorException {
		getAffected().execute(magic);
	}
	
	/**
	 * 施法
	 * @param skill
	 * @param objects
	 */
	public void conjure(IActiveSkill skill, Object...objects) throws RuleValidatorException {
		getConjure().execute(skill, objects);
	}
	
	/**
	 * 移动到指定位置
	 * @param position 指定位置
	 */
	public void move(IPlace place) throws RuleValidatorException {
		getMove().execute(place);
	}
	
	/**
	 * 召唤
	 *
	 */
	public void call(IPlace place, Integer nop) throws RuleValidatorException {
		getCall().execute(place, nop);
	}
	
	/**
	 * 刷新
	 * @param place
	 * @throws RuleValidatorException
	 */
	public void renew(IPlace place) throws RuleValidatorException {
		getRenew().execute(place);
	}

	/**
	 * 死亡
	 */
	public void death() throws RuleValidatorException {
		// TODO 自动生成方法存根
		getDeath().execute();
	}
	
	/**
	 * 丢弃
	 */
	public void chuck() throws RuleValidatorException {
		// TODO Auto-generated method stub
		getChuck().execute();
	}
	
	/**
	 * 升级
	 * @throws RuleValidatorException
	 */
	public void upgrade() throws RuleValidatorException {
		getUpgrade().execute();
	}
	
	/**
	 * 拾取
	 * @param treasure
	 * @throws RuleValidatorException
	 */
	public void pick(ITreasure treasure) throws RuleValidatorException {
		getPick().execute(treasure);
	}
	
	/**
	 * 初始化状态
	 */
	public void initState() {
		// TODO Auto-generated method stub

		clearBuff();            //首先执行是因为，Buff.invalid会影响死者属性
		
		getActivate().setActivation(activation);
		
		getAttack().setMode(attackMode);
		
		getActivate().setSpeed(speed);
		getAttack().setLockChance(lockChance);
		
		getCall().setConsume(consume);
		getCall().setNop(nop);
		
		getMove().setEnergy(energy);
		getMove().setType(moveType);
		getMove().setFlee(fleeChance);
		getMove().setHide(hide);
		
		getDeath().setHp(hp);
		
		IUpgradeLife up = (IUpgradeLife) getUpgrade();
		
		up.setLevel(level);
		up.setEmpiricValue(0);
		
		setHide(false);
		
		List<IBuff> buffs = new ArrayList<IBuff>();     //与自己相关的buff，不是自己发起的buff，例如AttackLockBuff
		buffs.addAll(this.nexusBuffList);
		for(IBuff buff : buffs){
			buff.invalid();
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
	public Boolean contains(Integer tag) {
		// TODO Auto-generated method stub
		List<Integer> objectList = Context.queryForTag(tag);
		return objectList.contains(getId());
	}

	@Override
	public List<Integer> queryTagForCategory(Integer category) {
		// TODO Auto-generated method stub
		List<Integer> list1 =  Context.queryForCategory(category);
		List<Integer> list2 = Context.queryForObject(getId());
		list2.retainAll(list1);
		return list2;
	}
	
	@Override
	public List<Integer> queryTagForObject() {
		// TODO Auto-generated method stub
		return Context.queryForObject(getId());
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
