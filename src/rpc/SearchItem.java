package rpc;

import db.DBConnection;
import db.DBConnectionFactory;
import entity.Jobs;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Set;

@WebServlet("/search")
public class SearchItem extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchItem() {
        super();
        // TODO Auto-generated constructor stub
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        double lat = Double.parseDouble(request.getParameter("lat"));
//        double lon = Double.parseDouble(request.getParameter("lon"));

        DBConnection connection = DBConnectionFactory.getConnection();
        try {
            // 这里做了search，也就是说只有获取数据。在这个searchItem方法里面做了一步保存到database，这个部分和html method是无关的
            // 这个servlet 从 ticketmaster API get data
            //List<Item> items = sqlconnection.searchItems(lat, lon, term);
            List<Jobs> jobs = DBConnectionFactory.getConnection().searchJobs(37.3229978, -122.0321823, null);
//            Set<String> favoritedItemIds = connection.getFavoriteItemIds(userId);

            JSONArray array = new JSONArray();
            for (Jobs job : jobs) {
                //array.put(item.toJSONObject());
                JSONObject obj = job.toJSONObject();
//                obj.put("favorite", favoritedItemIds.contains(item.getItemId()));
                array.put(obj);
            }
            RpcHelper.writeJsonArray(response, array);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //sqlconnection.close();
            DBConnectionFactory.getConnection().close();
        }
        connection.close();
    }
}
