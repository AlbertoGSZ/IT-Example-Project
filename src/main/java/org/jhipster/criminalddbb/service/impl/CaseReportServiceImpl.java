package org.jhipster.criminalddbb.service.impl;

import org.jhipster.criminalddbb.service.CaseReportService;
import org.jhipster.criminalddbb.domain.CaseReport;
import org.jhipster.criminalddbb.repository.CaseReportRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link CaseReport}.
 */
@Service
@Transactional
public class CaseReportServiceImpl implements CaseReportService {

    private final Logger log = LoggerFactory.getLogger(CaseReportServiceImpl.class);

    private final CaseReportRepository caseReportRepository;

    public CaseReportServiceImpl(CaseReportRepository caseReportRepository) {
        this.caseReportRepository = caseReportRepository;
    }

    /**
     * Save a caseReport.
     *
     * @param caseReport the entity to save.
     * @return the persisted entity.
     */
    @Override
    public CaseReport save(CaseReport caseReport) {
        log.debug("Request to save CaseReport : {}", caseReport);
        return caseReportRepository.save(caseReport);
    }

    /**
     * Get all the caseReports.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<CaseReport> findAll(Pageable pageable) {
        log.debug("Request to get all CaseReports");
        return caseReportRepository.findAll(pageable);
    }


    /**
     * Get one caseReport by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<CaseReport> findOne(Long id) {
        log.debug("Request to get CaseReport : {}", id);
        return caseReportRepository.findById(id);
    }

    /**
     * Delete the caseReport by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete CaseReport : {}", id);
        caseReportRepository.deleteById(id);
    }
}
