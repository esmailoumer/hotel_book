package test.servlets;

import test.api.UserDAO;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/signup")
public class UserSignUpServlet extends HttpServlet {
    private UserDAO userDAO = new UserDAO();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String role = "USER"; // Default role for new sign-ups

        // 2. Insert into Database
        String success = userDAO.addUser(name, email, password, role);

        // 3. Redirect based on result
        if ("sucess".equals(success)) {
            // Redirect to login or success page
            response.sendRedirect("login.html?status=success");
        } else {
            // Redirect back to signup with error message
            response.sendRedirect("signup.html?status=error");
        }
    }
}