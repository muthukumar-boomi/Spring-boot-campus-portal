package springboot.SS.College;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import jakarta.transaction.Transactional;
import java.util.List;

@Repository
public interface timetable_repo extends JpaRepository<timetable_model, String> {

    // -------------------- INSERT (Stored Procedure) --------------------
    @Transactional
    @Modifying
    @Query(value = "EXEC sp_timetable_insert :course, :sem, :section, :subject, :staff, :day, :stime, :etime",
           nativeQuery = true)
    void insert(@Param("course") String course,
                @Param("sem") String sem,
                @Param("section") String section,
                @Param("subject") String subject,
                @Param("staff") String staff,
                @Param("day") String day,
                @Param("stime") String stime,
                @Param("etime") String etime);

    // -------------------- UPDATE (Stored Procedure) --------------------
    @Transactional
    @Modifying
    @Query(value = "EXEC sp_timetable_update :course, :sem, :section, :subject, :staff, :day, :stime, :etime",
           nativeQuery = true)
    void update(@Param("course") String course,
                @Param("sem") String sem,
                @Param("section") String section,
                @Param("subject") String subject,
                @Param("staff") String staff,
                @Param("day") String day,
                @Param("stime") String stime,
                @Param("etime") String etime);

    // -------------------- DELETE (Stored Procedure) --------------------
    @Transactional
    @Modifying
    @Query(value = "EXEC sp_timetable_delete :course", nativeQuery = true)
    void deleteByCourse(@Param("course") String course);

    // -------------------- GET ALL (Stored Procedure) --------------------
    @Query(value = "EXEC sp_timetable_get_all", nativeQuery = true)
    List<timetable_model> getAll();
}