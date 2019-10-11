package com.criminalddbb.app.service;

import com.criminalddbb.app.domain.ResourceUrl;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link ResourceUrl}.
 */
public interface ResourceUrlService {

    /**
     * Save a resourceUrl.
     *
     * @param resourceUrl the entity to save.
     * @return the persisted entity.
     */
    ResourceUrl save(ResourceUrl resourceUrl);

    /**
     * Get all the resourceUrls.
     *
     * @return the list of entities.
     */
    List<ResourceUrl> findAll();


    /**
     * Get the "id" resourceUrl.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ResourceUrl> findOne(Long id);

    /**
     * Delete the "id" resourceUrl.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
