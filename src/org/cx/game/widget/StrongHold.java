package org.cx.game.widget;

import java.util.ArrayList;
import java.util.List;

import org.cx.game.core.Context;
import org.cx.game.intercepter.Intercepter;

public abstract class StrongHold implements IStrongHold {

	private Integer position = 0;
	private Integer range = 1;
	private Integer bout = 1;
	private Integer boutCount = 0;
	private Context context = null;
	private IGround ground = null;
	private List<Integer> area = new ArrayList<Integer>();
	
	/**
	 * 
	 * @param position 位置
	 * @param range    范围
	 * @param bout     刷新一次需要的回合数
	 */
	public StrongHold(Integer position, Integer range, Integer bout) {
		// TODO Auto-generated constructor stub
		this.position = position;
		this.range = range;
		this.bout = bout;
		this.boutCount = bout;
	}

	@Override
	public void setContext(Context context) {
		this.context = context;
		
		context.addIntercepter(new Intercepter("addBout"){
			
			@Override
			public void after(Object[] args) {
				// TODO Auto-generated method stub
				if(bout==boutCount){					
					refurbish();
					boutCount = 0;
				}else
					boutCount++;
			}
		});
	}

	public void setGround(IGround ground) {
		this.ground = ground;
		
		this.area = ground.areaForDistance(position, range, IGround.Contain);
	}

	@Override
	public Integer getPosition() {
		return position;
	}
	
	@Override
	public Integer getRange() {
		return range;
	}

	@Override
	public Integer getBout() {
		return bout;
	}

	protected IPlace getUsablePlace() {
		for(Integer position : area){
			IPlace place = ground.getPlace(position); 
			if(!place.isDisable())
				return place;
		}
		return null;
	}
}
