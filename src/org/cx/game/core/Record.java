package org.cx.game.core;

public class Record {

	private String playNo = null;
	private Integer sequence = null;
	private String command = null;
	private String action = null;
	public String getPlayNo() {
		return playNo;
	}
	public void setPlayNo(String playNo) {
		this.playNo = playNo;
	}
	public Integer getSequence() {
		return sequence;
	}
	public void setSequence(Integer sequence) {
		this.sequence = sequence;
	}
	public String getCommand() {
		return command;
	}
	public void setCommand(String command) {
		this.command = command;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
}
