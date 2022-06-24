package com.pradeep.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class ProjectAddress {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long projectAddressId;
	private Long countryId;
	private Long stateId;
	private Long cityId;
	private Long zipCode;
	private String street;
}
