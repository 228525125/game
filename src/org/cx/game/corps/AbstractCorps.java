package org.cx.game.corps;

import java.util.List;

import org.cx.game.action.ActionProxyHelper;
import org.cx.game.action.IAction;
import org.cx.game.core.AbstractContext;
import org.cx.game.core.IPlayer;
import org.cx.game.exception.RuleValidatorException;
import org.cx.game.magic.IMagic;
import org.cx.game.magic.buff.IBuff;
import org.cx.game.magic.skill.ISkill;
import org.cx.game.tag.ITag;
import org.cx.game.tag.TagHelper;
import org.cx.game.tools.I18n;
import org.cx.game.tools.Util;
import org.cx.game.widget.IGround;
import org.cx.game.widget.AbstractPlace;
import org.cx.game.widget.treasure.IResource;
import org.cx.game.widget.treasure.ITreasure;
import org.cx.game.widget.treasure.Resource;

/**
 * 所有生物卡的父类，
 * 注意：在子类的构造函数中要添加动作（如IAttack）的装饰器（AttackDecorator）
 * this.attack = new AttackDecorator(attack);
 * @author chenxian
 *
 */
public abstract class AbstractCorps implements ITag
{
	public final static Integer Corps = 1007;
	
	public final static Integer Stirps = 1004;   //种族
	public final static Integer Stirps_Human = 171;  //人族
	public final static Integer Stirps_Angel = 173;  //天神
	public final static Integer Stirps_Die = 172;    //亡灵
	public final static Integer Stirps_Daimon = 174;  //恶魔
	public final static Integer Stirps_Beast = 175;   //野兽
	public final static Integer Stirps_Insect = 176;  //昆虫
	public final static Integer Stirps_Plant = 177;   //植物
	public final static Integer Stirps_Machine = 178;  //机械
	public final static Integer Stirps_Dragon = 179;   //龙
	public final static Integer Stirps_Fish = 180;     //鱼
	
	public final static Integer Profession = 1006;     //职业
	public final static Integer Profession_Soldier = 301;  //战士
	public final static Integer Profession_Magic = 302;   //法师
	public final static Integer Profession_Pastor = 303;  //牧师
	public final static Integer Profession_Paladin = 304;  //圣骑士
	public final static Integer Profession_Hunter = 305;   //猎人
	public final static Integer Profession_Thief = 306;    //盗贼
	
	public final static Integer Attack_Mode_Near = 115;   //近战攻击
	public final static Integer Attack_Mode_Far = 116;    //远程攻击
	
	public final static Integer Death_Status_Live = 0;         //战斗
	public final static Integer Death_Status_Death = 1;        //死亡
	public final static Integer Death_Status_Exist = 2;        //存在
	
	public static final Integer Move_Type_Walk = 141;    //步行
	public static final Integer Move_Type_Equitation = 142;  //骑行
	public static final Integer Move_Type_Drive = 143;   //驾驶
	public static final Integer Move_Type_Fly = 144;  //飞行
	public static final Integer Move_Type_Flash = 145;  //传送
	
	private Integer type = null;
	private Integer id = null;
	private Integer position = null;
	
	private IPlayer player = null;
	private IGround ground = null;
	
	public AbstractCorps(Integer type) {
		// TODO Auto-generated constructor stub
		this.type = type;
		this.id = Util.newCount();
	}
	
	public Integer getType() {
		return type;
	}
	
	/**
	 * 比赛中的ID，临时的
	 */
	public Integer getId() {
		// TODO Auto-generated method stub
		return id;
	}

	public IPlayer getPlayer() {
		// TODO Auto-generated method stub
		return player;
	}
	
	public void setPlayer(IPlayer player) {
		// TODO Auto-generated method stub
		this.player = player;
	}
	
	public Integer getPosition() {
		// TODO Auto-generated method stub
		return position;
	}
	
	public void setPosition(Integer position) {
		// TODO Auto-generated method stub
		this.position = position;
	}
	
	public IGround getGround() {
		return ground;
	}

	public void setGround(IGround ground) {
		this.ground = ground;
	}
	
	public abstract String getName();
	public abstract Integer getHp();
	public abstract Integer getRation();
	public abstract Integer getEnergy();	
	public abstract Integer getMoveType();
	public abstract Boolean getHide();
	public abstract Integer getAtk();
	public abstract Integer getDmg();
	public abstract Integer getAttackRange();
	public abstract Integer getAttackMode();
	public abstract Boolean getMobile();
	public abstract Integer getDef();
	public abstract Integer getSpeed();
	public abstract Integer getFleeChance();
	public abstract Integer getLockChance();
	public abstract Boolean getHero();	
	public abstract Integer getStar();	
	public abstract IResource getConsume();
	public abstract Integer getLevel();	
	
	public abstract List<IBuff> getNexusBuffList();
	/**
	 * 发起方状态
	 */
	public abstract void addNexusBuff(IBuff buff);
	public abstract void removeNexusBuff(IBuff buff);
	public abstract List<IBuff> getNexusBuff(Class clazz);
	public abstract void clearNexusBuff();
	
	/**
	 * 自身Buff
	 */
	public abstract List<IBuff> getBuffList();	
	public abstract IAction getAddBuffAction();
	public abstract IAction getRemoveBuffAction();
	public abstract List<IBuff> getBuff(Class clazz);
	public abstract List<IBuff> getBuff(String className);
	public abstract void clearBuff();
	public abstract Boolean containsBuff(Class clazz);
	
