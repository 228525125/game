package org.cx.game.observer;

public class NotifyInfo {
	
	public NotifyInfo(String type, Object info) {
		// TODO Auto-generated constructor stub
		this.type = type;
		this.info = info;
	}

	public final static String Player_Power = "Player_Power";
	
	public final static String Container_Remove = "Container_Remove";
	public final static String Container_Add = "Container_Add";
	
	public final static String Container_Ground_Place_Cemetery_Add = "Container_Ground_Place_Cemetery_Add";
	public final static String Container_Ground_Place_Cemetery_Remove = "Container_Ground_Place_Cemetery_Remove";
	
	public final static String Container_Ground_Place_TrickList_Add = "Container_Ground_Place_TrickList_Add";
	public final static String Container_Ground_Place_TrickList_Remove = "Container_Ground_Place_TrickList_Remove";
	
	public final static String Container_UseCard_Add = "Container_UseCard_Add";
	public final static String Container_UseCard_Remove = "Container_UseCard_Remove";
	
	public final static String Container_CardGroup_Remove = "Container_CardGroup_Remove";
	public final static String Container_CardGroup_Add = "Container_CardGroup_Add";
	
	public final static String Container_Ground_Add = "Container_Ground_Add";
	public final static String Container_Ground_Remove = "Container_Ground_Remove";
	public final static String Container_Ground_LoadMap = "Container_Ground_LoadMap";
	
	public final static String Container_Place_In = "Container_Place_In";
	public final static String Container_Place_Out = "Container_Place_Out";
	
	public final static String Card_LifeCard_Action_Attack = "Card_LifeCard_Action_Attack";
	public final static String Card_LifeCard_Action_Attacked = "Card_LifeCard_Action_Attacked";
	
	public final static String Card_LifeCard_Action_Conjure = "Card_LifeCard_Action_Conjure";
	public final static String Card_LifeCard_Action_Affected = "Card_LifeCard_Action_Affected";
	
	public final static String Card_LifeCard_Action_Call = "Card_LifeCard_Action_Call";
	public final static String Card_LifeCard_Action_Renew = "Card_LifeCard_Action_Renew";
	public final static String Card_LifeCard_Action_Death = "Card_LifeCard_Action_Death";
	public final static String Card_LifeCard_Mirage_Action_Death = "Card_LifeCard_Mirage_Action_Death";
	public final static String Card_LifeCard_Action_Upgrade = "Card_LifeCard_Action_Upgrade";
	public final static String Card_LifeCard_Action_Swap = "Card_LifeCard_Action_Swap";
	public final static String Card_LifeCard_Action_Dodge = "Card_LifeCard_Action_Dodge";
	public final static String Card_LifeCard_Action_Chuck = "Card_LifeCard_Action_Chuck";
	public final static String Card_LifeCard_Action_Move = "Card_LifeCard_Action_Move";
	
	public final static String Card_LifeCard_State_Activate = "Card_LifeCard_State_Activate";
	public final static String Card_LifeCard_State_Hide = "Card_LifeCard_State_Hide";
	public final static String Card_LifeCard_State_Hp = "Card_LifeCard_State_Hp";
	public final static String Card_LifeCard_State_Atk = "Card_LifeCard_State_Atk";
	public final static String Card_LifeCard_State_Range = "Card_LifeCard_State_Range";
	public final static String Card_LifeCard_State_ImmuneDamageRatio = "Card_LifeCard_State_ImmuneDamageRatio";	
	
	public final static String Card_TrickCard_Trigger = "Card_TrickCard_Trigger";
	public final static String Card_TrickCard_Setup = "Card_TrickCard_Setup";
	
	public final static String Card_MagicCard_Apply = "Card_MagicCard_Apply";		
	
