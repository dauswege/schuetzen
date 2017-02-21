package at.dauswege.repositories.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.rest.core.annotation.HandleBeforeDelete;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.stereotype.Component;

import at.dauswege.entity.BookingDay;
import at.dauswege.entity.Position;
import at.dauswege.repositories.rest.BookingDayRepository;
import lombok.RequiredArgsConstructor;

@RepositoryEventHandler
@Component
@RequiredArgsConstructor
public class PositionEventHandler {

  public static final Logger LOGGER = LoggerFactory.getLogger(PositionEventHandler.class);

  private final BookingDayRepository bookingDayRepository;

  @HandleBeforeDelete
  public void handleBeforeDelete(Position position) {

    BookingDay bday = bookingDayRepository.findByPositionsId(position.getId());

    bday.remove(position);
    if (bday.getPositions().size() < 1) {
      bookingDayRepository.delete(bday);
    }

  }

}
