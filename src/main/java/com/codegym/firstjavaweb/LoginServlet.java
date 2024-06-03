package com.codegym.firstjavaweb;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class LoginServlet extends HttpServlet {
    @Override
    public void init() throws ServletException {
        super.init();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        out.println("<html>");
        out.println("<head><title>Login</title></head>");
        out.println("<body>");
        out.println("<h1>Login</h1>");
        out.println("<form method='post'>");
        out.println("<input type='text' name='username' placeholder='username'/>");
        out.println("<input type='password' name='password' placeholder='password'/>");
        out.println("<input type='submit' value='Login'/>");
        out.println("</form>");
        out.println("</body>");
        out.println("</html>");
    }
}
