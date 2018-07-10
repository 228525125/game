package org.cx.game.widget.treasure;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.cx.game.tools.CommonIdentifier;

public class Resource {

	private Map<Integer, Integer> resource = new HashMap<Integer, Integer>();
	
	public Resource() {
		// TODO Auto-generated constructor stub
		this.resource.put(CommonIdentifier.Gold, 0);
		this.resource.put(CommonIdentifier.Wood, 0);
		this.resource.put(CommonIdentifier.Stone, 0);
		this.resource.put(CommonIdentifier.Ore, 0);
	}
	
	public Resource(Integer gold, Integer wood, Integer stone, Integer ore) {
		// TODO Auto-generated constructor stub
		this.resource.put(CommonIdentifier.Gold, gold);
		this.resource.put(CommonIdentifier.Wood, wood);
		this.resource.put(CommonIdentifier.Stone, stone);
		this.resource.put(CommonIdentifier.Ore, ore);
	}
	
	/**
	 * 
	 * @param resourceString 格式 G/W/S/O = '0,0,0,0'
	 */
	public Resource(String resourceString) {
		// TODO Auto-generated constructor stub
		String [] res = resourceString.split(",");
		
		this.resource.put(CommonIdentifier.Gold, res.length>0 ? Integer.valueOf(res[0]) : 0);
		this.resource.put(CommonIdentifier.Wood, res.length>1 ? Integer.valueOf(res[1]) : 0);
		this.resource.put(CommonIdentifier.Stone, res.length>2 ? Integer.valueOf(res[2]) : 0);
		this.resource.put(CommonIdentifier.Ore, res.length>3 ? Integer.valueOf(res[3]) : 0);
	}
	
	public Integer get(Integer type) {
		// TODO Auto-generated method stub
		return this.resource.get(type);
	}
	
	/**
	 * 执行条件 get(type)=null 或 0!=resValue
	 * @param type
	 * @param resValue
	 */
	public void add(Resource resource){
		if(!resource.isEmpty()){
			for(Entry<Integer, Integer> entry : resource.entrySet()){
				Integer type = entry.getKey();
				Integer value = entry.getValue();
				add(type, value);
			}
		}
	}

	public void add(Integer type, Integer resValue) {
		// TODO Auto-generated method stub
		if(0!=resValue || null==this.resource.get(type)){
			Integer value = null==this.resource.get(type) ? 0 : this.resource.get(type);
			value += resValue;
			this.resource.put(type, value);
		}
	}
	
	public Set<Entry<Integer, Integer>> entrySet(){
		return this.resource.entrySet();
	}
	
	public Boolean isEmpty() {
		// TODO Auto-generated method stub
		Boolean ret = true;
		for(Entry<Integer,Integer> entry : entrySet()){
			if(!Integer.valueOf(0).equals(entry.getValue())){
				ret = false;
				break;
			}
		}
		return ret;
	}
	
	/**
	 * 绝对值小于，例如判断CallConsume时，会用到；
	 * @param res
	 * @return
	 */
	public Boolean absoluteLessThan(Resource res) {
		// TODO Auto-generated method stub
		Boolean ret = true;
		for(Entry<Integer,Integer> entry : res.entrySet()){
			Integer resType = entry.getKey();
			Integer resValue = Math.abs(entry.getValue());
			Integer curValue = Math.abs(get(resType));
			if(0<resValue){
				if(curValue<resValue){
					ret = true;
				}else{
					ret = false;
					break;
				}
			}
		}
		return ret;
	}
	
	public Integer getGold(){
		return get(CommonIdentifier.Gold);
	}
	
	public Integer getWood(){
		return get(CommonIdentifier.Wood);
	}
	
	public Integer getStone(){
		return get(CommonIdentifier.Stone);
	}
	
	public Integer getOre(){
		return get(CommonIdentifier.Ore);
	}
}
