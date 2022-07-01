package com.pradeep.service;

import java.util.List;

import com.pradeep.entities.ProjectUser;

public interface IProjectTeamService {

	ProjectUser createProjectUser(ProjectUser projectUser);

	List<ProjectUser> getProjectTeamByProjectId(Long projectId);

}
