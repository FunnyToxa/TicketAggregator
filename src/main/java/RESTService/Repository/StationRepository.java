package RESTService.Repository;

import RESTService.DTO.Response.Station;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StationRepository extends JpaRepository<Station, String> {
}
