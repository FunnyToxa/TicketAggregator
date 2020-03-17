package RESTService.Repository;

import RESTService.DTO.Response.Company;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CompanyRepository extends JpaRepository<Company, String> {
    List<Company> findByCompanyNameContaining(String companyName);
}
