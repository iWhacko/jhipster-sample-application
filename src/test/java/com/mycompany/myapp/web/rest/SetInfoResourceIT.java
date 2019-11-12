package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.JhipsterSampleApplicationApp;
import com.mycompany.myapp.domain.SetInfo;
import com.mycompany.myapp.repository.SetInfoRepository;
import com.mycompany.myapp.service.SetInfoService;
import com.mycompany.myapp.service.dto.SetInfoDTO;
import com.mycompany.myapp.service.mapper.SetInfoMapper;
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
 * Integration tests for the {@link SetInfoResource} REST controller.
 */
@SpringBootTest(classes = JhipsterSampleApplicationApp.class)
public class SetInfoResourceIT {

    private static final String DEFAULT_GESLACHT = "AAAAAAAAAA";
    private static final String UPDATED_GESLACHT = "BBBBBBBBBB";

    private static final String DEFAULT_NAAM = "AAAAAAAAAA";
    private static final String UPDATED_NAAM = "BBBBBBBBBB";

    private static final String DEFAULT_ACHTERNAAM = "AAAAAAAAAA";
    private static final String UPDATED_ACHTERNAAM = "BBBBBBBBBB";

    private static final String DEFAULT_POST_CODE = "AAAAAAAAAA";
    private static final String UPDATED_POST_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_HUIS_NUMMER = "AAAAAAAAAA";
    private static final String UPDATED_HUIS_NUMMER = "BBBBBBBBBB";

    private static final String DEFAULT_STRAAT = "AAAAAAAAAA";
    private static final String UPDATED_STRAAT = "BBBBBBBBBB";

    private static final String DEFAULT_PLAATS = "AAAAAAAAAA";
    private static final String UPDATED_PLAATS = "BBBBBBBBBB";

    private static final String DEFAULT_TELEFOON = "AAAAAAAAAA";
    private static final String UPDATED_TELEFOON = "BBBBBBBBBB";

    private static final String DEFAULT_EMAIL = "AAAAAAAAAA";
    private static final String UPDATED_EMAIL = "BBBBBBBBBB";

    private static final String DEFAULT_CONTRACT = "AAAAAAAAAA";
    private static final String UPDATED_CONTRACT = "BBBBBBBBBB";

    private static final Boolean DEFAULT_TELEFONISCH = false;
    private static final Boolean UPDATED_TELEFONISCH = true;

    private static final String DEFAULT_TAAL = "AAAAAAAAAA";
    private static final String UPDATED_TAAL = "BBBBBBBBBB";

    private static final String DEFAULT_VERZEND_METHODE = "AAAAAAAAAA";
    private static final String UPDATED_VERZEND_METHODE = "BBBBBBBBBB";

    @Autowired
    private SetInfoRepository setInfoRepository;

    @Autowired
    private SetInfoMapper setInfoMapper;

    @Autowired
    private SetInfoService setInfoService;

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

    private MockMvc restSetInfoMockMvc;

