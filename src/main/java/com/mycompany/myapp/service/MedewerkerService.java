package com.mycompany.myapp.service;

import com.mycompany.myapp.domain.Medewerker;
import com.mycompany.myapp.repository.MedewerkerRepository;
import com.mycompany.myapp.service.dto.MedewerkerDTO;
import com.mycompany.myapp.service.mapper.MedewerkerMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Medewerker}.
 */
@Service
@Transactional
public class MedewerkerService {

    private final Logger log = LoggerFactory.getLogger(MedewerkerService.class);

    private final MedewerkerRepository medewerkerRepository;

    private final MedewerkerMapper medewerkerMapper;

    public MedewerkerService(MedewerkerRepository medewerkerRepository, MedewerkerMapper medewerkerMapper) {
        this.medewerkerRepository = medewerkerRepository;
        this.medewerkerMapper = medewerkerMapper;
    }

    /**
     * Save a medewerker.
     *
     * @param medewerkerDTO the entity to save.
     * @return the persisted entity.
     */
    public MedewerkerDTO save(MedewerkerDTO medewerkerDTO) {
        log.debug("Request to save Medewerker : {}", medewerkerDTO);
        Medewerker medewerker = medewerkerMapper.toEntity(medewerkerDTO);
        medewerker = medewerkerRepository.save(medewerker);
        return medewerkerMapper.toDto(medewerker);
    }

    /**
     * Get all the medewerkers.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<MedewerkerDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Medewerkers");
        return medewerkerRepository.findAll(pageable)
            .map(medewerkerMapper::toDto);
    }


    /**
     * Get one medewerker by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<MedewerkerDTO> findOne(Long id) {
        log.debug("Request to get Medewerker : {}", id);
        return medewerkerRepository.findById(id)
            .map(medewerkerMapper::toDto);
    }

    /**
     * Delete the medewerker by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Medewerker : {}", id);
        medewerkerRepository.deleteById(id);
    }
}
