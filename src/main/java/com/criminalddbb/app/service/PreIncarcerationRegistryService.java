package com.criminalddbb.app.service;

import com.criminalddbb.app.domain.PreIncarcerationRegistry;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link PreIncarcerationRegistry}.
 */
public interface PreIncarcerationRegistryService {

    /**
     * Save a preIncarcerationRegistry.
     *
     * @param preIncarcerationRegistry the entity to save.
     * @return the persisted entity.
     */
    PreIncarcerationRegistry save(PreIncarcerationRegistry preIncarcerationRegistry);

    /**
     * Get all the preIncarcerationRegistries.
     *
     * @return the list of entities.
     */
    List<PreIncarcerationRegistry> findAll();


    /**
     * Get the "id" preIncarcerationRegistry.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<PreIncarcerationRegistry> findOne(Long id);

    /**
     * Delete the "id" preIncarcerationRegistry.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
