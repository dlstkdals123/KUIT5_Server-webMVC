package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HomeController implements Controller {
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        return "/home.jsp";
    }
}
