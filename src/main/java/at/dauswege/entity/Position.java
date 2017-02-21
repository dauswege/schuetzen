package at.dauswege.entity;

import java.time.Duration;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.Data;

@Data
@Entity
public class Position {

  @Id
  @GeneratedValue
  private Long id;

  @Column
  private String description;

  @Column
  private Duration duration;

  @ManyToOne(cascade = CascadeType.ALL)
  private Activity activity;

  // @Column(name = "bookingday_id")
  // private Long bookingdayId;

}
