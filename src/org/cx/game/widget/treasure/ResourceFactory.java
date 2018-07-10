package org.cx.game.widget.treasure;

public class ResourceFactory {

	/**
	 * 
	 * @param resString 格式：r10,0,0,0 或 e-100
	 * @return
	 */
	public static Resource getInstance(String resString){
		Resource resource = null;
		String type = resString.substring(0, 1);
		if("r".equals(type)){
			resource = new Resource(resString.substring(1));
		}
		
		if("e".equals(type)){
			resource = new EmpiricValue(Integer.valueOf(resString.substring(1)));
		}
		
		if("s".equals(type)){
			resource = new SkillCount(Integer.valueOf(resString.substring(1)));
		}
			
		return resource;
	}
}
