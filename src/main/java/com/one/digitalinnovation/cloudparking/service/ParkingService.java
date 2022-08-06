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

import com.one.digitalinnovation.cloudparking.exception.ParkingNotFoundException;
import com.one.digitalinnovation.cloudparking.model.Parking;

@Service
public class ParkingService {

	private static Map<String, Parking> parkingMap = new HashMap();

	static {
		var id1 = getUUID();
		var id2 = getUUID();
		Parking parking1 = new Parking(id1, "DMS-1111", "SC", "CELTA", "BRANCO");
		Parking parking2 = new Parking(id2, "AKA-7189", "PC", "CORSA", "AZUL");
		parkingMap.put(id1, parking1);
		parkingMap.put(id2, parking2);
	}

	private static String getUUID() {
		return UUID.randomUUID().toString().replace("-", "");
	}
	
	public List<Parking> findAll(){
		return parkingMap.values().stream().collect(Collectors.toList());
	}

	public Parking findById(String id) {
		Parking parking =  parkingMap.get(id);
		if(parking == null) {
			throw new ParkingNotFoundException(id);
		}
		return parking;
	}

	public Parking create(Parking parkingCreate) {
		String uuid = getUUID();
		parkingCreate.setId(getUUID());
		parkingCreate.setEntryDate(LocalDateTime.now());
		parkingMap.put(uuid, parkingCreate);
		return parkingCreate;
	}
}
