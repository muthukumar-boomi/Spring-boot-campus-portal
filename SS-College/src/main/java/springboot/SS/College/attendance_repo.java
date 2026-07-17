package springboot.SS.College;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import jakarta.transaction.Transactional;

@Repository
public interface attendance_repo extends JpaRepository<attendance_model, String> {
    
    // -------------------- STORED PROCEDURES (optional) --------------------
    @Transactional
    @Modifying
    @Query(value = "EXEC sp_attendance_insert :rnumber, :name, :date, :gender, :course, :section, :status", 
           nativeQuery = true)
    void insertAttendance(@Param("rnumber") String rnumber,
                          @Param("name") String name,
                          @Param("date") String date,
                          @Param("gender") String gender,
                          @Param("course") String course,
                          @Param("section") String section,
                          @Param("status") String status);

    @Query(value = "EXEC sp_attendance_get_all", nativeQuery = true)
    List<attendance_model> getAllAttendance();

    @Query(value = "EXEC sp_attendance_get_by_rnumber :rnumber", nativeQuery = true)
    List<attendance_model> getByRnumber(@Param("rnumber") String rnumber);

    @Transactional
    @Modifying
    @Query(value = "EXEC sp_attendance_update :rnumber, :name, :date, :gender, :course, :section, :status", 
           nativeQuery = true)
    void updateAttendance(@Param("rnumber") String rnumber,
                          @Param("name") String name,
                          @Param("date") String date,
                          @Param("gender") String gender,
                          @Param("course") String course,
                          @Param("section") String section,
                          @Param("status") String status);

    @Transactional
    @Modifying
    @Query(value = "EXEC sp_attendance_delete :rnumber", nativeQuery = true)
    void deleteAttendance(@Param("rnumber") String rnumber);
    
    // ❌ REMOVED: existsByRnumber – use existsById() from JpaRepository instead
}