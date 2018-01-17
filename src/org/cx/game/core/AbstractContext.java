package org.cx.game.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.UUID;

import org.cx.game.exception.RuleValidatorException;
import org.cx.game.observer.NotifyInfo;
import org.cx.game.out.ResponseFactory;
import org.cx.game.widget.ControlQueue;
import org.cx.game.widget.IControlQueue;
import org.cx.game.widget.IGround;

public abstract class AbstractContext extends Observable implements IContext
{	
	private String playNo = UUID.randomUUID().toString() ;           //比赛唯一编号
	private IControlQueue queue = new ControlQueue();
	private Long newPlayId = 1l;                          //用于记录本场比赛中生成的id 
	
	private AbstractPlayState playState = null;
	
	private List<IPlayer> playerList = new ArrayList<IPlayer>();
	
	private IPlayer controlPlayer=null;
	private IGround ground = null;
	
	public AbstractContext(IGround ground, IPlayer[] players) {
		// TODO Auto-generated constructor stub
		for(int i=0;i<players.length;i++){
			playerList.add(players[i]);
		}
		
		for(IPlayer player : playerList){
			this.queue.add(player);
		}
		
		this.ground = ground;
		
		addObserver(ResponseFactory.getResponse());
	}
	
	

	public IControlQueue getControlQueue() {
		return queue;
	}

	public void setPlayState(AbstractPlayState playState) {
		this.playState = playState;
		this.playState.setContext(this);
	}

	public void start() throws RuleValidatorException{
		this.playState.start();
	}
	
	public void deploy() throws RuleValidatorException{
		this.playState.deploy();
	}
	
	public void done() throws RuleValidatorException{
		this.playState.done();
	}
	
	public void finish() throws RuleValidatorException{
		this.playState.finish();
	}
	
	@Override
	public List<IPlayer> getPlayerList() {
		// TODO Auto-generated method stub
		return this.playerList;
	}
	
	/**
	 * 当前操作比赛的玩家对象
	 */
	public IPlayer getControlPlayer() {
		return controlPlayer;
	}

	public void setControlPlayer(IPlayer controlPlayer) {
		this.controlPlayer = controlPlayer;
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("control", controlPlayer);
		NotifyInfo info = new NotifyInfo(NotifyInfo.Context_Control,map);
		notifyObservers(info);
	}
	
	public void switchControl() throws RuleValidatorException{
		Object object = queue.out();
		
		setControlPlayer((IPlayer) object);
		
		addBout();
	}

	public String getPlayNo() {
		return playNo;
	}

	@Override
	public void notifyObservers(Object arg0) {
		// TODO Auto-generated method stub
		super.setChanged();
		super.notifyObservers(arg0);
	}

	@Override
	public Long newPlayId() {
		// TODO Auto-generated method stub
		return newPlayId++;
	}

	@Override
	public IGround getGround() {
		// TODO Auto-generated method stub
		return this.ground;
	}	
}
