package com.mycompany.myapp.service;

import com.mycompany.myapp.domain.SetInfo;
import com.mycompany.myapp.repository.SetInfoRepository;
import com.mycompany.myapp.service.dto.SetInfoDTO;
import com.mycompany.myapp.service.mapper.SetInfoMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link SetInfo}.
 */
@Service
@Transactional
public class SetInfoService {

    private final Logger log = LoggerFactory.getLogger(SetInfoService.class);

    private final SetInfoRepository setInfoRepository;

    private final SetInfoMapper setInfoMapper;

    public SetInfoService(SetInfoRepository setInfoRepository, SetInfoMapper setInfoMapper) {
        this.setInfoRepository = setInfoRepository;
        this.setInfoMapper = setInfoMapper;
    }

    /**
     * Save a setInfo.
     *
     * @param setInfoDTO the entity to save.
     * @return the persisted entity.
     */
    public SetInfoDTO save(SetInfoDTO setInfoDTO) {
        log.debug("Request to save SetInfo : {}", setInfoDTO);
        SetInfo setInfo = setInfoMapper.toEntity(setInfoDTO);
        setInfo = setInfoRepository.save(setInfo);
        return setInfoMapper.toDto(setInfo);
    }

    /**
     * Get all the setInfos.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<SetInfoDTO> findAll(Pageable pageable) {
        log.debug("Request to get all SetInfos");
        return setInfoRepository.findAll(pageable)
            .map(setInfoMapper::toDto);
    }


    /**
     * Get one setInfo by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<SetInfoDTO> findOne(Long id) {
        log.debug("Request to get SetInfo : {}", id);
        return setInfoRepository.findById(id)
            .map(setInfoMapper::toDto);
    }

    /**
     * Delete the setInfo by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete SetInfo : {}", id);
        setInfoRepository.deleteById(id);
    }
}
