package springboot.SS.College;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;   // 👈 முக்கியம்

@Controller
public class staff_record_con {
    
    @Autowired
    private staff_record_repo sr;
    
    @GetMapping("/staff_record")
    public String staff_record() { 
        return "staff_record"; 
    }
  
    @PostMapping("/staff_record")
    public String insert_staff_record(
            @RequestParam("id") String id,
            @RequestParam("fname") String fname,
            @RequestParam("name") String name,
            @RequestParam("gender") String gender,
            @RequestParam("email") String email,
            @RequestParam("mobile") String mobile,
            @RequestParam("designation") String designation,
            @RequestParam("subject") String subject,
            @RequestParam("dep") String dep,
            @RequestParam("exp") String exp,
            RedirectAttributes ra) {   // 👈 Model → RedirectAttributes

        try {
            // Check if staff exists by ID
            Optional<staff_record_model> staffExists = sr.findById(id);
            if(staffExists.isPresent()) {
                ra.addFlashAttribute("err", "Staff ID already exists");
                return "redirect:/staff_record";   // redirect with error message
            }
            
            // Create new staff record
            sr.insert(id, fname, name, gender, email, mobile, designation, subject, dep, exp);
            ra.addFlashAttribute("msg", "Staff record added successfully");
        } catch(Exception e) {
            ra.addFlashAttribute("err", "Failed to add staff record: " + e.getMessage());
        }
        return "redirect:/staff_record";   // success-ஆனால் list page-க்கு redirect
    }
    
    @GetMapping("/view_staff_record")
    public String view_staff_record(Model m) {
        List<staff_record_model> staff = sr.get_all();
        m.addAttribute("staff", staff);
        return "view_staff_record";
    }
}