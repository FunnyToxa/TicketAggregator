package RESTService.Service;

import RESTService.DTO.Response.Company;
import RESTService.Repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyService {
    @Autowired
    private CompanyRepository companyRepository;

    public List<Company> getAllCompanies(){
        return companyRepository.findAll();
    }

    public List<Company> findCompanyByName(String companyName){
        return companyRepository.findByCompanyNameContaining(companyName);
    }
}
