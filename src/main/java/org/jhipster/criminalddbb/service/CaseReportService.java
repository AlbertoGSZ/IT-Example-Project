package org.jhipster.criminalddbb.service;

import org.jhipster.criminalddbb.domain.CaseReport;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link CaseReport}.
 */
public interface CaseReportService {

    /**
     * Save a caseReport.
     *
     * @param caseReport the entity to save.
     * @return the persisted entity.
     */
    CaseReport save(CaseReport caseReport);

    /**
     * Get all the caseReports.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<CaseReport> findAll(Pageable pageable);


    /**
     * Get the "id" caseReport.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<CaseReport> findOne(Long id);

    /**
     * Delete the "id" caseReport.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
