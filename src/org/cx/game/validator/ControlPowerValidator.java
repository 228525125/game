package org.cx.game.validator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.cx.game.command.Command;
import org.cx.game.core.IContext;
import org.cx.game.core.IPlayer;
import org.cx.game.out.AbstractResponse;
import org.cx.game.tools.CommonIdentifier;
import org.cx.game.tools.I18n;
import org.cx.game.tools.XmlUtil;
import org.dom4j.Element;

/**
 * 执行命令前，验证是否具有控制权
 * @author chenxian
 *
 */
public class ControlPowerValidator extends Validator {

	private IPlayer player = null;
	private Command command = null;
	private List<String> commandsThatDoNotRequireControlList = new ArrayList<String>();
	
	public ControlPowerValidator(Command command, IPlayer player) {
		// TODO Auto-generated constructor stub
		this.player = player;
		this.command = command;
		
		loadCommandsThatDoNotRequireControl();
	}
	
	@Override
	public Boolean validate() {
		// TODO Auto-generated method stub
		IContext context = player.getContext();
		/*
		 * 创建游戏阶段不判断
		 */
		if(null==context)      //ready之后才有context
			return true;
		if(IContext.Status_Prepare.equals(context.getStatus()) || IContext.Status_Ready.equals(context.getStatus()))
			return true;
		else if(IContext.Status_Start.equals(context.getStatus()) && (this.commandsThatDoNotRequireControlList.contains(this.command.getClass().getName()) || player.equals(context.getControlPlayer()))) //start命令不判断
			return true;
		else{
			addMessage(I18n.getMessage(ControlPowerValidator.class.getName()));
			return false;
		}
	}
	
	private void loadCommandsThatDoNotRequireControl() {
		Element configEl = XmlUtil.getRoot("gameconfig.path");
		Element commandsEl = configEl.element(XmlUtil.GameConfig_CommandsThatDoNotRequireControl);
		for(Iterator it = commandsEl.elementIterator();it.hasNext();){
			Element el = (Element) it.next();
			String commandClassName = el.getText();
			this.commandsThatDoNotRequireControlList.add(commandClassName);
		}
	}
}
