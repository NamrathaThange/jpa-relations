package com.pradeep.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pradeep.dtos.OperationResponseDto;
import com.pradeep.entities.Project;
import com.pradeep.entities.ProjectUser;
import com.pradeep.enums.ProjectUserStatus;
import com.pradeep.exception.ResourceExistsException;
import com.pradeep.exception.ResourceNotFoundException;
import com.pradeep.repository.IProjectRepository;
import com.pradeep.repository.IProjectTeamRepository;

@Service
public class ProjectServiceImpl implements IProjectService {

	@Autowired
	private IProjectRepository projectRepository;
	
	@Autowired
	private IProjectTeamRepository projectTeamRepository;
	
	@Override
	public Project createProject(Project project) throws ResourceExistsException {
		findByCompanyIdAndJobNumber(project.getCompanyId(),project.getJobNumber());
		Project savedProject=projectRepository.save(project);
		createProjectUser(savedProject);
		return savedProject;
	}
	
	@Override
	public List<Project> getProjectsByCompanyId(Long companyId) {
		List<Project> projectList=projectRepository.findByCompanyId(companyId);
		return projectList;
	}

	@Override
	public Project getProjectById(Long id) throws ResourceNotFoundException {
		Project project = projectRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Project not found :: " + id));
		return project;
	}

	@Override
	public Project updateProject(Long projectId, Project updatedProjectInfo) throws ResourceExistsException, ResourceNotFoundException {
		Project existingProject = projectRepository.findById(projectId).orElseThrow(() -> new ResourceNotFoundException("Project not found :: " + projectId));
		findByCompanyIdAndProjectIdAndJobNumber(updatedProjectInfo.getCompanyId(),projectId,updatedProjectInfo.getJobNumber());
		String[] ignoreProjectFields = { "projectId"};
		BeanUtils.copyProperties(updatedProjectInfo, existingProject,ignoreProjectFields);
		final Project dbUpdatedProject=projectRepository.save(existingProject);
		return dbUpdatedProject;
	}

	@Override
	public OperationResponseDto deleteProject(Long projectId) throws ResourceNotFoundException {
		Project project = projectRepository.findById(projectId).orElseThrow(() -> new ResourceNotFoundException("Project not found :: " + projectId));
		projectRepository.delete(project);
		return new OperationResponseDto(true,"Deleted successfully");
	}
	
	private void findByCompanyIdAndJobNumber(Long companyId,String jobNumber) throws ResourceExistsException {
		Optional<Project> optionalProject=projectRepository.findByCompanyIdAndJobNumber(companyId,jobNumber);
		if(optionalProject.isPresent()) {
			throw new ResourceExistsException("Project exists");
		}
	}

	private void findByCompanyIdAndProjectIdAndJobNumber(Long companyId,Long projectId, String jobNumber) throws ResourceExistsException, ResourceNotFoundException {
		Optional<Project> optionalProject= projectRepository.findByCompanyIdAndProjectIdAndJobNumber(companyId,projectId,jobNumber);
		if(optionalProject.isPresent()) {
			throw new ResourceExistsException("Project exists");
		}
	}
	
	private void createProjectUser(Project savedProject) {
		ProjectUser projectUser=new ProjectUser();
		projectUser.setProjectId(savedProject.getProjectId());
		projectUser.setCompanyId(savedProject.getCompanyId());
		projectUser.setUserId(savedProject.getCreatedBy());
		projectUser.setRole("ProjectAdmin");
		projectUser.setAuthorities("create:read:update:delete");
		projectUser.setProjectUserStatus(ProjectUserStatus.CREATED);
		projectTeamRepository.save(projectUser);
	}

}
