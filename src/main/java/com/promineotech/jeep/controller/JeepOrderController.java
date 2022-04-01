package com.promineotech.jeep.controller;

import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.promineotech.jeep.entity.Order;
import com.promineotech.jeep.entity.OrderRequest;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.servers.Server;

@Validated // turns validation on at class level

@RequestMapping("/orders") // this tells spring to map the /orders URI to this JOS class

@OpenAPIDefinition(info = @Info(title = "Jeep Order Service"), servers = {
		@Server(url = "http://localhost:8080", description = "Local server.")})

public interface JeepOrderController {
	
	@Operation(
			summary = "Create an order for a Jeep",
			description = "Returns the created Jeep",
			responses = {
				@ApiResponse(
					responseCode = "201", 
					description = "Returned the created Jeep",
					content = @Content(
						mediaType = "application/json",
						schema = @Schema(implementation = Order.class))),
				@ApiResponse(
					responseCode = "400",
					description = "Invalid request",
					content = @Content(
						mediaType = "application/json")),
				@ApiResponse(
					responseCode = "404",
					description = "Component not found with given input",
					content = @Content(
						mediaType = "application/json")),
				@ApiResponse(
					responseCode = "500",
					description = "Unplanned error",
					content = @Content(
						mediaType = "application/json"))
			},
			
			parameters = {
				@Parameter(
					name = "orderRequest",
					required = true,
					description = "The order as JSON")
			}
		)
	
	// PostMapping, NOT GetMapping
	@PostMapping
	// if successful, return response status code
	@ResponseStatus(code = HttpStatus.CREATED)
	
	// method to create an order
	// return type Order
	// accepts one parameter (orderRequest) of type OrderRequest
	Order createOrder(@RequestBody OrderRequest orderRequest);
	
}
