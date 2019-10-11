package com.criminalddbb.app.service.impl;

import com.criminalddbb.app.service.ResourceUrlService;
import com.criminalddbb.app.domain.ResourceUrl;
import com.criminalddbb.app.repository.ResourceUrlRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing {@link ResourceUrl}.
 */
@Service
@Transactional
public class ResourceUrlServiceImpl implements ResourceUrlService {

    private final Logger log = LoggerFactory.getLogger(ResourceUrlServiceImpl.class);

    private final ResourceUrlRepository resourceUrlRepository;

    public ResourceUrlServiceImpl(ResourceUrlRepository resourceUrlRepository) {
        this.resourceUrlRepository = resourceUrlRepository;
    }

    /**
     * Save a resourceUrl.
     *
     * @param resourceUrl the entity to save.
     * @return the persisted entity.
     */
    @Override
    public ResourceUrl save(ResourceUrl resourceUrl) {
        log.debug("Request to save ResourceUrl : {}", resourceUrl);
        return resourceUrlRepository.save(resourceUrl);
    }

    /**
     * Get all the resourceUrls.
     *
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<ResourceUrl> findAll() {
        log.debug("Request to get all ResourceUrls");
        return resourceUrlRepository.findAll();
    }


    /**
     * Get one resourceUrl by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<ResourceUrl> findOne(Long id) {
        log.debug("Request to get ResourceUrl : {}", id);
        return resourceUrlRepository.findById(id);
    }

    /**
     * Delete the resourceUrl by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete ResourceUrl : {}", id);
        resourceUrlRepository.deleteById(id);
    }
}
