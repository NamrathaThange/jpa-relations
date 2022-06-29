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
public class Project {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long projectId;
	private String projectName;
	private String jobNumber;
	private Date startDate;
	private Date endDate;
	private Long companyId;
	private ProjectStatus projectStatus;
	private String projectImage;
	
	@OneToOne
	@JoinColumn(name="projectTypeId")
    private ProjectType projectType;   
	
	@OneToOne
	@JoinColumn(name="projectSubTypeId")
    private ProjectSubType projectSubType;
	
	@OneToOne(cascade = CascadeType.ALL,orphanRemoval = true)
   	private ProjectAddress projectAddress;      
	
	@ElementCollection
	@CollectionTable(name = "project_stake_holders", joinColumns = @JoinColumn(name = "projectId"))
	private List<Long> projectStakeHolders=new ArrayList<Long>();
	
}
