package com.criminalddbb.app.web.rest;

import com.criminalddbb.app.domain.CaseReport;
import com.criminalddbb.app.domain.Person;
import com.criminalddbb.app.domain.enumeration.Nationality;
import com.criminalddbb.app.domain.enumeration.Sex;
import com.criminalddbb.app.domain.enumeration.Status;
import com.criminalddbb.app.service.PersonService;
import com.criminalddbb.app.web.rest.errors.BadRequestAlertException;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.criminalddbb.app.domain.Person}. */
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
    @ApiOperation(value = "Get all people", notes = "Returns a list with every person in the repository")

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
    @ApiOperation(value = "Find person by ID", notes = "Returns a person if matches with specified ID.")
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
    @ApiOperation(value = "Create person", notes = "Creates a person and returns it")
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
    @ApiOperation(value = "Update person", notes = "Updates a person and returns it")
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
    @ApiOperation(value = "Find VIPs", notes = "Returns a list of every person with more than 50 'subordinates'")
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
    @ApiOperation(value = "Get CaseReports", notes = "Returns CaseReports of a specified person")
    public List<CaseReport> getPersonCaseReports(@PathVariable Long id) {
        log.debug("REST request to get all case reports of a person : {}", id);
        return personService.findAllCaseReports(id);
    }

    ////////// WIP MOD. RANK/STATUS ////////////////////////     Terminar especificaciones
    /** {@code GET /people/{id}/{modification}} : Receives a person and searches for it in DDBB.
     * @params person
     * @return the matching entity with status {@code 201 (Found)} and with body of the matching Person, or with status {@code 404 (Bad Request)} if the person doesn´t match with anyone.
     * @throws URISyntaxException if the Location URI syntax is incorrect.*/
    @PatchMapping("/people/{id}/{modification}")
    @ApiOperation(value = "Modify Status or Rank", notes = "Patches Status or Rank of a person and returns the person")
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
    @ApiOperation(value = "Search person", notes = "Returns a list of people matching the specified data ")
    public List<Person> searchPerson(
        @RequestParam (value = "id", required = false)Long id,
        @RequestParam (value = "name", required = false)String name,
        @RequestParam (value = "surname", required = false)String surname,
        @RequestParam (value = "age", required = false)Integer age,
        @RequestParam (value = "sex", required = false) Sex sex,
        @RequestParam (value = "alias", required = false)String alias,
        @RequestParam (value = "status", required = false) Status status,
        @RequestParam (value = "adress", required = false)String adress,
        @RequestParam (value = "nationality", required = false) Nationality nationality,
        @RequestParam (value = "rank", required = false)Integer rank) throws URISyntaxException {
        log.debug("REST request searching Person : {}", id, name, surname, age, sex, alias, status, adress, nationality, rank);
        Person aux= new Person (id, name, surname, age, sex, alias, status, adress, nationality, rank);
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
