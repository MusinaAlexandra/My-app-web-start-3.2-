package org.mycompany.myname.Servlets;

import org.mycompany.myname.Models.UserProfile;
import org.mycompany.myname.Services.AccountService;
import org.mycompany.myname.Services.DBService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@WebServlet("/authorization")
public class LoginServlet extends HttpServlet {
    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String login = req.getParameter("login");
        String pass = req.getParameter("pass");
        UserProfile user = DBService.getUser(login);

        if (user == null){
            req.getRequestDispatcher("/registration.html").forward(req, resp);
            return;
        }

        if (!pass.equals(user.getPass())){
            req.getRequestDispatcher("/authorization.html").forward(req, resp);
            return;
        }

        AccountService.addSession(req.getSession().getId(),user);
        String path = "http://localhost:8888/?path=c:\\tpp\\"+login+"/";
        resp.sendRedirect(new String(path.getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8));
    }

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        AccountService.deleteSession(req.getSession().getId());
        req.getRequestDispatcher("/authorization.html").forward(req, resp);
    }
}
