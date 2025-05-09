package com.university.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.*;
import java.sql.*;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String showLogin() {
        return "login";
    }

    @PostMapping("/login")
    public String processLogin(@RequestParam String email, @RequestParam String password,
                               HttpSession session, Model model) throws Exception {
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/university_db", "root", "#nytrvhh@54");
        PreparedStatement ps = con.prepareStatement("SELECT * FROM students WHERE email=? AND password=?");
        ps.setString(1, email);
        ps.setString(2, password);
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            session.setAttribute("student_id", rs.getInt("student_id"));
            return "redirect:/courses";
        } else {
            model.addAttribute("error", "Invalid credentials");
            return "login";
        }
    }
}
