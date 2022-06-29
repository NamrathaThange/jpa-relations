package com.pradeep.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

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

	@OneToMany(mappedBy = "projectType", cascade = {CascadeType.ALL})
	@JsonManagedReference
	private List<ProjectSubType > projectSubTypes;
}
