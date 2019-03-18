package org.cx.game.tools;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.PropertyConfigurator;

public class Logger
{
	public static org.apache.log4j.Logger logger;
	
	static {
		Properties prop = new Properties();
		
		InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream("resource/log4j.properties");
		try {
			prop.load(in);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		PropertyConfigurator.configure(prop);
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
}
