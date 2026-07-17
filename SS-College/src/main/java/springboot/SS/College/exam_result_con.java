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
public class exam_result_con {

    @Autowired
    private exam_result_repo ar;

    // Admission repository to fetch student details
    @Autowired
    private admission_repo admissionRepo;

    // ---------- SHOW ADD FORM ----------
    @GetMapping("/exam_result")
    public String showAddForm() {
        return "exam_result";
    }

    // ---------- INSERT EXAM RESULT ----------
    @PostMapping("/exam_result")
    public String insert_exam_result(
            @RequestParam("id") String id,
            @RequestParam("name") String name,
            @RequestParam("etype") String etype,
            @RequestParam("subject") String subject,
            @RequestParam("date") String date,
            @RequestParam("time") String time,
            @RequestParam("section") String section,
            @RequestParam("tmark") String tmark,
            @RequestParam("mark") String mark,
            @RequestParam("grade") String grade,
            @RequestParam("status") String status,
            RedirectAttributes ra) {

        if (ar.existsById(id)) {
            ra.addFlashAttribute("err", "Exam ID already exists");
            return "redirect:/exam_result";
        }
        try {
            ar.insert(id, name, etype, subject, date, time, section, tmark, mark, grade, status);
            ra.addFlashAttribute("msg", "Exam result added successfully");
        } catch (Exception e) {
            ra.addFlashAttribute("err", "Failed to add exam result: " + e.getMessage());
        }
        return "redirect:/exam_result";
    }

    // ---------- LIST ALL EXAM RESULTS ----------
    @GetMapping("/exam_results_list")
    public String listExamResults(Model m) {
        m.addAttribute("examResults", ar.get_all());
        return "exam_results_list";
    }

    // ---------- SHOW EDIT FORM ----------
    @GetMapping("/exam_result/edit/{id}")
    public String showEditForm(@PathVariable("id") String id, Model m) {
        Optional<exam_result_model> optional = ar.findById(id);
        if (optional.isEmpty()) {
            return "redirect:/exam_results_list";
        }
        m.addAttribute("exam", optional.get());
        return "exam_result_edit";
    }

    // ---------- UPDATE EXAM RESULT ----------
    @PostMapping("/exam_result/update")
    public String updateExamResult(
            @RequestParam("id") String id,
            @RequestParam("name") String name,
            @RequestParam("etype") String etype,
            @RequestParam("subject") String subject,
            @RequestParam("date") String date,
            @RequestParam("time") String time,
            @RequestParam("section") String section,
            @RequestParam("tmark") String tmark,
            @RequestParam("mark") String mark,
            @RequestParam("grade") String grade,
            @RequestParam("status") String status,
            RedirectAttributes ra) {

        try {
            ar.update(id, name, etype, subject, date, time, section, tmark, mark, grade, status);
            ra.addFlashAttribute("msg", "Exam result updated successfully");
        } catch (Exception e) {
            ra.addFlashAttribute("err", "Update failed: " + e.getMessage());
            return "redirect:/exam_result/edit/" + id;
        }
        return "redirect:/exam_results_list";
    }

    // ---------- DELETE EXAM RESULT ----------
    @GetMapping("/exam_result/delete/{id}")
    public String deleteExamResult(@PathVariable("id") String id, RedirectAttributes ra) {
        try {
            ar.delete(id);
            ra.addFlashAttribute("msg", "Exam result deleted successfully");
        } catch (Exception e) {
            ra.addFlashAttribute("err", "Delete failed: " + e.getMessage());
        }
        return "redirect:/exam_results_list";
    }

    // ✅ REST endpoint for auto-fill (unique name to avoid conflict with assessment_con)
    @GetMapping("/getStudentByRollNoExam")
    @ResponseBody
    public ResponseEntity<?> getStudentByRollNo(@RequestParam String rollno) {
        admission_model student = admissionRepo.findByRnumber(rollno);
        if (student == null) {
            return ResponseEntity.notFound().build();
        }
        Map<String, String> data = new HashMap<>();
        data.put("name", student.getName() + " " + student.getLname());
        data.put("section", student.getSection());
        return ResponseEntity.ok(data);
    }
}