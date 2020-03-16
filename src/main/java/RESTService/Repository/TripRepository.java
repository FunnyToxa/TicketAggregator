package RESTService.Repository;

import RESTService.DTOUnits.Response.Trip;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TripRepository extends JpaRepository<Trip, Integer> {
}
