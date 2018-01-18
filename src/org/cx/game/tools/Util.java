package org.cx.game.tools;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.cx.game.command.CommandBuffer;
import org.cx.game.command.expression.ParameterExpressionBuffer;
import org.cx.game.widget.treasure.IResource;
import org.cx.game.widget.treasure.Resource;

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
	
	public final static int Precision = 2;
	
	/**
	 * 设置double精度
	 * @param value 
	 * @param scale 精度，舍入模式：四舍五入 - ROUND_HALF_UP
	 * @return
	 */
	public static Double round(Double value, int scale) {   
       BigDecimal bd = new BigDecimal(value);   
       bd = bd.setScale(scale, BigDecimal.ROUND_HALF_UP);   
       Double d = bd.doubleValue();   
       return d;
    }
	
	/**
	 * double类型相加，因为直接相加会导致小数点很多
	 * @param d1
	 * @param d2
	 * @return
	 */
	public static Double sum(Double d1,Double d2){
		BigDecimal bd1 = new BigDecimal(Double.toString(d1)); 
        BigDecimal bd2 = new BigDecimal(Double.toString(d2)); 
        return bd1.add(bd2).doubleValue(); 
	}
	
	/**
	 * double类型相减
	 * @param d1
	 * @param d2
	 * @return
	 */
	public static Double sub(Double d1,Double d2){ 
        BigDecimal bd1 = new BigDecimal(Double.toString(d1)); 
        BigDecimal bd2 = new BigDecimal(Double.toString(d2)); 
        return bd1.subtract(bd2).doubleValue(); 
    }
	
	/**
	 * double类型相乘
	 * @param d1
	 * @param d2
	 * @return
	 */
	public Double mul(Double d1,Double d2){ 
        BigDecimal bd1 = new BigDecimal(Double.toString(d1)); 
        BigDecimal bd2 = new BigDecimal(Double.toString(d2)); 
        return bd1.multiply(bd2).doubleValue(); 
    }
	
	/**
	 * double类型相除
	 * @param d1
	 * @param d2
	 * @return
	 */
	public Double div(Double d1,Double d2){
        BigDecimal bd1 = new BigDecimal(Double.toString(d1)); 
        BigDecimal bd2 = new BigDecimal(Double.toString(d2)); 
        return bd1.divide 
               (bd2,Precision,BigDecimal.ROUND_HALF_UP).doubleValue(); 
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
	private static Integer count = 1;
	
	/**
	 * 始终返回一个唯一的值
	 * @return
	 */
	public static Integer newCount(){
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
	
	public static IResource stringToResource(String resString){
		String [] res = resString.split(",");
		return new Resource(Integer.valueOf(res[0]), Integer.valueOf(res[1]), Integer.valueOf(res[2]), Integer.valueOf(res[3]));
	}
	
	public static void copyBuffer(ParameterExpressionBuffer peBuffer, CommandBuffer cBuffer){
		peBuffer.setPlayer(cBuffer.getPlayer());
		peBuffer.setGround(cBuffer.getGround());
		peBuffer.setPlace(cBuffer.getPlace());
		peBuffer.setBuilding(cBuffer.getBuilding());
		peBuffer.setOption(cBuffer.getOption());
		peBuffer.setCemetery(cBuffer.getCemetery());
		peBuffer.setTrickList(cBuffer.getTrickList());
		peBuffer.setCorps(cBuffer.getCorps());
		peBuffer.setSkill(cBuffer.getSkill());
		peBuffer.setTrick(cBuffer.getTrick());
	}

}
