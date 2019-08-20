package org.jhipster.criminalddbb.service.impl;

import org.jhipster.criminalddbb.domain.CaseReport;
import org.jhipster.criminalddbb.service.PersonService;
import org.jhipster.criminalddbb.domain.Person;
import org.jhipster.criminalddbb.repository.PersonRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link Person}.
 */
@Service
@Transactional
public class PersonServiceImpl implements PersonService {

    private final Logger log = LoggerFactory.getLogger(PersonServiceImpl.class);

    private final PersonRepository personRepository;

    public PersonServiceImpl(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    /**
     * Save a person.
     * @param person the entity to save.
     * @return the persisted entity.
     */
    @Override
    public Person save(Person person) {
        log.debug("Request to save Person : {}", person);
        return personRepository.save(person);
    }


    /**
     * Get all the people.
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<Person> findAll(Pageable pageable) {
        log.debug("Request to get all People");
        return personRepository.findAll(pageable);
    }


    /**
     * Get one person by id.
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<Person> findOne(Long id) {
        log.debug("Request to get Person : {}", id);
        return personRepository.findById(id);
    }


    /**
     * Delete the person by id.
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Person : {}", id);
        personRepository.deleteById(id);
    }


    /**
     * Returns every CaseReport a person has.
     * @return a list of CaseReport entities.
     */
    public List<CaseReport> findAllCaseReports(Long id) {
        return personRepository.findById(id).map(Person::getFelonyRecordsDigitals).get().stream().collect(Collectors.toList());
    }


    /**
     * Returns every person with more than 50 subordinates.
     * @return the entity.
     */
    public List<Person> findVIPs() {
        return personRepository.findAll().stream().filter(person -> person.getSubordinates().size()>50).collect(Collectors.toList()) ;
    }



    ///////////////////////////////WIP SEARCH PERSON///////////////////////////////////////
    /**
     * Returns a list of every matching person if found in DDBB.
     * @return a list of entities.
     */
    @Override
    public List<Person> searchPerson(Person person) {
        List <Person> matchesList = new ArrayList<>();

        //Pedir a repositorio que meta en una lista auxiliar la entity Person que se corresponda con el id especificado, siempre y cuando éste no sea null o menor/igual que cero.
        if (person.getId()!=null && person.getId()>0) matchesList = personRepository.findAll()
                                                                                    .stream()
                                                                                    .filter(Person -> Person.getId()==person.getId())
                                                                                    .collect(Collectors.toList());

        //Si la lista auxiliar está vacía, preguntar en repo si hay alguien con el nombre especificado siempre y cuando éste no sea null
            //if (matchesList.size()==0) preguntar a repo...
        //Si la lista no está vacía, filtrar si el obtenido se llama como hemos especificado
            //else matchesList filter...




        return matchesList;
    }
    //////////////////////////////////////////////////////////////////////////////////////////
}
