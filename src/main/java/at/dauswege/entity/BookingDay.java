package at.dauswege.entity;

import java.time.LocalDate;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BookingDay {

  @Id
  @GeneratedValue
  private Long id;

  @Column
  @NotNull
  private LocalDate bookingDate;

  @OneToMany(cascade = CascadeType.ALL)
  private Set<Position> positions;

  @ManyToOne
  @NotNull
  private Person person;

}
