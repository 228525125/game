package org.cx.game.tools;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

//import org.apache.commons.lang.StringEscapeUtils;

public class I18n {

	private static Map<Locale, ResourceBundle> bundles = new java.util.HashMap<Locale, ResourceBundle>();
	private static I18n singleton = new I18n();
	private static Locale locale = Locale.ENGLISH;

	public static I18n getInstance() {
		return singleton;
	}
	
	/**
	 * 更改成某一个语言的支持
	 * 
	 * @param language
	 */
	public static void changeLocale(String language) {
		locale = new Locale(language);
	}
	
	public static Locale getLocale(){
		if(null!=locale)
			return locale;
		else
			return Locale.getDefault();
	}
	
	public static String getMessage(String key, Locale locale) {
		String ret = "";
		ResourceBundle bundle = bundles.get(locale);
		try {
			if (bundle == null) {
				bundle = ResourceBundle.getBundle("message", locale);
				if (bundle != null) {
					bundles.put(locale, bundle);
				}
			}
			ret = bundle.getString(key);
		} catch (java.util.MissingResourceException mre) {
			// mre.printStackTrace();
			ret = key;
		}
		return ret;
	}
	
	public static String getMessage(String key) {
		Locale locale = I18n.getLocale();
		return getMessage(key, locale);
	}
	
	public static String getMessage(Object object){
		String key = object.getClass().getName();
		Locale locale = I18n.getLocale();
		return getMessage(key, locale);
	}
	
	public static String getCardName(Object card, Integer id){
		String key = card.getClass().getName()+"."+id+".name";
		return getMessage(key);
	}
	
	public static String getMessage(Object object, String property){
		String key = object.getClass().getName()+"."+property;
		return getMessage(key);
	}
	
	public static void main(String[] args) {
		/*System.out.println(Locale.getDefault().getLanguage()+"_"+Locale.getDefault().getCountry());
		Locale [] ls = Locale.getAvailableLocales();		
		for(Locale l : ls)
			System.out.println("language:"+l.getLanguage()+" | country:"+l.getCountry());*/		
		//System.out.println(StringEscapeUtils.escapeJava("验证时发现了异常！"));
		
		System.out.println(I18n.getInstance().getClass().getName());
		
		System.out.println(I18n.getInstance().getMessage("org.cx.game.validator.ControlPowerValidator"));
	}
	
	
}
