package com.one.digitalinnovation.cloudparking;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import com.one.digitalinnovation.cloudparking.DTO.ParkingCreateDTO;

import io.restassured.RestAssured;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ParkingControllerIT extends AbstractContainerBase {

	@LocalServerPort
	private int randomPort;

	@BeforeEach
	public void setUpTest() {
		RestAssured.port = randomPort;
	}

	@Test
	void whenFindAllcheckResult() {
		RestAssured.given()
		.when()
		.get("/parking")
		.then()
		.statusCode(HttpStatus.OK.value());
	}

	@Test
	void whenCreateThenCheckIsCreated() {
		var created = new ParkingCreateDTO();
		created.setColor("ROSA");
		created.setLicense("VAR-1234");
		created.setModel("FUSCA");
		created.setState("SP");
		
		RestAssured.given()
		.when()
		.contentType(MediaType.APPLICATION_JSON_VALUE)
		.body(created)
		.post("/parking")
		.then()
		.statusCode(HttpStatus.CREATED.value())
		.body("license", Matchers.equalTo("VAR-1234"));
	}

}
