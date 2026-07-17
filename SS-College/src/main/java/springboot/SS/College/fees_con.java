package springboot.SS.College;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class fees_con {
    
    @Autowired
    private fees_repo fr;
    
    // Admission repository to fetch student name
    @Autowired
    private admission_repo admissionRepo;
    
    // Show add fee form
    @GetMapping("/fees")
    public String fees() { 
        return "fees"; 
    }
    
    // Show all fee records
    @GetMapping("/fees_list")
    public String fees_list(Model model) {
        List<fees_model> fees = fr.get_all();
        model.addAttribute("fees", fees);
        return "fees_list";
    }
    
    // Insert new fee record
    @PostMapping("/fees")
    public String insert_fees(
            @RequestParam("id") String id,
            @RequestParam("name") String name,
            @RequestParam("ftype") String ftype,
            @RequestParam("ddate") String ddate,
            @RequestParam("rs") String rs,
            @RequestParam("pdate") String pdate,
            @RequestParam("pmode") String pmode,
            @RequestParam("pamount") String pamount,
            @RequestParam("status") String status,
            RedirectAttributes ra) {

        try {
            Optional<fees_model> feeExists = fr.findById(id);
            if(feeExists.isPresent()) {
                ra.addFlashAttribute("err", "Fee ID already exists");
                return "redirect:/fees";
            }
            fr.insert(id, name, ftype, ddate, rs, pdate, pmode, pamount, status);
            ra.addFlashAttribute("msg", "Fee record added successfully");
        } catch(Exception e) {
            ra.addFlashAttribute("err", "Failed to add fee record: " + e.getMessage());
            e.printStackTrace();
        }
        return "redirect:/fees";
    }
    
    // Show edit form
    @GetMapping("/fees/edit/{id}")
    public String editFees(@PathVariable("id") String id, Model model) {
        fees_model fee = fr.get_by_id(id);
        model.addAttribute("fee", fee);
        return "edit_fees";
    }
    
    // Update fee record
    @PostMapping("/fees/update")
    public String updateFees(
            @RequestParam("id") String id,
            @RequestParam("name") String name,
            @RequestParam("ftype") String ftype,
            @RequestParam("ddate") String ddate,
            @RequestParam("rs") String rs,
            @RequestParam("pdate") String pdate,
            @RequestParam("pmode") String pmode,
            @RequestParam("pamount") String pamount,
            @RequestParam("status") String status,
            RedirectAttributes ra) {

        try {
            fees_model fee = new fees_model(id, name, ftype, ddate, rs, pdate, pmode, pamount, status);
            fr.save(fee);
            ra.addFlashAttribute("msg", "Fee record updated successfully");
        } catch(Exception e) {
            ra.addFlashAttribute("err", "Update failed: " + e.getMessage());
            return "redirect:/fees/edit/" + id;
        }
        return "redirect:/fees_list";
    }
    
    // Delete fee record
    @GetMapping("/fees/delete/{id}")
    public String deleteFees(@PathVariable("id") String id, RedirectAttributes ra) {
        try {
            fr.deleteById(id);
            ra.addFlashAttribute("msg", "Fee record deleted successfully");
        } catch(Exception e) {
            ra.addFlashAttribute("err", "Delete failed: " + e.getMessage());
        }
        return "redirect:/fees_list";
    }
    
    // ✅ REST endpoint to get student name by ID from admission
    @GetMapping("/getStudentNameById")
    @ResponseBody
    public ResponseEntity<?> getStudentNameById(@RequestParam String id) {
        Optional<admission_model> student = admissionRepo.findById(id);
        if (student.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Map<String, String> data = new HashMap<>();
        data.put("name", student.get().getName() + " " + student.get().getLname());
        return ResponseEntity.ok(data);
    }
}