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
public class attendance_con {

    @Autowired
    private attendance_repo attendanceRepo;

    // Admission repository to fetch student details
    @Autowired
    private admission_repo admissionRepo;

    // -------- SHOW ADD FORM (GET /attendance) --------
    @GetMapping("/attendance")
    public String showAddForm() {
        return "attendance";
    }

    // -------- INSERT NEW ATTENDANCE (POST /attendance) --------
    @PostMapping("/attendance")
    public String insertAttendance(
            @RequestParam("rnumber") String rnumber,
            @RequestParam("name") String name,
            @RequestParam("date") String date,
            @RequestParam("gender") String gender,
            @RequestParam("course") String course,
            @RequestParam("section") String section,
            @RequestParam("status") String status,
            RedirectAttributes ra) {

        try {
            if (attendanceRepo.existsById(rnumber)) {
                ra.addFlashAttribute("err", "Roll number already exists: " + rnumber);
                return "redirect:/attendance";
            }
            attendance_model attendance = new attendance_model(
                rnumber, name, date, gender, course, section, status
            );
            attendanceRepo.save(attendance);
            ra.addFlashAttribute("msg", "Attendance record added successfully!");
        } catch (Exception e) {
            ra.addFlashAttribute("err", "Error adding attendance: " + e.getMessage());
            return "redirect:/attendance";
        }
        return "redirect:/attendance";
    }

    // -------- LIST ALL ATTENDANCE RECORDS (GET /attendance_list) --------
    @GetMapping("/attendance_list")
    public String listAttendance(Model model) {
        List<attendance_model> attendances = attendanceRepo.findAll();
        model.addAttribute("attendances", attendances);
        return "attendance_list";
    }

    // -------- SHOW EDIT FORM (GET /attendance/edit/{rnumber}) --------
    @GetMapping("/attendance/edit/{rnumber}")
    public String showEditForm(@PathVariable("rnumber") String rnumber, Model model) {
        Optional<attendance_model> optional = attendanceRepo.findById(rnumber);
        if (optional.isEmpty()) {
            return "redirect:/attendance_list";
        }
        model.addAttribute("attendance", optional.get());
        return "attendance_edit";
    }

    // -------- UPDATE ATTENDANCE (POST /attendance/update) --------
    @PostMapping("/attendance/update")
    public String updateAttendance(
            @RequestParam("rnumber") String rnumber,
            @RequestParam("name") String name,
            @RequestParam("date") String date,
            @RequestParam("gender") String gender,
            @RequestParam("course") String course,
            @RequestParam("section") String section,
            @RequestParam("status") String status,
            RedirectAttributes ra) {

        try {
            attendance_model updated = new attendance_model(
                rnumber, name, date, gender, course, section, status
            );
            attendanceRepo.save(updated);
            ra.addFlashAttribute("msg", "Attendance record updated successfully!");
        } catch (Exception e) {
            ra.addFlashAttribute("err", "Update failed: " + e.getMessage());
            return "redirect:/attendance/edit/" + rnumber;
        }
        return "redirect:/attendance_list";
    }

    // -------- DELETE ATTENDANCE (GET /attendance/delete/{rnumber}) --------
    @GetMapping("/attendance/delete/{rnumber}")
    public String deleteAttendance(@PathVariable("rnumber") String rnumber, RedirectAttributes ra) {
        try {
            attendanceRepo.deleteById(rnumber);
            ra.addFlashAttribute("msg", "Record deleted successfully!");
        } catch (Exception e) {
            ra.addFlashAttribute("err", "Delete failed: " + e.getMessage());
        }
        return "redirect:/attendance_list";
    }

    // ✅ REST endpoint to get student details from admission by roll number
    @GetMapping("/getStudentByRollNoAttend")
    @ResponseBody
    public ResponseEntity<?> getStudentByRollNo(@RequestParam String rollno) {
        admission_model student = admissionRepo.findByRnumber(rollno);
        if (student == null) {
            return ResponseEntity.notFound().build();
        }
        Map<String, String> data = new HashMap<>();
        data.put("name", student.getName() + " " + student.getLname());
        data.put("gender", student.getGender());
        data.put("course", student.getCourse());
        data.put("section", student.getSection());
        return ResponseEntity.ok(data);
    }
}