package org.cx.game.ai;

import java.util.Map;

/**
 * 消息
 * @author chenxian
 *
 */
public class Telegram {

	public final static Integer MessageType_Fight = 8001;
	public final static Integer MessageType_Fight_Finish = 8002;
	public final static Integer MessageType_Guard = 8003;
	
	private AbstractAgent sender = null;
	private AbstractAgent receiver = null;
	private Integer messageType = null;
	private Map<String, Object> extraInfo = null;
	
	public Telegram(AbstractAgent sender, AbstractAgent receiver, Integer messageType, Map<String, Object> extraInfo) {
		// TODO Auto-generated constructor stub
		this.sender = sender;
		this.receiver = receiver;
		this.messageType = messageType;
		this.extraInfo = extraInfo;
	}
	
	public AbstractAgent getSender() {
		return sender;
	}
	public void setSender(AbstractAgent sender) {
		this.sender = sender;
	}
	public AbstractAgent getReceiver() {
		return receiver;
	}
	public void setReceiver(AbstractAgent receiver) {
		this.receiver = receiver;
	}
	public Integer getMessageType() {
		return messageType;
	}
	public void setMessageType(Integer messageType) {
		this.messageType = messageType;
	}
	public Map<String, Object> getExtraInfo() {
		return extraInfo;
	}
	public void setExtraInfo(Map<String, Object> extraInfo) {
		this.extraInfo = extraInfo;
	}
}
