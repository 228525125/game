package org.cx.game.out;

import java.util.Observable;
import java.util.Observer;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import org.cx.game.observer.NotifyInfo;

public class JsonOut extends Response implements Observer {
	
	private static JsonOut jsonOut = null;
	
	public static JsonOut getInstance(){
		if(null==jsonOut)
			jsonOut = new JsonOut();
		return jsonOut;
	}
	
	private JsonOut() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		if (arg instanceof NotifyInfo) {
			NotifyInfo info = (NotifyInfo) arg;
			setAction(info.getType());
			setInfo(info.getInfo());
			Object result = convert(this);
			super.process.get().append(result.toString()+";");
			System.out.println(result);
		}
	}
	
	@Override
	public Object convert(Response resp) {
		// TODO Auto-generated method stub
		JsonConfig config = new JsonConfig();
		config.setExcludes(new String[]{
			"cards","password","decks","cardGroup","useCard","commandBuffer",       //User
			"attendantList","context","ground","heroList",              //Player
			"near",                  //Place
			"index",                 //ControlQueue.Place
			"strongHoldList","randomEntry","landformMap","emptyList","disableList","npcMap","policyMap","treasureMap",        //Ground
			"appendCardList","hashCode","containerPosition","useCardPolicy",        //LifeCard
			"affectedList",                  //Skill
			"movePath",                       //Move
			"cardIDList","lifeList",                             //StrongHold
			"pri",                                              //Priority
			"locker",                                           //AttackLockBuff
			"conjureRange",                                     //MagicCard
			"unitNumber",                                       //HuntUnits
			"controlLife","controlPlayer","player1","player2","controlQueue",          //Context
			"policy",                                           //PolicyGroup
			"messageSource",                                    //RuleGroup
			"ruleParam",                                        //用于传递一些参数给rule，与前台无关
			//,                                            //Building
			"owner","instance","intercepterList","intercepterMethod","validators","errors","decorator"                    //系统的
		});
		return JSONObject.fromObject(resp,config);
	}
}
