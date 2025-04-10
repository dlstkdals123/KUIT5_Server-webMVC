package controller;

import jwp.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/user/updateUserForm")
public class UpdateUserFormController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userId = req.getParameter("userId");
        HttpSession session = req.getSession();
        Object value = session.getAttribute("user");
        if (value == null) {
            resp.sendRedirect("/user/userList");
            return;
        }
        User user = (User) value;

        if (!userId.equals(user.getUserId())) {
            resp.sendRedirect("/user/userList");
            return;
        }

        req.setAttribute("user", user);
        req.getRequestDispatcher("/user/updateForm.jsp").forward(req, resp);
    }
}
