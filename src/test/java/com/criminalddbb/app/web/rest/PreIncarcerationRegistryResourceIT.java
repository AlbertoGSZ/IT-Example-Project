package com.criminalddbb.app.web.rest;

import com.criminalddbb.app.CriminalDdbbApp;
import com.criminalddbb.app.domain.PreIncarcerationRegistry;
import com.criminalddbb.app.repository.PreIncarcerationRegistryRepository;
import com.criminalddbb.app.service.PreIncarcerationRegistryService;
import com.criminalddbb.app.web.rest.errors.ExceptionTranslator;

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
import java.util.List;

import static com.criminalddbb.app.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link PreIncarcerationRegistryResource} REST controller.
 */
@SpringBootTest(classes = CriminalDdbbApp.class)
public class PreIncarcerationRegistryResourceIT {

    private static final Integer DEFAULT_OLD_RANK = 1;
    private static final Integer UPDATED_OLD_RANK = 2;
    private static final Integer SMALLER_OLD_RANK = 1 - 1;

    private static final Long DEFAULT_OLD_CHIEF = 1L;
    private static final Long UPDATED_OLD_CHIEF = 2L;
    private static final Long SMALLER_OLD_CHIEF = 1L - 1L;

    private static final String DEFAULT_OLD_SUBORDINATES = "AAAAAAAAAA";
    private static final String UPDATED_OLD_SUBORDINATES = "BBBBBBBBBB";

    @Autowired
    private PreIncarcerationRegistryRepository preIncarcerationRegistryRepository;

    @Autowired
    private PreIncarcerationRegistryService preIncarcerationRegistryService;

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

    private MockMvc restPreIncarcerationRegistryMockMvc;

    private PreIncarcerationRegistry preIncarcerationRegistry;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final PreIncarcerationRegistryResource preIncarcerationRegistryResource = new PreIncarcerationRegistryResource(preIncarcerationRegistryService);
        this.restPreIncarcerationRegistryMockMvc = MockMvcBuilders.standaloneSetup(preIncarcerationRegistryResource)
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
    public static PreIncarcerationRegistry createEntity(EntityManager em) {
        PreIncarcerationRegistry preIncarcerationRegistry = new PreIncarcerationRegistry()
            .oldRank(DEFAULT_OLD_RANK)
            .oldChief(DEFAULT_OLD_CHIEF)
            .oldSubordinates(DEFAULT_OLD_SUBORDINATES);
        return preIncarcerationRegistry;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static PreIncarcerationRegistry createUpdatedEntity(EntityManager em) {
        PreIncarcerationRegistry preIncarcerationRegistry = new PreIncarcerationRegistry()
            .oldRank(UPDATED_OLD_RANK)
            .oldChief(UPDATED_OLD_CHIEF)
            .oldSubordinates(UPDATED_OLD_SUBORDINATES);
        return preIncarcerationRegistry;
    }

    @BeforeEach
    public void initTest() {
        preIncarcerationRegistry = createEntity(em);
    }

    @Test
    @Transactional
    public void createPreIncarcerationRegistry() throws Exception {
        int databaseSizeBeforeCreate = preIncarcerationRegistryRepository.findAll().size();

        // Create the PreIncarcerationRegistry
        restPreIncarcerationRegistryMockMvc.perform(post("/api/pre-incarceration-registries")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(preIncarcerationRegistry)))
            .andExpect(status().isCreated());

        // Validate the PreIncarcerationRegistry in the database
        List<PreIncarcerationRegistry> preIncarcerationRegistryList = preIncarcerationRegistryRepository.findAll();
        assertThat(preIncarcerationRegistryList).hasSize(databaseSizeBeforeCreate + 1);
        PreIncarcerationRegistry testPreIncarcerationRegistry = preIncarcerationRegistryList.get(preIncarcerationRegistryList.size() - 1);
        assertThat(testPreIncarcerationRegistry.getOldRank()).isEqualTo(DEFAULT_OLD_RANK);
        assertThat(testPreIncarcerationRegistry.getOldChief()).isEqualTo(DEFAULT_OLD_CHIEF);
        assertThat(testPreIncarcerationRegistry.getOldSubordinates()).isEqualTo(DEFAULT_OLD_SUBORDINATES);
    }

    @Test
    @Transactional
    public void createPreIncarcerationRegistryWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = preIncarcerationRegistryRepository.findAll().size();

