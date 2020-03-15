package RESTService._DB;

import RESTService.Units.Request.User;
import RESTService.Units.Response.Company;
import RESTService.Units.Response.Station;
import RESTService.Units.Response.Trip;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public void saveTrips(List<Trip> trips){
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
                    tripRepository.save(trip);
                });

//        tripRepository.saveAll(trips);
    }


}
