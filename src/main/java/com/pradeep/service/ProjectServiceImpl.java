package com.pradeep.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pradeep.dtos.OperationResponseDto;
import com.pradeep.entities.Project;
import com.pradeep.repository.IProjectRepository;

@Service
public class ProjectServiceImpl implements IProjectService {

	@Autowired
	private IProjectRepository projectRepository;
	
	@Override
	public Project createProject(Project project) {
		return projectRepository.save(project);
	}

	@Override
	public List<Project> getProjectsByCompanyId(Long companyId) {
		return null;
	}

	@Override
	public Project getProjectById(Long id) {
		return null;
	}

	@Override
	public Project updateProject(Long projectId, Project updatedProjectInfo) {
		return null;
	}

	@Override
	public OperationResponseDto deleteProject(Long projectId) {
		return null;
	}

}
