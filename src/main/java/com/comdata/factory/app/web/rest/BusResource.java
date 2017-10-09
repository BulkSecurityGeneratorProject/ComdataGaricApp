package com.comdata.factory.app.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codahale.metrics.annotation.Timed;
import com.comdata.factory.app.domain.Bus;
import com.comdata.factory.app.service.BusService;
import com.comdata.factory.app.web.rest.dto.BusDTO;
import com.comdata.factory.app.web.rest.util.HeaderUtil;
import com.comdata.factory.app.web.rest.util.PaginationUtil;

import io.github.jhipster.web.util.ResponseUtil;
import io.swagger.annotations.ApiParam;

/**
 * REST controller for managing Bus.
 */
@RestController
@RequestMapping("/api")
public class BusResource {

    private final Logger log = LoggerFactory.getLogger(BusResource.class);

    private static final String ENTITY_NAME = "bus";

    private final BusService busService;

    public BusResource(BusService busService) {
        this.busService = busService;
    }

    /**
     * POST  /buses : Create a new bus.
     *
     * @param bus the bus to create
     * @return the ResponseEntity with status 201 (Created) and with body the new bus, or with status 400 (Bad Request) if the bus has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/buses")
    @Timed
    public ResponseEntity<Bus> createBus(@RequestBody BusDTO dto) throws URISyntaxException {
        log.debug("REST request to save Bus : {}", dto);
        if (dto.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new bus cannot already have an ID")).body(null);
        }
        Bus result = busService.save(dto.convertToBusEntity());
        
        return ResponseEntity.created(new URI("/api/buses/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /buses : Updates an existing bus.
     *
     * @param bus the bus to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated bus,
     * or with status 400 (Bad Request) if the bus is not valid,
     * or with status 500 (Internal Server Error) if the bus couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/buses")
    @Timed
    public ResponseEntity<BusDTO> updateBus(@RequestBody BusDTO dto) throws URISyntaxException {
        log.debug("REST request to update Bus : {}", dto);
        if (dto.getId() == null) {
        	return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "You can only update an existing bus.")).body(null);
        }
        Bus result = busService.save(dto.convertToBusEntity());
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, dto.getId().toString()))
            .body(new BusDTO(result));
    }

    /**
     * GET  /buses : get all the buses.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of buses in body
     */
    @GetMapping("/buses")
    @Timed
    public ResponseEntity<List<BusDTO>> getAllBuses(@ApiParam Pageable pageable) {
        log.debug("REST request to get a page of Buses");
        Page<Bus> page = busService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/buses");
        List<Bus> allBuses =  page.getContent();
        
        
        List<BusDTO> allDTOs = new ArrayList<>();
        for(Bus bus: allBuses) {
        	allDTOs.add(new BusDTO(bus));
        }
        return new ResponseEntity<>(allDTOs, headers, HttpStatus.OK);
    }

    /**
     * GET  /buses/:id : get the "id" bus.
     *
     * @param id the id of the bus to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the bus, or with status 404 (Not Found)
     */
    @GetMapping("/buses/{id}")
    @Timed
    public ResponseEntity<BusDTO> getBus(@PathVariable Long id) {
        log.debug("REST request to get Bus : {}", id);
        Bus bus = busService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(new BusDTO(bus)));
    }

    /**
     * DELETE  /buses/:id : delete the "id" bus.
     *
     * @param id the id of the bus to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/buses/{id}")
    @Timed
    public ResponseEntity<Void> deleteBus(@PathVariable Long id) {
        log.debug("REST request to delete Bus : {}", id);
        busService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
