package com.yun.otherClass;

import org.json.JSONArray;
import org.json.JSONObject;

public class UtiltyClass {

	public static JSONArray storeJSONObjectToJSONArray(JSONArray arr, JSONObject job) {
		arr.put(job);
		return arr;
	}
}
