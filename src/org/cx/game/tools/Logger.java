package org.cx.game.tools;

import org.apache.log4j.PropertyConfigurator;

public class Logger
{
	public static org.apache.log4j.Logger logger; 
	
	static {
		//PropertyConfigurator.configure("C:/Users/贤/workspace/game/src/resource/log4j.lcf");
		PropertyConfigurator.configure("F:/CX/项目/MyEclipse/workspace101/game/src/resource/log4j.lcf");
		logger = org.apache.log4j.Logger.getRootLogger();
	}
}
