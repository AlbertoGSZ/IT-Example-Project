package org.jhipster.criminalddbbpruebas.web.rest;

import org.jhipster.criminalddbbpruebas.domain.CaseReport;
import org.jhipster.criminalddbbpruebas.service.CaseReportService;
import org.jhipster.criminalddbbpruebas.web.rest.errors.BadRequestAlertException;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link org.jhipster.criminalddbbpruebas.domain.CaseReport}.
 */
@RestController
@RequestMapping("/api")
public class CaseReportResource {

    private final Logger log = LoggerFactory.getLogger(CaseReportResource.class);

    private static final String ENTITY_NAME = "caseReport";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CaseReportService caseReportService;

    public CaseReportResource(CaseReportService caseReportService) {
        this.caseReportService = caseReportService;
    }

    /**
     * {@code POST  /case-reports} : Create a new caseReport.
     *
     * @param caseReport the caseReport to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new caseReport, or with status {@code 400 (Bad Request)} if the caseReport has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/case-reports")
    public ResponseEntity<CaseReport> createCaseReport(@RequestBody CaseReport caseReport) throws URISyntaxException {
        log.debug("REST request to save CaseReport : {}", caseReport);
        if (caseReport.getId() != null) {
            throw new BadRequestAlertException("A new caseReport cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CaseReport result = caseReportService.save(caseReport);
        return ResponseEntity.created(new URI("/api/case-reports/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /case-reports} : Updates an existing caseReport.
     *
     * @param caseReport the caseReport to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated caseReport,
     * or with status {@code 400 (Bad Request)} if the caseReport is not valid,
     * or with status {@code 500 (Internal Server Error)} if the caseReport couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/case-reports")
    public ResponseEntity<CaseReport> updateCaseReport(@RequestBody CaseReport caseReport) throws URISyntaxException {
        log.debug("REST request to update CaseReport : {}", caseReport);
        if (caseReport.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        CaseReport result = caseReportService.save(caseReport);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, caseReport.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /case-reports} : get all the caseReports.
     *

     * @param pageable the pagination information.

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of caseReports in body.
     */
    @GetMapping("/case-reports")
    public ResponseEntity<List<CaseReport>> getAllCaseReports(Pageable pageable) {
        log.debug("REST request to get a page of CaseReports");
        Page<CaseReport> page = caseReportService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /case-reports/:id} : get the "id" caseReport.
     *
     * @param id the id of the caseReport to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the caseReport, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/case-reports/{id}")
    public ResponseEntity<CaseReport> getCaseReport(@PathVariable Long id) {
        log.debug("REST request to get CaseReport : {}", id);
        Optional<CaseReport> caseReport = caseReportService.findOne(id);
        return ResponseUtil.wrapOrNotFound(caseReport);
    }

    /**
     * {@code DELETE  /case-reports/:id} : delete the "id" caseReport.
     *
     * @param id the id of the caseReport to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/case-reports/{id}")
    public ResponseEntity<Void> deleteCaseReport(@PathVariable Long id) {
        log.debug("REST request to delete CaseReport : {}", id);
        caseReportService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
