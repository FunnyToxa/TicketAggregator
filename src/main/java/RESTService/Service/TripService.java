package RESTService.Service;

import RESTService.DTOUnits.Request.User;
import RESTService.DTOUnits.Request.UserRequest;
import RESTService.DTOUnits.Response.Company;
import RESTService.DTOUnits.Response.Station;
import RESTService.DTOUnits.Response.Trip;
import RESTService.Repository.CompanyRepository;
import RESTService.Repository.StationRepository;
import RESTService.Repository.TripRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@Service
public class TripService {
    @Autowired
    private TripRepository tripRepository;
    @Autowired
    private StationRepository stationRepository;
    @Autowired
    private CompanyRepository companyRepository;

    public void saveTrips(List<Trip> trips, UserRequest userRequest){
        trips.forEach(trip -> {
                    Optional<Company> companyOpt = companyRepository.findById(trip.getCompany().getCompanyCode());
                    if (companyOpt.isPresent())
                        trip.setCompany(companyOpt.get());

                    Optional<Station> stationOpt = stationRepository.findById(trip.getStationFrom().getStationCode());
                    if (stationOpt.isPresent())
                        trip.setStationFrom(stationOpt.get());

                    stationOpt = stationRepository.findById(trip.getStationTo().getStationCode());
                    if (stationOpt.isPresent())
                        trip.setStationTo(stationOpt.get());
                    //TODO: заместо создание нужно сделать поиск
                    trip.setUserRequestsTrips(new HashSet<UserRequest>());
                    trip.getUserRequestsTrips().add(userRequest);
                    tripRepository.save(trip);
                });
    }
}
