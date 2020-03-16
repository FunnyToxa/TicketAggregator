package RESTService.Repository;

import RESTService.DTO.Response.Trip;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TripRepository extends JpaRepository<Trip, Integer> {
}
