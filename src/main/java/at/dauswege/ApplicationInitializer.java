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

@Component
public class ApplicationInitializer implements ApplicationRunner {

  private final PersonRepository personRepository;
  private final BookingDayRepository bookingDayRepository;

  @Autowired
  private BCryptPasswordEncoder bCryptPasswordEncoder;

  @Autowired
  public ApplicationInitializer(PersonRepository personRepository,
      BookingDayRepository bookingDayRepository) {
    super();
    this.personRepository = personRepository;
    this.bookingDayRepository = bookingDayRepository;
  }

  @Override
  public void run(ApplicationArguments arg0) throws Exception {

    Person person = new Person();
    person.setFirstname("David");
    person.setLastname("Ausweger");
    person.setUsername("daus");
    person.setMailAddress("daftinga@gmail.com");
    person.setPassword(bCryptPasswordEncoder.encode("pwd"));
    person = personRepository.save(person);

    BookingDay bookingDay = new BookingDay();
    bookingDay.setBookingDate(LocalDate.now());
    bookingDay.setPerson(person);

    Set<BookingDay> bookingDays = new HashSet<>();
    bookingDays.add(bookingDay);
    Position position = new Position();
    position.setDescription("testactivity");
    position.setDuration(Duration.ofHours(2));

    bookingDay.add(position);
    bookingDay.setPerson(person);
    bookingDayRepository.save(bookingDay);

    this.personRepository.save(person);

  }

}
