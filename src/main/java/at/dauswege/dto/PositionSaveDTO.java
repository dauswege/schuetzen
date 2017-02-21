package at.dauswege.dto;

import java.time.Duration;
import java.time.LocalDate;

import lombok.Data;

@Data
public class PositionSaveDTO {

  private LocalDate date;

  private Long personId;

  private String description;

  private String activity;

  private Duration duration;

}
