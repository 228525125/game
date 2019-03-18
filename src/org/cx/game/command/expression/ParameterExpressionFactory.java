package org.cx.game.command.expression;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import org.cx.game.command.CommandBuffer;
import org.cx.game.core.IPlayer;
import org.cx.game.tools.XmlUtil;
import org.dom4j.Attribute;
import org.dom4j.Element;

public class ParameterExpressionFactory {

	public static ParameterExpression getInstance(String cmd, Element cmdEl, CommandBuffer buffer) {
		Element paraEl = cmdEl.element(XmlUtil.Command_Parameter);
		Attribute expression = paraEl.attribute(XmlUtil.Command_Parameter_Expression);
		
		if(null==expression){
			return new ParameterExpression(cmd, cmdEl);
		}
		
		try {
			Class clazz = Class.forName(expression.getText());
			Constructor<WithCacheCommandParameterExpression> con = clazz.getConstructor(String.class, Element.class, CommandBuffer.class);
			return con.newInstance(cmd, cmdEl, buffer);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
		
		
		/*String action = Expression.getAction(cmd);
		if("query".equals(action)){
			return new InteriorCommandParameterActionExpression(cmd,cmdEl);
		}else if("set".equals(action)){
			return new InteriorCommandParameterPropertyExpression(cmd, cmdEl);
		}else{
			return new InteriorCommandParameterBufferExpression(player, cmd, cmdEl);
		}*/
	}
	
	public static ParameterExpression getInstance(String cmd, Element cmdEl) {
		return new ParameterExpression(cmd, cmdEl);
	}
}
