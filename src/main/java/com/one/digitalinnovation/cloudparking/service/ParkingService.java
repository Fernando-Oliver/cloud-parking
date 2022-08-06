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

	private static String getUUID() {
		return UUID.randomUUID().toString().replace("-", "");
	}

	public List<Parking> findAll() {
		return parkingMap.values().stream().collect(Collectors.toList());
	}

	public Parking findById(String id) {
		Parking parking = parkingMap.get(id);
		if (parking == null) {
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

	public Parking update(String id, Parking parkingCreate) {
		 Parking parking = findById(id);
		 parking.setColor(parkingCreate.getColor());
		 parkingMap.replace(id, parking);
		 return parking;
	}
	
	public void delete(String id) {
		Parking parking = findById(id);
		parkingMap.remove(id);
	}

	public Parking exit(String id) {
		return null;
	}


}