	/*
	 * skill = "Skill_"+class.name; buff = "Buff_"+class.name;
	 */
	/*public final static String Card_LifeCard_Skill_Dodge = "Card_LifeCard_Skill_Dodge";
	public final static String Card_LifeCard_Skill_Accurate = "Card_LifeCard_Skill_Accurate";	
	public final static String Card_LifeCard_Skill_AttackLock = "Card_LifeCard_Skill_AttackLock";
	public final static String Card_LifeCard_Skill_AttackDizzy = "Card_LifeCard_Skill_AttackDizzy";
	public final static String Card_LifeCard_Skill_Deadly = "Card_LifeCard_Skill_Deadly";
	public final static String Card_LifeCard_Skill_Disarm = "Card_LifeCard_Skill_Disarm";
	public final static String Card_LifeCard_Skill_DoubleAttack = "Card_LifeCard_Skill_DoubleAttack";
	public final static String Card_LifeCard_Skill_FarHarmHalve = "Card_LifeCard_Skill_FarHarmHalve";
	public final static String Card_LifeCard_Skill_Parry = "Card_LifeCard_Skill_Parry";
	public final static String Card_LifeCard_Skill_Puncture = "Card_LifeCard_Skill_Puncture";
	public final static String Card_LifeCard_Skill_Speed = "Card_LifeCard_Skill_Speed";
	public final static String Card_LifeCard_Skill_SunderArmor = "Card_LifeCard_Skill_SunderArmor";
	public final static String Card_LifeCard_Skill_Thump = "Card_LifeCard_Skill_Thump";
	public final static String Card_LifeCard_Skill_Aid = "Card_LifeCard_Skill_Aid";
	public final static String Card_LifeCard_Skill_Violent = "Card_LifeCard_Skill_Violent";
	public final static String Card_LifeCard_Skill_Wild = "Card_LifeCard_Skill_Wild";
	public final static String Card_LifeCard_Skill_Gamble = "Card_LifeCard_Skill_Gamble";
	public final static String Card_LifeCard_Skill_Sprint = "Card_LifeCard_Skill_Sprint";
	public final static String Card_LifeCard_Skill_MagicShield = "Card_LifeCard_Skill_MagicShield";
	public final static String Card_LifeCard_Skill_MagicShield_Invalid = "Card_LifeCard_Skill_MagicShield_Invalid";
	public final static String Card_LifeCard_Skill_TransmitOut = "Card_LifeCard_Skill_TransmitOut";
	public final static String Card_LifeCard_Skill_TransmitIn = "Card_LifeCard_Skill_TransmitIn";
	public final static String Card_LifeCard_Skill_IceStorm = "Card_LifeCard_Skill_IceStorm";
	public final static String Card_LifeCard_Skill_Cure = "Card_LifeCard_Skill_Cure";
	public final static String Card_LifeCard_Skill_WoundHealing = "Card_LifeCard_Skill_WoundHealing";
	public final static String Card_LifeCard_Skill_WindRun = "Card_LifeCard_Skill_WindRun";
	public final static String Card_LifeCard_Skill_Snipe = "Card_LifeCard_Skill_Snipe";
	public final static String Card_LifeCard_Skill_DamageIncrease = "Card_LifeCard_Skill_DamageIncrease";
	public final static String Card_LifeCard_Skill_AimShoot = "Card_LifeCard_Skill_AimShoot";
	public final static String Card_LifeCard_Skill_AngerRoar = "Card_LifeCard_Skill_AngerRoar";
	public final static String Card_LifeCard_Skill_AssistAttack = "Card_LifeCard_Skill_AssistAttack";
	public final static String Card_LifeCard_Skill_AssistDefend = "Card_LifeCard_Skill_AssistDefend";
	public final static String Card_LifeCard_Skill_AutomateAmmo = "Card_LifeCard_Skill_AutomateAmmo";
	public final static String Card_LifeCard_Skill_Bump = "Card_LifeCard_Skill_Bump";
	public final static String Card_LifeCard_Skill_CallBear = "Card_LifeCard_Skill_CallBear";
	public final static String Card_LifeCard_Skill_DispelMagic = "Card_LifeCard_Skill_DispelMagic";
	public final static String Card_LifeCard_Skill_FreezeTrickSkill = "Card_LifeCard_Skill_FreezeTrickSkill";
	public final static String Card_LifeCard_Skill_Hide = "Card_LifeCard_Skill_Hide";
	public final static String Card_LifeCard_Skill_HunterTab = "Card_LifeCard_Skill_HunterTab";
	public final static String Card_LifeCard_Skill_IceArrow = "Card_LifeCard_Skill_IceArrow";
	public final static String Card_LifeCard_Skill_IceRing = "Card_LifeCard_Skill_IceRing";
	public final static String Card_LifeCard_Skill_IceVallum = "Card_LifeCard_Skill_IceVallum";
	public final static String Card_LifeCard_Skill_PrickTrickSkill = "Card_LifeCard_Skill_PrickTrickSkill";
	public final static String Card_LifeCard_Skill_QuickCure = "Card_LifeCard_Skill_QuickCure";
	public final static String Card_LifeCard_Skill_Sheep = "Card_LifeCard_Skill_Sheep";
	public final static String Card_LifeCard_Skill_ShieldHit = "Card_LifeCard_Skill_ShieldHit";
	public final static String Card_LifeCard_Skill_SpurtingSpear = "Card_LifeCard_Skill_SpurtingSpear";
	public final static String Card_LifeCard_Skill_TransmitBack = "Card_LifeCard_Skill_Sheep";
	public final static String Card_LifeCard_Skill_WarnAureole = "Card_LifeCard_Skill_WarnAureole";
	public final static String Card_LifeCard_Skill_StrikeBack = "Card_LifeCard_Skill_StrikeBack";
	
	public final static String Card_LifeCard_Skill_Buff_FreezeBuff = "Card_LifeCard_Skill_Buff_FreezeBuff";
	public final static String Card_LifeCard_Skill_Buff_GambleBuff = "Card_LifeCard_Skill_Buff_GambleBuff";
	public final static String Card_LifeCard_Skill_Buff_MagicShielBuff = "Card_LifeCard_Skill_Buff_MagicShielBuff";
	public final static String Card_LifeCard_Skill_Buff_AidBuff = "Card_LifeCard_Skill_Buff_AidBuff";
	public final static String Card_LifeCard_Skill_Buff_WoundHealingBuff = "Card_LifeCard_Skill_Buff_WoundHealingBuff";
	public final static String Card_LifeCard_Skill_Buff_SheepBuff = "Card_LifeCard_Skill_Buff_SheepBuff";
	public final static String Card_LifeCard_Skill_Buff_DizzyBuff = "Card_LifeCard_Skill_Buff_DizzyBuff";
	public final static String Card_LifeCard_Skill_Buff_WindRunBuff = "Card_LifeCard_Skill_Buff_WindRunBuff";
	public final static String Card_LifeCard_Skill_Buff_WarnAureoleBuff = "Card_LifeCard_Skill_Buff_WarnAureoleBuff";
	public final static String Card_LifeCard_Skill_Buff_DamageIncreaseBuff = "Card_LifeCard_Skill_Buff_DamageIncreaseBuff";
	public final static String Card_LifeCard_Skill_Buff_ImmuneBuff = "Card_LifeCard_Skill_Buff_ImmuneBuff";
	public final static String Card_LifeCard_Skill_Buff_MaimedBuff = "Card_LifeCard_Skill_Buff_MaimedBuff";
	public final static String Card_LifeCard_Skill_Buff_TiredAttackBuff = "Card_LifeCard_Skill_Buff_TiredAttackBuff";
	public final static String Card_LifeCard_Skill_Buff_AddAmmoBuff = "Card_LifeCard_Skill_Buff_AddAmmoBuff";
	public final static String Card_LifeCard_Skill_Buff_AngerRoarBuff = "Card_LifeCard_Skill_Buff_AngerRoarBuff";
	public final static String Card_LifeCard_Skill_Buff_AssistAttackBuff = "Card_LifeCard_Skill_Buff_AssistAttackBuff";
	public final static String Card_LifeCard_Skill_Buff_AssistDefendBuff = "Card_LifeCard_Skill_Buff_AssistDefendBuff";
	public final static String Card_LifeCard_Skill_Buff_CureTiredBuff = "Card_LifeCard_Skill_Buff_CureTiredBuff";
	public final static String Card_LifeCard_Skill_Buff_HideBuff = "Card_LifeCard_Skill_Buff_HideBuff";
	public final static String Card_LifeCard_Skill_Buff_HunterTabBuff = "Card_LifeCard_Skill_Buff_HunterTabBuff";
	public final static String Card_LifeCard_Skill_Buff_IceVallumBuff = "Card_LifeCard_Skill_Buff_IceVallumBuff";
	public final static String Card_LifeCard_Skill_Buff_MagicShieldBuff = "Card_LifeCard_Skill_Buff_MagicShieldBuff";
	public final static String Card_LifeCard_Skill_Buff_SpiritCureBuff = "Card_LifeCard_Skill_Buff_SpiritCureBuff";
	public final static String Card_LifeCard_Skill_Buff_TransmitBackBuff = "Card_LifeCard_Skill_Buff_TransmitBackBuff";
	
	public final static String Card_LifeCard_Skill_Trick_FreezeTrick = "Card_LifeCard_Skill_Trick_FreezeTrick";
	public final static String Card_LifeCard_Skill_Trick_PrickTrick = "Card_LifeCard_Skill_Trick_PrickTrick";*/
	
