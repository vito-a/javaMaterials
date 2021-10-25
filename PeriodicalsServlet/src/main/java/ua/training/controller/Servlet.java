package ua.training.controller;

import ua.training.controller.commands.Command;
import ua.training.controller.commands.StudentListCommand;
import ua.training.model.entity.Student;
import ua.training.model.service.StudentService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Servlet extends HttpServlet {

    StudentService studentService = new StudentService();
    Map<String, Command> commands = new HashMap<>();

    public void init(){
        commands.put("students", new StudentListCommand(new StudentService()));
    }
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
            throws IOException, ServletException {
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");
        String path = request.getRequestURI();
        path = path.replaceAll(".*/api/" , "");
        Command command = commands.getOrDefault(path , (r)->"/index.jsp)");
        String page = command.execute(request);
        request.getRequestDispatcher(page).forward(request,response);
        //  response.getWriter().print("Hello from servlet");
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");

        List<Student> students = studentService.getAllStudents();
        request.setAttribute("students" , students);
        request.getRequestDispatcher("./WEB-INF/studentlist.jsp")
                .forward(request,response);
    }
}
