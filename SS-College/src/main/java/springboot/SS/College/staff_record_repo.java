package springboot.SS.College;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import jakarta.transaction.Transactional;

public interface staff_record_repo extends JpaRepository<staff_record_model, String> {
    
    // Insert staff record
    @Transactional
    @Modifying
    @Query(value = "EXEC sp_staff_record_insert :id, :fname, :name, :gender, :email, :mobile, :designation, :subject, :dep, :exp", 
           nativeQuery = true)
    void insert(
        @Param("id") String id,
        @Param("fname") String fname,
        @Param("name") String name,
        @Param("gender") String gender,
        @Param("email") String email,
        @Param("mobile") String mobile,
        @Param("designation") String designation,
        @Param("subject") String subject,
        @Param("dep") String dep,
        @Param("exp") String exp);

    // Get all staff records
    @Query(value = "EXEC sp_staff_record_get_all", nativeQuery = true)
    List<staff_record_model> get_all();
    
    // Get staff by ID
    @Query(value = "EXEC sp_staff_record_get_by_id :id", nativeQuery = true)
    staff_record_model get_by_id(@Param("id") String id);
    
    // Update staff record
    @Transactional
    @Modifying
    @Query(value = "EXEC sp_staff_record_update :id, :fname, :name, :gender, :email, :mobile, :designation, :subject, :dep, :exp", 
           nativeQuery = true)
    void update(
        @Param("id") String id,
        @Param("fname") String fname,
        @Param("name") String name,
        @Param("gender") String gender,
        @Param("email") String email,
        @Param("mobile") String mobile,
        @Param("designation") String designation,
        @Param("subject") String subject,
        @Param("dep") String dep,
        @Param("exp") String exp);
    
    // Delete staff record
    @Transactional
    @Modifying
    @Query(value = "EXEC sp_staff_record_delete :id", nativeQuery = true)
    void delete(@Param("id") String id);
    
    // Search staff records
    @Query(value = "EXEC sp_staff_record_search :id, :name, :fname, :dep, :designation, :subject", 
           nativeQuery = true)
    List<staff_record_model> search(
        @Param("id") String id,
        @Param("name") String name,
        @Param("fname") String fname,
        @Param("dep") String dep,
        @Param("designation") String designation,
        @Param("subject") String subject);
    
    // Check if staff exists
    @Query(value = "EXEC sp_staff_record_exists :id", nativeQuery = true)
    Integer exists_by_id(@Param("id") String id);
    
    // Get staff count by department
    @Query(value = "EXEC sp_staff_record_count_by_dep", nativeQuery = true)
    List<Object[]> count_by_dep();
}