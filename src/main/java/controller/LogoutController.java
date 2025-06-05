package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LogoutController implements Controller {

    public String execute (HttpServletRequest req, HttpServletResponse resp)  {
        HttpSession session = req.getSession();
        session.removeAttribute("user");

        return "redirect:/";
    }
}
