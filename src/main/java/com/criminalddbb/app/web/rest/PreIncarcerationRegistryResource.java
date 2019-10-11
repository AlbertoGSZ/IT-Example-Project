package com.criminalddbb.app.web.rest;

import com.criminalddbb.app.domain.PreIncarcerationRegistry;
import com.criminalddbb.app.service.PreIncarcerationRegistryService;
import com.criminalddbb.app.web.rest.errors.BadRequestAlertException;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.criminalddbb.app.domain.PreIncarcerationRegistry}.
 */
@RestController
@RequestMapping("/api")
public class PreIncarcerationRegistryResource {

    private final Logger log = LoggerFactory.getLogger(PreIncarcerationRegistryResource.class);

    private static final String ENTITY_NAME = "preIncarcerationRegistry";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final PreIncarcerationRegistryService preIncarcerationRegistryService;

    public PreIncarcerationRegistryResource(PreIncarcerationRegistryService preIncarcerationRegistryService) {
        this.preIncarcerationRegistryService = preIncarcerationRegistryService;
    }


    /**
     * {@code POST  /pre-incarceration-registries} : Create a new preIncarcerationRegistry.
     *
     * @param preIncarcerationRegistry the preIncarcerationRegistry to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new preIncarcerationRegistry, or with status {@code 400 (Bad Request)} if the preIncarcerationRegistry has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/pre-incarceration-registries")
    @ApiOperation(value = "Create a PreIncarcerationRegistry", notes = "Creates and return a PreIncarcerationRegistry")
    public ResponseEntity<PreIncarcerationRegistry> createPreIncarcerationRegistry(@RequestBody PreIncarcerationRegistry preIncarcerationRegistry) throws URISyntaxException {
        log.debug("REST request to save PreIncarcerationRegistry : {}", preIncarcerationRegistry);
        if (preIncarcerationRegistry.getId() != null) {
            throw new BadRequestAlertException("A new preIncarcerationRegistry cannot already have an ID", ENTITY_NAME, "idexists");
        }
        PreIncarcerationRegistry result = preIncarcerationRegistryService.save(preIncarcerationRegistry);
        return ResponseEntity.created(new URI("/api/pre-incarceration-registries/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }


    /**
     * {@code PUT  /pre-incarceration-registries} : Updates an existing preIncarcerationRegistry.
     *
     * @param preIncarcerationRegistry the preIncarcerationRegistry to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated preIncarcerationRegistry,
     * or with status {@code 400 (Bad Request)} if the preIncarcerationRegistry is not valid,
     * or with status {@code 500 (Internal Server Error)} if the preIncarcerationRegistry couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/pre-incarceration-registries")
    @ApiOperation(value = "Update PreIncarcerationRegistry", notes = "Updates and returns a PreIncarcerationRegistry")
    public ResponseEntity<PreIncarcerationRegistry> updatePreIncarcerationRegistry(@RequestBody PreIncarcerationRegistry preIncarcerationRegistry) throws URISyntaxException {
        log.debug("REST request to update PreIncarcerationRegistry : {}", preIncarcerationRegistry);
        if (preIncarcerationRegistry.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        PreIncarcerationRegistry result = preIncarcerationRegistryService.save(preIncarcerationRegistry);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, preIncarcerationRegistry.getId().toString()))
            .body(result);
    }


    /**
     * {@code GET  /pre-incarceration-registries} : get all the preIncarcerationRegistries.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of preIncarcerationRegistries in body.
     */
    @GetMapping("/pre-incarceration-registries")
    @ApiOperation(value = "Get all PreIncarcerationRegistry", notes = "Returns a list of al PreIncarcerationRegistries")
    public List<PreIncarcerationRegistry> getAllPreIncarcerationRegistries() {
        log.debug("REST request to get all PreIncarcerationRegistries");
        return preIncarcerationRegistryService.findAll();
    }


    /**
     * {@code GET  /pre-incarceration-registries/:id} : get the "id" preIncarcerationRegistry.
     *
     * @param id the id of the preIncarcerationRegistry to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the preIncarcerationRegistry, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/pre-incarceration-registries/{id}")
    @ApiOperation(value = "Get one PreIncarcerationRegistry", notes = "Returns a PreIncarcerationRegistry if matches with specified ID")
    public ResponseEntity<PreIncarcerationRegistry> getPreIncarcerationRegistry(@PathVariable Long id) {
        log.debug("REST request to get PreIncarcerationRegistry : {}", id);
        Optional<PreIncarcerationRegistry> preIncarcerationRegistry = preIncarcerationRegistryService.findOne(id);
        return ResponseUtil.wrapOrNotFound(preIncarcerationRegistry);
    }

    /*
    /**
     * {@code DELETE  /pre-incarceration-registries/:id} : delete the "id" preIncarcerationRegistry.
     *
     * @param id the id of the preIncarcerationRegistry to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    /*
    @DeleteMapping("/pre-incarceration-registries/{id}")
    public ResponseEntity<Void> deletePreIncarcerationRegistry(@PathVariable Long id) {
        log.debug("REST request to delete PreIncarcerationRegistry : {}", id);
        preIncarcerationRegistryService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
    */
}
