package it.unisa.libra.util;

import java.util.Map;

import com.codesnippets4all.json.parsers.JSONParser;
import com.codesnippets4all.json.parsers.JsonParserFactory;

public class JsonUtils {
	
	//EX. {"LUN":"9.30-11.30","MER":"9.30-11.30 14.30-18.30"}
	public static Map<String, String> parseOrariApertura(String json) {
		JsonParserFactory factory = JsonParserFactory.getInstance();
		JSONParser parser = factory.newJsonParser();
		Map<String, String> jsonData = parser.parseJson(json);
		
		return jsonData;
	}
	
}
