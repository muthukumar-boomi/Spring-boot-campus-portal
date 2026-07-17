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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class academic_record_con {

    @Autowired
    private academic_record_repo ar;

    @Autowired
    private admission_repo admissionRepo;

    // ---------- SHOW ADD FORM ----------
    @GetMapping("/academic_record")
    public String showAddForm() {
        return "academic_record";
    }

    // ---------- LIST ALL RECORDS ----------
    @GetMapping("/academic_records_list")
    public String listRecords(Model model) {
        model.addAttribute("academicRecords", ar.get_all());
        return "academic_records_list";
    }

    // ---------- INSERT NEW RECORD ----------
    @PostMapping("/academic_record")
    public String insert_academic_record(@RequestParam("id") String id,
                                         @RequestParam("name") String name,
                                         @RequestParam("sem") String sem,
                                         @RequestParam("dep") String dep,
                                         @RequestParam("subject") String subject,
                                         @RequestParam("imark") String imark,
                                         @RequestParam("mark") String mark,
                                         @RequestParam("total") String total,
                                         @RequestParam("status") String status,
                                         @RequestParam("grade") String grade,
                                         RedirectAttributes ra) {
        if (ar.existsById(id)) {
            ra.addFlashAttribute("err", "Student ID already exists");
            return "redirect:/academic_record";
        }
        try {
            ar.insert(id, name, sem, dep, subject, imark, mark, total, status, grade);
            ra.addFlashAttribute("msg", "Academic record added successfully");
        } catch (Exception e) {
            ra.addFlashAttribute("err", "Insert failed: " + e.getMessage());
            return "redirect:/academic_record";
        }
        return "redirect:/academic_record";
    }

    // ---------- SHOW EDIT FORM (FIXED) ----------
    @GetMapping("/academic/edit/{id}")
    public String showEditForm(@PathVariable("id") String id, Model model) {
        Optional<academic_record_model> optional = ar.findById(id);
        if (optional.isEmpty()) {
            return "redirect:/academic_records_list";
        }
        model.addAttribute("record", optional.get());
        return "academic_edit";   // must match the HTML file name (academic_edit.html)
    }

    // ---------- UPDATE RECORD (FIXED) ----------
    @PostMapping("/academic/update")
    public String updateRecord(@RequestParam("id") String id,
                               @RequestParam("name") String name,
                               @RequestParam("sem") String sem,
                               @RequestParam("dep") String dep,
                               @RequestParam("subject") String subject,
                               @RequestParam("imark") String imark,
                               @RequestParam("mark") String mark,
                               @RequestParam("total") String total,
                               @RequestParam("status") String status,
                               @RequestParam("grade") String grade,
                               RedirectAttributes ra) {
        try {
            // Use your repository's update method (if exists) or save method
            ar.update(id, name, sem, dep, subject, imark, mark, total, status, grade);
            ra.addFlashAttribute("msg", "Record updated successfully");
        } catch (Exception e) {
            ra.addFlashAttribute("err", "Update failed: " + e.getMessage());
            return "redirect:/academic/edit/" + id;
        }
        return "redirect:/academic_records_list";
    }

    // ---------- DELETE RECORD ----------
    @GetMapping("/academic/delete/{id}")
    public String deleteRecord(@PathVariable("id") String id, RedirectAttributes ra) {
        try {
            ar.delete(id);
            ra.addFlashAttribute("msg", "Record deleted successfully");
        } catch (Exception e) {
            ra.addFlashAttribute("err", "Delete failed: " + e.getMessage());
        }
        return "redirect:/academic_records_list";
    }

    // ---------- REST ENDPOINT FOR AUTO‑FILL (ADD FORM) ----------
    @GetMapping("/getStudentById")
    @ResponseBody
    public ResponseEntity<?> getStudentById(@RequestParam String id) {
        Optional<admission_model> optional = admissionRepo.findById(id);
        if (optional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        admission_model adm = optional.get();
        Map<String, String> data = new HashMap<>();
        data.put("name", adm.getName() + " " + adm.getLname());
        data.put("dep", adm.getDep());
        data.put("sem", adm.getSem());
        return ResponseEntity.ok(data);
    }
}