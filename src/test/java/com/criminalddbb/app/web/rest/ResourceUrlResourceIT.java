package com.criminalddbb.app.web.rest;

import com.criminalddbb.app.CriminalDdbbApp;
import com.criminalddbb.app.domain.ResourceUrl;
import com.criminalddbb.app.repository.ResourceUrlRepository;
import com.criminalddbb.app.service.ResourceUrlService;
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
 * Integration tests for the {@link ResourceUrlResource} REST controller.
 */
@SpringBootTest(classes = CriminalDdbbApp.class)
public class ResourceUrlResourceIT {

    private static final String DEFAULT_URL_LINK = "AAAAAAAAAA";
    private static final String UPDATED_URL_LINK = "BBBBBBBBBB";

    @Autowired
    private ResourceUrlRepository resourceUrlRepository;

    @Autowired
    private ResourceUrlService resourceUrlService;

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

    private MockMvc restResourceUrlMockMvc;

    private ResourceUrl resourceUrl;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ResourceUrlResource resourceUrlResource = new ResourceUrlResource(resourceUrlService);
        this.restResourceUrlMockMvc = MockMvcBuilders.standaloneSetup(resourceUrlResource)
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
    public static ResourceUrl createEntity(EntityManager em) {
        ResourceUrl resourceUrl = new ResourceUrl()
            .urlLink(DEFAULT_URL_LINK);
        return resourceUrl;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ResourceUrl createUpdatedEntity(EntityManager em) {
        ResourceUrl resourceUrl = new ResourceUrl()
            .urlLink(UPDATED_URL_LINK);
        return resourceUrl;
    }

    @BeforeEach
    public void initTest() {
        resourceUrl = createEntity(em);
    }

    @Test
    @Transactional
    public void createResourceUrl() throws Exception {
        int databaseSizeBeforeCreate = resourceUrlRepository.findAll().size();

        // Create the ResourceUrl
        restResourceUrlMockMvc.perform(post("/api/resource-urls")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(resourceUrl)))
            .andExpect(status().isCreated());

        // Validate the ResourceUrl in the database
        List<ResourceUrl> resourceUrlList = resourceUrlRepository.findAll();
        assertThat(resourceUrlList).hasSize(databaseSizeBeforeCreate + 1);
        ResourceUrl testResourceUrl = resourceUrlList.get(resourceUrlList.size() - 1);
        assertThat(testResourceUrl.getUrlLink()).isEqualTo(DEFAULT_URL_LINK);
    }

    @Test
    @Transactional
    public void createResourceUrlWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = resourceUrlRepository.findAll().size();

        // Create the ResourceUrl with an existing ID
        resourceUrl.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restResourceUrlMockMvc.perform(post("/api/resource-urls")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(resourceUrl)))
            .andExpect(status().isBadRequest());

        // Validate the ResourceUrl in the database
        List<ResourceUrl> resourceUrlList = resourceUrlRepository.findAll();
        assertThat(resourceUrlList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllResourceUrls() throws Exception {
        // Initialize the database
        resourceUrlRepository.saveAndFlush(resourceUrl);

        // Get all the resourceUrlList
        restResourceUrlMockMvc.perform(get("/api/resource-urls?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(resourceUrl.getId().intValue())))
            .andExpect(jsonPath("$.[*].urlLink").value(hasItem(DEFAULT_URL_LINK.toString())));
    }
    
    @Test
    @Transactional
    public void getResourceUrl() throws Exception {
        // Initialize the database
        resourceUrlRepository.saveAndFlush(resourceUrl);

        // Get the resourceUrl
        restResourceUrlMockMvc.perform(get("/api/resource-urls/{id}", resourceUrl.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(resourceUrl.getId().intValue()))
            .andExpect(jsonPath("$.urlLink").value(DEFAULT_URL_LINK.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingResourceUrl() throws Exception {
        // Get the resourceUrl
        restResourceUrlMockMvc.perform(get("/api/resource-urls/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateResourceUrl() throws Exception {
        // Initialize the database
        resourceUrlService.save(resourceUrl);

        int databaseSizeBeforeUpdate = resourceUrlRepository.findAll().size();

        // Update the resourceUrl
        ResourceUrl updatedResourceUrl = resourceUrlRepository.findById(resourceUrl.getId()).get();
        // Disconnect from session so that the updates on updatedResourceUrl are not directly saved in db
        em.detach(updatedResourceUrl);
        updatedResourceUrl
            .urlLink(UPDATED_URL_LINK);

        restResourceUrlMockMvc.perform(put("/api/resource-urls")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedResourceUrl)))
            .andExpect(status().isOk());

        // Validate the ResourceUrl in the database
        List<ResourceUrl> resourceUrlList = resourceUrlRepository.findAll();
        assertThat(resourceUrlList).hasSize(databaseSizeBeforeUpdate);
        ResourceUrl testResourceUrl = resourceUrlList.get(resourceUrlList.size() - 1);
        assertThat(testResourceUrl.getUrlLink()).isEqualTo(UPDATED_URL_LINK);
    }

    @Test
    @Transactional
    public void updateNonExistingResourceUrl() throws Exception {
        int databaseSizeBeforeUpdate = resourceUrlRepository.findAll().size();

        // Create the ResourceUrl

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restResourceUrlMockMvc.perform(put("/api/resource-urls")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(resourceUrl)))
            .andExpect(status().isBadRequest());

        // Validate the ResourceUrl in the database
        List<ResourceUrl> resourceUrlList = resourceUrlRepository.findAll();
        assertThat(resourceUrlList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteResourceUrl() throws Exception {
        // Initialize the database
        resourceUrlService.save(resourceUrl);

        int databaseSizeBeforeDelete = resourceUrlRepository.findAll().size();

        // Delete the resourceUrl
        restResourceUrlMockMvc.perform(delete("/api/resource-urls/{id}", resourceUrl.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ResourceUrl> resourceUrlList = resourceUrlRepository.findAll();
        assertThat(resourceUrlList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ResourceUrl.class);
        ResourceUrl resourceUrl1 = new ResourceUrl();
        resourceUrl1.setId(1L);
        ResourceUrl resourceUrl2 = new ResourceUrl();
        resourceUrl2.setId(resourceUrl1.getId());
        assertThat(resourceUrl1).isEqualTo(resourceUrl2);
        resourceUrl2.setId(2L);
        assertThat(resourceUrl1).isNotEqualTo(resourceUrl2);
        resourceUrl1.setId(null);
        assertThat(resourceUrl1).isNotEqualTo(resourceUrl2);
    }
}
