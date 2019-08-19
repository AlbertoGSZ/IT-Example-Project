package org.jhipster.criminalddbb.web.rest;

import org.jhipster.criminalddbb.domain.ResourceURL;
import org.jhipster.criminalddbb.service.ResourceURLService;
import org.jhipster.criminalddbb.web.rest.errors.BadRequestAlertException;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
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
 * REST controller for managing {@link org.jhipster.criminalddbb.domain.ResourceURL}.
 */
@RestController
@RequestMapping("/api")
public class ResourceURLResource {

    private final Logger log = LoggerFactory.getLogger(ResourceURLResource.class);

    private static final String ENTITY_NAME = "resourceURL";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ResourceURLService resourceURLService;

    public ResourceURLResource(ResourceURLService resourceURLService) {
        this.resourceURLService = resourceURLService;
    }

    /**
     * {@code POST  /resource-urls} : Create a new resourceURL.
     * @param resourceURL the resourceURL to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new resourceURL, or with status {@code 400 (Bad Request)} if the resourceURL has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/resource-urls")
    public ResponseEntity<ResourceURL> createResourceURL(@RequestBody ResourceURL resourceURL) throws URISyntaxException {
        log.debug("REST request to save ResourceURL : {}", resourceURL);
        if (resourceURL.getId() != null) {
            throw new BadRequestAlertException("A new resourceURL cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ResourceURL result = resourceURLService.save(resourceURL);
        return ResponseEntity.created(new URI("/api/resource-urls/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /resource-urls} : Updates an existing resourceURL.
     *
     * @param resourceURL the resourceURL to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated resourceURL,
     * or with status {@code 400 (Bad Request)} if the resourceURL is not valid,
     * or with status {@code 500 (Internal Server Error)} if the resourceURL couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/resource-urls")
    public ResponseEntity<ResourceURL> updateResourceURL(@RequestBody ResourceURL resourceURL) throws URISyntaxException {
        log.debug("REST request to update ResourceURL : {}", resourceURL);
        if (resourceURL.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ResourceURL result = resourceURLService.save(resourceURL);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, resourceURL.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /resource-urls/:id} : get the "id" resourceURL.
     *
     * @param id the id of the resourceURL to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the resourceURL, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/resource-urls/{id}")
    public ResponseEntity<ResourceURL> getResourceURL(@PathVariable Long id) {
        log.debug("REST request to get ResourceURL : {}", id);
        Optional<ResourceURL> resourceURL = resourceURLService.findOne(id);
        return ResponseUtil.wrapOrNotFound(resourceURL);
    }

    /**
     * {@code DELETE  /resource-urls/:id} : delete the "id" resourceURL.
     *
     * @param id the id of the resourceURL to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/resource-urls/{id}")
    public ResponseEntity<Void> deleteResourceURL(@PathVariable Long id) {
        log.debug("REST request to delete ResourceURL : {}", id);
        resourceURLService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }









    ////////////////////////////////////////////////////////////////////////
    ////////////////// DESCARTADO GET ALL RESOURCE URLS ////////////////////
    ////////////////////////////////////////////////////////////////////////

    /*
     * {@code GET  /resource-urls} : get all the resourceURLS.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of resourceURLS in body.
    @GetMapping("/resource-urls")
    public List<ResourceURL> getAllResourceURLS() {
        log.debug("REST request to get all ResourceURLS");
        return resourceURLService.findAll();
    }
    */
}
