package at.dauswege.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name = "BOOKINGDAY")
public class BookingDay {

  @Id
  @GeneratedValue
  private Long id;

  @Column
  @NotNull
  private LocalDate bookingDate;

  @JsonManagedReference
  @ManyToOne(cascade = CascadeType.MERGE)
  @NotNull
  private Person person;

  @OneToMany(cascade = CascadeType.ALL)
  @JoinColumn(name = "bookingday_id", referencedColumnName = "id")
  // @JoinTable(joinColumns = @JoinColumn(name = "id", referencedColumnName = "bookingday_id"))
  private List<Position> positions = new ArrayList<>();

  public BookingDay add(Position position) {

    positions.add(position);
    return this;
  }

  public BookingDay remove(Position position) {

    positions.remove(position);
    return this;
  }

}
