package at.dauswege.repositories.rest.projections;

import java.time.Duration;

import org.springframework.data.rest.core.config.Projection;

import at.dauswege.entity.BookingDay;
import at.dauswege.entity.Position;

@Projection(name = "inlineBookingDay", types = {Position.class})
public interface InlineBookingDay {

  String getDescription();

  Duration getDuration();

  BookingDay getBookingDay();

}
