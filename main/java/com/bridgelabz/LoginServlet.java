package com.bridgelabz;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.regex.Pattern;

@WebServlet(
        description = "Login Servlet Testing",
        urlPatterns = { "/LoginServlet" },
        initParams = {
                @WebInitParam(name = "user" , value = "harish15x"),
                @WebInitParam(name = "password" , value = "India")
        }
)
public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String user = request.getParameter("user");
        String pwd = request.getParameter("pwd");

        String userID = getServletConfig().getInitParameter("user");
        String password = getServletConfig().getInitParameter("password");
        if (Pattern.matches("[A-Z][a-z]{3,}", user) &&
                Pattern.matches("(?=.*?[A-Z])(?=.*?\\d)(?=.*?[a-z])(?=.*?[!@#$%^&*_()+-])[A-Za-z\\d!@#$%^&()*+_-]{8,}", password)){
            request.setAttribute("user" ,user);
            request.getRequestDispatcher("LoginSuccess.jsp").forward(request, response);
        }else {
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/login.html");
            PrintWriter out = response.getWriter();
            out.println("<font colour=red>Either user name or passwor is wrong.</font>");
            rd.include(request, response);
        }
    }
}
