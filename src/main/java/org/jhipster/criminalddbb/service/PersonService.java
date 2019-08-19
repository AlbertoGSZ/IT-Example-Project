package org.jhipster.criminalddbb.service;

import org.jhipster.criminalddbb.domain.CaseReport;
import org.jhipster.criminalddbb.domain.Person;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link Person}.
 */
public interface PersonService {

    /**
     * Save a person.
     * @param person the entity to save.
     * @return the persisted entity.
     */
    Person save(Person person);

    /**
     * Get all the people.
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<Person> findAll(Pageable pageable);


    /**
     * Get the "id" person.
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<Person> findOne(Long id);

    /**
     * Delete the "id" person.
     * @param id the id of the entity.
     */
    void delete(Long id);


    /**
     * Returns all the case reports of "id" person.
     * @param id the id of the entity Person.
     * @return a list of found entities.
     */
    List<CaseReport> findAllCaseReports(Long id);


    /**
     * Returns all the entities considered VIP.
     * @return a list of found entities.
     */
    List<Person> findVIPs();
}
