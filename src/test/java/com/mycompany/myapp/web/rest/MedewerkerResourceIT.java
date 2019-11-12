package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.JhipsterSampleApplicationApp;
import com.mycompany.myapp.domain.Medewerker;
import com.mycompany.myapp.repository.MedewerkerRepository;
import com.mycompany.myapp.service.MedewerkerService;
import com.mycompany.myapp.service.dto.MedewerkerDTO;
import com.mycompany.myapp.service.mapper.MedewerkerMapper;
import com.mycompany.myapp.web.rest.errors.ExceptionTranslator;

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

import static com.mycompany.myapp.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link MedewerkerResource} REST controller.
 */
@SpringBootTest(classes = JhipsterSampleApplicationApp.class)
public class MedewerkerResourceIT {

    private static final String DEFAULT_NAAM = "AAAAAAAAAA";
    private static final String UPDATED_NAAM = "BBBBBBBBBB";

    private static final String DEFAULT_ACHTER_NAAM = "AAAAAAAAAA";
    private static final String UPDATED_ACHTER_NAAM = "BBBBBBBBBB";

    @Autowired
    private MedewerkerRepository medewerkerRepository;

    @Autowired
    private MedewerkerMapper medewerkerMapper;

    @Autowired
    private MedewerkerService medewerkerService;

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

    private MockMvc restMedewerkerMockMvc;

    private Medewerker medewerker;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final MedewerkerResource medewerkerResource = new MedewerkerResource(medewerkerService);
        this.restMedewerkerMockMvc = MockMvcBuilders.standaloneSetup(medewerkerResource)
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
    public static Medewerker createEntity(EntityManager em) {
        Medewerker medewerker = new Medewerker()
            .naam(DEFAULT_NAAM)
            .achterNaam(DEFAULT_ACHTER_NAAM);
        return medewerker;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Medewerker createUpdatedEntity(EntityManager em) {
        Medewerker medewerker = new Medewerker()
            .naam(UPDATED_NAAM)
            .achterNaam(UPDATED_ACHTER_NAAM);
        return medewerker;
    }

    @BeforeEach
    public void initTest() {
        medewerker = createEntity(em);
    }

    @Test
    @Transactional
    public void createMedewerker() throws Exception {
        int databaseSizeBeforeCreate = medewerkerRepository.findAll().size();

        // Create the Medewerker
        MedewerkerDTO medewerkerDTO = medewerkerMapper.toDto(medewerker);
        restMedewerkerMockMvc.perform(post("/api/medewerkers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(medewerkerDTO)))
            .andExpect(status().isCreated());

        // Validate the Medewerker in the database
        List<Medewerker> medewerkerList = medewerkerRepository.findAll();
        assertThat(medewerkerList).hasSize(databaseSizeBeforeCreate + 1);
        Medewerker testMedewerker = medewerkerList.get(medewerkerList.size() - 1);
        assertThat(testMedewerker.getNaam()).isEqualTo(DEFAULT_NAAM);
        assertThat(testMedewerker.getAchterNaam()).isEqualTo(DEFAULT_ACHTER_NAAM);
    }

    @Test
    @Transactional
    public void createMedewerkerWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = medewerkerRepository.findAll().size();

        // Create the Medewerker with an existing ID
        medewerker.setId(1L);
        MedewerkerDTO medewerkerDTO = medewerkerMapper.toDto(medewerker);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMedewerkerMockMvc.perform(post("/api/medewerkers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(medewerkerDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Medewerker in the database
        List<Medewerker> medewerkerList = medewerkerRepository.findAll();
        assertThat(medewerkerList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllMedewerkers() throws Exception {
        // Initialize the database
        medewerkerRepository.saveAndFlush(medewerker);

        // Get all the medewerkerList
        restMedewerkerMockMvc.perform(get("/api/medewerkers?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(medewerker.getId().intValue())))
            .andExpect(jsonPath("$.[*].naam").value(hasItem(DEFAULT_NAAM)))
            .andExpect(jsonPath("$.[*].achterNaam").value(hasItem(DEFAULT_ACHTER_NAAM)));
    }
    
    @Test
    @Transactional
    public void getMedewerker() throws Exception {
        // Initialize the database
        medewerkerRepository.saveAndFlush(medewerker);

        // Get the medewerker
        restMedewerkerMockMvc.perform(get("/api/medewerkers/{id}", medewerker.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(medewerker.getId().intValue()))
            .andExpect(jsonPath("$.naam").value(DEFAULT_NAAM))
            .andExpect(jsonPath("$.achterNaam").value(DEFAULT_ACHTER_NAAM));
    }

    @Test
    @Transactional
    public void getNonExistingMedewerker() throws Exception {
        // Get the medewerker
        restMedewerkerMockMvc.perform(get("/api/medewerkers/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMedewerker() throws Exception {
        // Initialize the database
        medewerkerRepository.saveAndFlush(medewerker);

        int databaseSizeBeforeUpdate = medewerkerRepository.findAll().size();

        // Update the medewerker
        Medewerker updatedMedewerker = medewerkerRepository.findById(medewerker.getId()).get();
        // Disconnect from session so that the updates on updatedMedewerker are not directly saved in db
        em.detach(updatedMedewerker);
        updatedMedewerker
            .naam(UPDATED_NAAM)
            .achterNaam(UPDATED_ACHTER_NAAM);
        MedewerkerDTO medewerkerDTO = medewerkerMapper.toDto(updatedMedewerker);

        restMedewerkerMockMvc.perform(put("/api/medewerkers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(medewerkerDTO)))
            .andExpect(status().isOk());

        // Validate the Medewerker in the database
        List<Medewerker> medewerkerList = medewerkerRepository.findAll();
        assertThat(medewerkerList).hasSize(databaseSizeBeforeUpdate);
        Medewerker testMedewerker = medewerkerList.get(medewerkerList.size() - 1);
        assertThat(testMedewerker.getNaam()).isEqualTo(UPDATED_NAAM);
        assertThat(testMedewerker.getAchterNaam()).isEqualTo(UPDATED_ACHTER_NAAM);
    }

    @Test
    @Transactional
    public void updateNonExistingMedewerker() throws Exception {
        int databaseSizeBeforeUpdate = medewerkerRepository.findAll().size();

        // Create the Medewerker
        MedewerkerDTO medewerkerDTO = medewerkerMapper.toDto(medewerker);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMedewerkerMockMvc.perform(put("/api/medewerkers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(medewerkerDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Medewerker in the database
        List<Medewerker> medewerkerList = medewerkerRepository.findAll();
        assertThat(medewerkerList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteMedewerker() throws Exception {
        // Initialize the database
        medewerkerRepository.saveAndFlush(medewerker);

        int databaseSizeBeforeDelete = medewerkerRepository.findAll().size();

        // Delete the medewerker
        restMedewerkerMockMvc.perform(delete("/api/medewerkers/{id}", medewerker.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Medewerker> medewerkerList = medewerkerRepository.findAll();
        assertThat(medewerkerList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
