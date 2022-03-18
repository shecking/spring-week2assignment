package com.promineotech.jeep.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.promineotech.jeep.entity.Jeep;
import com.promineotech.jeep.entity.JeepModel;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class DefaultJeepSalesService implements JeepSalesService {

	public List<Jeep> fetchJeeps(JeepModel model, String trim) {
		
		//info-level log statement
		log.info("(ASSIGNMENT) The fetchJeeps method was called with model={} and trim={}", model, trim);
		return null;
	}

}
