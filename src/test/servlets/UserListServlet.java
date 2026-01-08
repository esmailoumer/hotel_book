package test.servlets;

import test.api.UserDAO;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map; 

@WebServlet("/api/users")
public class UserListServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        UserDAO dao = new UserDAO(); 
    
        List<Map<String, Object>> users = dao.getAllUsers();  // note that map is built in interface of hashmap

        PrintWriter out = response.getWriter();
        StringBuilder json = new StringBuilder("[");

        for (int i = 0; i < users.size(); i++) {
            Map<String, Object> u = users.get(i); 
            
            json.append("{")
                .append("\"id\":").append(u.get("id")).append(",")
                .append("\"name\":\"").append(u.get("name")).append("\",")
                .append("\"email\":\"").append(u.get("email")).append("\",")
                .append("\"role\":\"").append(u.get("role")).append("\"")
                .append("}");
            
            if (i < users.size() - 1) json.append(",");
        }
        json.append("]");

        out.print(json.toString());
        out.flush(); 
    }
}