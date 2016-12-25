package at.dauswege.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import at.dauswege.repositories.rest.PersonRepository;

@RestController
public class PersonRestController {

  private final PersonRepository personRepository;

  @Autowired
  public PersonRestController(PersonRepository personRepository) {
    super();
    this.personRepository = personRepository;

  }

  // @GetMapping("myPerson")
  // public Person getMyPerson(Principal principal) {
  //
  // return this.personRepository.findOne(Long.valueOf(principal.getName()));
  //
  // }
  //
  // System.out.println(principal.getName());
  // return personRepository.findByMailAddress(principal.getName());
  //
  // // return null;
  // }

}
