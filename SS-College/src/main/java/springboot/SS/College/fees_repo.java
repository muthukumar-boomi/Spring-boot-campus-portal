package springboot.SS.College;

import java.math.BigDecimal;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import jakarta.transaction.Transactional;

public interface fees_repo extends JpaRepository<fees_model, String> {
    
    // Insert using stored procedure - FIXED: Changed CALL to EXEC for SQL Server
    @Transactional
    @Modifying
    @Query(value = "EXEC sp_fees_insert :id, :name, :ftype, :ddate, :rs, :pdate, :pmode, :pamount, :status", 
           nativeQuery = true)
    void insert(
        @Param("id") String id,
        @Param("name") String name,
        @Param("ftype") String ftype,
        @Param("ddate") String ddate,
        @Param("rs") String rs,
        @Param("pdate") String pdate,
        @Param("pmode") String pmode,
        @Param("pamount") String pamount,  // Changed from BigDecimal to String
        @Param("status") String status);
    
    // Get all fees - FIXED: Changed CALL to EXEC
    @Query(value = "EXEC sp_fees_get_all", nativeQuery = true)
    List<fees_model> get_all();
    
    // Get fee by ID - FIXED: Changed CALL to EXEC
    @Query(value = "EXEC sp_fees_get_by_id :id", nativeQuery = true)
    fees_model get_by_id(@Param("id") String id);
}