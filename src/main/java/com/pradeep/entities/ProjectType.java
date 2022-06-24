package com.pradeep.entities;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class ProjectType {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long projectTypeId;
	private String projectTypeName;

	@OneToOne(cascade = CascadeType.ALL, mappedBy = "projectType")
	@JsonManagedReference
	private ProjectSubType projectSubType;
}
