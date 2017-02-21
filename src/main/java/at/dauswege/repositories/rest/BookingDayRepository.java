package at.dauswege.repositories.rest;

import java.time.LocalDate;
import java.util.Set;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import at.dauswege.entity.BookingDay;

@RepositoryRestResource // (excerptProjection = BookingDaysWithPositionsInline.class)
public interface BookingDayRepository extends CrudRepository<BookingDay, Long> {

  BookingDay findByBookingDate(LocalDate bookingDate);

  @Query("select bday from BookingDay bday where bday.person.id = ?#{authentication.principal} order by bday.bookingDate")
  Set<BookingDay> findMyBookingDays();

  @Query("select bday from BookingDay bday where bday.person.id = ?#{authentication.principal} and bday.bookingDate = :date")
  BookingDay findMyBookingDayByDate(@Param("date") LocalDate date);

  BookingDay findByPositionsId(@Param("id") Long id);

}
