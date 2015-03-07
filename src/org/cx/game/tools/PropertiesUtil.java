package org.cx.game.tools;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.cx.game.widget.GroundFactory;

public class PropertiesUtil {

	public static String getConfigure(String name) throws IOException{
		Properties prop = new Properties();
		
		InputStream in = GroundFactory.class.getResourceAsStream("/resource/configure.properties");
		prop.load(in);
		String path = prop.getProperty(name);
		return path;
	}
}