	public final static String Command_Error = "Command_Error";
	public final static String Command_Select = "Command_Select";
	public final static String Command_Show = "Command_Show";
	public final static String Command_Query_Call = "Command_Query_Call";
	public final static String Command_Query_Move = "Command_Query_Move";
	public final static String Command_Query_Attack = "Command_Query_Attack";
	public final static String Command_Query_Conjure = "Command_Query_Conjure";
	public final static String Command_Query_Swap = "Command_Query_Swap";
	public final static String Command_Query_Apply = "Command_Query_Apply";
	public final static String Command_Reload = "Command_Reload";
	
	public final static String Context_Control = "Context_Control";
	public final static String Context_Start = "Context_Start";
	public final static String Context_Deploy = "Context_Deploy";
	public final static String Context_Done = "Context_Done";
	public final static String Context_Finish = "Context_Finish";	
	public final static String Context_ControlQueue_Remove = "Context_ControlQueue_Remove";
	public final static String Context_ControlQueue_Insert = "Context_ControlQueue_Insert";
	public final static String Context_ControlQueue_Refurbish = "Context_ControlQueue_Refurbish";
	
	private String type;
	private Object info;
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Object getInfo() {
		return info;
	}
	public void setInfo(Object info) {
		this.info = info;
	}
	
}
