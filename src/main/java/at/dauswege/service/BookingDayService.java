package at.dauswege.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import at.dauswege.dto.PositionSaveDTO;
import at.dauswege.entity.Activity;
import at.dauswege.entity.BookingDay;
import at.dauswege.entity.Person;
import at.dauswege.entity.Position;
import at.dauswege.repositories.rest.ActivityRepository;
import at.dauswege.repositories.rest.BookingDayRepository;
import at.dauswege.repositories.rest.PersonRepository;

@Service
public class BookingDayService {

  @Autowired
  private BookingDayRepository bookingDayRepository;

  @Autowired
  private PersonRepository personRepository;

  @Autowired
  private ActivityRepository activityRepository;

  @Transactional(readOnly = false)
  public void addPosition(PositionSaveDTO positionSaveDTO, Long personId) {

    BookingDay bookingDay = bookingDayRepository.findMyBookingDayByDate(positionSaveDTO.getDate());

    if (bookingDay == null) {
      bookingDay = new BookingDay();
      bookingDay.setBookingDate(positionSaveDTO.getDate());
      Person person = personRepository.findOne(personId);
      bookingDay.setPerson(person);
    }

    Position position = new Position();
    position.setDescription(positionSaveDTO.getDescription());
    position.setDuration(positionSaveDTO.getDuration());

    if (positionSaveDTO.getActivity() != null) {
      Activity activity = activityRepository.findByName(positionSaveDTO.getActivity());
      if (activity == null) {
        activity = new Activity();
        activity.setName(positionSaveDTO.getActivity());
      }
      position.setActivity(activity);
    }
    bookingDay.add(position);

    bookingDayRepository.save(bookingDay);
  }

}
