package org.cx.game.tools;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.cx.game.command.CommandBuffer;
import org.cx.game.command.expression.ParameterExpressionBuffer;

public class Util {

	/**
	 * 四舍五入原则将double转换为int
	 * @param d
	 * @return
	 */
	public static Integer convertInteger(Double d){
		return Integer.parseInt(new java.text.DecimalFormat("0").format(d));
	}
	
	/**
	 * 将double转换为指定格式输出
	 * @param d
	 * @param format
	 * @return
	 */
	public static Double format(Double d, String format){
		DecimalFormat df = new java.text.DecimalFormat(format);
		return Double.valueOf(df.format(d));
	}
	
	/**
	 * 格式化日期输出
	 * @param date 
	 * @param pattern 格式例如："yyyy-MM-dd HH:mm:ss"
	 * @return
	 */
	public static String format(Date date, String pattern){
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		return sdf.format(date);
	}
	
	/**
	 * 格式化日期输出，默认格式为："yyyy-MM-dd HH:mm:ss"
	 * @param date
	 * @return
	 */
	public static String format(Date date){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(date);
	}
	
	/**
	 * 过滤出数字
	 * @param number
	 * @return
	 */
	public static String filterNumber(String number){
        number = number.replaceAll("[^(0-9)]", "");
        return number;
    }
	
	/**
	 * 过滤出字母
	 * @param alph
	 * @return
	 */
	public static String filterAlphabet(String alph){
        alph = alph.replaceAll("[^(A-Za-z)]", "");
        return alph;
    }
	
	/**
	 * 过滤出中文
	 * @param chin
	 * @return
	 */
	public static String filterChinese(String chin){
        chin = chin.replaceAll("[^(\\u4e00-\\u9fa5)]", "");
        return chin;
    }
	
	/**
	 * 判断整数
	 * @param integer
	 * @return
	 */
	public static Boolean isInteger(String integer){
		return integer.matches("^-?\\d+$");
	}
	
	/**
	 * 用于分配playId
	 */
	private static Long count = 0l;
	
	/**
	 * 始终返回一个唯一的值
	 * @return
	 */
	public static Long newCount(){
		return count+=1;
	}
	
	public static void main(String[] args) {
			String str = "";
			System.out.println(isInteger(str));
		
	}
	
	/**
	 * 首字大写
	 * @param s
	 * @return
	 */
	public static String toUpperCaseFirstOne(String s){
        if(Character.isUpperCase(s.charAt(0)))
            return s;
        else
            return (new StringBuilder()).append(Character.toUpperCase(s.charAt(0))).append(s.substring(1)).toString();
    }
	
	/**
	 * 判断是否为基本类型，增加string为基本类型
	 * @param clz
	 * @return
	 */
	public static boolean isWrapClass(Class clz) { 
        try {
           if(clz.equals(String.class))
        	   return true;
           return ((Class) clz.getField("TYPE").get(null)).isPrimitive();
        } catch (Exception e) { 
            return false; 
        }
    }
	
	public static void copyBuffer(ParameterExpressionBuffer peBuffer, CommandBuffer cBuffer){
		peBuffer.setPlayer(cBuffer.getPlayer());
		peBuffer.setContainer(cBuffer.getContainer());
		peBuffer.setPlace(cBuffer.getPlace());
		peBuffer.setCemetery(cBuffer.getCemetery());
		peBuffer.setTrickList(cBuffer.getTrickList());
		peBuffer.setCard(cBuffer.getCard());
		peBuffer.setSkill(cBuffer.getSkill());
		peBuffer.setTrick(cBuffer.getTrick());
	}

}
