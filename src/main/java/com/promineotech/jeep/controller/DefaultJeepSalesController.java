package com.promineotech.jeep.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.promineotech.jeep.entity.Jeep;
import com.promineotech.jeep.entity.JeepModel;
import com.promineotech.jeep.service.JeepSalesService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j // importing logger API
public class DefaultJeepSalesController implements JeepSalesController {
	
	// injecting service interface to use it, also with annotation
	@Autowired
	private JeepSalesService jeepSalesService;

	public List<Jeep> fetchJeeps(JeepModel model, String trim) {
		
		// info level logging statement
		log.info("model={}, trim={}", model, trim);
		return jeepSalesService.fetchJeeps(model, trim);
	}

}
