package cn.wmyskxz.controller;

import cn.wmyskxz.entity.Student;
import cn.wmyskxz.service.StudentService;
import cn.wmyskxz.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


@Controller
@RequestMapping("")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @RequestMapping("/addStudent")
    public String addStudent(HttpServletRequest request, HttpServletResponse response) {

        Student student = new Student();

        int studentID = Integer.parseInt(request.getParameter("studentid"));
        String name = request.getParameter("name");
        int age = Integer.parseInt(request.getParameter("age"));
        String sex = request.getParameter("sex");
        Date birthday = null;
        // String ���Ͱ��� yyyy-MM-dd �ĸ�ʽת��Ϊ java.util.Date ��
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            birthday = simpleDateFormat.parse(request.getParameter("birthday"));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        student.setStudentid(studentID);
        student.setName(name);
        student.setAge(age);
        student.setSex(sex);
        student.setBirthday(birthday);

        studentService.addStudent(student);

        return "redirect:listStudent";
    }

    @RequestMapping("/listStudent")
    public String listStudent(HttpServletRequest request, HttpServletResponse response) {

        // ��ȡ��ҳ����
        int start = 0;
        int count = 10;

        try {
            start = Integer.parseInt(request.getParameter("page.start"));
            count = Integer.parseInt(request.getParameter("page.count"));
        } catch (Exception e) {
        }

        Page page = new Page(start, count);

        List<Student> students = studentService.list(page.getStart(), page.getCount());
        int total = studentService.getTotal();
        page.setTotal(total);

        request.setAttribute("students", students);
        request.setAttribute("page", page);

        return "listStudent";
    }

    @RequestMapping("/deleteStudent")
    public String deleteStudent(int id) {
        studentService.deleteStudent(id);
        return "redirect:listStudent";
    }

    @RequestMapping("/editStudent")
    public ModelAndView editStudent(int id) {
        ModelAndView mav = new ModelAndView("editStudent");
        System.out.println("sadasd0");
        Student student = studentService.getStudent(id);
        System.out.println("sadasd1");
        mav.addObject("student", student);
        System.out.println("sadasd2");
        return mav;
    }

    @RequestMapping("/updateStudent")
    public String updateStudent(HttpServletRequest request, HttpServletResponse response) {

        Student student = new Student();

        int id = Integer.parseInt(request.getParameter("id"));
        int studentid = Integer.parseInt(request.getParameter("studentid"));
        String name = request.getParameter("name");
        int age = Integer.parseInt(request.getParameter("age"));
        String sex = request.getParameter("sex");

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date birthday = null;
        try {
            birthday = simpleDateFormat.parse(request.getParameter("birthday"));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        student.setId(id);
        student.setStudentid(studentid);
        student.setName(name);
        student.setAge(age);
        student.setSex(sex);
        student.setBirthday(birthday);

        studentService.updateStudent(student);
        return "redirect:listStudent";
    }
}