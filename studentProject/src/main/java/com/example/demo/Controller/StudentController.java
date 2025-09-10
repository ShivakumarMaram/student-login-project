package com.example.demo.Controller;
import com.example.demo.Entity.Student;
import com.example.demo.Service.StudentService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class StudentController {
    private final StudentService studentService;
    
    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }
    
    // Root mapping - redirects home page to login
    @GetMapping("/")
    public String home() {
        return "redirect:/login";
    }
    
    // Display signup form
    @GetMapping("/signup")
    public String signup(Model model) {
        model.addAttribute("student", new Student());
        return "signup";
    }
    
    // Handle signup form submission
    @PostMapping("/signup")
    public String signupSubmit(@ModelAttribute Student student, Model model) {
        if (studentService.findByUsername(student.getUsername()).isPresent()) {
            model.addAttribute("error", "User already exists");
            return "signup";
        }
        studentService.saveStudent(student);
        return "redirect:/login";
    }
    
    // Display login form
    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }
    
    // Handle login form submission
    @PostMapping("/login")
    public String loginSubmit(
            @RequestParam String username,
            @RequestParam String password,
            Model model,
            HttpSession session) {
        var studentOpt = studentService.findByUsername(username);
        if (studentOpt.isPresent() && studentOpt.get().getPassword().equals(password)) {
            session.setAttribute("student", studentOpt.get());
            return "redirect:/dashboard";
        }
        model.addAttribute("error", "Invalid username or password");
        return "login";
    }
    
    // Handle logout
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }
    
    @GetMapping("/dashboard")
    public String dashboard(HttpSession session, Model model) {
        Student student = (Student) session.getAttribute("student");
        if (student == null) {
            return "redirect:/login";
        }
        model.addAttribute("student", student);
        return "dashboard"; // Make sure dashboard.html exists in templates
    }
    
    // Student profile page
    @GetMapping("/studentPages/profile")
    public String profile(HttpSession session, Model model) {
        Student student = (Student) session.getAttribute("student");
        if (student == null) {
            return "redirect:/login";
        }
        model.addAttribute("student", student);
        return "profile";
    }
    
    // Courses page
    @GetMapping("/studentPages/courses")
    public String courses(HttpSession session) {
        if (session.getAttribute("student") == null) {
            return "redirect:/login";
        }
        return "courses";
    }
    
    // Grades page
    @GetMapping("/studentPages/grades")
    public String grades(HttpSession session) {
        if (session.getAttribute("student") == null) {
            return "redirect:/login";
        }
        return "grades";
    }
    
    // Notifications page
    @GetMapping("/studentPages/notifications")
    public String notifications(HttpSession session) {
        if (session.getAttribute("student") == null) {
            return "redirect:/login";
        }
        return "notifications";
    }
}