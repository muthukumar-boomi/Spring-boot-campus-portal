package springboot.SS.College;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;   // 👈 முக்கியம்

@Controller
public class admission_con {
    
    @Autowired
    private admission_repo ar;
    
    // Show admission form (with list)
    @GetMapping("/admission")
    public String admission(Model model) {
        List<admission_model> admissions = ar.get_all();
        model.addAttribute("admissions", admissions);
        return "admission";
    }
    
    @GetMapping("/admission_list")
    public String admission_list(Model model) {
        List<admission_model> admissions = ar.get_all();
        model.addAttribute("admissions", admissions);
        return "admission_list";
    }
    
    // Insert admission
    @PostMapping("/admission")
    public String insert_admission(@RequestParam("id") String id,
                                   @RequestParam("date") String date,
                                   @RequestParam("atype") String atype,
                                   @RequestParam("name") String name,
                                   @RequestParam("lname") String lname,
                                   @RequestParam("dob") String dob,
                                   @RequestParam("gender") String gender,
                                   @RequestParam("blood") String blood,
                                   @RequestParam("email") String email,
                                   @RequestParam("mobile") String mobile,
                                   @RequestParam("course") String course,
                                   @RequestParam("address") String address,
                                   @RequestParam("dep") String dep,
                                   @RequestParam("ayear") String ayear,
                                   @RequestParam("sem") String sem,
                                   @RequestParam("rnumber") String rnumber,
                                   @RequestParam("section") String section,
                                   @RequestParam("fname") String fname,
                                   @RequestParam("mname") String mname,
                                   @RequestParam("ph") String ph,
                                   @RequestParam("accommodation") String accommodation,
                                   @RequestParam("pcollege") String pcollege,
                                   @RequestParam("work") String work,
                                   RedirectAttributes ra) {   // 👈 Model → RedirectAttributes

        try {
            // Check if admission exists by ID
            Optional<admission_model> admissionExists = ar.findById(id);
            if(admissionExists.isPresent()) {
                ra.addFlashAttribute("err", "Admission ID already exists");
                return "redirect:/admission";   // redirect with error
            }
            
            // Create new admission
            ar.insert(id, date, atype, name, lname, dob, gender, blood, email, 
                     mobile, course, address, dep, ayear, sem, rnumber, section, 
                     fname, mname, ph, accommodation, pcollege, work);
            ra.addFlashAttribute("msg", "Admission successful");
        } catch(Exception e) {
            ra.addFlashAttribute("err", "Admission failed: " + e.getMessage());
            e.printStackTrace();
        }
        return "redirect:/admission";
    }
    
    // Show edit form
    @GetMapping("/admission/edit/{id}")
    public String showEditForm(@PathVariable("id") String id, Model model) {
        admission_model admission = ar.get_by_id(id);
        model.addAttribute("admission", admission);
        return "edit_admission";
    }
    
    // Update admission
    @PostMapping("/admission/update")
    public String update_admission(@RequestParam("id") String id,
                                   @RequestParam("date") String date,
                                   @RequestParam("atype") String atype,
                                   @RequestParam("name") String name,
                                   @RequestParam("lname") String lname,
                                   @RequestParam("dob") String dob,
                                   @RequestParam("gender") String gender,
                                   @RequestParam("blood") String blood,
                                   @RequestParam("email") String email,
                                   @RequestParam("mobile") String mobile,
                                   @RequestParam("course") String course,
                                   @RequestParam("address") String address,
                                   @RequestParam("dep") String dep,
                                   @RequestParam("ayear") String ayear,
                                   @RequestParam("sem") String sem,
                                   @RequestParam("rnumber") String rnumber,
                                   @RequestParam("section") String section,
                                   @RequestParam("fname") String fname,
                                   @RequestParam("mname") String mname,
                                   @RequestParam("ph") String ph,
                                   @RequestParam("accommodation") String accommodation,
                                   @RequestParam("pcollege") String pcollege,
                                   @RequestParam("work") String work,
                                   RedirectAttributes ra) {

        try {
            ar.update(id, date, atype, name, lname, dob, gender, blood, email, 
                     mobile, course, address, dep, ayear, sem, rnumber, section, 
                     fname, mname, ph, accommodation, pcollege, work);
            ra.addFlashAttribute("msg", "Admission updated successfully");
        } catch(Exception e) {
            ra.addFlashAttribute("err", "Update failed: " + e.getMessage());
            return "redirect:/admission/edit/" + id;   // edit page-க்கே redirect with error
        }
        return "redirect:/admission";
    }
    
    // Delete admission
    @GetMapping("/admission/delete/{id}")
    public String delete_admission(@PathVariable("id") String id, RedirectAttributes ra) {
        try {
            ar.delete(id);
            ra.addFlashAttribute("msg", "Admission deleted successfully");
        } catch(Exception e) {
            ra.addFlashAttribute("err", "Delete failed: " + e.getMessage());
        }
        return "redirect:/admission_list";
    }
    
    // Search admissions
    @GetMapping("/admission/search")
    public String search_admissions(@RequestParam(value = "id", required = false) String id,
                                    @RequestParam(value = "name", required = false) String name,
                                    @RequestParam(value = "course", required = false) String course,
                                    @RequestParam(value = "dep", required = false) String dep,
                                    @RequestParam(value = "ayear", required = false) String ayear,
                                    Model model) {
        List<admission_model> admissions = ar.search(id, name, course, dep, ayear);
        model.addAttribute("admissions", admissions);
        model.addAttribute("searchParams", new SearchParams(id, name, course, dep, ayear));
        return "admission_list";
    }
}

// Helper class for search parameters
class SearchParams {
    private String id;
    private String name;
    private String course;
    private String dep;
    private String ayear;
    
    public SearchParams(String id, String name, String course, String dep, String ayear) {
        this.id = id;
        this.name = name;
        this.course = course;
        this.dep = dep;
        this.ayear = ayear;
    }
    
    // Getters and setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getCourse() { return course; }
    public void setCourse(String course) { this.course = course; }
    public String getDep() { return dep; }
    public void setDep(String dep) { this.dep = dep; }
    public String getAyear() { return ayear; }
    public void setAyear(String ayear) { this.ayear = ayear; }
}