package springboot.SS.College;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import jakarta.transaction.Transactional;

public interface exam_result_repo extends JpaRepository<exam_result_model, String> {
    
    // Insert exam result
    @Transactional
    @Modifying
    @Query(value = "EXEC sp_exam_result_insert :id, :name, :etype, :subject, :date, :time, :section, :tmark, :mark, :grade, :status", 
           nativeQuery = true)
    void insert(
        @Param("id") String id,
        @Param("name") String name,
        @Param("etype") String etype,
        @Param("subject") String subject,
        @Param("date") String date,
        @Param("time") String time,
        @Param("section") String section,
        @Param("tmark") String tmark,
        @Param("mark") String mark,
        @Param("grade") String grade,
        @Param("status") String status);
    
    // Get all exam results
    @Query(value = "EXEC sp_exam_result_get_all", nativeQuery = true)
    List<exam_result_model> get_all();
    
    // Get exam result by ID
    @Query(value = "EXEC sp_exam_result_get_by_id :id", nativeQuery = true)
    exam_result_model get_by_id(@Param("id") String id);
    
    // Update exam result
    @Transactional
    @Modifying
    @Query(value = "EXEC sp_exam_result_update :id, :name, :etype, :subject, :date, :time, :section, :tmark, :mark, :grade, :status", 
           nativeQuery = true)
    void update(
        @Param("id") String id,
        @Param("name") String name,
        @Param("etype") String etype,
        @Param("subject") String subject,
        @Param("date") String date,
        @Param("time") String time,
        @Param("section") String section,
        @Param("tmark") String tmark,
        @Param("mark") String mark,
        @Param("grade") String grade,
        @Param("status") String status);
    
    // Delete exam result
    @Transactional
    @Modifying
    @Query(value = "EXEC sp_exam_result_delete :id", nativeQuery = true)
    void delete(@Param("id") String id);
    
}