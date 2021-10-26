package ua.training.controller.commands;

import ua.training.model.entity.Student;
import ua.training.model.service.StudentService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class StudentsList implements Command {
    StudentService studentService;

    @Override
    public String execute(HttpServletRequest request) {

        //ToDo Service Registration Form
        List<Student> students = studentService.getAllStudents();
        request.setAttribute("students" , students);
        return "/studentlist.jsp";
    }
}
