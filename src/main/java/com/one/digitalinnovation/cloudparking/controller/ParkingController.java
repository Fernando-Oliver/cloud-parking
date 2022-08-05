package com.one.digitalinnovation.cloudparking.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.one.digitalinnovation.cloudparking.model.Parking;

@RestController	
@RequestMapping("/parking")
public class ParkingController {

	@GetMapping
	public List<Parking> findAll(){
		
		var parking = new Parking();
		parking.setColor("Preto");
		parking.setLicense("bra-2345");
		parking.setModel("Jetta");
		parking.setState("SP");
		
		return Arrays.asList(parking);
	}
}
