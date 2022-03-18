package com.promineotech.jeep.controller;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;

import com.promineotech.jeep.entity.Jeep;
import com.promineotech.jeep.entity.JeepModel;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.servers.Server;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.info.Info;

// telling spring to map the /jeeps URI to the controller
@RequestMapping("/jeeps")
// open api documentation describing the controller
@OpenAPIDefinition(info = @Info(title = "Service for Jeep Sales"), servers = {
		@Server(url = "http://localhost:8080", description = "Local server.")})
public interface JeepSalesController {
	
	@Operation(
			summary = "Returns a list of Jeeps",
			description = "Returns Jeep list (model and trim optional)",
			responses = {
				@ApiResponse(
					responseCode = "200", 
					description = "Returned Jeep list",
					content = @Content(
						mediaType = "application/json",
						schema = @Schema(implementation = Jeep.class))),
				@ApiResponse(
					responseCode = "400",
					description = "Invalid parameters",
					content = @Content(
						mediaType = "application/json")),
				@ApiResponse(
					responseCode = "404",
					description = "No Jeeps found",
					content = @Content(
						mediaType = "application/json")),
				@ApiResponse(
					responseCode = "500",
					description = "A server error occurred",
					content = @Content(
						mediaType = "application/json"))
			},
			// describing model and trim
			parameters = {
				@Parameter(name = "model",
						   allowEmptyValue = false,
						   required = false,
						   description = "The model name (i.e. WRANGLER)"),
				@Parameter(name = "trim",
						   allowEmptyValue = false,
						   required = false,
						   description = "The trim level (i.e. Sport)")
			}
		)
	// telling spring to map a get request on /jeeps
	@GetMapping
	// if successful, return status code 200 OK
	@ResponseStatus(code = HttpStatus.OK)
	List<Jeep> fetchJeeps(
		@RequestParam JeepModel model,
		@RequestParam String trim);
}
