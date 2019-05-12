package cn.wmyskxz.dao;

import cn.wmyskxz.entity.Student;

import java.util.List;

import org.springframework.data.repository.query.Param;

public interface StudentDao {
    int getTotal();
    void addStudent(Student student);
    void deleteStudent(int id);
    void updateStudent(Student student);
    Student getStudent(int id);
    List<Student> findname(String name);
    List<Student> list(int start, int count);
}