package cn.lijunkui.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class JsonUtil {
	
	private static Gson gson = null;
	static {
	 if (gson == null) {
         gson= new GsonBuilder().create();
     }
	}
	
	public static  String toJson(Object object){
		return gson.toJson(object);
	}
	
	public static  <T> T GsonToBean(String json,Class<T> clazz){
        return gson.fromJson(json, clazz);
	}
}
