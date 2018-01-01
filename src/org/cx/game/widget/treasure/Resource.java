package org.cx.game.widget.treasure;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.cx.game.tools.I18n;
import org.cx.game.validator.CallConsumeValidator;

public class Resource implements IResource {

	private Map<Integer, Integer> resource = new HashMap<Integer, Integer>();
	
	public Resource() {
		// TODO Auto-generated constructor stub
		this.resource.put(Gold, 0);
		this.resource.put(Wood, 0);
		this.resource.put(Stone, 0);
		this.resource.put(Ore, 0);
	}
	
	public Resource(Integer gold, Integer wood, Integer stone, Integer ore) {
		// TODO Auto-generated constructor stub
		this.resource.put(Gold, gold);
		this.resource.put(Wood, wood);
		this.resource.put(Stone, stone);
		this.resource.put(Ore, ore);
	}
	
	/**
	 * 
	 * @param resourceString 格式 G/W/S/O = '0,0,0,0'
	 */
	public Resource(String resourceString) {
		// TODO Auto-generated constructor stub
		String [] res = resourceString.split(",");
		
		this.resource.put(Gold, res.length>0 ? Integer.valueOf(res[0]) : 0);
		this.resource.put(Wood, res.length>1 ? Integer.valueOf(res[1]) : 0);
		this.resource.put(Stone, res.length>2 ? Integer.valueOf(res[2]) : 0);
		this.resource.put(Ore, res.length>3 ? Integer.valueOf(res[3]) : 0);
	}
	
	@Override
	public Integer get(Integer type) {
		// TODO Auto-generated method stub
		return this.resource.get(type);
	}
	
	@Override
	public void add(IResource resource){
		if(!resource.isEmpty()){
			for(Entry<Integer, Integer> entry : resource.entrySet()){
				Integer type = entry.getKey();
				Integer value = entry.getValue();
				add(type, value);
			}
		}
	}

	@Override
	public void add(Integer type, Integer resValue) {
		// TODO Auto-generated method stub
		if(0!=resValue){
			Integer value = null==this.resource.get(type) ? 0 : this.resource.get(type);
			value += resValue;
			this.resource.put(type, value);
		}
	}
	
	@Override
	public Set<Entry<Integer, Integer>> entrySet(){
		return this.resource.entrySet();
	}
	
	@Override
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
	
	@Override
	public Boolean absoluteLessThan(IResource res) {
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
		return get(Gold);
	}
	
	public Integer getWood(){
		return get(Wood);
	}
	
	public Integer getStone(){
		return get(Stone);
	}
	
	public Integer getOre(){
		return get(Ore);
	}
}
