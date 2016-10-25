package org.cx.game.out;

import java.util.Observable;
import java.util.Observer;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import org.cx.game.observer.NotifyInfo;

public class JsonOut extends Response implements Observer {
	
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
			"cards","password","context","decks","cardGroup","ground","useCard","commandBuffer",       //User
			"near",                  //Place
			"index",                 //ControlQueue.Place
			//"place",                 //Camp
			"strongHoldList","randomEntry",        //Ground
			"appendCardList","hashCode","containerPosition","useCardPolicy","policy",        //LifeCard
			"affectedList",                  //Skill
			"cardIDList","lifeList",                             //StrongHold
			"pri",                                              //Priority
			"locker",                                           //AttackLockBuff
			"conjureRange",                                     //MagicCard
			"unitNumber",                                       //HuntUnits
			"ruleParam",                                        //用于传递一些参数给rule，与前台无关
			"owner","intercepterList","intercepterMethod","validators","errors","decorator"                    //系统的
		});
		return JSONObject.fromObject(resp,config);
	}
}
