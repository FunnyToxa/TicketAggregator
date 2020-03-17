package RESTService.DTO.Entries;

import RESTService.DTO.Response.Company;

import java.util.List;

public class CompanyEntry extends ResponseEntry{
    private List<Company> companies;

    public CompanyEntry(List<Company> companies) {
        this.companies = companies;
    }

    public List<Company> getCompanies() {
        return companies;
    }
}
