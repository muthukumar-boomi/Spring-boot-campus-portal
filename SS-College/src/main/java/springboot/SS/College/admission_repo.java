package springboot.SS.College;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import jakarta.transaction.Transactional;

public interface admission_repo extends JpaRepository<admission_model, String> {
    
    // Insert
    @Transactional
    @Modifying
    @Query(value = "EXEC sp_admission :id, :date, :atype, :name, :lname, :dob, :gender, :blood, :email, :mobile, :course, :address, :dep, :ayear, :sem, :rnumber, :section, :fname, :mname, :ph, :accommodation, :pcollege, :work", 
           nativeQuery = true)
    void insert(
        @Param("id") String id,
        @Param("date") String date,
        @Param("atype") String atype,
        @Param("name") String name,
        @Param("lname") String lname,
        @Param("dob") String dob,
        @Param("gender") String gender,
        @Param("blood") String blood,
        @Param("email") String email,
        @Param("mobile") String mobile,
        @Param("course") String course,
        @Param("address") String address,
        @Param("dep") String dep,
        @Param("ayear") String ayear,
        @Param("sem") String sem,
        @Param("rnumber") String rnumber,
        @Param("section") String section,
        @Param("fname") String fname,
        @Param("mname") String mname,
        @Param("ph") String ph,
        @Param("accommodation") String accommodation,
        @Param("pcollege") String pcollege,
        @Param("work") String work);
    
    // Get all
    @Query(value = "EXEC sp_admission_get_all", nativeQuery = true)
    List<admission_model> get_all();
    
    // Get by ID
    @Query(value = "EXEC sp_admission_get_by_id :id", nativeQuery = true)
    admission_model get_by_id(@Param("id") String id);
    
    // Update
    @Transactional
    @Modifying
    @Query(value = "EXEC sp_admission_update :id, :date, :atype, :name, :lname, :dob, :gender, :blood, :email, :mobile, :course, :address, :dep, :ayear, :sem, :rnumber, :section, :fname, :mname, :ph, :accommodation, :pcollege, :work", 
           nativeQuery = true)
    void update(
        @Param("id") String id,
        @Param("date") String date,
        @Param("atype") String atype,
        @Param("name") String name,
        @Param("lname") String lname,
        @Param("dob") String dob,
        @Param("gender") String gender,
        @Param("blood") String blood,
        @Param("email") String email,
        @Param("mobile") String mobile,
        @Param("course") String course,
        @Param("address") String address,
        @Param("dep") String dep,
        @Param("ayear") String ayear,
        @Param("sem") String sem,
        @Param("rnumber") String rnumber,
        @Param("section") String section,
        @Param("fname") String fname,
        @Param("mname") String mname,
        @Param("ph") String ph,
        @Param("accommodation") String accommodation,
        @Param("pcollege") String pcollege,
        @Param("work") String work);
    
    // Delete
    @Transactional
    @Modifying
    @Query(value = "EXEC sp_admission_delete :id", nativeQuery = true)
    void delete(@Param("id") String id);
    
    // Search
    @Query(value = "EXEC sp_admission_search :id, :name, :course, :dep, :ayear", nativeQuery = true)
    List<admission_model> search(
        @Param("id") String id,
        @Param("name") String name,
        @Param("course") String course,
        @Param("dep") String dep,
        @Param("ayear") String ayear);
    
    // Check exists
    @Query(value = "EXEC sp_admission_exists :id", nativeQuery = true)
    Integer exists_by_id(@Param("id") String id);
    
    // Get statistics
    @Query(value = "EXEC sp_admission_get_statistics", nativeQuery = true)
    List<Object[]> get_statistics();

    // ✅ NEW: Find by roll number (for assessment auto-fill)
    @Query(value = "SELECT * FROM admission WHERE rnumber = :rnumber", nativeQuery = true)
    admission_model findByRnumber(@Param("rnumber") String rnumber);
}