package org.mycompany.myname.Servlets;

import org.mycompany.myname.Realisation.AllLists;

import javax.servlet.http.HttpServlet;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class FileServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        try {
            String path = req.getParameter("path");
            path = new String(path.getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
            if (path.contains("c:\\")) {

                AllLists lists = new AllLists(path);

                req.setAttribute("path", lists.getCurDir());
                req.setAttribute("directory", lists.getDir());
                req.setAttribute("files", lists.getFiles());

                getServletContext().getRequestDispatcher("/index.jsp").forward(req, resp);

            } else {
                req.setAttribute("path", "c:\\aaa\\");
                req.getRequestDispatcher("/wrong.jsp").forward(req, resp);
            }
        }catch (NullPointerException ex) {
            req.setAttribute("path", "c:\\aaa\\");
            req.getRequestDispatcher("/index.jsp").forward(req, resp);
        }
    }


}