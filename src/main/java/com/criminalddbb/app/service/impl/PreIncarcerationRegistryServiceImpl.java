package com.criminalddbb.app.service.impl;

import com.criminalddbb.app.service.PreIncarcerationRegistryService;
import com.criminalddbb.app.domain.PreIncarcerationRegistry;
import com.criminalddbb.app.repository.PreIncarcerationRegistryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing {@link PreIncarcerationRegistry}.
 */
@Service
@Transactional
public class PreIncarcerationRegistryServiceImpl implements PreIncarcerationRegistryService {

    private final Logger log = LoggerFactory.getLogger(PreIncarcerationRegistryServiceImpl.class);

    private final PreIncarcerationRegistryRepository preIncarcerationRegistryRepository;

    public PreIncarcerationRegistryServiceImpl(PreIncarcerationRegistryRepository preIncarcerationRegistryRepository) {
        this.preIncarcerationRegistryRepository = preIncarcerationRegistryRepository;
    }

    /**
     * Save a preIncarcerationRegistry.
     *
     * @param preIncarcerationRegistry the entity to save.
     * @return the persisted entity.
     */
    @Override
    public PreIncarcerationRegistry save(PreIncarcerationRegistry preIncarcerationRegistry) {
        log.debug("Request to save PreIncarcerationRegistry : {}", preIncarcerationRegistry);
        return preIncarcerationRegistryRepository.save(preIncarcerationRegistry);
    }

    /**
     * Get all the preIncarcerationRegistries.
     *
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<PreIncarcerationRegistry> findAll() {
        log.debug("Request to get all PreIncarcerationRegistries");
        return preIncarcerationRegistryRepository.findAll();
    }


    /**
     * Get one preIncarcerationRegistry by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<PreIncarcerationRegistry> findOne(Long id) {
        log.debug("Request to get PreIncarcerationRegistry : {}", id);
        return preIncarcerationRegistryRepository.findById(id);
    }

    /**
     * Delete the preIncarcerationRegistry by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete PreIncarcerationRegistry : {}", id);
        preIncarcerationRegistryRepository.deleteById(id);
    }
}
