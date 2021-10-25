package ua.training.model.entity;

import java.util.ArrayList;
import java.util.List;

public class Teacher {
    private int id;
    private String name;
    private String course;
    private int room;

    private List<Student> students = new ArrayList<>();

    public Teacher() {
    }
    public Teacher(int id, String name, String course,
                   int room, List<Student> students) {
        this.id = id;
        this.name = name;
        this.course = course;
        this.room = room;
        this.students = students;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getCourse() {
        return course;
    }
    public void setCourse(String course) {
        this.course = course;
    }
    public int getRoom() {
        return room;
    }
    public void setRoom(int room) {
        this.room = room;
    }
    public List<Student> getStudents() {
        return students;
    }
    public void setStudents(List<Student> students) {
        this.students = students;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", course='" + course + '\'' +
                ", room=" + room +
                //", students=" + students +
                '}';
    }
}
