package com.one.digitalinnovation.cloudparking.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.one.digitalinnovation.cloudparking.DTO.ParkingDTO;
import com.one.digitalinnovation.cloudparking.controller.mapper.ParkingMapper;
import com.one.digitalinnovation.cloudparking.model.Parking;
import com.one.digitalinnovation.cloudparking.service.ParkingService;

@RestController	
@RequestMapping("/parking")
public class ParkingController {

	private final ParkingService parkingService;
	private final ParkingMapper parkingMapper;
	
	public ParkingController(ParkingService parkingService, ParkingMapper parkingMapper) {
		this.parkingService = parkingService;
		this.parkingMapper = parkingMapper;
	}
	
	@GetMapping
	public List<ParkingDTO> findAll(){
		List<Parking> parkingList = parkingService.findAll();
		List<ParkingDTO> result = parkingMapper.toParkingDTOList(parkingList);
		return result;
		}
	}

