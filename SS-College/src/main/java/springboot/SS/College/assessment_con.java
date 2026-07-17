package springboot.SS.College;

import java.util.HashMap;
import java.util.List;
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

@Controller
public class assessment_con {
    
    @Autowired
    private assessment_repo ar;
    
    // 👇 Inject admission repository to fetch student details
    @Autowired
    private admission_repo admissionRepo;
    
    @GetMapping("/assessment")
    public String assessment() { 
        return "assessment"; 
    }
    
    @PostMapping("/assessment")
    public String insert_assessment(
            @RequestParam("rnumber") String rnumber,
            @RequestParam("name") String name,
            @RequestParam("atype") String atype,
            @RequestParam("course") String course,
            @RequestParam("subject") String subject,
            @RequestParam("sem") String sem,
            @RequestParam("section") String section,
            @RequestParam("maxmark") String maxmark,
            @RequestParam("mobtined") String mobtined,
            @RequestParam("ddate") String ddate,
            @RequestParam("status") String status,
            @RequestParam("grade") String grade,
            Model m) {
        try {
            Optional<assessment_model> entryExists = ar.findById(rnumber);
            if(entryExists.isPresent()) {
                m.addAttribute("err", "Roll number already exists in assessment records");
                return "assessment";
            }
            
            ar.insert(rnumber, name, atype, course, subject, sem, section, maxmark, mobtined, ddate, status, grade);
            m.addAttribute("msg", "Assessment record added successfully");
        } catch(Exception e) {
            m.addAttribute("err", "Failed to add assessment record: " + e.getMessage());
        }
        return "assessment";
    }
    
    @GetMapping("/view_assessment")
    public String view_assessment(Model m) {
        List<assessment_model> assessment = ar.get_all();
        m.addAttribute("assessment", assessment);
        return "view_assessment";
    }
    
    // ✅ NEW REST endpoint: get student details by roll number from admission table
    @GetMapping("/getStudentByRollNo")
    @ResponseBody
    public ResponseEntity<?> getStudentByRollNo(@RequestParam String rollno) {
        // Search admission table by roll number (rnumber)
        admission_model student = admissionRepo.findByRnumber(rollno);
        if (student == null) {
            return ResponseEntity.notFound().build();
        }
        Map<String, String> data = new HashMap<>();
        data.put("name", student.getName() + " " + student.getLname()); // full name
        data.put("course", student.getCourse());
        data.put("sem", student.getSem());
        data.put("section", student.getSection());
        return ResponseEntity.ok(data);
    }
}