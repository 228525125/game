package org.cx.game.tools;

import java.io.File;

import org.apache.log4j.PropertyConfigurator;

public class Logger
{
	public static org.apache.log4j.Logger logger;
	
	static {
		PropertyConfigurator.configure("E:/workspace1/game/src/resource/log4j.lcf");
		logger = org.apache.log4j.Logger.getRootLogger();
	}
	
	public static void debug(String message) {
		logger.debug(message);
	}
	
	public static void debug(Object obj, String message) {
		logger.debug("["+obj.getClass().getName()+"] - "+message);
	}
	
	public static void info(String message) {
		logger.info(message);
	}
	
	public static void warn(String message) {
		logger.warn(message);
	}
	
	public static void error(String message) {
		logger.error(message);
	}
	
	public static void main(String[] args) {
		File file = new File("src/resource/log4j.lcf");
		System.out.println(file.getAbsolutePath());
		Logger.debug("test");
	}
}
