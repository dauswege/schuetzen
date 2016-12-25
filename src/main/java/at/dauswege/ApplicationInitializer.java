package at.dauswege;

import java.time.Duration;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import at.dauswege.entity.BookingDay;
import at.dauswege.entity.Person;
import at.dauswege.entity.Position;
import at.dauswege.repositories.rest.BookingDayRepository;
import at.dauswege.repositories.rest.PersonRepository;
import at.dauswege.repositories.rest.PositionsRepository;

@Component
public class ApplicationInitializer implements ApplicationRunner {

  private PersonRepository personRepository;

  private BookingDayRepository bookingDayRepository;

  private PositionsRepository positionsRepository;

  @Autowired
  private BCryptPasswordEncoder bCryptPasswordEncoder;

  @Autowired
  public ApplicationInitializer(PersonRepository personRepository,
      BookingDayRepository bookingDayRepository, PositionsRepository positionsRepository) {
    super();
    this.personRepository = personRepository;
    this.bookingDayRepository = bookingDayRepository;
    this.positionsRepository = positionsRepository;
  }

  @Override
  public void run(ApplicationArguments arg0) throws Exception {

    Person person = new Person(null, "David", "Ausweger", "daus", "daftinga@gmail.com",
        bCryptPasswordEncoder.encode("pwd"), null);
    person = this.personRepository.save(person);
    Person person2 = new Person(null, "Hans", "Wurst", "wurst", "hans@wurst.at",
        bCryptPasswordEncoder.encode("wurst"), null);
    person2 = this.personRepository.save(person2);
    // .save(new Person(null, "David", "Ausweger", "daftinga@gmail.com", null));

    BookingDay bookingDay = new BookingDay(null, LocalDate.now(), null, person);
    bookingDay = bookingDayRepository.save(bookingDay);

    Set<BookingDay> bookingDays = new HashSet<>();
    bookingDays.add(bookingDay);

    Position position =
        new Position(null, "testactivity", Duration.ofHours(2).minusMinutes(45), bookingDay);
    Set<Position> positions = new HashSet<>();
    positions.add(position);

    bookingDay.setPositions(positions);
    bookingDay = bookingDayRepository.save(bookingDay);
    person.setBookingDays(bookingDays);

    this.personRepository.save(person);

  }

}
