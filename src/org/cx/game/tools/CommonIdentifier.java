package org.cx.game.tools;

public class CommonIdentifier {
	
	//----------------- NotifyInfo -----------------//
	
	public final static String Player_Resource = "Player_Resource";  
	public final static String Player_Ration = "Player_Ration";   
	
	public final static String Building_Option_Execute = "Building_Option_Execute"; 	
	public final static String Building_Upgrade = "Building_Upgrade";  
	
	//public final static String Context_Control = "Context_Control";  
	public final static String Context_ControlQueue_Remove = "Context_ControlQueue_Remove";  
	public final static String Context_ControlQueue_Insert = "Context_ControlQueue_Insert";  
	public final static String Context_ControlQueue_Refurbish = "Context_ControlQueue_Refurbish";  
	public final static String Context_ControlQueue_Move = "Context_ControlQueue_Move";
	
	public final static String Command_Reload = "Command_Reload";
	
	public final static String Skill_Used = "Skill_Used";
	public final static String Option_Executed = "Option_Executed";
	
	//------------------ Treasure ------------------//
	
	public final static Integer Gold = 701;                    //金币
	public final static Integer Wood = 702;                    //木材
	public final static Integer Stone = 703;                   //石材
	public final static Integer Ore = 704;                     //矿石
	public final static Integer EmpiricValue = 710;            //经验值
	public final static Integer SkillCount = 720;              //技能点
	
	//---------------- Corps ----------------//
	
	public final static Integer Death_Status_Live = 0;         //战斗
	public final static Integer Death_Status_Death = 1;        //死亡
	public final static Integer Death_Status_Exist = 2;        //存在
	
	public static final Integer Move_Type_Walk = 141;          //步行
	public static final Integer Move_Type_Equitation = 142;    //骑行
	public static final Integer Move_Type_Drive = 143;         //驾驶
	public static final Integer Move_Type_Fly = 144;           //飞行
	public static final Integer Move_Type_Transmit = 145;           //传送
	
	public final static Integer Attack_Mode_Near = 115;   //近战肉搏
	public final static Integer Attack_Mode_Far = 116;    //远程射击
	public final static Integer Attack_Mode_EquitationFar = 117;  //骑射
	
}
