package at.dauswege.entity;

import java.time.Duration;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
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
public class Position {

  @Id
  @GeneratedValue
  private Long id;

  @Column
  private String description;

  @Column
  private Duration duration;

  @ManyToOne
  @NotNull
  private BookingDay bookingDay;

}
