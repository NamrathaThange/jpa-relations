package com.pradeep.service;

import java.util.List;

import com.pradeep.dtos.OperationResponseDto;
import com.pradeep.entities.Project;

public interface IProjectService {

	Project createProject(Project project);

	List<Project> getProjectsByCompanyId(Long companyId);

	Project getProjectById(Long id);

	Project updateProject(Long projectId, Project updatedProjectInfo);

	OperationResponseDto deleteProject(Long projectId);

}
