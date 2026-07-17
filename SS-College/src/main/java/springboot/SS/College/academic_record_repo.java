package springboot.SS.College;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import jakarta.transaction.Transactional;

@Repository
public interface academic_record_repo extends JpaRepository<academic_record_model, String> {

    // -------------------- INSERT --------------------
    @Transactional
    @Modifying
    @Query(value = "EXEC sp_academic_record_insert :id, :name, :sem, :dep, :subject, :imark, :mark, :total, :status, :grade", 
           nativeQuery = true)
    void insert(
        @Param("id") String id,
        @Param("name") String name,
        @Param("sem") String sem,
        @Param("dep") String dep,
        @Param("subject") String subject,
        @Param("imark") String imark,
        @Param("mark") String mark,
        @Param("total") String total,
        @Param("status") String status,
        @Param("grade") String grade);

    // -------------------- GET ALL (FIXED) --------------------
    @Query(value = "EXEC sp_academic_record_get_all", nativeQuery = true)
    List<academic_record_model> get_all();   // ✅ instance method, not static

    // -------------------- GET BY ID --------------------
    @Query(value = "EXEC sp_academic_record_get_by_id :id", nativeQuery = true)
    academic_record_model get_by_id(@Param("id") String id);

    // -------------------- UPDATE --------------------
    @Transactional
    @Modifying
    @Query(value = "EXEC sp_academic_record_update :id, :name, :sem, :dep, :subject, :imark, :mark, :total, :status, :grade", 
           nativeQuery = true)
    void update(
        @Param("id") String id,
        @Param("name") String name,
        @Param("sem") String sem,
        @Param("dep") String dep,
        @Param("subject") String subject,
        @Param("imark") String imark,
        @Param("mark") String mark,
        @Param("total") String total,
        @Param("status") String status,
        @Param("grade") String grade);

    // -------------------- DELETE --------------------
    @Transactional
    @Modifying
    @Query(value = "EXEC sp_academic_record_delete :id", nativeQuery = true)
    void delete(@Param("id") String id);

    // -------------------- SEARCH --------------------
    @Query(value = "EXEC sp_academic_record_search :id, :name, :sem, :dep", nativeQuery = true)
    List<academic_record_model> search(
        @Param("id") String id,
        @Param("name") String name,
        @Param("sem") String sem,
        @Param("dep") String dep);

    // -------------------- GET BY SEM & DEP --------------------
    @Query(value = "EXEC sp_academic_record_get_by_sem_dep :sem, :dep", nativeQuery = true)
    List<academic_record_model> get_by_sem_dep(
        @Param("sem") String sem,
        @Param("dep") String dep);
}