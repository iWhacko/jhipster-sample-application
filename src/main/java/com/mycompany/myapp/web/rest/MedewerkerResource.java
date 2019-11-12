package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.service.MedewerkerService;
import com.mycompany.myapp.web.rest.errors.BadRequestAlertException;
import com.mycompany.myapp.service.dto.MedewerkerDTO;

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
 * REST controller for managing {@link com.mycompany.myapp.domain.Medewerker}.
 */
@RestController
@RequestMapping("/api")
public class MedewerkerResource {

    private final Logger log = LoggerFactory.getLogger(MedewerkerResource.class);

    private static final String ENTITY_NAME = "medewerker";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MedewerkerService medewerkerService;

    public MedewerkerResource(MedewerkerService medewerkerService) {
        this.medewerkerService = medewerkerService;
    }

    /**
     * {@code POST  /medewerkers} : Create a new medewerker.
     *
     * @param medewerkerDTO the medewerkerDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new medewerkerDTO, or with status {@code 400 (Bad Request)} if the medewerker has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/medewerkers")
    public ResponseEntity<MedewerkerDTO> createMedewerker(@RequestBody MedewerkerDTO medewerkerDTO) throws URISyntaxException {
        log.debug("REST request to save Medewerker : {}", medewerkerDTO);
        if (medewerkerDTO.getId() != null) {
            throw new BadRequestAlertException("A new medewerker cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MedewerkerDTO result = medewerkerService.save(medewerkerDTO);
        return ResponseEntity.created(new URI("/api/medewerkers/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /medewerkers} : Updates an existing medewerker.
     *
     * @param medewerkerDTO the medewerkerDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated medewerkerDTO,
     * or with status {@code 400 (Bad Request)} if the medewerkerDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the medewerkerDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/medewerkers")
    public ResponseEntity<MedewerkerDTO> updateMedewerker(@RequestBody MedewerkerDTO medewerkerDTO) throws URISyntaxException {
        log.debug("REST request to update Medewerker : {}", medewerkerDTO);
        if (medewerkerDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MedewerkerDTO result = medewerkerService.save(medewerkerDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, medewerkerDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /medewerkers} : get all the medewerkers.
     *

     * @param pageable the pagination information.

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of medewerkers in body.
     */
    @GetMapping("/medewerkers")
    public ResponseEntity<List<MedewerkerDTO>> getAllMedewerkers(Pageable pageable) {
        log.debug("REST request to get a page of Medewerkers");
        Page<MedewerkerDTO> page = medewerkerService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /medewerkers/:id} : get the "id" medewerker.
     *
     * @param id the id of the medewerkerDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the medewerkerDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/medewerkers/{id}")
    public ResponseEntity<MedewerkerDTO> getMedewerker(@PathVariable Long id) {
        log.debug("REST request to get Medewerker : {}", id);
        Optional<MedewerkerDTO> medewerkerDTO = medewerkerService.findOne(id);
        return ResponseUtil.wrapOrNotFound(medewerkerDTO);
    }

    /**
     * {@code DELETE  /medewerkers/:id} : delete the "id" medewerker.
     *
     * @param id the id of the medewerkerDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/medewerkers/{id}")
    public ResponseEntity<Void> deleteMedewerker(@PathVariable Long id) {
        log.debug("REST request to delete Medewerker : {}", id);
        medewerkerService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
