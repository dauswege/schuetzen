package at.dauswege.repositories.rest;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import at.dauswege.entity.Position;

@RepositoryRestResource(exported = true)
public interface PositionsRepository extends CrudRepository<Position, Long> {

}
