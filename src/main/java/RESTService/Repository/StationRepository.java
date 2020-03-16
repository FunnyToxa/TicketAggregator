package RESTService.Repository;

import RESTService.DTOUnits.Response.Station;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StationRepository extends JpaRepository<Station, String> {
}
