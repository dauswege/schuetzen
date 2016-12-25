package at.dauswege.repositories.rest;

import java.util.Set;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import at.dauswege.dto.PositionDTO;
import at.dauswege.entity.Position;
import at.dauswege.repositories.rest.projections.InlineBookingDay;

@RepositoryRestResource(excerptProjection = InlineBookingDay.class)
public interface PositionsRepository extends CrudRepository<Position, Long> {

  @Query("select pos from Person p "
      + "inner join p.bookingDays bday "
      + "inner join bday.positions pos "
      + "where p.id = ?#{authentication.principal}")
  public Set<Position> findMyPositions();

  @Query("select new at.dauswege.dto.PositionDTO(pos.description, pos.duration, bday.bookingDate) "
      + "from Person p "
      + "inner join p.bookingDays bday "
      + "inner join bday.positions pos "
      + "where p.id = ?#{authentication.principal}")
  public Set<PositionDTO> findMyPositionsMapped();

}
