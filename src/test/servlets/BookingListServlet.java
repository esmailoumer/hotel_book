package test.servlets;

import test.api.Booking;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

@WebServlet("/api/bookings")
public class BookingListServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        Booking dao = new Booking();

        List<Map<String, Object>> bookings = dao.getAllUsers(); // note that map is built in interface of hashmap

        PrintWriter out = response.getWriter();
        StringBuilder json = new StringBuilder("[");

        for (int i = 0; i < bookings.size(); i++) {
            Map<String, Object> u = bookings.get(i);

            json.append("{")
                    .append("\"id\":").append(u.get("id")).append(",")
                    .append("\"user_id\":").append(u.get("user_id")).append(",")
                    .append("\"room_id\":").append(u.get("room_id")).append(",")
                    .append("\"guests\":").append(u.get("guests")).append(",")
                    .append("\"check_in\":\"").append(u.get("check_in")).append("\",")
                    .append("\"check_out\":\"").append(u.get("check_out")).append("\",")
                    .append("\"status\":\"").append(u.get("status")).append("\",")
                    .append("\"total_price\":").append(u.get("total_price")).append(",")
                    .append("\"special_request\":\"").append(u.get("special_request")).append("\"")
                    .append("}");
            if (i < bookings.size() - 1)
                json.append(",");
        }
        json.append("]");

        out.print(json.toString());
        out.flush();
    }
}