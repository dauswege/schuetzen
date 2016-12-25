package at.dauswege.repositories.rest;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import at.dauswege.entity.BookingDay;

@RepositoryRestResource
public interface BookingDayRepository extends CrudRepository<BookingDay, Long> {

}
