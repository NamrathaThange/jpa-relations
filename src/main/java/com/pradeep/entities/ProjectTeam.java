package com.pradeep.entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.pradeep.enums.ProjectStatus;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class ProjectTeam {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long projectTeamId;
	private Long projectId;
	private Long companyId;
	private Long userId;
	private String role;
	private String authorities;
	
}
