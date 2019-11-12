package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.service.SetInfoService;
import com.mycompany.myapp.web.rest.errors.BadRequestAlertException;
import com.mycompany.myapp.service.dto.SetInfoDTO;

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
 * REST controller for managing {@link com.mycompany.myapp.domain.SetInfo}.
 */
@RestController
@RequestMapping("/api")
public class SetInfoResource {

    private final Logger log = LoggerFactory.getLogger(SetInfoResource.class);

    private static final String ENTITY_NAME = "setInfo";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final SetInfoService setInfoService;

    public SetInfoResource(SetInfoService setInfoService) {
        this.setInfoService = setInfoService;
    }

    /**
     * {@code POST  /set-infos} : Create a new setInfo.
     *
     * @param setInfoDTO the setInfoDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new setInfoDTO, or with status {@code 400 (Bad Request)} if the setInfo has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/set-infos")
    public ResponseEntity<SetInfoDTO> createSetInfo(@RequestBody SetInfoDTO setInfoDTO) throws URISyntaxException {
        log.debug("REST request to save SetInfo : {}", setInfoDTO);
        if (setInfoDTO.getId() != null) {
            throw new BadRequestAlertException("A new setInfo cannot already have an ID", ENTITY_NAME, "idexists");
        }
        SetInfoDTO result = setInfoService.save(setInfoDTO);
        return ResponseEntity.created(new URI("/api/set-infos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /set-infos} : Updates an existing setInfo.
     *
     * @param setInfoDTO the setInfoDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated setInfoDTO,
     * or with status {@code 400 (Bad Request)} if the setInfoDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the setInfoDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/set-infos")
    public ResponseEntity<SetInfoDTO> updateSetInfo(@RequestBody SetInfoDTO setInfoDTO) throws URISyntaxException {
        log.debug("REST request to update SetInfo : {}", setInfoDTO);
        if (setInfoDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        SetInfoDTO result = setInfoService.save(setInfoDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, setInfoDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /set-infos} : get all the setInfos.
     *

     * @param pageable the pagination information.

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of setInfos in body.
     */
    @GetMapping("/set-infos")
    public ResponseEntity<List<SetInfoDTO>> getAllSetInfos(Pageable pageable) {
        log.debug("REST request to get a page of SetInfos");
        Page<SetInfoDTO> page = setInfoService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /set-infos/:id} : get the "id" setInfo.
     *
     * @param id the id of the setInfoDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the setInfoDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/set-infos/{id}")
    public ResponseEntity<SetInfoDTO> getSetInfo(@PathVariable Long id) {
        log.debug("REST request to get SetInfo : {}", id);
        Optional<SetInfoDTO> setInfoDTO = setInfoService.findOne(id);
        return ResponseUtil.wrapOrNotFound(setInfoDTO);
    }

    /**
     * {@code DELETE  /set-infos/:id} : delete the "id" setInfo.
     *
     * @param id the id of the setInfoDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/set-infos/{id}")
    public ResponseEntity<Void> deleteSetInfo(@PathVariable Long id) {
        log.debug("REST request to delete SetInfo : {}", id);
        setInfoService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
