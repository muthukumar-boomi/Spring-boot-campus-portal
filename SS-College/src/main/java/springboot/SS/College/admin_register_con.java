package springboot.SS.College;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.servlet.http.HttpSession;

@Controller
public class admin_register_con {

    @Autowired
    private admin_register_repo adminRepo;

    // ========== PAGE MAPPINGS ==========
    @GetMapping("/")
    public String home() {
        return "dashboard";
    }

    @GetMapping("/admin_register")
    public String showRegister() {
        return "admin_register";
    }

    @GetMapping("/admin_login")
    public String showLogin() {
        return "admin_login";
    }

    @GetMapping("/mainpage")
    public String mainpage(HttpSession session, Model model) {
        admin_register_model loggedUser = (admin_register_model) session.getAttribute("loggedUser");
        if (loggedUser == null) {
            return "redirect:/admin_login";
        }
        model.addAttribute("user", loggedUser);
        return "mainpage";
    }

    // ========== REGISTRATION (Plain text – simple) ==========
    @PostMapping("/admin_register")
    public String register(@RequestParam String name,
                           @RequestParam String mobile,
                           @RequestParam String pass,
                           @RequestParam String dep,
                           Model model) {
        try {
            if (adminRepo.findById(name).isPresent()) {
                model.addAttribute("err", "Username already exists");
                return "admin_register";
            }
            admin_register_model newUser = new admin_register_model();
            newUser.setName(name);
            newUser.setMobile(mobile);
            newUser.setPass(pass);      // plain text
            newUser.setDep(dep);
            adminRepo.save(newUser);
            model.addAttribute("msg", "Registration successful! Please login.");
        } catch (Exception e) {
            model.addAttribute("err", "Registration failed: " + e.getMessage());
        }
        return "admin_register";
    }

    // ========== LOGIN – ONLY ADMIN ALLOWED ==========
    @PostMapping("/admin_login")
    public String login(@RequestParam String mobile,
                        @RequestParam String pass,
                        @RequestParam String dep,
                        Model model,
                        HttpSession session) {
        try {
            Optional<admin_register_model> userOpt = adminRepo.findByMobileAndDep(mobile, dep);
            if (!userOpt.isPresent()) {
                model.addAttribute("err", "Invalid mobile number or role");
                return "admin_login";
            }
            admin_register_model user = userOpt.get();

            // ** Strictly only "Admin" role allowed **
            if (!"Admin".equalsIgnoreCase(user.getDep())) {
                model.addAttribute("err", "Access Denied. Only Admin can login.");
                return "admin_login";
            }

            // Plain text password check
            if (user.getPass().equals(pass)) {
                session.setAttribute("loggedUser", user);
                return "redirect:/mainpage";
            } else {
                model.addAttribute("err", "Invalid password");
                return "admin_login";
            }
        } catch (Exception e) {
            model.addAttribute("err", "Login error: " + e.getMessage());
            return "admin_login";
        }
    }

    // ========== REST API for AJAX – Fetch user by mobile ==========
    @GetMapping("/api/user/{mobile}")
    @ResponseBody
    public ResponseEntity<?> getUserByMobile(@PathVariable String mobile) {
        Optional<admin_register_model> userOpt = adminRepo.findByMobile(mobile);
        if (!userOpt.isPresent()) {
            return ResponseEntity.status(404).body(Map.of("error", "User not found"));
        }
        admin_register_model user = userOpt.get();
        // Only return details if role is Admin (privacy)
        if (!"Admin".equalsIgnoreCase(user.getDep())) {
            return ResponseEntity.status(403).body(Map.of("error", "Only Admin accounts are allowed"));
        }
        Map<String, String> response = new HashMap<>();
        response.put("name", user.getName());
        response.put("mobile", user.getMobile());
        response.put("dep", user.getDep());
        return ResponseEntity.ok(response);
    }

    // ========== LOGOUT ==========
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/admin_login";
    }
}