package org.cx.game.tools;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map.Entry;

import org.cx.game.command.CommandBuffer;
import org.cx.game.command.expression.ParameterExpressionBuffer;
import org.cx.game.exception.OperatingException;
import org.cx.game.widget.treasure.EmpiricValue;
import org.cx.game.widget.treasure.Mineral;
import org.cx.game.widget.treasure.Resource;
import org.cx.game.widget.treasure.SkillCount;

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
	
	//----------------- Resource -------------------
	
	/**
	 * 将字符串表示的资源信息，转换为相应对象
	 * @param resString
	 * @return
	 */
	public static Resource stringToResource(String resString){
		String [] res = resString.split(",");
		return new Mineral(Integer.valueOf(res[0]), Integer.valueOf(res[1]), Integer.valueOf(res[2]), Integer.valueOf(res[3]));
	}

	/**
	 * 根据funType，对r1和r2进行函数运算，并返回结果
	 * @param funType 函数类型
	 * @param r1
	 * @param r2
	 * @return
	 */
	public static Resource operating(Integer funType, Resource r1, Resource r2) {
		Resource ret = null;
		switch (funType) {
		case Util.Sum:
			try {
				ret = Util.sum(r1, r2);
			} catch (OperatingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
			
		case Util.Sub:
			try {
				ret = Util.sub(r1, r2);
			} catch (OperatingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
				
		default:
			break;
		}
		
		return ret;
	}
	
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
	
	public static Resource operating(Integer funType, Resource r1, Integer number) {
		Resource ret = null;
		switch (funType) {
		case Util.Mul:
			try {
				ret = multiplicative(r1, number);
			} catch (OperatingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
			
		default:
			break;
		}
		
		return ret;
	}
	
	/**
	 * 两种资源相加，这里要保证r1和r2同属一种类型
	 * @param r1
	 * @param r2
	 * @return r1和r2不能为空，并且必须同属一种类型，否则返回null
	 */
	public static Resource sum(Resource r1, Resource r2) throws OperatingException {
		Resource ret = null;
		if(null!=r1 && null!=r2 && r1.getClass().equals(r2.getClass())){
			if (r1 instanceof EmpiricValue) {
				EmpiricValue ev1 = (EmpiricValue) r1;
				EmpiricValue ev2 = (EmpiricValue) r2;
				Integer ev = ev1.getValue()+ev2.getValue();
				ret = new EmpiricValue(ev);
			}else if (r1 instanceof SkillCount) {
				SkillCount sc1 = (SkillCount) r1;
				SkillCount sc2 = (SkillCount) r2;
				Integer sc = sc1.getCount()+sc2.getCount();
				ret = new SkillCount(sc);
			}else{
				Integer gold = r1.get(CommonIdentifier.Gold)+r2.get(CommonIdentifier.Gold);
				Integer stone = r1.get(CommonIdentifier.Stone)+r2.get(CommonIdentifier.Stone);
				Integer wood = r1.get(CommonIdentifier.Wood)+r2.get(CommonIdentifier.Wood);
				Integer ore = r1.get(CommonIdentifier.Ore)+r2.get(CommonIdentifier.Ore);
				ret = new Mineral(gold,stone,wood,ore);
			}
		}else{
			throw new OperatingException();
		}
		
		return ret;
	}
	
	/**
	 * 两种资源求差，r1 - r2，这里要保证r1和r2同属一种类型；
	 * @param r1
	 * @param r2
	 * @return r1和r2不能为空，并且必须同属一种类型，否则返回null
	 */
	public static Resource sub(Resource r1, Resource r2) throws OperatingException {
		Resource ret = null;
		if(null!=r1 && null!=r2 && r1.getClass().equals(r2.getClass())){
			if (r1 instanceof EmpiricValue) {
				EmpiricValue ev1 = (EmpiricValue) r1;
				EmpiricValue ev2 = (EmpiricValue) r2;
				Integer ev = ev1.getValue() - ev2.getValue();
				ret = new EmpiricValue(ev);
			}else if (r1 instanceof SkillCount) {
				SkillCount sc1 = (SkillCount) r1;
				SkillCount sc2 = (SkillCount) r2;
				Integer sc = sc1.getCount() - sc2.getCount();
				ret = new SkillCount(sc);
			}else{
				Integer gold = r1.get(CommonIdentifier.Gold) - r2.get(CommonIdentifier.Gold);
				Integer stone = r1.get(CommonIdentifier.Stone) - r2.get(CommonIdentifier.Stone);
				Integer wood = r1.get(CommonIdentifier.Wood) - r2.get(CommonIdentifier.Wood);
				Integer ore = r1.get(CommonIdentifier.Ore) - r2.get(CommonIdentifier.Ore);
				ret = new Mineral(gold,stone,wood,ore);
			}
		}else{
			throw new OperatingException();
		}
		
		return ret;
	}
	
	/**
	 * 资源做乘法
	 * @param r1 
	 * @param number 倍数
	 * @return
	 * @throws OperatingException
	 */
	public static Resource multiplicative(Resource r1, Integer number) throws OperatingException {
		Resource ret = null;
		if(null!=r1 && null!=number){
			if (r1 instanceof EmpiricValue) {
				EmpiricValue ev1 = (EmpiricValue) r1;
				Integer ev = ev1.getValue() * number;
				ret = new EmpiricValue(ev);
			}else if (r1 instanceof SkillCount) {
				SkillCount sc1 = (SkillCount) r1;
				Integer sc = sc1.getCount() * number;
				ret = new SkillCount(sc);
			}else{
				Integer gold = r1.get(CommonIdentifier.Gold) * number;
				Integer stone = r1.get(CommonIdentifier.Stone) * number;
				Integer wood = r1.get(CommonIdentifier.Wood) * number;
				Integer ore = r1.get(CommonIdentifier.Ore) * number;
				ret = new Mineral(gold,stone,wood,ore);
			}
		}else{
			throw new OperatingException();
		}
		
		return ret;
	}
	
	/**
	 * 绝对值小于，r1<r2，例如判断CallConsume时，会用到；
	 * @param r1
	 * @param r2
	 * @return
	 */
	public static Boolean absoluteLessThan(Resource r1, Resource r2) {
		Boolean ret = true;
		for(Entry<Integer,Integer> entry : r1.entrySet()){
			Integer r1Type = entry.getKey();
			Integer r1Value = Math.abs(entry.getValue());
			Integer r2Value = Math.abs(r2.get(r1Type));
			if(r1Value<r2Value){
				ret = true;
			}else{
				ret = false;
				break;
			}
		}
		return ret;
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
