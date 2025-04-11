package controller;

import core.db.MemoryUserRepository;
import jwp.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginController implements Controller {

    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        String userId = req.getParameter("userId");
        String password = req.getParameter("password");

        User user = MemoryUserRepository.getInstance().findUserById(userId);
        if (user == null || !user.getPassword().equals(password)) {
            return "redirect:/user/login_failed.jsp";
        }

        HttpSession session = req.getSession();
        session.setAttribute("user", user);
        return "/home.jsp";
    }
}
