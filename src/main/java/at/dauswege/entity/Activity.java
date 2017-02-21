package at.dauswege.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class Activity {

  @Id
  @GeneratedValue
  private Long id;

  @Column
  private String name;

}
