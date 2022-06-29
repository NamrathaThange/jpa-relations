package com.pradeep.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pradeep.dtos.OperationResponseDto;
import com.pradeep.entities.Project;
import com.pradeep.exception.ResourceExistsException;
import com.pradeep.exception.ResourceNotFoundException;
import com.pradeep.repository.IProjectRepository;

@Service
public class ProjectServiceImpl implements IProjectService {

	@Autowired
	private IProjectRepository projectRepository;
	
	@Override
	public Project createProject(Project project) throws ResourceExistsException {
		findByJobNumber(project.getCompanyId(),project.getJobNumber());
		Project savedProject=projectRepository.save(project);
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
		verifyByJobNumberAndProjectId(updatedProjectInfo.getCompanyId(),projectId,updatedProjectInfo.getJobNumber());
		String[] ignoreFields = { "projectAddressId"};
		BeanUtils.copyProperties(updatedProjectInfo.getProjectAddress(), existingProject.getProjectAddress(),ignoreFields);
		final Project dbUpdatedProject=projectRepository.save(existingProject);
		return dbUpdatedProject;
	}

	@Override
	public OperationResponseDto deleteProject(Long projectId) throws ResourceNotFoundException {
		Project project = projectRepository.findById(projectId).orElseThrow(() -> new ResourceNotFoundException("Project not found :: " + projectId));
		projectRepository.delete(project);
		return new OperationResponseDto(true,"Deleted successfully");
	}
	
	private void findByJobNumber(Long companyId,String jobNumber) throws ResourceExistsException {
		Optional<Project> optionalProject=projectRepository.findByCompanyIdAndJobNumber(jobNumber);
		if(optionalProject.isPresent()) {
			throw new ResourceExistsException("Project exists");
		}
	}

	private void verifyByJobNumberAndProjectId(Long companyId,Long projectId, String jobNumber) throws ResourceExistsException, ResourceNotFoundException {
		Optional<Project> optionalProject= projectRepository.findByCompanyIdAndProjectIdAndJobNumber(companyId,projectId,jobNumber);
		if(optionalProject.isPresent()) {
			throw new ResourceExistsException("Project exists");
		}
	}

}
