package org.cx.game.core;

import java.util.ArrayList;
import java.util.List;

/**
 * 用于记录整场比赛的摄像机
 * @author chenxian
 *
 */
public class Camera {

	private List<Record> recordList = new ArrayList<Record>();
	
	private static Camera camera = null;
	
	private Camera() {
		// TODO Auto-generated constructor stub
	}
	
	public static Camera getInstance(){
		if(null==camera)
			camera = new Camera();
		return camera;
	}
	
	public void addRecord(Record record){
		recordList.add(record);
	}
	
	public Integer getNewSequence(){
		return recordList.size()+1;
	}
	
	/**
	 * 根据sequence获取记录，默认sequence=List.index+1，
	 * @param sequence
	 * @return
	 */
	public Record getRecord(Integer sequence){
		if(recordList.size()>=sequence)
			return recordList.get(sequence-1);
		return null;
	}
	
	/**
	 * 查询sequence大于greaterThan的记录
	 * @param greaterThan 基零
	 * @return
	 */
	public List<Record> query(Integer greaterThan){
		List<Record> list = new ArrayList<Record>();
		for(int i=greaterThan;i<recordList.size();i++){
			list.add(recordList.get(i));
		}
		
		return list;
	}
}
