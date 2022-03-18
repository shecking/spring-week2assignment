package com.promineotech.jeep.controller;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;

import com.promineotech.jeep.entity.Jeep;
import com.promineotech.jeep.entity.JeepModel;

// required annotations
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@Sql(scripts = {
	    "classpath:flyway/migrations/V1.0__Jeep_Schema.sql",
	    "classpath:flyway/migrations/V1.1__Jeep_Data.sql"}, 
	    config = @SqlConfig(encoding = "utf-8"))

class FetchJeepTest {
	
	// telling spring boot to inject template
	@Autowired
	private TestRestTemplate restTemplate;
	
	@LocalServerPort
	private int serverPort;
	
	// method to return list of predefined Jeep objects pulled from V1.1__Jeep_Data.sql
	// could place this in a support package (see video)
	protected List<Jeep> buildExpected() {
		List<Jeep> listOfJeeps = new LinkedList<>();
		
		listOfJeeps.add(Jeep.builder()
				.modelId(JeepModel.WRANGLER)
				.trimLevel("Freedom")
				.numDoors(2)
				.wheelSize(17)
				.basePrice(new BigDecimal("36110.00"))
				.build());
		listOfJeeps.add(Jeep.builder()
				.modelId(JeepModel.WRANGLER)
				.trimLevel("Freedom")
				.numDoors(4)
				.wheelSize(17)
				.basePrice(new BigDecimal("39915.00"))
				.build());
		return listOfJeeps;
	}
	
	// test method
	@Test
	void testThatJeepsAreReturnedWhenAValidModelAndTrimAreSupplied() {
		
		// GIVEN a valid model, trim, URI
		JeepModel model = JeepModel.WRANGLER;
		String trim = "Freedom";
		String uri = String.format("http://localhost:%d/jeeps?model=%s&trim=%s", serverPort, model, trim);
		
		// WHEN
		ResponseEntity<List<Jeep>> response = restTemplate.exchange(
				uri, HttpMethod.GET, null, new ParameterizedTypeReference<>() {}
			);
		
		// THEN
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		
		// ALSO THEN
		List<Jeep> expectedList = buildExpected();
		List<Jeep> actualList = response.getBody();
		assertThat(actualList).isEqualTo(expectedList);
	}
	
}
