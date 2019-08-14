package org.jhipster.criminalddbbpruebas.service;

import org.jhipster.criminalddbbpruebas.domain.ResourceURL;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link ResourceURL}.
 */
public interface ResourceURLService {

    /**
     * Save a resourceURL.
     *
     * @param resourceURL the entity to save.
     * @return the persisted entity.
     */
    ResourceURL save(ResourceURL resourceURL);

    /**
     * Get all the resourceURLS.
     *
     * @return the list of entities.
     */
    List<ResourceURL> findAll();


    /**
     * Get the "id" resourceURL.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ResourceURL> findOne(Long id);

    /**
     * Delete the "id" resourceURL.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
