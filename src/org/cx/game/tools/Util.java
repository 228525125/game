package org.cx.game.tools;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map.Entry;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.cx.game.exception.OperatingException;

public class Util {

	/**
	 * 替换，用于set方法
	 */
	public static final int Replace = 1;
	
	/**
	 * 求和，用于set方法
	 */
	public final static int Sum = 2;
	
	/**
	 * 求差，用于set方法
	 */
	public final static int Sub = 3;
	
	/**
	 * 乘法
	 */
	public final static int Mul = 4;
	
	/**
	 * 除法
	 */
	public final static int Div = 5;
	
	/**
	 * 默认精度
	 */
	public final static int Precision = 2;
	
	
	
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
	
	//----------------- Resource -------------------
	
	/**
	 * 根据funType，对r1和r2进行函数运算，并返回结果
	 * @param funType 函数类型
	 * @param num1
	 * @param num2
	 * @return
	 */
	public static Integer operating(Integer funType, Integer num1, Integer num2) {
		Integer ret = null;
		switch (funType) {
		case Util.Sum:
			ret = num1 + num2;
			break;
			
		case Util.Sub:
			ret = num1 - num2;
			break;
			
		default:
			break;
		}
		
		return ret;
	}
	
	/**
	 * 生成一个不重复的随机数序列；例如 max=3；list = [1,0,3,2]
	 * @param max
	 * @return
	 */
	public static List<Integer> generateRandomNumber(Integer max) {
		List<Integer> list = new ArrayList<Integer>();
		Random r = new Random();
		max += 1;
		
		while(list.size()<max){
			Integer num = r.nextInt(max);
			if(list.contains(num))
				continue;
			
			list.add(num);
		}
		
		return list;
		
	}
	
	/**
	 * 指定一个触发几率，并执行判断
	 * @param chance 基数：100
	 * @return
	 */
	public static Boolean isTrigger(Integer chance){
		java.util.Random r = new java.util.Random();
		Integer rand = r.nextInt(100);
		if(rand<=--chance)
			return true;
		else
			return false;
	}
	
	/**
	 * 生成一个随机数
	 * @param number 不能为0，例如number=2；只能输出0，1；
	 * @return
	 */
	public static Integer nextInt(Integer number){
		java.util.Random r = new java.util.Random();
		return r.nextInt(number);
	}
	
	/**
     * 利用正则表达式判断字符串是否是数字
     * @param str
     * @return
     */
    public static boolean isNumeric(String str) {
           Pattern pattern = Pattern.compile("[0-9]*");
           Matcher isNum = pattern.matcher(str);
           if( !isNum.matches() ){
               return false;
           }
           return true;
    }
    
    /**
     * 判断字符串是否为布尔值
     * @param str
     * @return
     */
    public static boolean isBoolean(String str) {
    	if("true".equals(str) || "false".equals(str))
    		return true;
    	else
    		return false;
    }
    
    //---------------------Ground---------------------------
    
    /**
     * 位置坐标间隔符
     */
    public static String space = "8008";
	
    /**
     * 数字和点的互换
     * @param point
     * @return 180081 = 1,1
     */
	public static Integer[] integerToPoint(Integer point){
		String [] points = point.toString().split(space);
		Integer x = Integer.valueOf(points[0]);
		Integer y = Integer.valueOf(points[1]);

		return new Integer[]{x,y}; 
	}
	
	/**
	 * 数字和点的互换
	 * @param x
	 * @param y
	 * @return 1,1 = 180081
	 */
	public static Integer pointToInteger(Integer x,Integer y){
		return Integer.valueOf(x+space+y);
	}
	
	public static void main(String[] args) {
		
	}
	
	/**
	 * 
	 * @param obj
	 * @return obj本身的Class，obj子类以及接口的Class
	
	public static List<Class> getClasses(Object obj){
		List<Class> list = new ArrayList<Class>();
		Class clazz = obj.getClass();
		list.add(clazz);
		
		for(Class c : clazz.getInterfaces())
			list.add(c);
		
		Class superClass = clazz.getSuperclass();
		while(null!=superClass){
			list.add(superClass);
			superClass = superClass.getSuperclass();
		}
		
		return list;
	} */

}
