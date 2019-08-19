package org.jhipster.criminalddbb.repository;

import org.jhipster.criminalddbb.domain.CaseReport;
import org.jhipster.criminalddbb.domain.Person;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * Spring Data  repository for the Person entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

}
