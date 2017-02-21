package at.dauswege.entity;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.rest.core.config.Projection;

@Projection(name = "bookingdaysWithPositionsInline", types = {BookingDay.class})
public interface BookingDaysWithPositionsInline {

  LocalDate getBookingDate();

  List<Position> getPositions();

}
