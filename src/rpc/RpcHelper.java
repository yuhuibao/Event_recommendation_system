package rpc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class RpcHelper {
	//Writes a JSONArray��already exist, content come from server�� to http response
	//��JSONArray���뵽response����
	public static void writeJsonArray(HttpServletResponse response, JSONArray array)throws IOException{
		response.setContentType("application/json");
		// front end
		response.setHeader("Access-Control-Allow-Origin", "*");
		PrintWriter out = response.getWriter();
		out.print(array);
		out.close();
	}
	//Writes a JSONObject to http response
	public static void writeJsonObject(HttpServletResponse response, JSONObject obj)throws IOException{
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		
		out.print(obj);
		out.close();
	}
	public static JSONObject readJSONObject(HttpServletRequest request) {
	   	   StringBuilder sBuilder = new StringBuilder();
	   	   try (BufferedReader reader = request.getReader()) {
	   		 String line = null;
	   		 while((line = reader.readLine()) != null) {
	   			 sBuilder.append(line);
	   		 }
	   		 return new JSONObject(sBuilder.toString());
	   		 
	   	   } catch (Exception e) {
	   		 e.printStackTrace();
	   	   }
	   	 
	   	  return new JSONObject();
	}


}
