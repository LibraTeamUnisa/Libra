package it.unisa.libra.util;

import java.util.Map;

import com.codesnippets4all.json.parsers.JSONParser;
import com.codesnippets4all.json.parsers.JsonParserFactory;

public class JsonUtils {
	
	public static Map<String, String> parseOrariApertura(String json) {
		JsonParserFactory factory = JsonParserFactory.getInstance();
		JSONParser parser = factory.newJsonParser();
		Map<String, String> jsonData = parser.parseJson(json);
		
		return jsonData;
	}
	
}
