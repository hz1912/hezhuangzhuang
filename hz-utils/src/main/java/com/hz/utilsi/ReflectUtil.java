package com.hz.utilsi;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ReflectUtil {
	   /**
     * 
     */
    private static Map<String, String> cachedMethods = new ConcurrentHashMap<String, String>();

    /**
     * 
     *
     * @param name
     * @return
     */
    public static String defaultGetMtd(String name) {
        String method;
        if (cachedMethods.containsKey(name)) {
            return cachedMethods.get(name);
        }
        StringBuilder sBuilder = new StringBuilder();
        /**
         * 
         */
        sBuilder.append("get").append(Character.toUpperCase(name.charAt(0))).append(name.substring(1));
        method = sBuilder.toString();
        cachedMethods.put(name, method);
        return method;
    }
	
}
