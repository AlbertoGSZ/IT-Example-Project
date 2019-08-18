package org.jhipster.criminalddbb.web.rest;

import org.jhipster.criminalddbb.CriminalddbbApp;
import org.jhipster.criminalddbb.domain.ResourceURL;
import org.jhipster.criminalddbb.repository.ResourceURLRepository;
import org.jhipster.criminalddbb.service.ResourceURLService;
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
import java.util.List;

import static org.jhipster.criminalddbb.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link ResourceURLResource} REST controller.
 */
@SpringBootTest(classes = CriminalddbbApp.class)
public class ResourceURLResourceIT {

    private static final String DEFAULT_URL = "AAAAAAAAAA";
    private static final String UPDATED_URL = "BBBBBBBBBB";

    @Autowired
    private ResourceURLRepository resourceURLRepository;

    @Autowired
    private ResourceURLService resourceURLService;

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

    private MockMvc restResourceURLMockMvc;

    private ResourceURL resourceURL;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ResourceURLResource resourceURLResource = new ResourceURLResource(resourceURLService);
        this.restResourceURLMockMvc = MockMvcBuilders.standaloneSetup(resourceURLResource)
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
    public static ResourceURL createEntity(EntityManager em) {
        ResourceURL resourceURL = new ResourceURL()
            .url(DEFAULT_URL);
        return resourceURL;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ResourceURL createUpdatedEntity(EntityManager em) {
        ResourceURL resourceURL = new ResourceURL()
            .url(UPDATED_URL);
        return resourceURL;
    }

    @BeforeEach
    public void initTest() {
        resourceURL = createEntity(em);
    }

    @Test
    @Transactional
    public void createResourceURL() throws Exception {
        int databaseSizeBeforeCreate = resourceURLRepository.findAll().size();

        // Create the ResourceURL
        restResourceURLMockMvc.perform(post("/api/resource-urls")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(resourceURL)))
            .andExpect(status().isCreated());

        // Validate the ResourceURL in the database
        List<ResourceURL> resourceURLList = resourceURLRepository.findAll();
        assertThat(resourceURLList).hasSize(databaseSizeBeforeCreate + 1);
        ResourceURL testResourceURL = resourceURLList.get(resourceURLList.size() - 1);
        assertThat(testResourceURL.getUrl()).isEqualTo(DEFAULT_URL);
    }

    @Test
    @Transactional
    public void createResourceURLWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = resourceURLRepository.findAll().size();

        // Create the ResourceURL with an existing ID
        resourceURL.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restResourceURLMockMvc.perform(post("/api/resource-urls")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(resourceURL)))
            .andExpect(status().isBadRequest());

        // Validate the ResourceURL in the database
        List<ResourceURL> resourceURLList = resourceURLRepository.findAll();
        assertThat(resourceURLList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllResourceURLS() throws Exception {
        // Initialize the database
        resourceURLRepository.saveAndFlush(resourceURL);

        // Get all the resourceURLList
        restResourceURLMockMvc.perform(get("/api/resource-urls?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(resourceURL.getId().intValue())))
            .andExpect(jsonPath("$.[*].url").value(hasItem(DEFAULT_URL.toString())));
    }
    
    @Test
    @Transactional
    public void getResourceURL() throws Exception {
        // Initialize the database
        resourceURLRepository.saveAndFlush(resourceURL);

        // Get the resourceURL
        restResourceURLMockMvc.perform(get("/api/resource-urls/{id}", resourceURL.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(resourceURL.getId().intValue()))
            .andExpect(jsonPath("$.url").value(DEFAULT_URL.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingResourceURL() throws Exception {
        // Get the resourceURL
        restResourceURLMockMvc.perform(get("/api/resource-urls/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateResourceURL() throws Exception {
        // Initialize the database
        resourceURLService.save(resourceURL);

        int databaseSizeBeforeUpdate = resourceURLRepository.findAll().size();

        // Update the resourceURL
        ResourceURL updatedResourceURL = resourceURLRepository.findById(resourceURL.getId()).get();
        // Disconnect from session so that the updates on updatedResourceURL are not directly saved in db
        em.detach(updatedResourceURL);
        updatedResourceURL
            .url(UPDATED_URL);

        restResourceURLMockMvc.perform(put("/api/resource-urls")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedResourceURL)))
            .andExpect(status().isOk());

        // Validate the ResourceURL in the database
        List<ResourceURL> resourceURLList = resourceURLRepository.findAll();
        assertThat(resourceURLList).hasSize(databaseSizeBeforeUpdate);
        ResourceURL testResourceURL = resourceURLList.get(resourceURLList.size() - 1);
        assertThat(testResourceURL.getUrl()).isEqualTo(UPDATED_URL);
    }

    @Test
    @Transactional
    public void updateNonExistingResourceURL() throws Exception {
        int databaseSizeBeforeUpdate = resourceURLRepository.findAll().size();

        // Create the ResourceURL

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restResourceURLMockMvc.perform(put("/api/resource-urls")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(resourceURL)))
            .andExpect(status().isBadRequest());

        // Validate the ResourceURL in the database
        List<ResourceURL> resourceURLList = resourceURLRepository.findAll();
        assertThat(resourceURLList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteResourceURL() throws Exception {
        // Initialize the database
        resourceURLService.save(resourceURL);

        int databaseSizeBeforeDelete = resourceURLRepository.findAll().size();

        // Delete the resourceURL
        restResourceURLMockMvc.perform(delete("/api/resource-urls/{id}", resourceURL.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ResourceURL> resourceURLList = resourceURLRepository.findAll();
        assertThat(resourceURLList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ResourceURL.class);
        ResourceURL resourceURL1 = new ResourceURL();
        resourceURL1.setId(1L);
        ResourceURL resourceURL2 = new ResourceURL();
        resourceURL2.setId(resourceURL1.getId());
        assertThat(resourceURL1).isEqualTo(resourceURL2);
        resourceURL2.setId(2L);
        assertThat(resourceURL1).isNotEqualTo(resourceURL2);
        resourceURL1.setId(null);
        assertThat(resourceURL1).isNotEqualTo(resourceURL2);
    }
}
