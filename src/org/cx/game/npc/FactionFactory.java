package org.cx.game.npc;

import java.util.HashMap;
import java.util.Map;

import org.cx.game.core.IPlayer;

public class FactionFactory {

	private static Map<Integer, IPlayer> map = new HashMap<Integer, IPlayer>();
	
	public static IPlayer getInstance(Integer id){
		if(map.containsKey(id)){
			return map.get(id);
		}else{
			IPlayer faction = new Faction(id);
			map.put(id, faction);
			return faction;
		}
	}
}