	public abstract List<ISkill> getSkillList();
	public abstract void setSkillList(List<ISkill> skillList);
	public abstract ISkill getSkill(Integer type);
	public abstract IAction getAddSkillAction();
	public abstract Boolean containsSkill(Class clazz);

	public abstract IAction getActivate();
	public abstract IAction getAttack();
	public abstract IAction getAttacked();
	public abstract IAction getConjure();
	public abstract IAction getAffected();
	public abstract IAction getMove();
	public abstract IAction getCall();
	public abstract IAction getDeath();
	public abstract IAction getChuck();
	public abstract IAction getUpgrade();
	public abstract IAction getPick();
	
	public void addBuff(IBuff buff) {
		IAction action = new ActionProxyHelper(getAddBuffAction());
		action.action(buff);
	}
	
	/**
	 * 该方法仅用于Buff.invalid
	 * @param buff
	 */
	public void removeBuff(IBuff buff) {
		IAction action = new ActionProxyHelper(getRemoveBuffAction());
		action.action(buff);
	}
	
	public void addSkill(ISkill skill) {
		IAction action = new ActionProxyHelper(getAddSkillAction());
		action.action(skill);
	}
	
	/**
	 * 激活
	 * @param activate
	 * @throws RuleValidatorException
	 */
	public void activate(Boolean activate) {
		IAction action = new ActionProxyHelper(getActivate());
		action.action(activate);
	}

	/**
	 * 攻击
	 * @param attacked 被攻击的卡片
	 */
	public void attack(AbstractCorps attacked) {
		IAction action = new ActionProxyHelper(getAttack());
		action.action(attacked);
	}
	
	/**
	 * 受攻击
	 * @param attack
	 */
	public void attacked(AbstractCorps corps, IAction attack) {
		IAction action = new ActionProxyHelper(getAttacked());
		action.action(corps,attack);
	}
	
	/**
	 * 受到法术影响
	 * @param magic
	 */
	public void affected(IMagic magic) {
		IAction action = new ActionProxyHelper(getAffected());
		action.action(magic);
	}
	
	/**
	 * 施法
	 * @param skill ActiveSkill
	 * @param objects
	 */
	public void conjure(ISkill skill, Object...objects) {
		IAction action = new ActionProxyHelper(getConjure());
		action.action(skill,objects);
	}
	
	/**
	 * 移动到指定位置
	 * @param position 指定位置
	 */
	public void move(AbstractPlace place) {
		IAction action = new ActionProxyHelper(getMove());
		action.action(place);
	}
	
	/**
	 * 召唤
	 *
	 */
	public void call(AbstractPlace place, Integer nop) {
		IAction action = new ActionProxyHelper(getCall());
		action.action(place,nop);
	}

	/**
	 * 死亡
	 */
	public void death() {
		IAction action = new ActionProxyHelper(getDeath());
		action.action();
	}
	
	/**
	 * 丢弃
	 */
	public void chuck() {
		IAction action = new ActionProxyHelper(getChuck());
		action.action();
	}
	
	/**
	 * 升级
	 * @throws RuleValidatorException
	 */
	public void upgrade() {
		IAction action = new ActionProxyHelper(getUpgrade());
		action.action();
	}
	
	/**
	 * 拾取
	 * @param treasure
	 * @throws RuleValidatorException
	 */
	public void pick(ITreasure treasure) {
		IAction action = new ActionProxyHelper(getPick());
		action.action(treasure);
	}
	
	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return id.intValue();
	}
	
	public Boolean contains(Integer tag) {
		// TODO Auto-generated method stub
		List<Integer> objectList = TagHelper.queryForTag(tag);
		return objectList.contains(getType());
	}

	public List<Integer> queryTagForCategory(Integer category) {
		// TODO Auto-generated method stub
		List<Integer> list1 =  TagHelper.queryForCategory(category);
		List<Integer> list2 = TagHelper.queryForObject(getType());
		list2.retainAll(list1);
		return list2;
	}
	
	public List<Integer> queryTagForObject() {
		// TODO Auto-generated method stub
		return TagHelper.queryForObject(getType());
	}

	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		if (obj instanceof AbstractCorps) {
			AbstractCorps corps = (AbstractCorps) obj;
			return getId().equals(corps.getId());
		}else{
			return false;
		}
	}
	
	/**
	 * 初始化状态
	 
	public void initState() {
		// TODO Auto-generated method stub

		clearBuff();            //首先执行是因为，Buff.invalid会影响死者属性
		
		getActivate().setActivation(activation);
		
		getAttack().setMode(attackMode);
		
		getActivate().setSpeed(speed);
		getAttack().setLockChance(lockChance);
		
		getCall().setConsume(consume);
		
		getMove().setEnergy(energy);
		getMove().setType(moveType);
		getMove().setFlee(fleeChance);
		getMove().setHide(hide);
		
		getDeath().setHp(hp);
		
		IUpgradeLife up = (IUpgradeLife) getUpgrade();
		
		up.setLevel(level);
		
		setHide(false);
		
		List<IBuff> buffs = new ArrayList<IBuff>();     //与自己相关的buff，不是自己发起的buff，例如AttackLockBuff
		buffs.addAll(this.nexusBuffList);
		for(IBuff buff : buffs){
			buff.invalid();
		}
	}*/
	
}
