import controller.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

@WebServlet("/")
public class DispatcherServlet extends HttpServlet {
    private static final String redirectStr = "redirect:";
    private static final HashMap<String, Controller> controllers = new HashMap<>();

    static {
        controllers.put("/user/signup", new CreateUserController());
        controllers.put("/", new HomeController());
        controllers.put("/user/userList", new ListUserController());
        controllers.put("/user/login", new LoginController());
        controllers.put("/user/logout", new LogoutController());
        controllers.put("/user/update", new UpdateUserController());
        controllers.put("/user/updateUserForm", new UpdateUserFormController());
    }

    public static void processRequest(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        for (String key: controllers.keySet()) {
            if (req.getRequestURI().equals(key)) {
                String url = controllers.get(key).execute(req, resp);
                if (url.startsWith(redirectStr)) {
                    resp.sendRedirect(url.substring(redirectStr.length()));
                    return;
                }
                req.getRequestDispatcher(url).forward(req, resp);
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }
}