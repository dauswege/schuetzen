package at.dauswege.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
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

  @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
  private Set<BookingDay> bookingDays;

  public Person() {
    super();
  }

  public Person(Long id, String firstname, String lastname, String username, String mailAddress,
      String password, Set<BookingDay> bookingDays) {
    super();
    this.id = id;
    this.firstname = firstname;
    this.lastname = lastname;
    this.username = username;
    this.mailAddress = mailAddress;
    this.password = password;
    this.bookingDays = bookingDays;
  }

  public Long getId() {

    return id;
  }

  public String getFirstname() {

    return firstname;
  }

  public String getLastname() {

    return lastname;
  }

  public String getMailAddress() {

    return mailAddress;
  }

  public String getPassword() {

    return password;
  }

  public Set<BookingDay> getBookingDays() {

    return bookingDays;
  }

  public void setId(Long id) {

    this.id = id;
  }

  public void setFirstname(String firstname) {

    this.firstname = firstname;
  }

  public void setLastname(String lastname) {

    this.lastname = lastname;
  }

  public void setMailAddress(String mailAddress) {

    this.mailAddress = mailAddress;
  }

  public void setPassword(String password) {

    this.password = password;
  }

  public void setBookingDays(Set<BookingDay> bookingDays) {

    this.bookingDays = bookingDays;
  }

  public String getUsername() {

    return username;
  }

  public void setUsername(String username) {

    this.username = username;
  }

}
