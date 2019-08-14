package org.jhipster.criminalddbbpruebas.service.impl;

import org.jhipster.criminalddbbpruebas.service.ResourceURLService;
import org.jhipster.criminalddbbpruebas.domain.ResourceURL;
import org.jhipster.criminalddbbpruebas.repository.ResourceURLRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing {@link ResourceURL}.
 */
@Service
@Transactional
public class ResourceURLServiceImpl implements ResourceURLService {

    private final Logger log = LoggerFactory.getLogger(ResourceURLServiceImpl.class);

    private final ResourceURLRepository resourceURLRepository;

    public ResourceURLServiceImpl(ResourceURLRepository resourceURLRepository) {
        this.resourceURLRepository = resourceURLRepository;
    }

    /**
     * Save a resourceURL.
     *
     * @param resourceURL the entity to save.
     * @return the persisted entity.
     */
    @Override
    public ResourceURL save(ResourceURL resourceURL) {
        log.debug("Request to save ResourceURL : {}", resourceURL);
        return resourceURLRepository.save(resourceURL);
    }

    /**
     * Get all the resourceURLS.
     *
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<ResourceURL> findAll() {
        log.debug("Request to get all ResourceURLS");
        return resourceURLRepository.findAll();
    }


    /**
     * Get one resourceURL by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<ResourceURL> findOne(Long id) {
        log.debug("Request to get ResourceURL : {}", id);
        return resourceURLRepository.findById(id);
    }

    /**
     * Delete the resourceURL by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete ResourceURL : {}", id);
        resourceURLRepository.deleteById(id);
    }
}
