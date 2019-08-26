package org.jhipster.criminalddbb.web.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.jhipster.criminalddbb.domain.CaseReport;
import org.jhipster.criminalddbb.domain.Person;
import org.jhipster.criminalddbb.domain.enumeration.Nationality;
import org.jhipster.criminalddbb.domain.enumeration.Sex;
import org.jhipster.criminalddbb.domain.enumeration.Status;
import org.jhipster.criminalddbb.service.PersonService;
import org.jhipster.criminalddbb.web.rest.errors.BadRequestAlertException;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.time.Instant;
import java.util.*;

/**
 * REST controller for managing {@link org.jhipster.criminalddbb.domain.Person}.
 */
@RestController
@RequestMapping("/api")
public class PersonResource {

    private final Logger log = LoggerFactory.getLogger(PersonResource.class);

    private static final String ENTITY_NAME = "person";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final PersonService personService;

    public PersonResource(PersonService personService) {
        this.personService = personService;
    }

    /**
     * {@code GET  /people} : get all the people.
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of people in body.
     */
    @GetMapping("/people")
    public ResponseEntity<List<Person>> getAllPeople(Pageable pageable) {
        log.debug("REST request to get a page of all people");
        Page<Person> page = personService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }


    /**
     * {@code GET  /people/:id} : get the "id" person.
     * @param id the id of the person to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the person, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/people/{id}")
    public ResponseEntity<Person> getPerson(@PathVariable Long id) {
        log.debug("REST request to get Person : {}", id);
        Optional<Person> person = personService.findOne(id);
        return ResponseUtil.wrapOrNotFound(person);
    }


    /**
     * {@code POST  /people} : Create a new person.
     * @param person the person to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new person, or with status {@code 400 (Bad Request)} if the person has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/people")
    public ResponseEntity<Person> createPerson(@RequestBody Person person) throws URISyntaxException {
        log.debug("REST request to save Person : {}", person);
        if (person.getId() != null) {
            throw new BadRequestAlertException("A new person cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Person result = personService.save(person);
        return ResponseEntity.created(new URI("/api/people/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }


    /**
     * {@code PUT  /people} : Updates an existing person.
     * @param person the person to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated person,
     * or with status {@code 400 (Bad Request)} if the person is not valid,
     * or with status {@code 500 (Internal Server Error)} if the person couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/people")
    public ResponseEntity<Person> updatePerson(@RequestBody Person person) throws URISyntaxException {
        log.debug("REST request to update Person : {}", person);
        if (person.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Person result = personService.save(person);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, person.getId().toString()))
            .body(result);
    }


    /**
     * {@code GET  /people/vips} : get every person considered VIP (X or more subordinates).
     * @return a list of Person entities with status {@code 200 (OK)}, or no list with status {@code 404 (Not Found)}.
     */
    @GetMapping("/people/vips")
    public List<Person> getPersonCaseReports() {
        log.debug("REST request to get all case reports of a person : {}");
        return personService.findVIPs();
    }


    /**
     * {@code GET  /people/case-reports/:id} : get all the case reports of the person with matching "id".
     * @param id the id of the person´s case reports list to retrieve.
     * @return a list of CaseReport entities with status {@code 200 (OK)}, or no list with status {@code 404 (Not Found)}.
     */
    @GetMapping("/people/{id}/case-reports")
    public List<CaseReport> getPersonCaseReports(@PathVariable Long id) {
        log.debug("REST request to get all case reports of a person : {}", id);
        return personService.findAllCaseReports(id);
    }


    //FALTAN:
    //
    //GET MATCHING PEOPLE
    //PATCH REL-IMP-UP-DOWN



    ////////// WIP MOD. RANK/STATUS ////////////////////////     Terminar especificaciones
    /** {@code GET /people/{id}/{modification}} : Receives a person and searches for it in DDBB.
     * @params person
     * @return the matching entity with status {@code 201 (Found)} and with body of the matching Person, or with status {@code 404 (Bad Request)} if the person doesn´t match with anyone.
     * @throws URISyntaxException if the Location URI syntax is incorrect.*/
    @PatchMapping("/people/{id}/{modification}")
    public Optional<Person> modStatusOrRank(@PathVariable Long id, String modification) throws URISyntaxException {
        log.debug("REST request to patch status or rank : {}", modification);
        return personService.modStatusOrRank(id,modification);
    }
    /////////////////////////////////////////////////////////






    ////////// WIP MATCHING PERSON ////////////////////////
     /** {@code GET /people/search} : Receives a person and searches for it in DDBB.
     * @params person
     * @return the matching entity with status {@code 201 (Found)} and with body of the matching Person, or with status {@code 404 (Bad Request)} if the person doesn´t match with anyone.
     * @throws URISyntaxException if the Location URI syntax is incorrect.*/
    @GetMapping("/people/search")
    public List<Person> searchPerson(@RequestParam Long id, String name, String surname, Integer age, Sex sex, String alias, Status status, String adress, Nationality nationality, Integer rank) throws URISyntaxException {
        log.debug("REST request searching Person : {}", id, name, surname, age, sex, alias, status, adress, nationality, rank);
        //System.out.println("\n\n\n----------------------------------------------------------\n\n");
        Person aux= new Person (id, name, surname, age, sex, alias, status, adress, nationality, rank);
        //System.out.println(aux.toHashMap());
        //System.out.println("\n\n----------------------------------------------------------\n\n\n");
        //return null
        return personService.searchPerson(aux.toHashMap()); /// Linea de retorno pendiente de cambio
    }
    /////////////////////////////////////////////////////////





















    ////////////////////////////////////////////////////////////////////////
    /////////////////////// DESCARTADO DELETE PERSON ///////////////////////
    ////////////////////////////////////////////////////////////////////////


    /*
     // {@code DELETE  /people/:id} : delete the "id" person.
     //
     // @param id the id of the person to delete.
     // @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.

    @DeleteMapping("/people/{id}")
    public ResponseEntity<Void> deletePerson(@PathVariable Long id) {
        log.debug("REST request to delete Person : {}", id);
        personService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
    */
}
