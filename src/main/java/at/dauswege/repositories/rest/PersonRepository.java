package at.dauswege.repositories.rest;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.security.access.prepost.PreAuthorize;

import at.dauswege.entity.Person;

// @RepositoryRestResource(path = "persons")
@RepositoryRestResource
public interface PersonRepository extends CrudRepository<Person, Long> {

  public Person findOneByMailAddressOrUsername(String mailAddress, String username);

  @PreAuthorize("#id == authentication.principal")
  @Override
  public Person findOne(@Param("id") Long id);

  @Query("select p from Person p where p.id = ?#{authentication.principal}")
  public Person findMyPerson();

  // @PreAuthorize("hasRole('ROLE_ADMIN')")
  // @Override
  // public List<Person> findAll();

}
