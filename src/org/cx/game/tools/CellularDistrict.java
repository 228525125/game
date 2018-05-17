package org.cx.game.tools;

import java.util.HashMap;
import java.util.Map;

import org.cx.game.widget.IGround;

/** 
 * 蜂窝小区，以1为中心，顺时针编号，编号最大限定为100000。 求任意两编号之间的最短距离。 两个相邻小区的距离为1 
 *  
 *  
 */
public class CellularDistrict {

	private int maxSeq = 0;  
    private Point firstPoint;  
    private Point secondPoint;  
    
    private static final Long OverPoint = 110010l;
    private static final Integer base = 100;
    
    private static Integer xBorder = 100;
    private static Integer yBorder = 100;
    
    private static Map<Integer, Long> coordinateMap = new HashMap<Integer, Long>();
    private static Map<Long, Integer> serialNumberMap = new HashMap<Long, Integer>();
    
    static {
    	initCoordinateSystem(getCenterPoint(xBorder,yBorder),Math.max(xBorder, yBorder));
    }
    
    /** 
     * 初始化蜂窝小区信息 
     *  
     * @param iMaxSeqValue 
     *            蜂窝小区的最大值编号，注：编号从1开始 
     * @return 成功返回0，失败返回-1 
     */  
    private int initCellularDistrict(int iMaxSeqValue) {  
        if (iMaxSeqValue > 0 && iMaxSeqValue <= 100000) {  
            this.maxSeq = iMaxSeqValue;  
            return 0;  
        }
        return -1;  
    }
    
    /** 
     * 计算出蜂窝小区指定两点（编号值）之间的最短距离 
     *  
     * @param iFirstValue 
     *            起点编号值 
     * @param iSecondValue 
     *            终点编号值 
     * @return 计算成功返回最短距离，失败返回-1 
     */  
    public int getShortestPathLength(int iFirstValue, int iSecondValue) {  
        if (0 < iFirstValue && iFirstValue <= this.maxSeq && 0 < iSecondValue  
                && iSecondValue <= this.maxSeq) {  
            firstPoint = new Point(iFirstValue);  
            secondPoint = new Point(iSecondValue);  
            return firstPoint.minus(secondPoint);  
        }  
        return -1;  
    }
    
    /** 
     * 清空相关信息 
     */  
    private void clear() {  
        maxSeq = 0;  
        firstPoint = null;  
        secondPoint = null;  
    }
    
    //--------------------- static ---------------------  这部分主要实现 坐标系 与 数字编号 的转换
    
    public static Integer getShortPathLength(Long origin, Long dest){
    	CellularDistrict cellularDistrict = new CellularDistrict();
    	cellularDistrict.initCellularDistrict(getMaxSerial(Math.max(xBorder, yBorder)));
		return cellularDistrict.getShortestPathLength(getSerialNumber(origin), getSerialNumber(dest));
	}
    
    /**
     * 将坐标转换成数字编号
     * @param coordinate 坐标
     * @return
     */
    public static Integer getSerialNumber(Long coordinate){
    	Integer [] ps = longToPoint(coordinate);
    	Long point = pointToLong(base+ps[0], base+ps[1]);
    	return serialNumberMap.get(point);
    }
    
    /**
     * 将数字编号转换成坐标
     * @param serialNumber 数字编号
     * @return
     */
    public static Long getCoordinate(Integer serialNumber){
    	Long point = coordinateMap.get(serialNumber);
    	Integer [] ps = longToPoint(point);
    	return pointToLong(ps[0]%base, ps[1]%base);
    }
    
    private static void addCoordinateMap(Integer serialNumber, Long point){
    	if(!isOver(point)){
			coordinateMap.put(serialNumber, point);
    		serialNumberMap.put(point, serialNumber);
		}
    }
    
    /**
     * 缓存坐标与数字编号的对应表
     * @param centerPoint 坐标系中心点
     * @param circleNumber 圈数
     */
    private static void initCoordinateSystem(Long centerPoint, Integer circleNumber){
    	
    	Integer n = 0;
    	Integer [] point = longToPoint(centerPoint);
    	
    	Long starPoint = pointToLong(base+point[0], base+point[1]);
    	n += 1;
		addCoordinateMap(n, starPoint);
    	
    	starPoint = getPosition(starPoint, IGround.Relative_Left);
		n += 1;
		addCoordinateMap(n, starPoint);
    	
    	for (int i = 1; i < circleNumber+1; i++) {
			for (int j = 1; j < i; j++) {
				starPoint = getPosition(starPoint, IGround.Relative_LeftTop);
				n += 1;
				addCoordinateMap(n, starPoint);
			}
			
			for (int j = 0; j < i; j++) {
				starPoint = getPosition(starPoint, IGround.Relative_RightTop);
				n += 1;
				addCoordinateMap(n, starPoint);
			}
			
			for (int j = 0; j < i; j++) {
				starPoint = getPosition(starPoint, IGround.Relative_Right);
				n += 1;
				addCoordinateMap(n, starPoint);
			}
			
			for (int j = 0; j < i; j++) {
				starPoint = getPosition(starPoint, IGround.Relative_RightBottom);
				n += 1;
				addCoordinateMap(n, starPoint);
			}
			
			for (int j = 0; j < i; j++) {
				starPoint = getPosition(starPoint, IGround.Relative_LeftBottom);
				n += 1;
				addCoordinateMap(n, starPoint);
			}
			
			for (int j = 0; j < i+1; j++) {
				starPoint = getPosition(starPoint, IGround.Relative_Left);
				n += 1;
				addCoordinateMap(n, starPoint);
			}
		}
    }
    
