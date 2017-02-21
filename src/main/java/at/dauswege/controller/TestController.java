package at.dauswege.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import at.dauswege.dto.PositionSaveDTO;
import at.dauswege.entity.BookingDay;
import at.dauswege.entity.Person;
import at.dauswege.repositories.rest.BookingDayRepository;
import at.dauswege.repositories.rest.PersonRepository;
import at.dauswege.service.BookingDayService;

@RestController
public class TestController {

  @Autowired
  private PersonRepository personRepository;
  @Autowired
  private BookingDayRepository bookingDayRepository;

  @Autowired
  private BookingDayService bookingDayService;

  @GetMapping("dolala")
  public Iterable<Person> doSomething() {

    Iterable<Person> personList = personRepository.findAll();
    for (Person person : personList) {
      System.err.println(person.toString());
    }

    Iterable<BookingDay> bookingDays = bookingDayRepository.findAll();
    if (bookingDays == null) {
      System.err.println("Booking days null");
    }
    for (BookingDay bookingDay : bookingDays) {
      System.err.println("booking day: " + bookingDay.toString());
      System.err.println(bookingDay.getPerson().toString());
    }

    return personList;

  }

  @PostMapping("addPosition")
  public void addPosition(Authentication auth, @RequestBody PositionSaveDTO positionSaveDTO) {

    this.bookingDayService.addPosition(positionSaveDTO, Long.parseLong(auth.getName()));

  }

}
