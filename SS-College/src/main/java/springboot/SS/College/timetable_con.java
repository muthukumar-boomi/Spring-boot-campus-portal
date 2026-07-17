package springboot.SS.College;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class timetable_con {

    @Autowired
    private timetable_repo tr;

    // ✅ Inject staff repository to get staff names
    @Autowired
    private staff_record_repo staffRepo;

    // -------- SHOW ADD FORM (with staff list) --------
    @GetMapping("/timetable")
    public String showAddForm(Model model) {
        // Fetch all staff records
        List<staff_record_model> staffList = staffRepo.get_all();
        // Pass staff list to the view
        model.addAttribute("staffList", staffList);
        return "timetable";
    }

    // -------- INSERT NEW ENTRY --------
    @PostMapping("/timetable")
    public String insert_timetable(
            @RequestParam("course") String course,
            @RequestParam("sem") String sem,
            @RequestParam("section") String section,
            @RequestParam("subject") String subject,
            @RequestParam("staff") String staff,   // This will receive the selected staff name
            @RequestParam("day") String day,
            @RequestParam("stime") String stime,
            @RequestParam("etime") String etime,
            RedirectAttributes ra) {

        try {
            tr.insert(course, sem, section, subject, staff, day, stime, etime);
            ra.addFlashAttribute("msg", "Timetable entry added successfully");
        } catch (Exception e) {
            ra.addFlashAttribute("err", "Insert failed: " + e.getMessage());
        }
        return "redirect:/timetable";
    }

    // -------- SHOW EDIT FORM (also needs staff list) --------
    @GetMapping("/timetable/edit/{course}")
    public String editTimetable(@PathVariable("course") String course, Model m) {
        timetable_model entry = tr.findById(course).orElse(null);
        if (entry == null) {
            return "redirect:/timetable";
        }
        List<staff_record_model> staffList = staffRepo.get_all();
        m.addAttribute("staffList", staffList);
        m.addAttribute("timetable", entry);
        return "timetable_edit";
    }

    // -------- UPDATE --------
    @PostMapping("/timetable/update")
    public String updateTimetable(
            @RequestParam("course") String course,
            @RequestParam("sem") String sem,
            @RequestParam("section") String section,
            @RequestParam("subject") String subject,
            @RequestParam("staff") String staff,
            @RequestParam("day") String day,
            @RequestParam("stime") String stime,
            @RequestParam("etime") String etime,
            RedirectAttributes ra) {

        try {
            tr.update(course, sem, section, subject, staff, day, stime, etime);
            ra.addFlashAttribute("msg", "Timetable updated successfully");
        } catch (Exception e) {
            ra.addFlashAttribute("err", "Update failed: " + e.getMessage());
            return "redirect:/timetable/edit/" + course;
        }
        return "redirect:/timetable";
    }

    // -------- DELETE --------
    @GetMapping("/timetable/delete/{course}")
    public String deleteTimetable(@PathVariable("course") String course, RedirectAttributes ra) {
        try {
            tr.deleteByCourse(course);
            ra.addFlashAttribute("msg", "Timetable deleted successfully");
        } catch (Exception e) {
            ra.addFlashAttribute("err", "Delete failed: " + e.getMessage());
        }
        return "redirect:/timetable";
    }
}