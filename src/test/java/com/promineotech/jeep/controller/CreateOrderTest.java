package com.promineotech.jeep.controller;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import com.promineotech.jeep.entity.JeepModel;
import com.promineotech.jeep.entity.Order;

// 2a. spring boot test annotations
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@Sql(scripts = {
		"classpath:flyway/migrations/V1.0__Jeep_Schema.sql",
		"classpath:flyway/migrations/V1.1__Jeep_Data.sql"
		})

public class CreateOrderTest {
	
	@LocalServerPort
	private int serverPort;
	
	@Autowired
	private TestRestTemplate restTemplate;
	
	// test method
	@Test
	void testCreateOrderReturnsSuccess201() {

		String body = createOrderBody();
		String uri = String.format("http://localhost:%d/orders", serverPort);

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		
		// creating HttpEntity object, setting body and headers
		HttpEntity<String> bodyEntity = new HttpEntity<>(body, headers);
		
		// sending request body and headers to the server
		ResponseEntity<Order> response = restTemplate.exchange(uri,
			    HttpMethod.POST, bodyEntity, Order.class);

		// assertJ assertions
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
		assertThat(response.getBody()).isNotNull();
		
		Order order = response.getBody();
		assertThat(order.getCustomer().getCustomerId()).isEqualTo("ROTH_GARTH");
		assertThat(order.getModel().getModelId()).isEqualTo(JeepModel.RENEGADE);
		assertThat(order.getModel().getTrimLevel()).isEqualTo("Jeepster");
		assertThat(order.getModel().getNumDoors()).isEqualTo(4);
		assertThat(order.getColor().getColorId()).isEqualTo("EXT_SNAZZBERRY");
		assertThat(order.getEngine().getEngineId()).isEqualTo("2_4_GAS");
		assertThat(order.getTire().getTireId()).isEqualTo("LT285_GOODYEAR_OWL");
		assertThat(order.getOptions()).hasSize(6);

	}
	
	
	// method returning JSON object
	protected String createOrderBody() {
		return "{\n"
				+ "  \"customer\":\"ROTH_GARTH\",\n"
				+ "  \"model\":\"RENEGADE\",\n"
				+ "  \"trim\":\"Jeepster\",\n"
				+ "  \"doors\":4,\n"
				+ "  \"color\":\"EXT_SNAZZBERRY\",\n"
				+ "  \"engine\":\"2_4_GAS\",\n"
				+ "  \"tire\":\"LT285_GOODYEAR_OWL\",\n"
				+ "  \"options\":[\n"
				+ "    \"DOOR_SMITTY_FRONT\",\n"
				+ "    \"EXT_MOPAR_KEYLESS\",\n"
				+ "    \"INT_QUAD_LINER\",\n"
				+ "    \"STOR_BESTOP_TRUNK\",\n"
				+ "    \"TOP_MOPAR_POWER\",\n"
				+ "    \"WHEEL_MOPAR_ALUM\"\n"
				+ "  ]\n"
				+ "}";
	}
}
