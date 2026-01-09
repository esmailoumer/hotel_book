package test.servlets;

import test.api.UserDAO;
import java.io.IOException;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/login")
public class UserLoginServlet extends HttpServlet {

    private UserDAO userDAO = new UserDAO();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String email = request.getParameter("email");
        String password = request.getParameter("password");

        Map<String, Object> user = userDAO.loginUser(email, password);

        if (user != null) {
            HttpSession session = request.getSession();

            String name = (String) user.get("name");
            String role = (String) user.get("role"); // 👈 get role

            session.setAttribute("userName", name);
            session.setAttribute("userRole", role);

            // 🔀 Role-based redirect
            if ("ADMIN".equalsIgnoreCase(role)) {
                response.sendRedirect("dashboard.html");
            } else {
                response.sendRedirect("index.html");
            }

        } else {
            // Email exists check for proper error message
            boolean emailExists = userDAO.checkEmailExists(email);

            if (!emailExists) {
                response.sendRedirect("login.html?error=email");
            } else {
                response.sendRedirect("login.html?error=password");
            }
        }
    }
}
