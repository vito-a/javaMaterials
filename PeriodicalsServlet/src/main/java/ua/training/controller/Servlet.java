package ua.training.controller;

import ua.training.controller.commands.*;
import ua.training.controller.commands.Registration;
import ua.training.controller.commands.Exception;
import ua.training.controller.commands.admin.UsersList;
import ua.training.model.service.StudentService;
import ua.training.model.service.UserService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class Servlet extends HttpServlet {
    private static final String regexPatch = ".*/app/";
    StudentService studentService = new StudentService();
    Map<String, Command> commands = new HashMap<>();

    public void init(ServletConfig servletConfig) {

        servletConfig.getServletContext()
                .setAttribute("loggedUsers", new HashSet<String>());

        commands.put("admin/users", new UsersList(new UserService()));
        commands.put("logout", new LogOut());
        commands.put("login", new Login(new UserService()));
        commands.put("registration", new Registration());
        commands.put("exception" , new Exception());
        commands.put("locale", new Locale());
    }
    
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
            throws IOException, ServletException {
        processRequest(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");

        String path = request.getRequestURI();
//        System.out.println(path);
        path = path.replaceAll( regexPatch, "");
        path = path.replaceAll(".*/api/" , "");
//        System.out.println(path);
        Command command = commands.getOrDefault(path, (r) -> "/index.jsp");
        String page = command.execute(request);
        if (page.contains("redirect:")) {
            response.sendRedirect(page.replace("redirect:/", "/"));
            // request.getRequestDispatcher(page.replace("redirect:", "/")).forward(request, response);
        } else {
            request.getRequestDispatcher(page).forward(request, response);
            //chain.doFilter(request, response);
        }
        //  response.getWriter().print("Hello from servlet");
    }
}
