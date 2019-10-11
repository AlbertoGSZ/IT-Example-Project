package com.criminalddbb.app.web.rest;

import com.criminalddbb.app.domain.ResourceUrl;
import com.criminalddbb.app.service.ResourceUrlService;
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
 * REST controller for managing {@link com.criminalddbb.app.domain.ResourceUrl}.
 */
@RestController
@RequestMapping("/api")
public class ResourceUrlResource {

    private final Logger log = LoggerFactory.getLogger(ResourceUrlResource.class);

    private static final String ENTITY_NAME = "resourceUrl";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ResourceUrlService resourceUrlService;

    public ResourceUrlResource(ResourceUrlService resourceUrlService) {
        this.resourceUrlService = resourceUrlService;
    }


    /**
     * {@code POST  /resource-urls} : Create a new resourceUrl.
     *
     * @param resourceUrl the resourceUrl to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new resourceUrl, or with status {@code 400 (Bad Request)} if the resourceUrl has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/resource-urls")
    @ApiOperation(value = "Create ResourceUrl", notes = "Creates and return a ResourceUrl")
    public ResponseEntity<ResourceUrl> createResourceUrl(@RequestBody ResourceUrl resourceUrl) throws URISyntaxException {
        log.debug("REST request to save ResourceUrl : {}", resourceUrl);
        if (resourceUrl.getId() != null) {
            throw new BadRequestAlertException("A new resourceUrl cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ResourceUrl result = resourceUrlService.save(resourceUrl);
        return ResponseEntity.created(new URI("/api/resource-urls/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }


    /**
     * {@code PUT  /resource-urls} : Updates an existing resourceUrl.
     *
     * @param resourceUrl the resourceUrl to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated resourceUrl,
     * or with status {@code 400 (Bad Request)} if the resourceUrl is not valid,
     * or with status {@code 500 (Internal Server Error)} if the resourceUrl couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/resource-urls")
    @ApiOperation(value = "Update ResourceUrl", notes = "Updates and return a ResourceUrl")
    public ResponseEntity<ResourceUrl> updateResourceUrl(@RequestBody ResourceUrl resourceUrl) throws URISyntaxException {
        log.debug("REST request to update ResourceUrl : {}", resourceUrl);
        if (resourceUrl.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ResourceUrl result = resourceUrlService.save(resourceUrl);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, resourceUrl.getId().toString()))
            .body(result);
    }


    /**
     * {@code GET  /resource-urls} : get all the resourceUrls.
     *

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of resourceUrls in body.
     */
    @GetMapping("/resource-urls")
    @ApiOperation(value = "Get all ResourceUrls", notes = "Returns a list of all ResourceUrls")
    public List<ResourceUrl> getAllResourceUrls() {
        log.debug("REST request to get all ResourceUrls");
        return resourceUrlService.findAll();
    }


    /**
     * {@code GET  /resource-urls/:id} : get the "id" resourceUrl.
     *
     * @param id the id of the resourceUrl to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the resourceUrl, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/resource-urls/{id}")
    @ApiOperation(value = "Get one ResourceUrl", notes = "Returns a ResourceUrl if matches the specified ID")
    public ResponseEntity<ResourceUrl> getResourceUrl(@PathVariable Long id) {
        log.debug("REST request to get ResourceUrl : {}", id);
        Optional<ResourceUrl> resourceUrl = resourceUrlService.findOne(id);
        return ResponseUtil.wrapOrNotFound(resourceUrl);
    }


    /*
    /**
     * {@code DELETE  /resource-urls/:id} : delete the "id" resourceUrl.
     *
     * @param id the id of the resourceUrl to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    /*
    @DeleteMapping("/resource-urls/{id}")
    public ResponseEntity<Void> deleteResourceUrl(@PathVariable Long id) {
        log.debug("REST request to delete ResourceUrl : {}", id);
        resourceUrlService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
    */


}
