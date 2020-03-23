package RESTService.Controllers;

import RESTService.DTO.Entries.CompanyEntry;
import RESTService.DTO.RequestResponse;
import RESTService.DTO.Response.Company;
import RESTService.Service.CompanyService;
import RESTService.Service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.CannotCreateTransactionException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/company")
public class CompanyController {
    @Autowired
    private CompanyService companyService;

    /**
     * Получение списка всех компаний, которые есть в базе
     * @return
     */
    @GetMapping
    public RequestResponse getCompanies(){
        try {
            List<Company> companies = companyService.getAllCompanies();
            if (companies.size() == 0)
                return new RequestResponse("Нет компаний в базе");
            return new RequestResponse(new CompanyEntry(companies));
        } catch (CannotCreateTransactionException e){ //обработка ошибки если БД стала недоступна во время работы приложения
            return new RequestResponse("Сервис временно недоступен. Просьба повторить попытку через некоторое время.");
        } catch (RuntimeException e){
            return new RequestResponse("Неизвестная ошибка!");
        }
    }

    @GetMapping("{companyName}")
    public RequestResponse getSameCompany(@PathVariable String companyName){
        try {
            List<Company> companies = companyService.findCompanyByName(companyName);
            if (companies.size() == 0)
                return new RequestResponse("Компании с таким именем не найдены");
            return new RequestResponse(new CompanyEntry(companies));
        } catch (CannotCreateTransactionException e){ //обработка ошибки если БД стала недоступна во время работы приложения
            return new RequestResponse("Сервис временно недоступен. Просьба повторить попытку через некоторое время.");
        } catch (RuntimeException e){
            return new RequestResponse("Неизвестная ошибка!");
        }
    }
}
