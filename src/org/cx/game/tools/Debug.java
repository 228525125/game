package org.cx.game.tools;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class Debug {

	public static Boolean isDebug = true;
	public static Integer power = 10000;
	public static Boolean activate = true;
	public static Boolean moveable = true;
	
	public static void main(String[] args) {
        //add()和remove()方法在失败的时候会抛出异常(不推荐)
        Queue<Map<String, String>> queue = new LinkedList<Map<String,String>>();
        //添加元素
        Map<String, String> a = new HashMap<String, String>();
        a.put("a", "a");
        queue.offer(a);
        
        Map<String, String> b = new HashMap<String, String>();
        b.put("b", "b");
        queue.offer(b);
        
        Object [] ss = queue.toArray();
        
        Map<String, String> map = (Map<String, String>) ss[queue.size()-1];
        map.put("b", "c");
        System.out.println(map);
        
        System.out.println(queue);
                
    }
}
