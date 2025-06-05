package controller;

import jwp.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class UpdateUserFormController implements Controller {

    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        String userId = req.getParameter("userId");
        HttpSession session = req.getSession();
        Object value = session.getAttribute("user");
        if (value == null) {
            return "redirect:/user/userList";
        }
        User user = (User) value;

        if (!userId.equals(user.getUserId())) {
            return "redirect:/user/userList";
        }

        req.setAttribute("user", user);
        return "/user/updateForm.jsp";
    }
}
