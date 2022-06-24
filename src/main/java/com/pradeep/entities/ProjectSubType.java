package com.pradeep.entities;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class ProjectSubType {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long projectSubTypeId;
	private String projectSubTypeName;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "project_type_id")
	@JsonBackReference
	private ProjectType projectType;
}
