package ua.training.controller.commands;

import ua.training.model.entity.Student;
import ua.training.model.service.StudentService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class StudentList implements Command {

    public StudentList(StudentService studentService) {
        this.studentService = studentService;
    }

    StudentService studentService;

    @Override
    public String execute(HttpServletRequest request) {
        List<Student> students = studentService.getAllStudents();
        request.setAttribute("students" , students);
        return "/WEB-INF/studentlist.jsp";
    }
}
