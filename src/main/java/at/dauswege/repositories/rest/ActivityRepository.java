package at.dauswege.repositories.rest;

import org.springframework.data.repository.CrudRepository;

import at.dauswege.entity.Activity;

public interface ActivityRepository extends CrudRepository<Activity, Long> {

  public Activity findByName(String name);

}