        // Create the PreIncarcerationRegistry with an existing ID
        preIncarcerationRegistry.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restPreIncarcerationRegistryMockMvc.perform(post("/api/pre-incarceration-registries")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(preIncarcerationRegistry)))
            .andExpect(status().isBadRequest());

        // Validate the PreIncarcerationRegistry in the database
        List<PreIncarcerationRegistry> preIncarcerationRegistryList = preIncarcerationRegistryRepository.findAll();
        assertThat(preIncarcerationRegistryList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllPreIncarcerationRegistries() throws Exception {
        // Initialize the database
        preIncarcerationRegistryRepository.saveAndFlush(preIncarcerationRegistry);

        // Get all the preIncarcerationRegistryList
        restPreIncarcerationRegistryMockMvc.perform(get("/api/pre-incarceration-registries?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(preIncarcerationRegistry.getId().intValue())))
            .andExpect(jsonPath("$.[*].oldRank").value(hasItem(DEFAULT_OLD_RANK)))
            .andExpect(jsonPath("$.[*].oldChief").value(hasItem(DEFAULT_OLD_CHIEF.intValue())))
            .andExpect(jsonPath("$.[*].oldSubordinates").value(hasItem(DEFAULT_OLD_SUBORDINATES.toString())));
    }
    
    @Test
    @Transactional
    public void getPreIncarcerationRegistry() throws Exception {
        // Initialize the database
        preIncarcerationRegistryRepository.saveAndFlush(preIncarcerationRegistry);

        // Get the preIncarcerationRegistry
        restPreIncarcerationRegistryMockMvc.perform(get("/api/pre-incarceration-registries/{id}", preIncarcerationRegistry.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(preIncarcerationRegistry.getId().intValue()))
            .andExpect(jsonPath("$.oldRank").value(DEFAULT_OLD_RANK))
            .andExpect(jsonPath("$.oldChief").value(DEFAULT_OLD_CHIEF.intValue()))
            .andExpect(jsonPath("$.oldSubordinates").value(DEFAULT_OLD_SUBORDINATES.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingPreIncarcerationRegistry() throws Exception {
        // Get the preIncarcerationRegistry
        restPreIncarcerationRegistryMockMvc.perform(get("/api/pre-incarceration-registries/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updatePreIncarcerationRegistry() throws Exception {
        // Initialize the database
        preIncarcerationRegistryService.save(preIncarcerationRegistry);

        int databaseSizeBeforeUpdate = preIncarcerationRegistryRepository.findAll().size();

        // Update the preIncarcerationRegistry
        PreIncarcerationRegistry updatedPreIncarcerationRegistry = preIncarcerationRegistryRepository.findById(preIncarcerationRegistry.getId()).get();
        // Disconnect from session so that the updates on updatedPreIncarcerationRegistry are not directly saved in db
        em.detach(updatedPreIncarcerationRegistry);
        updatedPreIncarcerationRegistry
            .oldRank(UPDATED_OLD_RANK)
            .oldChief(UPDATED_OLD_CHIEF)
            .oldSubordinates(UPDATED_OLD_SUBORDINATES);

        restPreIncarcerationRegistryMockMvc.perform(put("/api/pre-incarceration-registries")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedPreIncarcerationRegistry)))
            .andExpect(status().isOk());

        // Validate the PreIncarcerationRegistry in the database
        List<PreIncarcerationRegistry> preIncarcerationRegistryList = preIncarcerationRegistryRepository.findAll();
        assertThat(preIncarcerationRegistryList).hasSize(databaseSizeBeforeUpdate);
        PreIncarcerationRegistry testPreIncarcerationRegistry = preIncarcerationRegistryList.get(preIncarcerationRegistryList.size() - 1);
        assertThat(testPreIncarcerationRegistry.getOldRank()).isEqualTo(UPDATED_OLD_RANK);
        assertThat(testPreIncarcerationRegistry.getOldChief()).isEqualTo(UPDATED_OLD_CHIEF);
        assertThat(testPreIncarcerationRegistry.getOldSubordinates()).isEqualTo(UPDATED_OLD_SUBORDINATES);
    }

    @Test
    @Transactional
    public void updateNonExistingPreIncarcerationRegistry() throws Exception {
        int databaseSizeBeforeUpdate = preIncarcerationRegistryRepository.findAll().size();

        // Create the PreIncarcerationRegistry

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPreIncarcerationRegistryMockMvc.perform(put("/api/pre-incarceration-registries")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(preIncarcerationRegistry)))
            .andExpect(status().isBadRequest());

        // Validate the PreIncarcerationRegistry in the database
        List<PreIncarcerationRegistry> preIncarcerationRegistryList = preIncarcerationRegistryRepository.findAll();
        assertThat(preIncarcerationRegistryList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deletePreIncarcerationRegistry() throws Exception {
        // Initialize the database
        preIncarcerationRegistryService.save(preIncarcerationRegistry);

        int databaseSizeBeforeDelete = preIncarcerationRegistryRepository.findAll().size();

        // Delete the preIncarcerationRegistry
        restPreIncarcerationRegistryMockMvc.perform(delete("/api/pre-incarceration-registries/{id}", preIncarcerationRegistry.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<PreIncarcerationRegistry> preIncarcerationRegistryList = preIncarcerationRegistryRepository.findAll();
        assertThat(preIncarcerationRegistryList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(PreIncarcerationRegistry.class);
        PreIncarcerationRegistry preIncarcerationRegistry1 = new PreIncarcerationRegistry();
        preIncarcerationRegistry1.setId(1L);
        PreIncarcerationRegistry preIncarcerationRegistry2 = new PreIncarcerationRegistry();
        preIncarcerationRegistry2.setId(preIncarcerationRegistry1.getId());
        assertThat(preIncarcerationRegistry1).isEqualTo(preIncarcerationRegistry2);
        preIncarcerationRegistry2.setId(2L);
        assertThat(preIncarcerationRegistry1).isNotEqualTo(preIncarcerationRegistry2);
        preIncarcerationRegistry1.setId(null);
        assertThat(preIncarcerationRegistry1).isNotEqualTo(preIncarcerationRegistry2);
    }
}
