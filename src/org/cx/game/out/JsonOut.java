package org.cx.game.out;

import java.util.HashMap;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import org.cx.game.observer.NotifyInfo;
import org.cx.game.widget.UseCard;

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
			"cards","password","context","decks","cardGroup","cemetery","ground","useCard","commandBuffer",       //User
			"near","trickList",                  //Place
			//"place",                 //Camp
			"appendCardList","hashCode",        //LifeCard
			"affectedList",                  //Skill
			"owner","intercepterList","validators","errors"                    //系统的
		});
		return JSONObject.fromObject(resp,config);
	}
}
