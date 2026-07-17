package springboot.SS.College;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface admin_register_repo extends JpaRepository<admin_register_model, String> {
    Optional<admin_register_model> findByMobileAndDep(String mobile, String dep);
    Optional<admin_register_model> findByMobile(String mobile);   // needed for AJAX
}