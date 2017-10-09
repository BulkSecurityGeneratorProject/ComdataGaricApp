package com.comdata.factory.app.service.impl;

import com.comdata.factory.app.service.VehicleService;
import com.comdata.factory.app.domain.Vehicle;
import com.comdata.factory.app.repository.VehicleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Service Implementation for managing Vehicle.
 */
@Service
@Transactional
public class ParkingServiceImpl implements VehicleService{

    private final Logger log = LoggerFactory.getLogger(ParkingServiceImpl.class);

    private final VehicleRepository vehicleRepository;

    public ParkingServiceImpl(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    /**
     * Save a vehicle.
     *
     * @param vehicle the entity to save
     * @return the persisted entity
     */
    @Override
    public Vehicle save(Vehicle vehicle) {
        log.debug("Request to save Vehicle : {}", vehicle);
        return vehicleRepository.save(vehicle);
    }

    /**
     *  Get all the vehicles.
     *
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<Vehicle> findAll(Pageable pageable) {
        log.debug("Request to get all Vehicles");
        return vehicleRepository.findAll(pageable);
    }

    /**
     *  Get one vehicle by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Vehicle findOne(Long id) {
        log.debug("Request to get Vehicle : {}", id);
        return vehicleRepository.findOne(id);
    }

    /**
     *  Delete the  vehicle by id.
     *
     *  @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Vehicle : {}", id);
        vehicleRepository.delete(id);
    }
}
