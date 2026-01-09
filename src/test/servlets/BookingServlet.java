package test.servlets;

import test.api.Booking;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/submitBooking")
public class BookingServlet extends HttpServlet {
    private Booking bookingAPI = new Booking();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        Integer userId = (Integer) session.getAttribute("userId");

        if (userId == null) {
            response.sendRedirect("login.html");
            return;
        }

        // 'room-type' must match the name attribute in your HTML <select>
        String type = request.getParameter("room-type");
        String checkIn = request.getParameter("checkin");
        String checkOut = request.getParameter("checkout");
        String notes = request.getParameter("special-request");

        String result = bookingAPI.bookRoomByType(userId, type, checkIn, checkOut, notes);
        
        response.sendRedirect("booking.html?status=" + result);
    }
}