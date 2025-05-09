package com.university.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.*;
import java.sql.*;

@Controller
public class CourseController {

    @GetMapping("/courses")
    public String showCourses(Model model) throws Exception {
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/university_db", "root", "#nytrvhh@54");
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM courses");

        model.addAttribute("courses", rs);
        return "courses";
    }

    @PostMapping("/register/{courseId}")
    public String registerCourse(@PathVariable int courseId, HttpSession session) throws Exception {
        Integer studentId = (Integer) session.getAttribute("student_id");
        if (studentId == null) return "redirect:/login";

        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/university_db", "root", "#nytrvhh@54");
        PreparedStatement check = con.prepareStatement("SELECT * FROM registrations WHERE student_id=? AND course_id=?");
        check.setInt(1, studentId);
        check.setInt(2, courseId);
        ResultSet rs = check.executeQuery();

        if (!rs.next()) {
            PreparedStatement ps = con.prepareStatement("INSERT INTO registrations(student_id, course_id, date) VALUES (?, ?, NOW())");
            ps.setInt(1, studentId);
            ps.setInt(2, courseId);
            ps.executeUpdate();
        }

        return "success";
    }
}
