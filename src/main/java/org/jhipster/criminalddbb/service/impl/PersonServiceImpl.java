package org.jhipster.criminalddbb.service.impl;

import org.jhipster.criminalddbb.domain.CaseReport;
import org.jhipster.criminalddbb.domain.enumeration.Status;
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




    ///////////////////////////////WIP PATCH RANKorSTATUS///////////////////////////////////////
    public Optional<Person> modStatusOrRank(Long id, String mod) {   //degrade/ascend
        if (!personRepository.existsById(id)) try {
            throw new Exception("Error 404, entity not found.");
        } catch (Exception e) {
            e.printStackTrace();
        }

        Status auxStatus = null;
        if (mod.equalsIgnoreCase("Imprisoned")) auxStatus = Status.IMPRISONED;
        if (mod.equalsIgnoreCase("Dead")) auxStatus = Status.DEAD;
        if (mod.equalsIgnoreCase("Alive")) auxStatus = Status.ALIVE;
        if (mod.equalsIgnoreCase("Unknown")) auxStatus = Status.UNKNOWN;

        if (auxStatus != null) {
            personRepository.findById(id).get().setStatus(auxStatus);
            //Insertar método de autoregulación
        }

        if (mod.equalsIgnoreCase("Ascend")) {
            personRepository.findById(id).get().setRank(personRepository.findById(id).get().getRank() + 1);
            //Insertar método de autoregulación
        }

        if (mod.equalsIgnoreCase("Degrade")) {
            personRepository.findById(id).get().setRank(personRepository.findById(id).get().getRank() - 1);
            //Insertar método de autoregulación
        }

        /*else try {
            throw new Exception("Error 400, Invalid specified syntax");
        } catch (Exception e) {
            e.printStackTrace();
        }*/

        return personRepository.findById(id);
        }
        //////////////////////////////////////////////////////////////////////////////////////////



        ///////////////////////////////WIP SEARCH PERSON///////////////////////////////////////
        /**
         * Returns a list of every matching person if found in DDBB.
         * @return a list of entities.
         */
        public List<Person> searchPerson(HashMap<String,String>hash) {
            return personRepository.getData(hash);
        }
        //////////////////////////////////////////////////////////////////////////////////////////
    }















