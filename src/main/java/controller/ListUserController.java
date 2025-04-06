package controller;

import core.db.MemoryUserRepository;
import jwp.model.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Collection;
import java.util.Enumeration;

@WebServlet("/user/userList")
public class ListUserController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Object value = session.getAttribute("user");
        if (value == null) {
            resp.sendRedirect("/user/login.jsp");
            return;
        }

        User user = (User) value;

        Enumeration<String> paramNames = req.getParameterNames();
        if (!paramNames.hasMoreElements()) {
            System.out.println("파라미터가 없습니다.");
        } else {
            System.out.println("=== 요청 파라미터 목록 ===");
            while (paramNames.hasMoreElements()) {
                String paramName = paramNames.nextElement();
                String paramValue = req.getParameter(paramName);
                System.out.println(paramName + ": " + paramValue);
            }
        }

        String inputUserId = (String) req.getParameter("userId");

        if (!inputUserId.equals(user.getUserId())) {
            resp.sendRedirect("/user/login.jsp");
            return;
        }


        Collection<User> users = MemoryUserRepository.getInstance().findAll();
        req.setAttribute("users", users);

        RequestDispatcher rd = req.getRequestDispatcher("/user/list.jsp");
        rd.forward(req, resp);
    }
}
