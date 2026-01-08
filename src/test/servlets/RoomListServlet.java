package test.servlets;

import test.api.Rooms;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

@WebServlet("/api/rooms")
public class RoomListServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        Rooms dao = new Rooms();

        List<Map<String, Object>> rooms = dao.getAllUsers(); // note that map is built in interface of hashmap

        PrintWriter out = response.getWriter();
        StringBuilder json = new StringBuilder("[");

        for (int i = 0; i < rooms.size(); i++) {
            Map<String, Object> u = rooms.get(i);

            json.append("{")
                    .append("\"id\":").append(u.get("id")).append(",")
                    .append("\"room_number\":\"").append(u.get("room_number")).append("\",")
                    .append("\"room_type\":\"").append(u.get("room_type")).append("\",")
                    .append("\"base_price\":\"").append(u.get("base_price")).append("\",")
                    .append("\"status\":\"").append(u.get("status")).append("\"")
                    .append("}");

            if (i < rooms.size() - 1)
                json.append(",");
        }
        json.append("]");

        out.print(json.toString());
        out.flush();
    }
}