    private static Long getPosition(Long stand, Integer direction) {
		// TODO Auto-generated method stub
		Integer [] p1 = longToPoint(stand);
		Long ret = null;
		
		if(p1[1]%2==0){
			switch (direction) {
			case 10:
				ret = pointToLong(p1[0]-1, p1[1]-1);
				break;
			case 2:
				ret = pointToLong(p1[0], p1[1]-1);
				break;
			case 9:
				ret = pointToLong(p1[0]-1, p1[1]);
				break;
			case 3:
				ret = pointToLong(p1[0]+1, p1[1]);
				break;
			case 8:
				ret = pointToLong(p1[0]-1, p1[1]+1);
				break;
			case 4:
				ret = pointToLong(p1[0], p1[1]+1);
				break;

			default:
				break;
			}
		}else{
			switch (direction) {
			case 10:
				ret = pointToLong(p1[0], p1[1]-1);
				break;
			case 2:
				ret = pointToLong(p1[0]+1, p1[1]-1);
				break;
			case 9:
				ret = pointToLong(p1[0]-1, p1[1]);
				break;
			case 3:
				ret = pointToLong(p1[0]+1, p1[1]);
				break;
			case 8:
				ret = pointToLong(p1[0], p1[1]+1);
				break;
			case 4:
				ret = pointToLong(p1[0]+1, p1[1]+1);
				break;

			default:
				break;
			}
		}
		
		return ret ;
	}
    
    private static Long getCenterPoint(Integer xBorder, Integer yBorder){
    	return pointToLong(xBorder/2, yBorder/2);
    }
    
    private static Integer getMaxSerial(Integer circleNumber){
    	Integer number = 1;
    	for (int i = 1; i < circleNumber+1; i++) {
			number += i*6;
		}
    	return number;
    }
    
    public static Integer[] longToPoint(Long point){
		String [] points = point.toString().split(SpaceArithmetic.space);
		Integer x = Integer.valueOf(points[0]);
		Integer y = Integer.valueOf(points[1]);

		return new Integer[]{x,y}; 
	}
    
    public static Long pointToLong(Integer x,Integer y){
		if(x<1 || y<1)
			return OverPoint;
		else
			return Long.valueOf(x+SpaceArithmetic.space+y);
	}
    
    private static Boolean isOver(Long point){
    	Integer [] ps = longToPoint(point);
		if(ps[0]<base+1)
			return true;
		if(ps[1]<base+1)
			return true;
		return false;
    }
    
    
    //----------------------- static ----------------------  
  
    private class Point {  
        private int x;  
        private int y;  
        private int z;  
  
        /** 
         * 构造方法 
         */  
        Point(int seqValue) {  
            // 点在哪一个圈  
            int i = 0;  
  
            // 点所在圈序号最大的点  
            int v = 1;  
  
            // 查找给定点是属于哪一个圈  
            for (; v < seqValue; v += 6 * (++i))  
                ;  
  
            // 获取点的x、y和z坐标  
            if (i > 0) {  
                // 点在圈的哪一条边  
                int side = (v - seqValue) / i;  
  
                // 点在边的位置  
                int step = (v - seqValue) % i;  
                switch (side) {  
                case 0:  
                    x = i;  
                    y = -i + step;  
                    z = x + y;  
                    break;  
                case 1:  
                    z = i;  
                    y = step;  
                    x = z - y;  
                    break;  
                case 2:  
                    y = i;  
                    z = i - step;  
                    x = z - y;  
                    break;  
                case 3:  
                    x = -i;  
                    y = i - step;  
                    z = x + y;  
                    break;  
                case 4:  
                    z = -i;  
                    y = -step;  
                    x = z - y;  
                    break;  
                case 5:  
                    y = -i;  
                    z = -i + step;  
                    x = z - y;  
                    break;  
                default:  
                    break;  
                }  
            }  
        }  
  
        // 计算给定点和本点的距离  
        int minus(Point p) {  
            int i = x > p.x ? x - p.x : p.x - x;  
            int j = y > p.y ? y - p.y : p.y - y;  
            int k = z > p.z ? z - p.z : p.z - z;  
            return i > j ? (i > k ? i : k) : (j > k ? j : k);  
        }  
    }
    
    public static void main(String[] args) {
    	//CellularDistrict cellularDistrict = new CellularDistrict(21,12);
    	//cellularDistrict.initCellularDistrict(5000);
    	//System.out.println(cellularDistrict.getShortestPathLength(1, 18));
    	
    	
    	//CellularDistrict cellularDistrict = new CellularDistrict();
    	//cellularDistrict.initCellularDistrict(getMaxSerial(Math.max(xBorder, yBorder)));
    	
    	//initCoordinateSystem(getCentrePoint(xBorder, yBorder),Math.max(xBorder, yBorder));
    	
    	//initCoordinateSystem(getCentrePoint(xBorder, yBorder),21);
    	
    	//System.out.println(getCentrePoint(xBorder, yBorder));
    	
    	//for(Integer i : coordinateMap.keySet())
    	//	System.out.println(i.toString() +"-"+ coordinateMap.get(i));
    	//System.out.println(coordinateMap.get(20));
    	
    	//System.out.println(serialNumberMap.get(110011));
    	//System.out.println(serialNumberMap.get(15100112));
    	
    	//System.out.println(getCentrePoint(xBorder, yBorder));
    	//System.out.println(Math.max(xBorder, yBorder));
    	//System.out.println(getMaxSerial(8));
    	//System.out.println(serialNumberMap.size());
    	//System.out.println(coordinateMap.size());
    	
    	System.out.println(CellularDistrict.getShortPathLength(380086l, 480087l));
    	System.out.println(CellularDistrict.getShortPathLength(580086l, 480087l));
    	
    	//System.out.println(getShortPathLength(380086, 480087));
    	//System.out.println(getShortPathLength(580086, 480087));
	}

}

