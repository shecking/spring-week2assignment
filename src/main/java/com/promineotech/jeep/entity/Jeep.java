package com.promineotech.jeep.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class Jeep {
	private Long modelPK;
	private JeepModel modelId;
	private String trimLevel;
	private int numDoors;
	private int wheelSize;
	private BigDecimal basePrice;
	
	@JsonIgnore
	public Long getModelPK() {
		return modelPK;
	}
	
}
