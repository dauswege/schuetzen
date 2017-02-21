package at.dauswege.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
public class Person {

  @Id
  @GeneratedValue
  private Long id;

  @Column
  private String firstname;

  @Column
  private String lastname;

  @Column
  private String username;

  @Column
  private String mailAddress;

  @Column
  @JsonIgnore
  private String password;

  // @OneToMany(targetEntity = BookingDay.class, mappedBy = "person")
  // private List<BookingDay> bookingDays = new ArrayList<>();

  // public Person add(BookingDay bookingDay) {
  //
  // this.bookingDays.add(bookingDay);
  // return this;
  // }

}
