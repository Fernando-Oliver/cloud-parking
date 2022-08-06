package com.one.digitalinnovation.cloudparking.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.one.digitalinnovation.cloudparking.exception.ParkingNotFoundException;
import com.one.digitalinnovation.cloudparking.model.Parking;
import com.one.digitalinnovation.cloudparking.repository.ParkingRepository;

@Service
public class ParkingService {

	private final ParkingRepository repository;

	public ParkingService(ParkingRepository repository) {
		this.repository = repository;
	}

	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public List<Parking> findAll() {
		return repository.findAll();
	}

	private static String getUUID() {
		return UUID.randomUUID().toString().replace("-", "");
	}

	@Transactional(readOnly = true)
	public Parking findById(String id) {
		return repository.findById(id).orElseThrow(() -> 
		new ParkingNotFoundException(id));

	}

	@Transactional
	public Parking create(Parking parkingCreate) {
		String uuid = getUUID();
		parkingCreate.setId(getUUID());
		parkingCreate.setEntryDate(LocalDateTime.now());
		repository.save(parkingCreate);
		return parkingCreate;
	}

	@Transactional
	public Parking update(String id, Parking parkingCreate) {
		Parking parking = findById(id);
		parking.setColor(parkingCreate.getColor());
		parking.setState(parkingCreate.getState());
		parking.setModel(parkingCreate.getModel());
		parking.setLicense(parkingCreate.getLicense());
		repository.save(parking);
		return parking;
	}

	@Transactional
	public void delete(String id) {
		Parking parking = findById(id);
		repository.deleteById(id);
	}

	@Transactional
	public Parking checkOut(String id) {
		Parking parking = findById(id);
		parking.setExitDate(LocalDateTime.now());
		parking.setBill(ParkingCheckout.getBill(parking));
		repository.save(parking);
		return parking;
	}

}
