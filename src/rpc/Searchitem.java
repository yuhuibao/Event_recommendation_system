package rpc;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import db.DBConnection;
import db.DBConnectionFactory;

//import com.sun.xml.internal.bind.v2.schemagen.xmlschema.List;

import external.TicketMasterAPI;
import entity.Item;
import java.util.*;

/**
 * Servlet implementation class Searchitem
 */
@WebServlet("/search")
public class Searchitem extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Searchitem() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		double lat = Double.parseDouble(request.getParameter("lat"));
		double lon = Double.parseDouble(request.getParameter("lon"));
		String userId = request.getParameter("user_id");
		
		String term = request.getParameter("term");
		DBConnection connection = DBConnectionFactory.getConnection();
		JSONArray array = new JSONArray();
		
		try {
			List<Item> items = connection.searchItems(lat, lon, term);
			Set<String> favoritedItemIds = connection.getFavoriteItemIds(userId);
			for (Item item : items) {
				JSONObject obj = item.toJSONObject();
				obj.put("favorite", favoritedItemIds.contains(item.getItemId()));
				array.put(obj);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			connection.close();
		}
		RpcHelper.writeJsonArray(response, array);

		
		/*response.setContentType("text/html");
		
		PrintWriter out = response.getWriter();
		if (request.getParameter("username") != null) {
			String username = request.getParameter("username");
			out.println("<html><body>");
			out.println("<h1>Hellow" + username + "</h1>");
			out.println("</body></html>");
		}
		
		
		out.close();*/
		/*response.setContentType("application/json");
		
		PrintWriter out = response.getWriter();
		if (request.getParameter("username") != null) {
			String username = request.getParameter("username");
			JSONObject obj = new JSONObject();
			try {
				obj.put("usename", username);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			out.print(obj);
			out.close();
		}*/
		
		/*JSONArray array = new JSONArray();

		try {
			array.put(new JSONObject().put("username", "abcd"));
			array.put(new JSONObject().put("username","1234"));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JSONObject obj = new JSONObject();
		RpcHelper.writeJsonObject(response, obj);

		RpcHelper.writeJsonArray(response, array);*/
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
