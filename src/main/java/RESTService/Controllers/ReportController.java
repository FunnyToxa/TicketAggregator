package RESTService.Controllers;

import RESTService.DTO.Entries.ReportEntry;
import RESTService.DTO.RequestResponse;
import RESTService.Repository.UserRequestRepository;
import RESTService.Service.TicketService;
import org.hibernate.internal.build.AllowSysOut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.CannotCreateTransactionException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController("/Reports")
public class ReportController {
    @Autowired
    private TicketService ticketService;

    @Autowired
    private UserRequestRepository userRequestRepository;


    @GetMapping("Reports/RequestCountByDays")
    public RequestResponse getCountUserRequests(@RequestParam String token){
        //проверяем авторизацию
        try {
            if (!ticketService.checkToken(token))
                return new RequestResponse("Ошибка авторизации. Токен не действителен!");
        } catch (Exception e){ //обработка ошибки если БД стала недоступна во время работы приложения
            return ticketService.checkException(e);
        }

        List<Map<String, String>> report = userRequestRepository.getCountOfUserRequestsGroupedByDays();
        return new RequestResponse(new ReportEntry(report));
    }

}