    private SetInfo setInfo;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final SetInfoResource setInfoResource = new SetInfoResource(setInfoService);
        this.restSetInfoMockMvc = MockMvcBuilders.standaloneSetup(setInfoResource)
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
    public static SetInfo createEntity(EntityManager em) {
        SetInfo setInfo = new SetInfo()
            .geslacht(DEFAULT_GESLACHT)
            .naam(DEFAULT_NAAM)
            .achternaam(DEFAULT_ACHTERNAAM)
            .postCode(DEFAULT_POST_CODE)
            .huisNummer(DEFAULT_HUIS_NUMMER)
            .straat(DEFAULT_STRAAT)
            .plaats(DEFAULT_PLAATS)
            .telefoon(DEFAULT_TELEFOON)
            .email(DEFAULT_EMAIL)
            .contract(DEFAULT_CONTRACT)
            .telefonisch(DEFAULT_TELEFONISCH)
            .taal(DEFAULT_TAAL)
            .verzendMethode(DEFAULT_VERZEND_METHODE);
        return setInfo;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static SetInfo createUpdatedEntity(EntityManager em) {
        SetInfo setInfo = new SetInfo()
            .geslacht(UPDATED_GESLACHT)
            .naam(UPDATED_NAAM)
            .achternaam(UPDATED_ACHTERNAAM)
            .postCode(UPDATED_POST_CODE)
            .huisNummer(UPDATED_HUIS_NUMMER)
            .straat(UPDATED_STRAAT)
            .plaats(UPDATED_PLAATS)
            .telefoon(UPDATED_TELEFOON)
            .email(UPDATED_EMAIL)
            .contract(UPDATED_CONTRACT)
            .telefonisch(UPDATED_TELEFONISCH)
            .taal(UPDATED_TAAL)
            .verzendMethode(UPDATED_VERZEND_METHODE);
        return setInfo;
    }

    @BeforeEach
    public void initTest() {
        setInfo = createEntity(em);
    }

    @Test
    @Transactional
    public void createSetInfo() throws Exception {
        int databaseSizeBeforeCreate = setInfoRepository.findAll().size();

        // Create the SetInfo
        SetInfoDTO setInfoDTO = setInfoMapper.toDto(setInfo);
        restSetInfoMockMvc.perform(post("/api/set-infos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(setInfoDTO)))
            .andExpect(status().isCreated());

        // Validate the SetInfo in the database
        List<SetInfo> setInfoList = setInfoRepository.findAll();
        assertThat(setInfoList).hasSize(databaseSizeBeforeCreate + 1);
        SetInfo testSetInfo = setInfoList.get(setInfoList.size() - 1);
        assertThat(testSetInfo.getGeslacht()).isEqualTo(DEFAULT_GESLACHT);
        assertThat(testSetInfo.getNaam()).isEqualTo(DEFAULT_NAAM);
        assertThat(testSetInfo.getAchternaam()).isEqualTo(DEFAULT_ACHTERNAAM);
        assertThat(testSetInfo.getPostCode()).isEqualTo(DEFAULT_POST_CODE);
        assertThat(testSetInfo.getHuisNummer()).isEqualTo(DEFAULT_HUIS_NUMMER);
        assertThat(testSetInfo.getStraat()).isEqualTo(DEFAULT_STRAAT);
        assertThat(testSetInfo.getPlaats()).isEqualTo(DEFAULT_PLAATS);
        assertThat(testSetInfo.getTelefoon()).isEqualTo(DEFAULT_TELEFOON);
        assertThat(testSetInfo.getEmail()).isEqualTo(DEFAULT_EMAIL);
        assertThat(testSetInfo.getContract()).isEqualTo(DEFAULT_CONTRACT);
        assertThat(testSetInfo.isTelefonisch()).isEqualTo(DEFAULT_TELEFONISCH);
        assertThat(testSetInfo.getTaal()).isEqualTo(DEFAULT_TAAL);
        assertThat(testSetInfo.getVerzendMethode()).isEqualTo(DEFAULT_VERZEND_METHODE);
    }

    @Test
    @Transactional
    public void createSetInfoWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = setInfoRepository.findAll().size();

        // Create the SetInfo with an existing ID
        setInfo.setId(1L);
        SetInfoDTO setInfoDTO = setInfoMapper.toDto(setInfo);

        // An entity with an existing ID cannot be created, so this API call must fail
        restSetInfoMockMvc.perform(post("/api/set-infos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(setInfoDTO)))
            .andExpect(status().isBadRequest());

        // Validate the SetInfo in the database
        List<SetInfo> setInfoList = setInfoRepository.findAll();
        assertThat(setInfoList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllSetInfos() throws Exception {
        // Initialize the database
        setInfoRepository.saveAndFlush(setInfo);

        // Get all the setInfoList
        restSetInfoMockMvc.perform(get("/api/set-infos?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(setInfo.getId().intValue())))
            .andExpect(jsonPath("$.[*].geslacht").value(hasItem(DEFAULT_GESLACHT)))
            .andExpect(jsonPath("$.[*].naam").value(hasItem(DEFAULT_NAAM)))
            .andExpect(jsonPath("$.[*].achternaam").value(hasItem(DEFAULT_ACHTERNAAM)))
            .andExpect(jsonPath("$.[*].postCode").value(hasItem(DEFAULT_POST_CODE)))
            .andExpect(jsonPath("$.[*].huisNummer").value(hasItem(DEFAULT_HUIS_NUMMER)))
            .andExpect(jsonPath("$.[*].straat").value(hasItem(DEFAULT_STRAAT)))
            .andExpect(jsonPath("$.[*].plaats").value(hasItem(DEFAULT_PLAATS)))
            .andExpect(jsonPath("$.[*].telefoon").value(hasItem(DEFAULT_TELEFOON)))
            .andExpect(jsonPath("$.[*].email").value(hasItem(DEFAULT_EMAIL)))
            .andExpect(jsonPath("$.[*].contract").value(hasItem(DEFAULT_CONTRACT)))
            .andExpect(jsonPath("$.[*].telefonisch").value(hasItem(DEFAULT_TELEFONISCH.booleanValue())))
            .andExpect(jsonPath("$.[*].taal").value(hasItem(DEFAULT_TAAL)))
            .andExpect(jsonPath("$.[*].verzendMethode").value(hasItem(DEFAULT_VERZEND_METHODE)));
    }
    
    @Test
    @Transactional
    public void getSetInfo() throws Exception {
        // Initialize the database
        setInfoRepository.saveAndFlush(setInfo);

        // Get the setInfo
        restSetInfoMockMvc.perform(get("/api/set-infos/{id}", setInfo.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(setInfo.getId().intValue()))
            .andExpect(jsonPath("$.geslacht").value(DEFAULT_GESLACHT))
            .andExpect(jsonPath("$.naam").value(DEFAULT_NAAM))
            .andExpect(jsonPath("$.achternaam").value(DEFAULT_ACHTERNAAM))
            .andExpect(jsonPath("$.postCode").value(DEFAULT_POST_CODE))
            .andExpect(jsonPath("$.huisNummer").value(DEFAULT_HUIS_NUMMER))
            .andExpect(jsonPath("$.straat").value(DEFAULT_STRAAT))
            .andExpect(jsonPath("$.plaats").value(DEFAULT_PLAATS))
            .andExpect(jsonPath("$.telefoon").value(DEFAULT_TELEFOON))
            .andExpect(jsonPath("$.email").value(DEFAULT_EMAIL))
            .andExpect(jsonPath("$.contract").value(DEFAULT_CONTRACT))
            .andExpect(jsonPath("$.telefonisch").value(DEFAULT_TELEFONISCH.booleanValue()))
            .andExpect(jsonPath("$.taal").value(DEFAULT_TAAL))
            .andExpect(jsonPath("$.verzendMethode").value(DEFAULT_VERZEND_METHODE));
    }

    @Test
    @Transactional
    public void getNonExistingSetInfo() throws Exception {
        // Get the setInfo
        restSetInfoMockMvc.perform(get("/api/set-infos/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateSetInfo() throws Exception {
        // Initialize the database
        setInfoRepository.saveAndFlush(setInfo);

        int databaseSizeBeforeUpdate = setInfoRepository.findAll().size();

        // Update the setInfo
        SetInfo updatedSetInfo = setInfoRepository.findById(setInfo.getId()).get();
        // Disconnect from session so that the updates on updatedSetInfo are not directly saved in db
        em.detach(updatedSetInfo);
        updatedSetInfo
            .geslacht(UPDATED_GESLACHT)
            .naam(UPDATED_NAAM)
            .achternaam(UPDATED_ACHTERNAAM)
            .postCode(UPDATED_POST_CODE)
            .huisNummer(UPDATED_HUIS_NUMMER)
            .straat(UPDATED_STRAAT)
            .plaats(UPDATED_PLAATS)
            .telefoon(UPDATED_TELEFOON)
            .email(UPDATED_EMAIL)
            .contract(UPDATED_CONTRACT)
            .telefonisch(UPDATED_TELEFONISCH)
            .taal(UPDATED_TAAL)
            .verzendMethode(UPDATED_VERZEND_METHODE);
        SetInfoDTO setInfoDTO = setInfoMapper.toDto(updatedSetInfo);

        restSetInfoMockMvc.perform(put("/api/set-infos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(setInfoDTO)))
            .andExpect(status().isOk());

        // Validate the SetInfo in the database
        List<SetInfo> setInfoList = setInfoRepository.findAll();
        assertThat(setInfoList).hasSize(databaseSizeBeforeUpdate);
        SetInfo testSetInfo = setInfoList.get(setInfoList.size() - 1);
        assertThat(testSetInfo.getGeslacht()).isEqualTo(UPDATED_GESLACHT);
        assertThat(testSetInfo.getNaam()).isEqualTo(UPDATED_NAAM);
        assertThat(testSetInfo.getAchternaam()).isEqualTo(UPDATED_ACHTERNAAM);
        assertThat(testSetInfo.getPostCode()).isEqualTo(UPDATED_POST_CODE);
        assertThat(testSetInfo.getHuisNummer()).isEqualTo(UPDATED_HUIS_NUMMER);
        assertThat(testSetInfo.getStraat()).isEqualTo(UPDATED_STRAAT);
        assertThat(testSetInfo.getPlaats()).isEqualTo(UPDATED_PLAATS);
        assertThat(testSetInfo.getTelefoon()).isEqualTo(UPDATED_TELEFOON);
        assertThat(testSetInfo.getEmail()).isEqualTo(UPDATED_EMAIL);
        assertThat(testSetInfo.getContract()).isEqualTo(UPDATED_CONTRACT);
        assertThat(testSetInfo.isTelefonisch()).isEqualTo(UPDATED_TELEFONISCH);
        assertThat(testSetInfo.getTaal()).isEqualTo(UPDATED_TAAL);
        assertThat(testSetInfo.getVerzendMethode()).isEqualTo(UPDATED_VERZEND_METHODE);
    }

    @Test
    @Transactional
    public void updateNonExistingSetInfo() throws Exception {
        int databaseSizeBeforeUpdate = setInfoRepository.findAll().size();

        // Create the SetInfo
        SetInfoDTO setInfoDTO = setInfoMapper.toDto(setInfo);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSetInfoMockMvc.perform(put("/api/set-infos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(setInfoDTO)))
            .andExpect(status().isBadRequest());

        // Validate the SetInfo in the database
        List<SetInfo> setInfoList = setInfoRepository.findAll();
        assertThat(setInfoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteSetInfo() throws Exception {
        // Initialize the database
        setInfoRepository.saveAndFlush(setInfo);

        int databaseSizeBeforeDelete = setInfoRepository.findAll().size();

        // Delete the setInfo
        restSetInfoMockMvc.perform(delete("/api/set-infos/{id}", setInfo.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<SetInfo> setInfoList = setInfoRepository.findAll();
        assertThat(setInfoList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
