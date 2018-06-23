package org.cx.game.tools;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesUtil {

	public static String getConfigure(String name) {
		Properties prop = new Properties();
		
		InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream("resource/configure.properties");
		try {
			prop.load(in);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String path = prop.getProperty(name);
		return path;
	}
	
	public static void main(String[] args) {
		String value = getConfigure("area.path");
		System.out.println(value);
	}
}
