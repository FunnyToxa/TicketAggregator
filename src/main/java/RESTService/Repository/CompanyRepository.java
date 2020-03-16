package RESTService.Repository;

import RESTService.DTOUnits.Response.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company, String> {
}
