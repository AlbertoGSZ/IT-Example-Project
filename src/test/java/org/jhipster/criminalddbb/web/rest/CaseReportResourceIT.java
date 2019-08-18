package org.jhipster.criminalddbb.web.rest;

import org.jhipster.criminalddbb.CriminalddbbApp;
import org.jhipster.criminalddbb.domain.CaseReport;
import org.jhipster.criminalddbb.repository.CaseReportRepository;
import org.jhipster.criminalddbb.service.CaseReportService;
import org.jhipster.criminalddbb.web.rest.errors.ExceptionTranslator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Validator;

import javax.persistence.EntityManager;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static org.jhipster.criminalddbb.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link CaseReportResource} REST controller.
 */
@SpringBootTest(classes = CriminalddbbApp.class)
public class CaseReportResourceIT {

    private static final Instant DEFAULT_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);
    private static final Instant SMALLER_DATE = Instant.ofEpochMilli(-1L);

    private static final String DEFAULT_PERSON_DETAILS = "AAAAAAAAAA";
    private static final String UPDATED_PERSON_DETAILS = "BBBBBBBBBB";

    private static final String DEFAULT_EVENT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_EVENT_DESCRIPTION = "BBBBBBBBBB";

    @Autowired
    private CaseReportRepository caseReportRepository;

    @Autowired
    private CaseReportService caseReportService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    @Autowired
    private Validator validator;

    private MockMvc restCaseReportMockMvc;

    private CaseReport caseReport;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final CaseReportResource caseReportResource = new CaseReportResource(caseReportService);
        this.restCaseReportMockMvc = MockMvcBuilders.standaloneSetup(caseReportResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter)
            .setValidator(validator).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CaseReport createEntity(EntityManager em) {
        CaseReport caseReport = new CaseReport()
            .date(DEFAULT_DATE)
            .personDetails(DEFAULT_PERSON_DETAILS)
            .eventDescription(DEFAULT_EVENT_DESCRIPTION);
        return caseReport;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CaseReport createUpdatedEntity(EntityManager em) {
        CaseReport caseReport = new CaseReport()
            .date(UPDATED_DATE)
            .personDetails(UPDATED_PERSON_DETAILS)
            .eventDescription(UPDATED_EVENT_DESCRIPTION);
        return caseReport;
    }

    @BeforeEach
    public void initTest() {
        caseReport = createEntity(em);
    }

    @Test
    @Transactional
    public void createCaseReport() throws Exception {
        int databaseSizeBeforeCreate = caseReportRepository.findAll().size();

        // Create the CaseReport
        restCaseReportMockMvc.perform(post("/api/case-reports")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(caseReport)))
            .andExpect(status().isCreated());

        // Validate the CaseReport in the database
        List<CaseReport> caseReportList = caseReportRepository.findAll();
        assertThat(caseReportList).hasSize(databaseSizeBeforeCreate + 1);
        CaseReport testCaseReport = caseReportList.get(caseReportList.size() - 1);
        assertThat(testCaseReport.getDate()).isEqualTo(DEFAULT_DATE);
        assertThat(testCaseReport.getPersonDetails()).isEqualTo(DEFAULT_PERSON_DETAILS);
        assertThat(testCaseReport.getEventDescription()).isEqualTo(DEFAULT_EVENT_DESCRIPTION);
    }

    @Test
    @Transactional
    public void createCaseReportWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = caseReportRepository.findAll().size();

        // Create the CaseReport with an existing ID
        caseReport.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restCaseReportMockMvc.perform(post("/api/case-reports")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(caseReport)))
            .andExpect(status().isBadRequest());

        // Validate the CaseReport in the database
        List<CaseReport> caseReportList = caseReportRepository.findAll();
        assertThat(caseReportList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllCaseReports() throws Exception {
        // Initialize the database
        caseReportRepository.saveAndFlush(caseReport);

        // Get all the caseReportList
        restCaseReportMockMvc.perform(get("/api/case-reports?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(caseReport.getId().intValue())))
            .andExpect(jsonPath("$.[*].date").value(hasItem(DEFAULT_DATE.toString())))
            .andExpect(jsonPath("$.[*].personDetails").value(hasItem(DEFAULT_PERSON_DETAILS.toString())))
            .andExpect(jsonPath("$.[*].eventDescription").value(hasItem(DEFAULT_EVENT_DESCRIPTION.toString())));
    }
    
    @Test
    @Transactional
    public void getCaseReport() throws Exception {
        // Initialize the database
        caseReportRepository.saveAndFlush(caseReport);

        // Get the caseReport
        restCaseReportMockMvc.perform(get("/api/case-reports/{id}", caseReport.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(caseReport.getId().intValue()))
            .andExpect(jsonPath("$.date").value(DEFAULT_DATE.toString()))
            .andExpect(jsonPath("$.personDetails").value(DEFAULT_PERSON_DETAILS.toString()))
            .andExpect(jsonPath("$.eventDescription").value(DEFAULT_EVENT_DESCRIPTION.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingCaseReport() throws Exception {
        // Get the caseReport
        restCaseReportMockMvc.perform(get("/api/case-reports/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCaseReport() throws Exception {
        // Initialize the database
        caseReportService.save(caseReport);

        int databaseSizeBeforeUpdate = caseReportRepository.findAll().size();

        // Update the caseReport
        CaseReport updatedCaseReport = caseReportRepository.findById(caseReport.getId()).get();
        // Disconnect from session so that the updates on updatedCaseReport are not directly saved in db
        em.detach(updatedCaseReport);
        updatedCaseReport
            .date(UPDATED_DATE)
            .personDetails(UPDATED_PERSON_DETAILS)
            .eventDescription(UPDATED_EVENT_DESCRIPTION);

        restCaseReportMockMvc.perform(put("/api/case-reports")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedCaseReport)))
            .andExpect(status().isOk());

        // Validate the CaseReport in the database
        List<CaseReport> caseReportList = caseReportRepository.findAll();
        assertThat(caseReportList).hasSize(databaseSizeBeforeUpdate);
        CaseReport testCaseReport = caseReportList.get(caseReportList.size() - 1);
        assertThat(testCaseReport.getDate()).isEqualTo(UPDATED_DATE);
        assertThat(testCaseReport.getPersonDetails()).isEqualTo(UPDATED_PERSON_DETAILS);
        assertThat(testCaseReport.getEventDescription()).isEqualTo(UPDATED_EVENT_DESCRIPTION);
    }

    @Test
    @Transactional
    public void updateNonExistingCaseReport() throws Exception {
        int databaseSizeBeforeUpdate = caseReportRepository.findAll().size();

        // Create the CaseReport

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCaseReportMockMvc.perform(put("/api/case-reports")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(caseReport)))
            .andExpect(status().isBadRequest());

        // Validate the CaseReport in the database
        List<CaseReport> caseReportList = caseReportRepository.findAll();
        assertThat(caseReportList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteCaseReport() throws Exception {
        // Initialize the database
        caseReportService.save(caseReport);

        int databaseSizeBeforeDelete = caseReportRepository.findAll().size();

        // Delete the caseReport
        restCaseReportMockMvc.perform(delete("/api/case-reports/{id}", caseReport.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<CaseReport> caseReportList = caseReportRepository.findAll();
        assertThat(caseReportList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(CaseReport.class);
        CaseReport caseReport1 = new CaseReport();
        caseReport1.setId(1L);
        CaseReport caseReport2 = new CaseReport();
        caseReport2.setId(caseReport1.getId());
        assertThat(caseReport1).isEqualTo(caseReport2);
        caseReport2.setId(2L);
        assertThat(caseReport1).isNotEqualTo(caseReport2);
        caseReport1.setId(null);
        assertThat(caseReport1).isNotEqualTo(caseReport2);
    }
}
