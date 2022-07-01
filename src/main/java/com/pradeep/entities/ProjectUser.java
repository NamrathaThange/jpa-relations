package com.pradeep.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.pradeep.enums.ProjectUserStatus;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class ProjectUser {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long projectUserId;
	private Long projectId;
	private Long companyId;
	private Long userId;
	private String role;
	private String authorities;
	private Long inviterUserId;
	private ProjectUserStatus projectUserStatus;
}
