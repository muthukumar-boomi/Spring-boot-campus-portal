package springboot.SS.College;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import jakarta.transaction.Transactional;

public interface assessment_repo extends JpaRepository<assessment_model, String> {
    
    @Transactional
    @Modifying
    @Query(nativeQuery = true, value = "EXEC sp_assessment_insert :rnumber, :name, :atype, :course, :subject, :sem, :section, :maxmark, :mobtined, :ddate, :status, :grade")
    public void insert(
            @Param("rnumber") String rnumber,
            @Param("name") String name,
            @Param("atype") String atype,
            @Param("course") String course,
            @Param("subject") String subject,
            @Param("sem") String sem,
            @Param("section") String section,
            @Param("maxmark") String maxmark,
            @Param("mobtined") String mobtined,
            @Param("ddate") String ddate,
            @Param("status") String status,
            @Param("grade") String grade);
    
    @Query(nativeQuery = true, value = "EXEC sp_assessment_get")
    public List<assessment_model> get_all();
    
    @Transactional
    @Modifying
    @Query(nativeQuery = true, value = "EXEC sp_assessment_update :rnumber, :name, :atype, :course, :subject, :sem, :section, :maxmark, :mobtined, :ddate, :status, :grade")
    public void update(
            @Param("rnumber") String rnumber,
            @Param("name") String name,
            @Param("atype") String atype,
            @Param("course") String course,
            @Param("subject") String subject,
            @Param("sem") String sem,
            @Param("section") String section,
            @Param("maxmark") String maxmark,
            @Param("mobtined") String mobtined,
            @Param("ddate") String ddate,
            @Param("status") String status,
            @Param("grade") String grade);
    
    @Transactional
    @Modifying
    @Query(nativeQuery = true, value = "EXEC sp_assessment_delete :rnumber")
    public void delete(@Param("rnumber") String rnumber);
}